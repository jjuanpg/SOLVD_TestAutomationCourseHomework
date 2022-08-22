package com.solvd.secondHomework;

public class BranchOffice {

	private final String branch;
	private final int Employees;
	private final String address;
	
	public BranchOffice(String branch, String address, int employees){
		this.branch = branch;
		this.address = address;
		this.Employees = employees;
	}

	public String getBranch() {
		return branch;
	}
	public int getEmployees() {
		return Employees;
	}
	public String getAddress() {
		return address;
	}
	
}
