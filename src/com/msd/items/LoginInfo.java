package com.msd.items;

import com.sun.org.apache.xml.internal.security.utils.Base64;

public final class LoginInfo {

	private String username;
	private String password;
	private boolean company;

	public LoginInfo() {/**/}
	
	public LoginInfo(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public LoginInfo(String username, String password, boolean company) {
		this.username = username;
		this.password = password;
		this.company = company;
	}

	public String getencodedPassword() {
		// Creates a base 64 encoded password
		return Base64.encode(password.getBytes());
	}

	public String decodePassword(String password) {
		// Decodes the encrypted password
		try {
			return new String(Base64.decode(password), "UTF-8");
		} catch (Exception e) {
			return null;
		}
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

	public boolean isCompany() {
		return company;
	}

	public void setCompany(boolean company) {
		this.company = company;
	}
}
