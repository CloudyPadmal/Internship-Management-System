package com.msd.pool.items;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.msd.items.LoginInfo;
import com.msd.pool.interfaces.PasswordDAO;

public class PoolPasswords implements PasswordDAO {

	// Create a database handler
	JdbcTemplate dbHandler;

	private @Autowired PasswordEncoder passwordEncoder;

	// Setter for handler
	public void setdbHandler(JdbcTemplate dbHandler) {
		this.dbHandler = dbHandler;
	}

	public PasswordEncoder getPasswordEncoder() {
		return passwordEncoder;
	}

	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public int addPassword(LoginInfo info) {
		String sql = "INSERT INTO " + PasswordDAO.TABLE + " (username, password, user_type) " + "VALUES (?, ?, ?)";
		try {
			return dbHandler.update(sql, info.getUsername(), passwordEncoder.encode(info.getPassword()),
					info.getUserType());
		} catch (org.springframework.dao.DuplicateKeyException e) {
			return 0;
		}
	}

	@Override
	public int deletePassword(String username) {
		String sql = "DELETE FROM " + PasswordDAO.TABLE + " WHERE username = ?";
		return dbHandler.update(sql, username);
	}

	@Override
	public int updatePassword(LoginInfo info) {
		String sql = "UPDATE " + PasswordDAO.TABLE + " SET password = ? WHERE username = ?";
		return dbHandler.update(sql, info.getUsername(), passwordEncoder.encode(info.getPassword()));
	}

	public int makeActive(String username) {
		String sql = "UPDATE " + PasswordDAO.TABLE + " SET active = TRUE WHERE username = ?";
		return dbHandler.update(sql, username);
	}
	
	public int makeInactive(String username) {
		String sql = "UPDATE " + PasswordDAO.TABLE + " SET active = FALSE WHERE username = ?";
		return dbHandler.update(sql, username);		
	}
}
