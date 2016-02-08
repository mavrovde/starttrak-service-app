package com.starttrak;

/**
 * @author serg.mavrov@gmail.com
 */
public class Ping {

	String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "Ping [message=" + message + "]";
	}

}
