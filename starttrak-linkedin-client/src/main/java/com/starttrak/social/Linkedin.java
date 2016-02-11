package com.starttrak.social;

import javax.inject.Qualifier;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author sergii.mavrov@traveltainment.de
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER,
        ElementType.TYPE, ElementType.METHOD})
@Qualifier
public @interface Linkedin {

}