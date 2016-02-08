package com.starttrak.rest;

/**
 * @author serg.mavrov@gmail.com
 */
public interface StandardResponse<TYPE> {

    public String getCode();

    public String getMessage();

    public TYPE getContent();

}
