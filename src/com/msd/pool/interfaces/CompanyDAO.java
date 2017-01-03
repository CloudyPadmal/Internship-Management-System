package com.msd.pool.interfaces;

import java.util.List;

import com.msd.items.Applicant;
import com.msd.items.Company;
import com.msd.pool.items.PoolCriteria;

public interface CompanyDAO {

	public final String TABLE = "company_table";

	// New applicant
	public int addCompany(Company company);

	// Get applicant
	public Company fetchCompany(String companyName);

	// Delete applicant
	public int deleteCompany(String companyName);

	// Increment vacancy count
	public int incrementVacancyCount(String company);

	// Decrement vacancy count
	public int decrementVacancyCount(String companyName);

	// Update applicant <Username is assumed to be the same>
	public int updateCompany(Company newCompany);

	// Get a list of applicants
	public List<Company> getAllCompanies();

	// Get a list of applicants in a given criteria
	public List<Company> getTypeOfCompanies(PoolCriteria criteria);
}
