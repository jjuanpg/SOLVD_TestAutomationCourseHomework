package secondHomework;

public class Employer extends Client {
	
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
        return "Hi!" + this.getName() + " we are happy to announce you that with a score of " + this.getCreditScore() + " and the salary of " + this.getSalary() + " you qualify for the nexts credits.";
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
