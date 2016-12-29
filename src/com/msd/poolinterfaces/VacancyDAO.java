package com.msd.poolinterfaces;

import java.util.List;

import com.msd.pool.PoolCriteria;
import com.msd.users.Vacancy;

public interface VacancyDAO {

	public final String TABLE = "positions";

	// New Vacancy
	public int addVacancy(Vacancy vacancy);

	// Get Vacancy
	public Vacancy fetchVacancy(String vacancy);

	// Delete Vacancy
	public int deleteVacancy(String vacancy);

	// Update Vacancy
	public void updateVacancy(Vacancy newVacancy);

	// Get a list of Vacancy
	public List<Vacancy> getAllVacancies();
	
	// Get a list of Vacancy matching given preferences
	public List<Vacancy> getVacancies(PoolCriteria criteria);
}
