package com.msd.pool.interfaces;

import java.util.List;

import com.msd.items.Vacancy;
import com.msd.pool.items.PoolCriteria;

public interface VacancyDAO {

	public final String TABLE = "positions";

	// New Vacancy
	public int addVacancy(Vacancy vacancy);

	// Get Vacancy
	public Vacancy fetchVacancy(int vacancyID);

	// Delete Vacancy
	public int deleteVacancy(int vacancyID);

	// Update Vacancy
	public int updateVacancy(Vacancy newVacancy);

	// Get a list of vacancies
	public List<Vacancy> getAllVacancies();

	// Get a list of vacancies matching given preferences
	public List<Vacancy> getVacancies(PoolCriteria criteria);

	// Get a list of vacancies in a company
	public List<Vacancy> getCompanyVacancies(String company);

	// Get the company name of a vacancy
	public String getCompanyName(int vacancyID);

	// Check if a vacancy is open
	public boolean isOpen(int vacancyID);

	// Update vacancy status
	public int closeVacancy(int vacancyID, String indexNumber);

	// Remove applicant from vacancy
	public int openVacancy(int vacancyID);
}
