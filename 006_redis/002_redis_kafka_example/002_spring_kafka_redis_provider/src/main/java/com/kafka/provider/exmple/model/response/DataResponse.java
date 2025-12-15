package com.kafka.provider.exmple.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;

public class DataResponse<T> {
	
    private String message;
    
    private Boolean status;
    
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;
    
	public DataResponse() {
	}

	public DataResponse(String message, Boolean status, T data) {
		this.message = message;
		this.status = status;
		this.data = data;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @return the status
	 */
	public Boolean getStatus() {
		return status;
	}

	/**
	 * @return the data
	 */
	public T getData() {
		return data;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Boolean status) {
		this.status = status;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(T data) {
		this.data = data;
	}

}
