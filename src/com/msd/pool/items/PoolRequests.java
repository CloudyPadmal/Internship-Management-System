package com.msd.pool.items;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import com.msd.items.Appeal;
import com.msd.pool.interfaces.RequestDAO;

public class PoolRequests implements RequestDAO {

	// Create a database handler
	JdbcTemplate dbHandler;

	// Setter for handler
	public void setdbHandler(JdbcTemplate dbHandler) {
		this.dbHandler = dbHandler;
	}

	@Override
	public int addRequest(Appeal appeal) {
		String sql = "INSERT INTO " + RequestDAO.TABLE
				+ " (applicant, currentApplicant, vacancy, vacancyName, gradedPoint, currentGradedPoint) "
				+ "VALUES (?, ?, ?, ?, ?, ?)";
		return dbHandler.update(sql, appeal.getIndexNumber(), appeal.getCurrentNumber(), appeal.getVacancyID(),
				appeal.getVacancyName(), appeal.getGradedPoint(), appeal.getCurrentGradedPoint());
	}

	@Override
	public Appeal fetchRequest(int id) {
		try {
			String sql = "SELECT * FROM " + RequestDAO.TABLE + " WHERE request_id = " + id;
			return dbHandler.query(sql, new ResultSetExtractor<Appeal>() {
				@Override
				public Appeal extractData(ResultSet rs) throws SQLException, DataAccessException {
					if (rs.next()) {
						// Create a new applicant and a criteria
						Appeal info = new Appeal(rs.getInt("request_id"), rs.getInt("vacancy"),
								rs.getString("applicant"), rs.getBoolean("attended"));
						info.setGradedPoint(rs.getDouble("gradedPoint"));
						info.setCurrentGradedPoint(rs.getDouble("currentGradedPoint"));
						info.setCurrentNumber(rs.getString("currentApplicant"));
						info.setVacancyName(rs.getString("vacancyName"));
						return info;
					}
					return null;
				}
			});
		} catch (org.springframework.dao.EmptyResultDataAccessException e) {
			return null;
		} catch (org.springframework.dao.IncorrectResultSizeDataAccessException n) {
			return null;
		}
	}

	@Override
	public int deleteRequest(int id) {
		String sql = "DELETE FROM " + RequestDAO.TABLE + " WHERE request_id = ?";
		return dbHandler.update(sql, id);
	}

	@Override
	public List<Appeal> getAllRequests() {
		List<Appeal> list = dbHandler.query("SELECT * FROM " + RequestDAO.TABLE, new RowMapper<Appeal>() {
			public Appeal mapRow(ResultSet rs, int row) throws SQLException {
				Appeal info = new Appeal(rs.getInt("request_id"), rs.getInt("vacancy"), rs.getString("applicant"),
						rs.getBoolean("attended"));
				info.setGradedPoint(rs.getDouble("gradedPoint"));
				info.setCurrentGradedPoint(rs.getDouble("currentGradedPoint"));
				info.setCurrentNumber(rs.getString("currentApplicant"));
				info.setVacancyName(rs.getString("vacancyName"));
				return info;
			}
		});
		return list;
	}

	@Override
	public int updateRequest(int id) {
		String sql = "UPDATE " + RequestDAO.TABLE + " SET attended = TRUE WHERE request_id = ?";
		return dbHandler.update(sql, id);
	}
	
	@Override
	public int deleteRequestsByUser(String indexNumber) {
		String sql = "DELETE FROM " + RequestDAO.TABLE + " WHERE applicant = ?";
		return dbHandler.update(sql, indexNumber);
	}
}
