package com.kafka.provider.exmple.model.request;

public class NewRequest {
	
    private String message;

	public NewRequest() {
	}

	public NewRequest(String message) {
		this.message = message;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}  

}
