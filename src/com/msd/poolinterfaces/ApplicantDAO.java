package com.msd.poolinterfaces;

import java.util.List;

import com.msd.pool.PoolCriteria;
import com.msd.registers.LoginInfo;
import com.msd.users.Applicant;

public interface ApplicantDAO {
	
	public final String TABLE = "user_table";
	
	// New applicant
	public int addApplicant(Applicant applicant);
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
