package com.msd.pool.items;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import com.msd.items.Applicant;
import com.msd.pool.interfaces.ApplicantDAO;
import com.msd.pool.interfaces.CompanyDAO;
import com.msd.pool.interfaces.VacancyDAO;

public class PoolApplicants implements ApplicantDAO {

	// Create a database handler
	JdbcTemplate dbHandler;

	// Setter for handler
	public void setdbHandler(JdbcTemplate dbHandler) {
		this.dbHandler = dbHandler;
	}

	@Override
	public int addApplicant(Applicant applicant) {
		PoolCriteria criteria = applicant.convertListToPref();
		String sql = "INSERT INTO " + ApplicantDAO.TABLE
				+ " (name, surname, gender, indexNumber, emailAddress, telephone, gradedPoint, aboutMe, ARDUINO, "
				+ "FPGA, ROBOTICS, WIFI, ANTENNAS, NETWORKING, PROCESSORDESIGN, IMAGEPROCESSING, PROGRAMMING, AUTOMATION, "
				+ "BIOMEDICAL, BIOMECHANICS, TELECOM, SEMICONDUCTORS, CIRCUITS, IOT, AI, SIGNALPROCESSING) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		return dbHandler.update(sql, applicant.getName(), applicant.getSurname(), applicant.getGender(),
				applicant.getIndexNumber(), applicant.getEmailAddress(), applicant.getTelephone(),
				applicant.getGradedPoint(), applicant.getAboutMe(), criteria.isARDUINO(), criteria.isFPGA(),
				criteria.isROBOTICS(), criteria.isWIFI(), criteria.isANTENNAS(), criteria.isNETWORKING(),
				criteria.isPROCESSORDESIGN(), criteria.isIMAGEPROCESSING(), criteria.isPROGRAMMING(),
				criteria.isAUTOMATION(), criteria.isBIOMEDICAL(), criteria.isBIOMECHANICS(), criteria.isTELECOM(),
				criteria.isSEMICONDUCTORS(), criteria.isCIRCUITS(), criteria.isIOT(), criteria.isAI(),
				criteria.isSIGNALPROCESSING());
	}

	@Override
	public Applicant fetchApplicant(String indexNumber) {

		String sql = "SELECT * FROM " + ApplicantDAO.TABLE + " WHERE indexNumber = '" + indexNumber + "'";
		return dbHandler.query(sql, new ResultSetExtractor<Applicant>() {
			@Override
			public Applicant extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					// Create a new applicant and a criteria
					Applicant info = new Applicant(rs.getString("indexNumber"), rs.getString("surname"),
							rs.getString("emailAddress"), rs.getString("telephone"), rs.getDouble("gradedPoint"),
							rs.getString("aboutMe"));
					PoolCriteria criteria = new PoolCriteria(rs.getBoolean("ARDUINO"), rs.getBoolean("FPGA"),
							rs.getBoolean("ROBOTICS"), rs.getBoolean("WIFI"), rs.getBoolean("ANTENNAS"),
							rs.getBoolean("NETWORKING"), rs.getBoolean("PROCESSORDESIGN"),
							rs.getBoolean("IMAGEPROCESSING"), rs.getBoolean("PROGRAMMING"), rs.getBoolean("AUTOMATION"),
							rs.getBoolean("BIOMEDICAL"), rs.getBoolean("BIOMECHANICS"), rs.getBoolean("TELECOM"),
							rs.getBoolean("SEMICONDUCTORS"), rs.getBoolean("CIRCUITS"), rs.getBoolean("IOT"),
							rs.getBoolean("AI"), rs.getBoolean("SIGNALPROCESSING"));
					// Fetch
					info.setVacancy1(rs.getString("vacancy_A"));
					info.setVacancy2(rs.getString("vacancy_B"));
					info.setVacancy3(rs.getString("vacancy_C"));
					info.setApplied1(rs.getBoolean("status_A"));
					info.setApplied2(rs.getBoolean("status_B"));
					info.setApplied3(rs.getBoolean("status_C"));
					info.setAppeal(rs.getString("appeal"));
					info.setAppealStatus(rs.getBoolean("appealStatus"));
					info.setName(rs.getString("name"));
					info.setGender(rs.getString("gender"));
					info.setAwarded(rs.getBoolean("awarded"));
					info.setAwardedVacancy(rs.getInt("awardedVacancy"));
					info.convertPrefToList(criteria);
					return info;
				}
				return null;
			}
		});
	}

	@Override
	public int deleteApplicant(String indexNumber) {
		String sql = "DELETE FROM " + ApplicantDAO.TABLE + " WHERE indexNumber = ?";
		return dbHandler.update(sql, indexNumber);
	}

	@Override
	public int updateApplicant(Applicant newApplicant) {
		PoolCriteria criteria = newApplicant.convertListToPref();
		String sql = "UPDATE " + ApplicantDAO.TABLE
				+ " SET name = ?, surname = ?, gender = ?, emailAddress = ?, telephone = ?, gradedPoint = ?, "
				+ "aboutMe = ?, emailAddress = ?, ARDUINO = ?, FPGA = ?, ROBOTICS = ?, WIFI = ?, ANTENNAS = ?, "
				+ "NETWORKING = ?, PROCESSORDESIGN = ?, IMAGEPROCESSING = ?, PROGRAMMING = ?, AUTOMATION = ?, "
				+ "BIOMEDICAL = ?, BIOMECHANICS = ?, TELECOM = ?, SEMICONDUCTORS = ?, CIRCUITS = ?, IOT = ?, "
				+ "AI = ?, SIGNALPROCESSING = ? WHERE indexNumber = ?";
		return dbHandler.update(sql, newApplicant.getName(), newApplicant.getSurname(), newApplicant.getGender(),
				newApplicant.getEmailAddress(), newApplicant.getTelephone(), newApplicant.getGradedPoint(),
				newApplicant.getAboutMe(), newApplicant.getEmailAddress(), criteria.isARDUINO(), criteria.isFPGA(),
				criteria.isROBOTICS(), criteria.isWIFI(), criteria.isANTENNAS(), criteria.isNETWORKING(),
				criteria.isPROCESSORDESIGN(), criteria.isIMAGEPROCESSING(), criteria.isPROGRAMMING(),
				criteria.isAUTOMATION(), criteria.isBIOMEDICAL(), criteria.isBIOMECHANICS(), criteria.isTELECOM(),
				criteria.isSEMICONDUCTORS(), criteria.isCIRCUITS(), criteria.isIOT(), criteria.isAI(),
				criteria.isSIGNALPROCESSING(), newApplicant.getIndexNumber());
	}

	@Override
	public List<Applicant> getAllApplicants() {
		List<Applicant> list = dbHandler.query("SELECT * FROM " + ApplicantDAO.TABLE, new RowMapper<Applicant>() {
			public Applicant mapRow(ResultSet rs, int row) throws SQLException {
				// Create a new applicant and a criteria
				Applicant info = new Applicant(rs.getString("indexNumber"), rs.getString("surname"),
						rs.getString("emailAddress"), rs.getString("telephone"), rs.getDouble("gradedPoint"),
						rs.getString("aboutMe"));
				PoolCriteria criteria = new PoolCriteria(rs.getBoolean("ARDUINO"), rs.getBoolean("FPGA"),
						rs.getBoolean("ROBOTICS"), rs.getBoolean("WIFI"), rs.getBoolean("ANTENNAS"),
						rs.getBoolean("NETWORKING"), rs.getBoolean("PROCESSORDESIGN"), rs.getBoolean("IMAGEPROCESSING"),
						rs.getBoolean("PROGRAMMING"), rs.getBoolean("AUTOMATION"), rs.getBoolean("BIOMEDICAL"),
						rs.getBoolean("BIOMECHANICS"), rs.getBoolean("TELECOM"), rs.getBoolean("SEMICONDUCTORS"),
						rs.getBoolean("CIRCUITS"), rs.getBoolean("IOT"), rs.getBoolean("AI"),
						rs.getBoolean("SIGNALPROCESSING"));
				// Fetch
				info.setName(rs.getString("name"));
				info.setGender(rs.getString("gender"));
				info.setVacancy1(rs.getString("vacancy_A"));
				info.setVacancy2(rs.getString("vacancy_B"));
				info.setVacancy3(rs.getString("vacancy_C"));
				info.setApplied1(rs.getBoolean("status_A"));
				info.setApplied2(rs.getBoolean("status_B"));
				info.setApplied3(rs.getBoolean("status_C"));
				info.setAppeal(rs.getString("appeal"));
				info.setAppealStatus(rs.getBoolean("appealStatus"));
				info.setAwarded(rs.getBoolean("awarded"));
				info.setAwardedVacancy(rs.getInt("awardedVacancy"));
				info.convertPrefToList(criteria);
				return info;
			}
		});
		return list;
	}

	@Override
	public List<Applicant> getTypeOfApplicants(PoolCriteria criteria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int setChoice(String indexNumber, int choice, int vacancy) {
		// Map the choice
		String userChoice = "";
		String choiceBool = "";
		switch (choice) {
		case 1:
			userChoice = "vacancy_A";
			choiceBool = "status_A";
			break;
		case 2:
			userChoice = "vacancy_B";
			choiceBool = "status_B";
			break;
		case 3:
			userChoice = "vacancy_C";
			choiceBool = "status_C";
			break;
		default:
			return 0;
		}
		String sql = "UPDATE " + ApplicantDAO.TABLE + " SET " + userChoice + " = " + vacancy + ", " + choiceBool
				+ " = TRUE WHERE indexNumber = '" + indexNumber + "'";
		return dbHandler.update(sql);
	}

	@Override
	public int removeChoice(String indexNumber, int choice) {
		// Map the choice
		String userChoice = "";
		String choiceBool = "";
		switch (choice) {
		case 1:
			userChoice = "vacancy_A";
			choiceBool = "status_A";
			break;
		case 2:
			userChoice = "vacancy_B";
			choiceBool = "status_B";
			break;
		case 3:
			userChoice = "vacancy_C";
			choiceBool = "status_C";
			break;
		default:
			return 0;
		}
		String sql = "UPDATE " + ApplicantDAO.TABLE + " SET " + userChoice + " = NULL , " + choiceBool + " = NULL"
				+ " WHERE indexNumber = '" + indexNumber + "'";
		return dbHandler.update(sql);
	}

	@Override
	public int updateChoice(String indexNumber, int newchoice, int vacancy) {
		// Map the choice
		String userChoice = "";
		String choiceBool = "";
		switch (newchoice) {
		case 1:
			userChoice = "vacancy_A";
			choiceBool = "status_A";
			break;
		case 2:
			userChoice = "vacancy_B";
			choiceBool = "status_B";
			break;
		case 3:
			userChoice = "vacancy_C";
			choiceBool = "status_C";
			break;
		default:
			return 0;
		}
		String sql = "UPDATE " + ApplicantDAO.TABLE + " SET " + userChoice + " = " + vacancy + ", " + choiceBool
				+ " = TRUE " + " WHERE indexNumber = '" + indexNumber + "'";
		return dbHandler.update(sql);
	}

	@Override
	public double getGPA(String indexNumber) {
		String sql = "SELECT gradedPoint FROM " + ApplicantDAO.TABLE + " WHERE indexNumber = '" + indexNumber + "'";
		return dbHandler.query(sql, new ResultSetExtractor<Double>() {
			@Override
			public Double extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					return rs.getDouble("gradedPoint");
				}
				return null;
			}
		});
	}

	@Override
	public String getAppeal(String indexNumber) {
		String sql = "SELECT appeal FROM " + ApplicantDAO.TABLE + " WHERE indexNumber = '" + indexNumber + "'";
		return dbHandler.query(sql, new ResultSetExtractor<String>() {
			@Override
			public String extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					return rs.getString("appeal");
				}
				return null;
			}
		});
	}

	@Override
	public int addRequest(String indexNumber, int vacancyID) {
		String sql = "UPDATE " + ApplicantDAO.TABLE + " SET appeal = " + vacancyID + ", appealStatus = TRUE "
				+ " WHERE indexNumber = '" + indexNumber + "'";
		return dbHandler.update(sql);
	}

	@Override
	public int deleteRequest(String indexNumber) {
		String sql = "UPDATE " + ApplicantDAO.TABLE + " SET appeal = NULL, appealStatus = NULL WHERE indexNumber = '"
				+ indexNumber + "'";
		return dbHandler.update(sql);
	}

	@Override
	public int markAwarded(String applicant, int vacancy) {
		String sql = "UPDATE " + ApplicantDAO.TABLE + " SET awardedVacancy = ?, awarded = TRUE WHERE indexNumber = '"
				+ applicant + "'";
		return dbHandler.update(sql, vacancy);
	}
}
