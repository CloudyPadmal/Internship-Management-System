package com.msd.items;

import java.util.List;

import com.msd.pool.items.PoolCriteria;

public class Vacancy {

	private int id;
	private String title;
	private String companyName;
	private String companyID;
	private int salary;
	private List<String> preferences;
	private String description_1;
	private String description_2;
	private String applicant;
	private int choice;
	private boolean open;

	public Vacancy() {/**/}
	
	public Vacancy(String companyID, String applicant, boolean open) {
		this.companyID = companyID;
		this.applicant = applicant;
		this.open = open;
	}

	public Vacancy(String title, String companyID, int salary, String description_1,
			String description_2) {
		this.title = title;
		this.companyID = companyID;
		this.salary = salary;
		this.description_1 = description_1;
		this.description_2 = description_2;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyID() {
		return companyID;
	}

	public void setCompanyID(String companyID) {
		this.companyID = companyID;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public List<String> getPreferences() {
		return preferences;
	}

	public void setPreferences(List<String> preferences) {
		this.preferences = preferences;
	}

	public String getDescription_1() {
		return description_1;
	}

	public void setDescription_1(String description_1) {
		this.description_1 = description_1;
	}

	public String getDescription_2() {
		return description_2;
	}

	public void setDescription_2(String description_2) {
		this.description_2 = description_2;
	}

	public String getApplicant() {
		return applicant;
	}

	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}

	public int getChoice() {
		return choice;
	}

	public void setChoice(int choice) {
		this.choice = choice;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
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