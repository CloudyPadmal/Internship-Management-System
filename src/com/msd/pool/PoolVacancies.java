package com.msd.pool;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.msd.poolinterfaces.VacancyDAO;
import com.msd.users.Vacancy;

public class PoolVacancies implements VacancyDAO {

	// Create a database handler
	JdbcTemplate dbHandler;

	// Setter for handler
	public void setdbHandler(JdbcTemplate dbHandler) {
		this.dbHandler = dbHandler;
	}

	@Override
	public int addVacancy(Vacancy vacancy) {
		PoolCriteria criteria = vacancy.convertListToPref();
		String sql = "INSERT INTO " + VacancyDAO.TABLE
				+ " (company, salary, title, description_1, description_2, ARDUINO, "
				+ "FPGA, ROBOTICS, WIFI, ANTENNAS, NETWORKING, PROCESSORDESIGN, IMAGEPROCESSING, PROGRAMMING, AUTOMATION, "
				+ "BIOMEDICAL, BIOMECHANICS, TELECOM, SEMICONDUCTORS, CIRCUITS, IOT, AI, SIGNALPROCESSING) "
				+ "VALUES ('" + vacancy.getCompany() + "', '" + vacancy.getSalary() + "', '" + vacancy.getTitle()
				+ "', '" + vacancy.getDescription_1() + "', '" + vacancy.getDescription_2() + "',"
				+ criteria.isARDUINO() + "," + criteria.isFPGA() + "," + criteria.isROBOTICS() + "," + criteria.isWIFI()
				+ "," + criteria.isANTENNAS() + "," + criteria.isNETWORKING() + "," + criteria.isPROCESSORDESIGN() + ","
				+ criteria.isIMAGEPROCESSING() + "," + criteria.isPROGRAMMING() + "," + criteria.isAUTOMATION() + ","
				+ criteria.isBIOMEDICAL() + "," + criteria.isBIOMECHANICS() + "," + criteria.isTELECOM() + ","
				+ criteria.isSEMICONDUCTORS() + "," + criteria.isCIRCUITS() + "," + criteria.isIOT() + ","
				+ criteria.isAI() + "," + criteria.isSIGNALPROCESSING() + ")";
		return dbHandler.update(sql);
	}

	@Override
	public Vacancy fetchVacancy(String vacancy) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteVacancy(String vacancy) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void updateVacancy(Vacancy newVacancy) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Vacancy> getAllVacancies() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Vacancy> getVacancies(PoolCriteria criteria) {
		// TODO Auto-generated method stub
		return null;
	}
}
