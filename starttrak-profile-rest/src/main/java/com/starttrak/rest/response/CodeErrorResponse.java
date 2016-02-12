package com.starttrak.rest.response;

/**
 * @author serg.mavrov@gmail.com
 */
public class CodeErrorResponse extends AbstractResponse<Object>
        implements StandardResponse<Object> {

    public CodeErrorResponse(int code, String message) {
        super(code, message, null);
    }

    public CodeErrorResponse(int code, String message, Object content) {
        super(code, message, content);
    }

}
