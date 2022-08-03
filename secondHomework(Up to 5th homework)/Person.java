package secondHomework;

public abstract class Person {
		
		final double REQUIREDSALARY = 25000;
		final int REQUIREDSCORE = 700;
		private final String firstName;
		private final String lastName;
		private final String dni;
		private int age;
		private String gender;
		private double salary;
		private int creditScore;
		
		public Person(String firstName, String lastName, String dni){
			this.firstName = firstName;
			this.lastName = lastName;
			this.dni = dni;
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

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}

		public String getGender() {
			return gender;
		}

		public void setGender(String gender) {
			this.gender = gender;
		}

		public double getSalary() {
			return salary;
		}

		public void setSalary(double salary) {
			this.salary = salary;
		}

		public int getCreditScore() {
			return creditScore;
		}

		public void setCreditScore(int creditScore) {
			this.creditScore = creditScore;
		}

		public boolean isUserQualified(int score, double salary){
			if(score >= REQUIREDSCORE && salary >= REQUIREDSALARY){
				return true;
			}
			return false;
		}
		
}
