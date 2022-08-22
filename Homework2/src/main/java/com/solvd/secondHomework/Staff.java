package com.solvd.secondHomework;

public abstract class Staff {

	private final String staffFirstName;
	private final String staffLastName;
	private final int staffID;
	
	public Staff(String firstName, String lastName){
		this.staffFirstName = firstName;
		this.staffLastName = lastName;
		this.staffID = hashCode();
	}
	public String getFirstName() {
		return staffFirstName;
	}
	public String getLastName() {
		return staffLastName;
	}
	public int getID() {
		return staffID;
	}
}
