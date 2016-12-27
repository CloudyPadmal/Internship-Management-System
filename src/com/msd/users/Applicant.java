package com.msd.users;

import java.util.List;

import com.msd.pool.PoolCriteria;
import com.msd.poolinterfaces.Preferences;

public class Applicant extends User implements Preferences {

	private String indexNumber;
	private String surname;
	private List<String> preferences;
	private String emailAddress;
	private String telephone;
	private Double gradedPoint;
	private String aboutMe;

	public Applicant() {/**/}

	public Applicant(String indexNumber, String surname, String emailAddress, String telephone, Double gradedPoint,
			String aboutMe) {
		this.indexNumber = indexNumber;
		this.surname = surname;
		this.emailAddress = emailAddress;
		this.telephone = telephone;
		this.gradedPoint = gradedPoint;
		this.aboutMe = aboutMe;
	}

	public String getIndexNumber() {
		return indexNumber;
	}

	public void setIndexNumber(String indexNumber) {
		this.indexNumber = indexNumber;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public List<String> getPreferences() {
		return preferences;
	}

	public void setPreferences(List<String> preferences) {
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

	public String getAboutMe() {
		return aboutMe;
	}

	public void setAboutMe(String aboutMe) {
		this.aboutMe = aboutMe;
	}

	public PoolCriteria convertListToPref() {
		ListPrefConverter converter = new ListPrefConverter();
		return converter.convertListToPref(this.preferences);
	}

	public void convertPrefToList(PoolCriteria criteria) {
		ListPrefConverter converter = new ListPrefConverter();
		this.preferences = converter.convertPrefToList(criteria);
	}
}