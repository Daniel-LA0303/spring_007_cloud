package com.kafka.provider.exmple.model.response;

import java.util.List;

import com.kafka.provider.exmple.model.response.enums.ErrorType;

public class ErrorResponse {

	private String code;

	private ErrorType type;

	private String message;

	private List<String> details;

	private String timestamp;

	public ErrorResponse() {
	}

	public ErrorResponse(String code, ErrorType type, String message, List<String> details, String timestamp) {
		this.code = code;
		this.type = type;
		this.message = message;
		this.details = details;
		this.timestamp = timestamp;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @return the type
	 */
	public ErrorType getType() {
		return type;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @return the details
	 */
	public List<String> getDetails() {
		return details;
	}

	/**
	 * @return the timestamp
	 */
	public String getTimestamp() {
		return timestamp;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(ErrorType type) {
		this.type = type;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @param details the details to set
	 */
	public void setDetails(List<String> details) {
		this.details = details;
	}

	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

}
