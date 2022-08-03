package secondHomework;

public class Employee extends Person{
	
	public Employee(String firstName, String lastName, String dni) {
		super(firstName, lastName, dni);
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
		return "Hi!" + this.getFirstName() + " we are happy to announce you that with a score of " + this.getCreditScore() + " and the salary of " + this.getSalary() + " you qualify for the nexts credits:";
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
