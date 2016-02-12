package com.starttrak.common;

import java.util.Scanner;

/**
 * @author serg.mavrov@gmail.com
 */
public enum SocNetwork {

    STTR(0, "LINKEDIN"),
    FCBK(1, "FACEBOOK"),
    LNKD(2, "LINKEDIN"),
    XING(3, "XING");

    private int code;
    private String label;

    SocNetwork(int code, String label) {
        this.code = code;
        this.label = label;
    }

    public int getCode() {
        return code;
    }

    public String getLabel() {
        return label;
    }

    public static SocNetwork findByCode(int code) {
        for (SocNetwork net : values()) {
            if (code == net.getCode()) {
                return net;
            }
        }
        throw new IllegalArgumentException("unknown network id");
    }
}
