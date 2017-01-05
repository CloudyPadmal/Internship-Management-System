package com.msd.items;

public class Appeal {

	private int id;
	private int vacancyID;
	private String vacancyName;
	private String indexNumber;
	private String currentNumber;
	private double gradedPoint;
	private double currentGradedPoint;
	private boolean attended;
	
	public Appeal() {/**/}
	
	public Appeal(int vacancyID, String indexNumber) {
		this.vacancyID = vacancyID;
		this.indexNumber = indexNumber;
	}
	
	public Appeal(int vacancyID, String indexNumber, boolean attended) {
		this.vacancyID = vacancyID;
		this.indexNumber = indexNumber;
		this.attended = attended;
	}

	public Appeal(int id, int vacancyID, String indexNumber, boolean attended) {
		this.id = id;
		this.vacancyID = vacancyID;
		this.indexNumber = indexNumber;
		this.attended = attended;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isAttended() {
		return attended;
	}

	public void setAttended(boolean attended) {
		this.attended = attended;
	}

	public int getVacancyID() {
		return vacancyID;
	}

	public void setVacancyID(int vacancyID) {
		this.vacancyID = vacancyID;
	}

	public double getGradedPoint() {
		return gradedPoint;
	}

	public void setGradedPoint(double gradedPoint) {
		this.gradedPoint = gradedPoint;
	}

	public String getIndexNumber() {
		return indexNumber;
	}

	public void setIndexNumber(String indexNumber) {
		this.indexNumber = indexNumber;
	}

	public String getVacancyName() {
		return vacancyName;
	}

	public void setVacancyName(String vacancyName) {
		this.vacancyName = vacancyName;
	}

	public String getCurrentNumber() {
		return currentNumber;
	}

	public void setCurrentNumber(String currentNumber) {
		this.currentNumber = currentNumber;
	}

	public double getCurrentGradedPoint() {
		return currentGradedPoint;
	}

	public void setCurrentGradedPoint(double currentGradedPoint) {
		this.currentGradedPoint = currentGradedPoint;
	}
}
