package com.msd.registers;

import com.sun.org.apache.xml.internal.security.utils.Base64;

public final class LoginInfo {

	private String username;
	private String password;

	public LoginInfo() {
		// Default Constructor
	}

	public String getencodedPassword() {
		// Creates a base 64 encoded password
		return Base64.encode(password.getBytes());
	}

	public String decodePassword() {
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

	public void writeCredentialsToDB(LoginInfo info) {
		// Write credentials to the password table
	}

	public boolean validCredentials(LoginInfo info) {
		LoginInfo credFromDB = getCredentialsFromDB();
		return isMatching(credFromDB);
	}

	private LoginInfo getCredentialsFromDB() {
		LoginInfo info = new LoginInfo();
		// Fetch password string from database
		return info;
	}

	private boolean isMatching(LoginInfo oldInfo) {
		// Get the password relevant to the user name
		String oldPassword = oldInfo.decodePassword();
		// Decode the current password
		String newPassword = decodePassword(); 
		// Match them and return result
		return newPassword.equals(oldPassword);
	}
}
