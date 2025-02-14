package com.starttrak.rest.profile;

import javax.xml.bind.annotation.XmlElement;
import java.util.List;

/**
 * @author serg.mavrov@gmail.com
 */
public class MeetRequest {

    @XmlElement(name = "user_ids")
    private List<Long> ids;

    private boolean send;

    private String message;

    public MeetRequest() {
    }

    public MeetRequest(List<Long> ids) {
        this.ids = ids;
    }

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }

    public boolean isSend() {
        return send;
    }

    public void setSend(boolean send) {
        this.send = send;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
