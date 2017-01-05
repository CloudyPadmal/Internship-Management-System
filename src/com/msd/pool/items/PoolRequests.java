package com.msd.pool.items;

import org.springframework.jdbc.core.JdbcTemplate;

public class PoolRequests {

	// Create a database handler
	JdbcTemplate dbHandler;

	// Setter for handler
	public void setdbHandler(JdbcTemplate dbHandler) {
		this.dbHandler = dbHandler;
	}
	
	
}
