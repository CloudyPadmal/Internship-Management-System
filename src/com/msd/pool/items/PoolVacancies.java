package com.msd.pool.items;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import com.msd.items.Vacancy;
import com.msd.pool.interfaces.VacancyDAO;

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
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		return dbHandler.update(sql, vacancy.getCompanyID(), vacancy.getSalary(), vacancy.getTitle(),
				vacancy.getDescription_1(), vacancy.getDescription_2(), criteria.isARDUINO(), criteria.isFPGA(),
				criteria.isROBOTICS(), criteria.isWIFI(), criteria.isANTENNAS(), criteria.isNETWORKING(),
				criteria.isPROCESSORDESIGN(), criteria.isIMAGEPROCESSING(), criteria.isPROGRAMMING(),
				criteria.isAUTOMATION(), criteria.isBIOMEDICAL(), criteria.isBIOMECHANICS(), criteria.isTELECOM(),
				criteria.isSEMICONDUCTORS(), criteria.isCIRCUITS(), criteria.isIOT(), criteria.isAI(),
				criteria.isSIGNALPROCESSING());
	}

	@Override
	public Vacancy fetchVacancy(int vacancyID) {
		String sql = "SELECT * FROM " + VacancyDAO.TABLE + " WHERE id = '" + vacancyID + "'";
		return dbHandler.query(sql, new ResultSetExtractor<Vacancy>() {
			@Override
			public Vacancy extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					// Create a new vacancy and a criteria
					Vacancy info = new Vacancy(rs.getString("title"), rs.getString("company"), rs.getInt("salary"),
							rs.getString("description_1"), rs.getString("description_2"));
					PoolCriteria criteria = new PoolCriteria(rs.getBoolean("ARDUINO"), rs.getBoolean("FPGA"),
							rs.getBoolean("ROBOTICS"), rs.getBoolean("WIFI"), rs.getBoolean("ANTENNAS"),
							rs.getBoolean("NETWORKING"), rs.getBoolean("PROCESSORDESIGN"),
							rs.getBoolean("IMAGEPROCESSING"), rs.getBoolean("PROGRAMMING"), rs.getBoolean("AUTOMATION"),
							rs.getBoolean("BIOMEDICAL"), rs.getBoolean("BIOMECHANICS"), rs.getBoolean("TELECOM"),
							rs.getBoolean("SEMICONDUCTORS"), rs.getBoolean("CIRCUITS"), rs.getBoolean("IOT"),
							rs.getBoolean("AI"), rs.getBoolean("SIGNALPROCESSING"));
					// Fetch
					info.setId(vacancyID);
					info.setApplicant(rs.getString("applicant"));
					info.setOpen(rs.getBoolean("open"));
					info.convertPrefToList(criteria);
					return info;
				}
				return null;
			}
		});
	}

	@Override
	public int deleteVacancy(int vacancy) {
		String sql = "DELETE FROM " + VacancyDAO.TABLE + " WHERE id = ?";
		return dbHandler.update(sql, vacancy);
	}

	@Override
	public int updateVacancy(Vacancy vacancy) {
		PoolCriteria criteria = vacancy.convertListToPref();
		String sql = "UPDATE " + VacancyDAO.TABLE
				+ " SET salary = ?, title = ?, description_1 = ?, description_2 = ?, ARDUINO = ?, "
				+ "FPGA = ?, ROBOTICS = ?, WIFI = ?, ANTENNAS = ?, NETWORKING = ?, PROCESSORDESIGN = ?, "
				+ "IMAGEPROCESSING = ?, PROGRAMMING = ?, AUTOMATION = ?, BIOMEDICAL = ?, BIOMECHANICS = ?, "
				+ "TELECOM = ?, SEMICONDUCTORS = ?, CIRCUITS = ?, IOT = ?, AI = ?, SIGNALPROCESSING = ? "
				+ "WHERE id = ?";
		return dbHandler.update(sql, vacancy.getSalary(), vacancy.getTitle(), vacancy.getDescription_1(),
				vacancy.getDescription_2(), criteria.isARDUINO(), criteria.isFPGA(), criteria.isROBOTICS(),
				criteria.isWIFI(), criteria.isANTENNAS(), criteria.isNETWORKING(), criteria.isPROCESSORDESIGN(),
				criteria.isIMAGEPROCESSING(), criteria.isPROGRAMMING(), criteria.isAUTOMATION(),
				criteria.isBIOMEDICAL(), criteria.isBIOMECHANICS(), criteria.isTELECOM(), criteria.isSEMICONDUCTORS(),
				criteria.isCIRCUITS(), criteria.isIOT(), criteria.isAI(), criteria.isSIGNALPROCESSING(),
				vacancy.getId());
	}

	@Override
	public List<Vacancy> getAllVacancies() {
		List<Vacancy> list = dbHandler.query("SELECT * FROM " + VacancyDAO.TABLE, new RowMapper<Vacancy>() {
			public Vacancy mapRow(ResultSet rs, int row) throws SQLException {
				// Create a new vacancy and a criteria
				Vacancy info = new Vacancy(rs.getString("title"), rs.getString("company"), rs.getInt("salary"),
						rs.getString("description_1"), rs.getString("description_2"));
				PoolCriteria criteria = new PoolCriteria(rs.getBoolean("ARDUINO"), rs.getBoolean("FPGA"),
						rs.getBoolean("ROBOTICS"), rs.getBoolean("WIFI"), rs.getBoolean("ANTENNAS"),
						rs.getBoolean("NETWORKING"), rs.getBoolean("PROCESSORDESIGN"), rs.getBoolean("IMAGEPROCESSING"),
						rs.getBoolean("PROGRAMMING"), rs.getBoolean("AUTOMATION"), rs.getBoolean("BIOMEDICAL"),
						rs.getBoolean("BIOMECHANICS"), rs.getBoolean("TELECOM"), rs.getBoolean("SEMICONDUCTORS"),
						rs.getBoolean("CIRCUITS"), rs.getBoolean("IOT"), rs.getBoolean("AI"),
						rs.getBoolean("SIGNALPROCESSING"));
				// Fetch
				info.setId(rs.getInt("id"));
				info.setApplicant(rs.getString("applicant"));
				info.setChoice(rs.getInt("choice"));
				info.setOpen(rs.getBoolean("open"));
				info.convertPrefToList(criteria);
				return info;
			}
		});
		return list;
	}

	@Override
	public List<Vacancy> getVacancies(PoolCriteria criteria) {
		List<Vacancy> list = dbHandler.query("SELECT * FROM " + VacancyDAO.TABLE + criteria.getWhereQuery(),
				new RowMapper<Vacancy>() {
					public Vacancy mapRow(ResultSet rs, int row) throws SQLException {
						// Create a new vacancy and a criteria
						Vacancy info = new Vacancy(rs.getString("title"), rs.getString("company"), rs.getInt("salary"),
								rs.getString("description_1"), rs.getString("description_2"));
						PoolCriteria criteria = new PoolCriteria(rs.getBoolean("ARDUINO"), rs.getBoolean("FPGA"),
								rs.getBoolean("ROBOTICS"), rs.getBoolean("WIFI"), rs.getBoolean("ANTENNAS"),
								rs.getBoolean("NETWORKING"), rs.getBoolean("PROCESSORDESIGN"),
								rs.getBoolean("IMAGEPROCESSING"), rs.getBoolean("PROGRAMMING"),
								rs.getBoolean("AUTOMATION"), rs.getBoolean("BIOMEDICAL"), rs.getBoolean("BIOMECHANICS"),
								rs.getBoolean("TELECOM"), rs.getBoolean("SEMICONDUCTORS"), rs.getBoolean("CIRCUITS"),
								rs.getBoolean("IOT"), rs.getBoolean("AI"), rs.getBoolean("SIGNALPROCESSING"));
						// Fetch
						info.setId(rs.getInt("id"));
						info.setApplicant(rs.getString("applicant"));
						info.setChoice(rs.getInt("choice"));
						info.setOpen(rs.getBoolean("open"));
						info.convertPrefToList(criteria);
						return info;
					}
				});
		return list;
	}

	@Override
	public List<Vacancy> getCompanyVacancies(String company) {
		List<Vacancy> list = dbHandler.query("SELECT * FROM " + VacancyDAO.TABLE + " WHERE company = '" + company + "'",
				new RowMapper<Vacancy>() {
					public Vacancy mapRow(ResultSet rs, int row) throws SQLException {
						// Create a new vacancy and a criteria
						Vacancy info = new Vacancy(rs.getString("title"), rs.getString("company"), rs.getInt("salary"),
								rs.getString("description_1"), rs.getString("description_2"));
						PoolCriteria criteria = new PoolCriteria(rs.getBoolean("ARDUINO"), rs.getBoolean("FPGA"),
								rs.getBoolean("ROBOTICS"), rs.getBoolean("WIFI"), rs.getBoolean("ANTENNAS"),
								rs.getBoolean("NETWORKING"), rs.getBoolean("PROCESSORDESIGN"),
								rs.getBoolean("IMAGEPROCESSING"), rs.getBoolean("PROGRAMMING"),
								rs.getBoolean("AUTOMATION"), rs.getBoolean("BIOMEDICAL"), rs.getBoolean("BIOMECHANICS"),
								rs.getBoolean("TELECOM"), rs.getBoolean("SEMICONDUCTORS"), rs.getBoolean("CIRCUITS"),
								rs.getBoolean("IOT"), rs.getBoolean("AI"), rs.getBoolean("SIGNALPROCESSING"));
						// Fetch
						info.setId(rs.getInt("id"));
						info.setApplicant(rs.getString("applicant"));
						info.setChoice(rs.getInt("choice"));
						info.setOpen(rs.getBoolean("open"));
						info.convertPrefToList(criteria);
						return info;
					}
				});
		return list;
	}

	@Override
	public String getCompanyName(int vacancyID) {
		String sql = "SELECT company FROM " + VacancyDAO.TABLE + " WHERE id = '" + vacancyID + "'";
		return dbHandler.query(sql, new ResultSetExtractor<String>() {
			@Override
			public String extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					return rs.getString("company");
				}
				return null;
			}
		});
	}

	@Override
	public boolean isOpen(int vacancyID) {
		String sql = "SELECT open FROM " + VacancyDAO.TABLE + " WHERE id = '" + vacancyID + "'";
		return dbHandler.query(sql, new ResultSetExtractor<Boolean>() {
			@Override
			public Boolean extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					return rs.getBoolean("company");
				}
				return null;
			}
		});
	}

	@Override
	public int closeVacancy(int vacancyID, String indexNumber, int choice) {
		String sql = "UPDATE " + VacancyDAO.TABLE + " SET open = TRUE, applicant = '" + indexNumber + "', choice = "
				+ choice + " WHERE id = " + vacancyID;
		return dbHandler.update(sql);
	}

	@Override
	public int openVacancy(int vacancyID) {
		String sql = "UPDATE " + VacancyDAO.TABLE + " SET open = FALSE, applicant = NULL, choice = NULL WHERE id = "
				+ vacancyID;
		return dbHandler.update(sql);
	}

	public String getApplicant(int vacancyID) {
		String sql = "SELECT applicant FROM " + VacancyDAO.TABLE + " WHERE id = '" + vacancyID + "'";
		return dbHandler.query(sql, new ResultSetExtractor<String>() {
			@Override
			public String extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					return rs.getString("applicant");
				}
				return null;
			}
		});
	}

	public String getVacancyName(int vacancyID) {
		String sql = "SELECT title FROM " + VacancyDAO.TABLE + " WHERE id = '" + vacancyID + "'";
		return dbHandler.query(sql, new ResultSetExtractor<String>() {
			@Override
			public String extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					return rs.getString("title");
				}
				return null;
			}
		});
	}

	@Override
	public int changeApplicant(String newID, int vacancyID, int newChoice) {
		String sql = "UPDATE " + VacancyDAO.TABLE + " SET applicant = '" + newID + "', choice = " + newChoice
				+ " WHERE id = " + vacancyID;
		return dbHandler.update(sql);
	}

	@Override
	public int getChoice(int vacancyID) {
		String sql = "SELECT choice FROM " + VacancyDAO.TABLE + " WHERE id = '" + vacancyID + "'";
		return dbHandler.query(sql, new ResultSetExtractor<Integer>() {
			@Override
			public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					return rs.getInt("choice");
				}
				return null;
			}
		});
	}

	@Override
	public int deleteApplicantBinds(String indexNumber) {
		String sql = "UPDATE " + VacancyDAO.TABLE
				+ " SET applicant = NULL, choice = NULL, open = FALSE WHERE applicant = '" + indexNumber + "'";
		return dbHandler.update(sql);
	}

	@Override
	public List<Vacancy> getUserVacancies(String indexNumber) {
		List<Vacancy> list = dbHandler.query(
				"SELECT * FROM " + VacancyDAO.TABLE + " WHERE applicant = '" + indexNumber + "'",
				new RowMapper<Vacancy>() {
					public Vacancy mapRow(ResultSet rs, int row) throws SQLException {
						// Create a new vacancy and a criteria
						Vacancy info = new Vacancy(rs.getString("title"), rs.getString("company"), rs.getInt("salary"),
								rs.getString("description_1"), rs.getString("description_2"));
						PoolCriteria criteria = new PoolCriteria(rs.getBoolean("ARDUINO"), rs.getBoolean("FPGA"),
								rs.getBoolean("ROBOTICS"), rs.getBoolean("WIFI"), rs.getBoolean("ANTENNAS"),
								rs.getBoolean("NETWORKING"), rs.getBoolean("PROCESSORDESIGN"),
								rs.getBoolean("IMAGEPROCESSING"), rs.getBoolean("PROGRAMMING"),
								rs.getBoolean("AUTOMATION"), rs.getBoolean("BIOMEDICAL"), rs.getBoolean("BIOMECHANICS"),
								rs.getBoolean("TELECOM"), rs.getBoolean("SEMICONDUCTORS"), rs.getBoolean("CIRCUITS"),
								rs.getBoolean("IOT"), rs.getBoolean("AI"), rs.getBoolean("SIGNALPROCESSING"));
						// Fetch
						info.setId(rs.getInt("id"));
						info.setApplicant(rs.getString("applicant"));
						info.setChoice(rs.getInt("choice"));
						info.setOpen(rs.getBoolean("open"));
						info.convertPrefToList(criteria);
						return info;
					}
				});
		return list;
	}
}
