package com.solvd.secondHomework;

public class Employer extends Person {
	
	public Employer(String firstName, String lastName, String dni, int creditScore, double salary, double balance) {
		super(firstName, lastName, dni, creditScore, salary, balance);
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
		return getDni().hashCode();
	}
}
