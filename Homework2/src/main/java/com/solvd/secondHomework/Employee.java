package com.solvd.secondHomework;

public class Employee extends Person{
	
	public Employee(String firstName, String lastName, String dni, int creditScore, double salary, double balance) {
		super(firstName, lastName, dni, creditScore, salary, balance);
	}

	@Override
	public String toString() {
		return this.getFirstName()+" "+this.getLastName()+" "+this.getDni()+" "+this.getCreditScore()+" "+this.getSalary()+" "+this.getBalance();
	}
	
	@Override
	public boolean equals(Object obj) {
	    return (obj instanceof Employee);
	}
	
	@Override
	public int hashCode(){
		return getDni().hashCode();
	}
}
