package com.starttrak.repo;

import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.jboss.logging.Logger;

import com.starttrak.jpa.AbstractEntity;

/**
 * @author serg.mavrov@gmail.com
 */
public abstract class AbstractRepository<ENTITY extends AbstractEntity>
        implements StandardRepository<ENTITY> {

    private final Logger logger = Logger.getLogger(getClass().getName());

    //    private static final String USED_CACHE = "org.hibernate.cacheable";
    private CriteriaBuilder builder;
    private CriteriaQuery<ENTITY> selectCriteria;
    private CriteriaUpdate<ENTITY> updateCriteria;
    private CriteriaDelete<ENTITY> deleteCriteria;
    private Root<ENTITY> from;
    private TypedQuery<ENTITY> typedQuery;
    //private Query query;

    @Inject
    private EntityManager entityManager;

    public EntityManager getEm() {
        return entityManager;
    }

    @PostConstruct
    public void initQueryTools() {
        builder = getEm().getCriteriaBuilder();
    }

    public abstract Class<ENTITY> getEntityClass();

    protected Optional<ENTITY> findBy(Predicate... predicates) {
        selectCriteria.where(predicates);
        typedQuery = getEm().createQuery(selectCriteria);
//        typedQuery.setHint(USED_CACHE, false);
        try {
            return Optional.of(typedQuery.getSingleResult());
        } catch (NoResultException nre) {
            return Optional.empty();
        }
    }

    protected List<ENTITY> findAllBy(Optional<Page> page, Predicate... predicates) {
        if (predicates != null && predicates.length > 0) {
            selectCriteria.where(predicates);
        }
        typedQuery = getEm().createQuery(selectCriteria);
//        typedQuery.setHint(CACHE_PARAM, cacheConfig.getEnabled());
        typedQuery.setFirstResult(page.orElse(Page.DEFAULT).getOffset());
        typedQuery.setMaxResults(page.orElse(Page.DEFAULT).getLimit());
        return typedQuery.getResultList();
    }

    public List<ENTITY> findAllBy(Optional<Page> page) {
        getFrom(Operation.select); //todo:: refactor, init normal
        typedQuery = getEm().createQuery(selectCriteria);
//        typedQuery.setHint(CACHE_PARAM, cacheConfig.getEnabled());
        typedQuery.setFirstResult(page.orElse(Page.DEFAULT).getOffset());
        typedQuery.setMaxResults(page.orElse(Page.DEFAULT).getLimit());
        return typedQuery.getResultList();
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public ENTITY create(ENTITY t) {
        getEm().persist(t);
        logger.debug("The " + t + " created.");
        return t;
    }

    @Override
    public Optional<ENTITY> find(Long id) {
        return Optional.ofNullable(getEm().find(getEntityClass(), id));
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public ENTITY update(ENTITY t) {
        return getEm().merge(t);
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public void delete(ENTITY t) {
        ENTITY tUp = update(t);
        getEm().remove(tUp);
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public void delete(Long id) {
        Optional<ENTITY> t = find(id);
        delete(t.orElseThrow(IllegalArgumentException::new));
    }

    public CriteriaBuilder getBuilder() {
        return builder;
    }

    public CriteriaQuery<ENTITY> getCriteria() {
        return selectCriteria;
    }

    /**
     * depends on operation type method initialize CriteriaQuery, CriteriaUpdate or CriteriaDelete
     */
    public Root<ENTITY> getFrom(Operation operation) {
        switch (operation) {
            case select: {
                if (selectCriteria == null) {
                    selectCriteria = builder.createQuery(getEntityClass());
                    from = selectCriteria.from(getEntityClass());
                }
                return from;
            }
            case delete: {
                if (deleteCriteria == null) {
                    deleteCriteria = builder.createCriteriaDelete(getEntityClass());
                    from = deleteCriteria.from(getEntityClass());
                }
                return from;
            }
            case update: {
                if (updateCriteria == null) {
                    updateCriteria = builder.createCriteriaUpdate(getEntityClass());
                    from = updateCriteria.from(getEntityClass());
                }
                return from;
            }
            default: {
                selectCriteria = builder.createQuery(getEntityClass());
                from = selectCriteria.from(getEntityClass());
                return from;
            }
        }
    }

    public TypedQuery<ENTITY> getTypedQuery() {
        return typedQuery;
    }

    /**
     * possible operations on CriteriaQuery {@link javax.persistence.criteria.CriteriaQuery}
     */
    public enum Operation {
        select,
        delete,
        update
    }


}
