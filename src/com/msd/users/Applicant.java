package com.msd.users;

import com.msd.pool.Preferences;

public class Applicant extends User {
	
	private String indexNumber;
	private Preferences preferences;
	
	public Applicant() {/**/}
	
	public String getIndexNumber() {
		return indexNumber;
	}
	public void setIndexNumber(String indexNumber) {
		this.indexNumber = indexNumber;
	}
	public Preferences getPreferences() {
		return preferences;
	}
	public void setPreferences(Preferences preferences) {
		this.preferences = preferences;
	}
}