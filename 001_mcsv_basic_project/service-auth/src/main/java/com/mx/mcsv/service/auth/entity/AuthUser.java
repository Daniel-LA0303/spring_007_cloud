package com.mx.mcsv.service.auth.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class AuthUser {
	// Clase Builder como método estático
	public static class AuthUserBuilder {
		private int id;
		private String userName;
		private String password;
		private String role;

		// Constructor vacío
		public AuthUserBuilder() {
		}

		// Método para construir la instancia de AuthUser
		public AuthUser build() {
			return new AuthUser(id, userName, password, role);
		}

		// Métodos para establecer los valores de los atributos
		public AuthUserBuilder id(int id) {
			this.id = id;
			return this;
		}

		public AuthUserBuilder password(String password) {
			this.password = password;
			return this;
		}

		public AuthUserBuilder role(String role) {
			this.role = role;
			return this;
		}

		public AuthUserBuilder userName(String userName) {
			this.userName = userName;
			return this;
		}
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String userName;
	private String password;
	private String role;

	public AuthUser() {
		// Inicialización opcional
	}

	public AuthUser(int id, String userName, String password, String role) {
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.role = role;
	}

	public static AuthUserBuilder builder() {
		return new AuthUserBuilder();
	}

	public int getId() {
		return id;
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

	public void setId(int id) {
		this.id = id;
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
