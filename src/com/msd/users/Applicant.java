package com.msd.users;

import java.util.ArrayList;

public class Applicant extends User {
	
	private String indexNumber;
	private ArrayList<String> preferences;
	
	public Applicant() {
		preferences = new ArrayList<>();
	}
	
	public String getIndexNumber() {
		return indexNumber;
	}
	public void setIndexNumber(String indexNumber) {
		this.indexNumber = indexNumber;
	}
	public ArrayList<String> getPreferences() {
		return preferences;
	}
	public void setPreferences(ArrayList<String> preferences) {
		this.preferences = preferences;
	}
}