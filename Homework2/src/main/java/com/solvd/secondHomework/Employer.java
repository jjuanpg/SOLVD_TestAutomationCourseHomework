package com.solvd.secondHomework;

public class Employer extends Person {
	
	public Employer(String firstName, String lastName, String dni, int creditScore, double salary, double balance) {
		super(firstName, lastName, dni, creditScore, salary, balance);
	}
	private boolean isEmployer;
	private String company;
	private String positionInCompany;
	
	public boolean isEmployer() {
		return isEmployer;
	}
	public void setEmployer(boolean isEmployer) {
		this.isEmployer = isEmployer;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getPositionInCompany() {
		return positionInCompany;
	}
	public void setPositionInCompany(String positionInCompany) {
		this.positionInCompany = positionInCompany;
	}

	@Override
	public String toString() {
        return getFirstName()+" "+getLastName()+" "+getDni()+" "+getCreditScore()+" "+getSalary()+" "+getBalance();
    }

	@Override
	public boolean equals(Object obj) {
		return (obj instanceof Employer);
	}
	
	@Override
	public int hashCode(){
		return company.hashCode();
	}
}
