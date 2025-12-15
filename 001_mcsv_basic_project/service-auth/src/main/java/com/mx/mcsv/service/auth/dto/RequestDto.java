package com.mx.mcsv.service.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class RequestDto {
	private String uri;

	private String method;

	public RequestDto() {
	}

	public RequestDto(String uri, String method) {
		this.uri = uri;
		this.method = method;
	}

	public String getMethod() {
		return method;
	}

	public String getUri() {
		return uri;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

}
