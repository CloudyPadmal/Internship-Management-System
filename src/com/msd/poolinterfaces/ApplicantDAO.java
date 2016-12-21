package com.msd.poolinterfaces;

import java.util.List;

import com.msd.pool.PoolCriteria;
import com.msd.users.Applicant;

public interface ApplicantDAO {
	
	// New applicant
	public void addApplicant(Applicant applicant);
	// Delete applicant
	public void deleteApplicant(Applicant applicant);
	// Update applicant
	public void updateApplicant(Applicant oldApplicant, Applicant newApplicant);
	
	// Get a list of applicants
	public List<Applicant> getAllApplicants();
	// Get a list of applicants in a given criteria
	public List<Applicant> getTypeOfApplicants(PoolCriteria criteria);

}
