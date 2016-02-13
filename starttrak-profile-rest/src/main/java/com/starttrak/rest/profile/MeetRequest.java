package com.starttrak.rest.profile;

import javax.xml.bind.annotation.XmlElement;
import java.util.List;

/**
 * @author serg.mavrov@gmail.com
 */
public class MeetRequest {

    @XmlElement(name = "user_ids")
    private List<Long> ids;

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

    @Override
    public String toString() {
        return "MeetRequest{" +
                "ids=" + ids +
                '}';
    }

}
