package com.msd.pool.interfaces;

import java.util.List;

import com.msd.items.LoginInfo;

/******************************************************************************
 * EntryController will handle the entry point. It will redirect user to the
 * "User" login page or the "Company" login page
 ******************************************************************************/
public interface PasswordDAO {

	public final String TABLE = "password_table";
	public final String ADMIN = "admin_table";
	
	// Add a password to the password table
	public int addPassword(LoginInfo info);
	// Fetch a password from the password table
	public LoginInfo fetchUser(String username);
	// Fetch admin details from admin table
	public LoginInfo fetchAdmin(String username);
	// Delete an existing password from the table
	public int deletePassword(String username);
	// Update an existing password from the table
	public int updatePassword(LoginInfo info);
	
	// Get a list of LoginInfo <Remove at production>
	public List<LoginInfo> listOutPWs();
	// Get a list of this type of LoginInfo
	public List<LoginInfo> listTypeOfPWs(boolean type);
}