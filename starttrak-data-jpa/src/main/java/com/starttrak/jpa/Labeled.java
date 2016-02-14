package com.starttrak.jpa;

/**
 * @author serg.mavrov@gmail.com
 */
public interface Labeled extends Comparable<Labeled>{

    String getLabel();

    default int compareTo(Labeled o){
        return getLabel().compareTo(o.getLabel());
    }

}
