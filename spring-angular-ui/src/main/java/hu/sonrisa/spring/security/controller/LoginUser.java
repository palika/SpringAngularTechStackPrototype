package hu.sonrisa.spring.security.controller;

import java.io.Serializable;

public class LoginUser implements Serializable {
	private String username;
	private String password;

	public LoginUser() {
	}

	public LoginUser(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
