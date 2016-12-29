package com.msd.pool.items;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import com.msd.items.Company;
import com.msd.pool.interfaces.CompanyDAO;

public class PoolCompanies implements CompanyDAO {

	// Create a database handler
	JdbcTemplate dbHandler;

	// Setter for handler
	public void setdbHandler(JdbcTemplate dbHandler) {
		this.dbHandler = dbHandler;
	}

	@Override
	public int addCompany(Company company) {
		String sql = "INSERT INTO " + CompanyDAO.TABLE
				+ " (loginID, company, address, emailAddress, telephone, aboutUS, positions) " + "VALUES ('"
				+ company.getLoginID() + "', '" + company.getCompany() + "', '" + company.getAddress() + "', '"
				+ company.getEmailAddress() + "', '" + company.getTelephone() + "', '" + company.getAboutUs() + "',"
				+ company.getPositions() + ")";

		return dbHandler.update(sql);
	}

	@Override
	public Company fetchCompany(String companyName) {
		String sql = "SELECT * FROM " + CompanyDAO.TABLE + " WHERE company = '" + companyName + "'";
		return dbHandler.query(sql, new ResultSetExtractor<Company>() {
			@Override
			public Company extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					// Create a new Company
					Company info = new Company(rs.getString("loginID"), rs.getString("company"),
							rs.getString("address"), rs.getString("emailAddress"), rs.getString("telephone"),
							rs.getString("aboutUS"), rs.getInt("positions"));
					// Fetch
					return info;
				}
				return null;
			}
		});
	}

	@Override
	public int deleteCompany(String companyName) {
		String sql = "DELETE FROM " + CompanyDAO.TABLE + " WHERE company = ?";
		return dbHandler.update(sql, companyName);
	}

	@Override
	public void updateCompany(Company newCompany) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Company> getAllCompanies() {
		List<Company> list = dbHandler.query("SELECT * FROM " + CompanyDAO.TABLE, new RowMapper<Company>() {
			public Company mapRow(ResultSet rs, int row) throws SQLException {
				// Create a new Company
				Company info = new Company(rs.getString("loginID"), rs.getString("company"), rs.getString("address"),
						rs.getString("emailAddress"), rs.getString("telephone"), rs.getString("aboutUS"),
						rs.getInt("positions"));
				// Fetch
				return info;
			}
		});
		return list;
	}

	@Override
	public List<Company> getTypeOfCompanies(PoolCriteria criteria) {
		// TODO Auto-generated method stub
		return null;
	}
}
