package com.msd.pool.interfaces;

import java.util.List;

import com.msd.items.Applicant;
import com.msd.pool.items.PoolCriteria;

public interface CoordinatorDAO {

	public final String TABLE = "admin_table";

	// Get applicant
	public Applicant fetchApplicant(String indexNumber);

	// Delete applicant
	public int deleteApplicant(String indexNumber);

	// Update applicant <Username is assumed to be the same>
	public void updateApplicant(Applicant newApplicant);

	// Get a list of applicants
	public List<Applicant> getAllApplicants();

	// Get a list of applicants in a given criteria
	public List<Applicant> getTypeOfApplicants(PoolCriteria criteria);
}
