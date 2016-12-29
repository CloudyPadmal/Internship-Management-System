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
	public int deleteVacancy(int vacancy);

	// Update Vacancy
	public void updateVacancy(Vacancy newVacancy);

	// Get a list of Vacancy
	public List<Vacancy> getAllVacancies();
	
	// Get a list of Vacancy matching given preferences
	public List<Vacancy> getVacancies(PoolCriteria criteria);
}
