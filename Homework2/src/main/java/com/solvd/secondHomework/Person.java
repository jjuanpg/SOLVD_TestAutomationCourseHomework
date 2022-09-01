package com.solvd.secondHomework;

public abstract class Person {
		
		final double REQUIREDSALARY = 25000;
		final int REQUIREDSCORE = 700;
		private final String firstName;
		private final String lastName;
		private final String dni;
		private int age;
		private int creditScore;
		private String gender;
		private double salary;
		private double balance;

		
		public Person(String firstName, String lastName, String dni, int creditScore, double salary, double balance){
			this.firstName = firstName;
			this.lastName = lastName;
			this.dni = dni;
			this.creditScore = creditScore;
			this.salary = salary;
			this.balance = balance;
		}
		
		public String getFirstName() {
			return firstName;
		}
		public String getLastName() {
			return lastName;
		}
		public String getDni() {
			return dni;
		}

		public void setBalance(Double balance){
			this.balance = balance;
		}

		public Double getBalance(){
			return balance;
		}

		public int getAge() {
			return age;
		}

		public String getGender() {
			return gender;
		}

		public double getSalary() {
			return salary;
		}

		public int getCreditScore() {
			return creditScore;
		}

		public boolean isUserQualified(int score, double salary){
			return score >= REQUIREDSCORE && salary >= REQUIREDSALARY;
		}
}
