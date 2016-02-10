package com.starttrak.rest.response;

import com.starttrak.rest.response.AbstractResponse;
import com.starttrak.rest.response.StandardResponse;

/**
 * @author serg.mavrov@gmail.com
 */
public class AuthErrorResponse extends AbstractResponse<String>
        implements StandardResponse<String> {


    public AuthErrorResponse() {
    }

    @Override
    public String getCode() {
        return "1001";
    }

    @Override
    public String getMessage() {
        return "incorrect own session id";
    }

    @Override
    public String getContent() {
        return "empty";
    }

}
