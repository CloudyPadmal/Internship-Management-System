package com.msd.users;

import com.msd.pool.Preferences;

public class Applicant extends User {
	
	private String indexNumber;
	private Preferences preferences;
	private String emailAddress;
	private String telephone;
	private Double gradedPoint;
	
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

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Double getGradedPoint() {
		return gradedPoint;
	}

	public void setGradedPoint(Double gradedPoint) {
		this.gradedPoint = gradedPoint;
	}
}