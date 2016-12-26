package com.msd.poolinterfaces;

import java.util.List;

import com.msd.pool.PoolCriteria;
import com.msd.users.Applicant;
import com.msd.users.Company;

public interface CompanyDAO {
public final String TABLE = "company_table";
	
	// New applicant
	public int addCompany(Company company);
	// Get applicant
	public Company fetchCompany(String companyName);
	// Delete applicant
	public int deleteCompany(String companyName);
	// Update applicant <Username is assumed to be the same>
	public void updateCompany(Company newCompany);
	
	// Get a list of applicants
	public List<Company> getAllCompanies();
	// Get a list of applicants in a given criteria
	public List<Company> getTypeOfCompanies(PoolCriteria criteria);
}
