package com.starttrak.rest.response;

/**
 * @author serg.mavrov@gmail.com
 */
public class InternalErrorResponse extends CodeErrorResponse {

    public InternalErrorResponse( Object content) {
        super(1, "internal error", content);
    }

}
