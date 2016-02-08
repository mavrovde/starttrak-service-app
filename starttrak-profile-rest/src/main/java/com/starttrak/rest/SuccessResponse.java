package com.starttrak.rest;

/**
 * @author serg.mavrov@gmail.com
 */
public class SuccessResponse<TYPE> implements StandardResponse<TYPE> {

    private final static String SUCCESS= "success";

    private String code;
    private String message;
    private TYPE content;

    public SuccessResponse() {
        this.code = "0";
        this.message = SUCCESS;
    }

    public SuccessResponse(TYPE content) {
        this();
        this.content = content;
    }

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
        return "SuccessResponse{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", content=" + content +
                '}';
    }

}
