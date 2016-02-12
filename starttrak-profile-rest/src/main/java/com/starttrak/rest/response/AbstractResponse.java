package com.starttrak.rest.response;

/**
 * @author serg.mavrov@gmail.com
 */
public abstract class AbstractResponse<TYPE> implements StandardResponse<TYPE> {

    private int code;
    private String message;
    private TYPE content;

    public AbstractResponse(int code, String message, TYPE content) {
        this.code = code;
        this.message = message;
        this.content = content;
    }

    public int getCode() {
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
