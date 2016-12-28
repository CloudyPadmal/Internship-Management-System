package com.msd.users;

import java.util.List;

public class Vacancy {

	private int vacancyID;
	private String vacancy;
	private String title;
	private String company;
	private int salary;
	private List<String> preferences;
	private String description_1;
	private String description_2;	
	
	public Vacancy() {/**/}
	
	public Vacancy(int vacancyID, String vacancy, String title, String company, int salary, List<String> preferences,
			String description_1, String description_2) {
		super();
		this.vacancyID = vacancyID;
		this.vacancy = vacancy;
		this.title = title;
		this.company = company;
		this.salary = salary;
		this.preferences = preferences;
		this.description_1 = description_1;
		this.description_2 = description_2;
	}

	public int getVacancyID() {
		return vacancyID;
	}

	public void setVacancyID(int vacancyID) {
		this.vacancyID = vacancyID;
	}
	
	public String getVacancy() {
		return vacancy;
	}

	public void setVacancy(String vacancy) {
		this.vacancy = vacancy;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
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

}