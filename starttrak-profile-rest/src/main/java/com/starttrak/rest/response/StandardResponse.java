package com.starttrak.rest.response;

/**
 * @author serg.mavrov@gmail.com
 */
public interface StandardResponse<TYPE> {

    int getCode();

    String getMessage();

    TYPE getContent();

}
