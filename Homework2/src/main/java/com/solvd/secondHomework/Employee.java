package com.solvd.secondHomework;

public class Employee extends Person{
	
	public Employee(String firstName, String lastName, String dni, int creditScore, double salary) {
		super(firstName, lastName, dni, creditScore, salary);
	}
	private boolean isEmployee;
	private String workPlace;
	private String typeWork;
	
	public boolean isEmployee() {
		return isEmployee;
	}
	public void setEmployee(boolean isEmployee) {
		this.isEmployee = isEmployee;
	}
	public String getWorkPlace() {
		return workPlace;
	}
	public void setWorkPlace(String workPlace) {
		this.workPlace = workPlace;
	}
	public String getTypeWork() {
		return typeWork;
	}
	public void setTypeWork(String typeWork) {
		this.typeWork = typeWork;
	}

	@Override
	public String toString() {
		return this.getFirstName() + " " + this.getLastName() + " " + this.getDni() + " " + this.getCreditScore()  + " " + this.getSalary();
	}
	
	@Override
	public boolean equals(Object obj) {
	    return (obj instanceof Employee);
	}
	
	@Override
	public int hashCode(){
		return workPlace.hashCode();
	}
}
