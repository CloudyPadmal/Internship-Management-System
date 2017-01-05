package com.msd.items;

public class User {

	private int id;
	private String name;
	private String gender;
	private String password;
	private String confirmPassword;
	private String vacancy1;
	private String vacancy2;
	private String vacancy3;
	private boolean applied1;
	private boolean applied2;
	private boolean applied3;

	// Default constructor
	public User() {/**/}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
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
}
