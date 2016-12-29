package com.msd.items;

public class Company extends User {

	private String loginID;
	private String company;
	private String address;
	private String emailAddress;
	private String telephone;
	private String aboutUs;
	private int positions;

	
	public Company() {/**/}
	
	public Company(String loginID, String company, String address, String emailAddress, String telephone,
			String aboutUs, int positions) {
		super();
		this.loginID = loginID;
		this.company = company;
		this.address = address;
		this.emailAddress = emailAddress;
		this.telephone = telephone;
		this.aboutUs = aboutUs;
		this.positions = positions;
	}

	public String getLoginID() {
		return loginID;
	}

	public void setLoginID(String loginID) {
		this.loginID = loginID;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public String getAboutUs() {
		return aboutUs;
	}

	public void setAboutUs(String aboutUs) {
		this.aboutUs = aboutUs;
	}

	public int getPositions() {
		return positions;
	}

	public void setPositions(int positions) {
		this.positions = positions;
	}
}
