package com.starttrak.social;

/**
 * @author serg.mavrov@gmail.com
 */
public class SocialNetworkException extends Exception {

	private static final long serialVersionUID = 1L;

	public SocialNetworkException(String message, Throwable cause) {
        super(message, cause);
    }

    public SocialNetworkException(String message) {
        super(message);
    }

}
