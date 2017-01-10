package com.msd.items;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

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
	
	public String getUserType(){
		return company ? UserType.ROLE_COMPANY.toString() : UserType.ROLE_USER.toString();
	}
}
