package com.starttrak.common;

/**
 * @author serg.mavrov@gmail.com
 */
public enum SocNetwork {

    STTR(0L, "LINKEDIN"),
    FCBK(1L, "FACEBOOK"),
    LNKD(2L, "LINKEDIN"),
    XING(3L, "XING");

    private Long code;
    private String label;

    SocNetwork(Long code, String label) {
        this.code = code;
        this.label = label;
    }

    public Long getCode() {
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
