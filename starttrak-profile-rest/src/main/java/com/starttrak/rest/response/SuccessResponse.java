package com.starttrak.rest.response;

/**
 * @author serg.mavrov@gmail.com
 */
public class SuccessResponse<TYPE>
        extends AbstractResponse<TYPE> implements StandardResponse<TYPE> {

    private final static String SUCCESS= "success";

    public SuccessResponse(TYPE content) {
        super(0, SUCCESS, content);
    }

    @Override
    public String toString() {
        return "SuccessResponse{" +
                "code='" + getCode() + '\'' +
                ", message='" + getMessage() + '\'' +
                ", content=" + getContent() +
                '}';
    }

}
