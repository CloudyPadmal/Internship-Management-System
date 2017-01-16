package com.msd.pool.interfaces;

import com.msd.items.LoginInfo;

/******************************************************************************
 * EntryController will handle the entry point. It will redirect user to the
 * "User" login page or the "Company" login page
 ******************************************************************************/
public interface PasswordDAO {

	public final String TABLE = "password_table";

	// Add a password to the password table
	public int addPassword(LoginInfo info);

	// Delete an existing password from the table
	public int deletePassword(String username);

	// Update an existing password from the table
	public int updatePassword(LoginInfo info);
}