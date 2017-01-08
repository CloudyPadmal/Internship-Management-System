package com.msd.pool.interfaces;

import java.util.List;

import com.msd.items.Appeal;

public interface RequestDAO {

	public final String TABLE = "request_table";

	// New Request
	public int addRequest(Appeal appeal);

	// Get Request
	public Appeal fetchRequest(int id);

	// Delete Request
	public int deleteRequest(int id);

	// Get a list of requests
	public List<Appeal> getAllRequests();

	// Update a request
	public int updateRequest(int id);

	// Remove requests by a user
	public int deleteRequestsByUser(String indexNumber);

	// List out requests for a vacancy
	public List<Appeal> getAppeals(int id);
}
