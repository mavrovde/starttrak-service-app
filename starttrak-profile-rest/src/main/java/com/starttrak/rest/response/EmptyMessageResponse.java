package com.starttrak.rest.response;

/**
 * @author serg.mavrov@gmail.com
 */
public class EmptyMessageResponse extends CodeErrorResponse {

    public EmptyMessageResponse() {
        super(3001, "the message is empty", null);
    }

}
