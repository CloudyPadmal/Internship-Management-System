package com.msd.pool;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.msd.poolinterfaces.CompanyDAO;
import com.msd.users.Company;

public class PoolCompanies implements CompanyDAO {
	
	// Create a database handler
	JdbcTemplate dbHandler;

	// Setter for handler
	public void setdbHandler(JdbcTemplate dbHandler) {
		this.dbHandler = dbHandler;
	}

	@Override
	public int addCompany(Company company) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Company fetchCompany(String companyName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteCompany(String companyName) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void updateCompany(Company newCompany) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Company> getAllCompanies() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Company> getTypeOfCompanies(PoolCriteria criteria) {
		// TODO Auto-generated method stub
		return null;
	}
}
