package com.msd.items;

import java.util.List;

import com.msd.pool.interfaces.Preferences;
import com.msd.pool.items.PoolCriteria;

public class Applicant extends User implements Preferences {

	private String indexNumber;
	private String surname;
	private List<String> preferences;
	private String emailAddress;
	private String telephone;
	private Double gradedPoint;
	private String aboutMe;
	private String vacancy1;
	private String vacancy2;
	private String vacancy3;
	private boolean applied1;
	private boolean applied2;
	private boolean applied3;
	private String appeal;
	private boolean appealStatus;

	public Applicant() {
		/**/}

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

	public String getVacancy1() {
		return vacancy1;
	}

	public void setVacancy1(String vacancy1) {
		this.vacancy1 = vacancy1;
	}

	public String getVacancy2() {
		return vacancy2;
	}

	public void setVacancy2(String vacancy2) {
		this.vacancy2 = vacancy2;
	}

	public String getVacancy3() {
		return vacancy3;
	}

	public void setVacancy3(String vacancy3) {
		this.vacancy3 = vacancy3;
	}

	public boolean isApplied1() {
		return applied1;
	}

	public void setApplied1(boolean applied1) {
		this.applied1 = applied1;
	}

	public boolean isApplied2() {
		return applied2;
	}

	public void setApplied2(boolean applied2) {
		this.applied2 = applied2;
	}

	public boolean isApplied3() {
		return applied3;
	}

	public void setApplied3(boolean applied3) {
		this.applied3 = applied3;
	}

	public String getAppeal() {
		return appeal;
	}

	public void setAppeal(String appeal) {
		this.appeal = appeal;
	}

	public boolean isAppealStatus() {
		return appealStatus;
	}

	public void setAppealStatus(boolean appealStatus) {
		this.appealStatus = appealStatus;
	}

	public PoolCriteria convertListToPref() {
		ListPrefConverter converter = new ListPrefConverter();
		return converter.convertListToPref(this.preferences);
	}

	public void convertPrefToList(PoolCriteria criteria) {
		ListPrefConverter converter = new ListPrefConverter();
		this.preferences = converter.convertPrefToList(criteria);
	}

	public int leftChoice() {
		if (!this.applied1) {
			return 1;
		} else if (!this.applied2) {
			return 2;
		} else if (!this.applied3) {
			return 3;
		} else {
			return 0;
		}
	}
}