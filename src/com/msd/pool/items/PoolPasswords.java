package com.msd.pool.items;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
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
	public LoginInfo fetchUser(String username) {
		try {
			String sql = "SELECT * FROM " + PasswordDAO.TABLE + " WHERE username = '" + username + "'";
			return dbHandler.query(sql, new ResultSetExtractor<LoginInfo>() {
				@Override
				public LoginInfo extractData(ResultSet rs) throws SQLException, DataAccessException {
					if (rs.next()) {
						// Create a new applicant and a criteria
						LoginInfo info = new LoginInfo(rs.getString("username"), rs.getString("password"),
								rs.getBoolean("user_type"));
						return info;
					}
					return null;
				}
			});
		} catch (org.springframework.dao.EmptyResultDataAccessException e) {
			return null;
		} catch (org.springframework.dao.IncorrectResultSizeDataAccessException n) {
			return null;
		}
	}

	@Override
	public LoginInfo fetchAdmin(String username) {
		try {
			String sql = "SELECT * FROM " + PasswordDAO.ADMIN + " WHERE username = '" + username + "'";
			return dbHandler.query(sql, new ResultSetExtractor<LoginInfo>() {
				@Override
				public LoginInfo extractData(ResultSet rs) throws SQLException, DataAccessException {
					if (rs.next()) {
						// Create a new applicant and a criteria
						LoginInfo info = new LoginInfo(rs.getString("username"), rs.getString("password"),
								rs.getBoolean("user_type"));
						return info;
					}
					return null;
				}
			});
		} catch (org.springframework.dao.EmptyResultDataAccessException e) {
			return null;
		} catch (org.springframework.dao.IncorrectResultSizeDataAccessException n) {
			return null;
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

	@Override
	public List<LoginInfo> listOutPWs() {
		List<LoginInfo> list = dbHandler.query("SELECT * FROM " + PasswordDAO.TABLE, new RowMapper<LoginInfo>() {
			public LoginInfo mapRow(ResultSet rs, int row) throws SQLException {
				LoginInfo info = new LoginInfo(rs.getString("username"), rs.getString("password"),
						rs.getBoolean("user_type"));
				return info;
			}
		});
		return list;
	}

	// Checks if the typed password and username match with the one in the table
	public boolean matchThisAndThat(LoginInfo typedData) {
		// Fetch data in the password table
		LoginInfo originalData = fetchUser(typedData.getUsername());
		// If there are no matching password or no account, data will be null
		if (originalData == null) {
			return false;
		}
		// Check if the passwords are correct as well as the user types
		return passwordEncoder.matches(typedData.getPassword(), originalData.getPassword())
				&& (originalData.isCompany() == typedData.isCompany());
	}

	// Validate admin passwords
	public boolean matchThisAndAdmin(LoginInfo typedData) {
		// Fetch data from the admin password table
		LoginInfo originalData = fetchAdmin(typedData.getUsername());
		// If there are no matching password or no account, data will be null
		if (originalData == null) {
			return false;
		}
		// Check if the passwords are correct
		return passwordEncoder.matches(typedData.getPassword(), originalData.getPassword());
	}

	@Override
	public List<LoginInfo> listTypeOfPWs(boolean type) {
		List<LoginInfo> list = dbHandler.query("SELECT * FROM " + PasswordDAO.TABLE + " WHERE user_type = " + type,
				new RowMapper<LoginInfo>() {
					public LoginInfo mapRow(ResultSet rs, int row) throws SQLException {
						LoginInfo info = new LoginInfo(rs.getString("username"), rs.getString("password"),
								rs.getBoolean("user_type"));
						return info;
					}
				});
		return list;
	}

	public PasswordEncoder getPasswordEncoder() {
		return passwordEncoder;
	}

	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}
}
