package com.starttrak.rest.response;

/**
 * @author serg.mavrov@gmail.com
 */
public class AuthErrorResponse extends AbstractResponse<Object>
        implements StandardResponse<Object> {

    public AuthErrorResponse() {
        super(1001, "incorrect own session id", null);
    }

}
