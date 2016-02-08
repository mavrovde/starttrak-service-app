package com.starttrak.repo;

import java.util.Optional;

/**
 * @author serg.mavrov@gmail.com
 */
public final class Page {

    public static Page DEFAULT = createNew(0, 500);
    public static Optional<Page> OPTIONAL_DEFAULT = Optional.of(createNew(0, 500));

    private int offset;
    private int limit;

    public static Page createNew(int offset, int limit) {
        return new Page(offset, limit);
    }

    private Page(int offset, int limit) {
        this.offset = offset;
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public int getLimit() {
        return limit;
    }
}
