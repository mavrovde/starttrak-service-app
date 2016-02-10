package com.starttrak.rest.response;

/**
 * @author serg.mavrov@gmail.com
 */
public interface StandardResponse<TYPE> {

    public String getCode();

    public String getMessage();

    public TYPE getContent();

}
