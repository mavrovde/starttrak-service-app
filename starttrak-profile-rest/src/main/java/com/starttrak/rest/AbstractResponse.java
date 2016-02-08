package com.starttrak.rest;

/**
 * @author serg.mavrov@gmail.com
 */
public abstract class AbstractResponse<TYPE> implements StandardResponse<TYPE> {

    private String code;
    private String message;
    private TYPE content;

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public TYPE getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "StandardResponse{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", content=" + content +
                '}';
    }

}
