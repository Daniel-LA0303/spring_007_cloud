package com.mx.mcsv.service.auth.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class NewUserDto {

	private String userName;

	private String password;

	private String role;

	public NewUserDto() {
	}

	public NewUserDto(String userName, String password, String role) {
		this.userName = userName;
		this.password = password;
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public String getRole() {
		return role;
	}

	public String getUserName() {
		return userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}