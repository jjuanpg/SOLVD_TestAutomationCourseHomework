package secondHomework;
import java.util.*;

public class CreditTester {
	static Scanner sc = new Scanner(System.in);
	static double requiredSalary = 25000;
	static int requiredScore = 700;
	static String typeA = "employer";
	static String typeB = "employee";
	static String typeC = "unemployed";
	static int election;
	
	public static void main(String[] args) {
		
		//Get the type of client
		System.out.println("Enter one of the next types of clients: employer(1) | employee(2) | unemployed(3)");
		election = sc.nextInt();
		String type = getType(election);
		
		//Set the client based on the type
		if(type.equals(typeA)){
			Employer employer = new Employer();
			System.out.println("Enter the name of the client");
			employer.setName(sc.next());
			System.out.println("Enter the age of the client");
			employer.setAge(sc.nextInt());
			System.out.println("Enter the gender of the client");
			employer.setGender(sc.next());
			System.out.println("Enter the salary of the client");
			employer.setSalary(sc.nextDouble());
			System.out.println("Enter the credit score of the client");
			employer.setCreditScore(sc.nextInt());
			employer.setEmployer(true);
			System.out.println("Enter the company that the client work for");
			employer.setCompany(sc.next());
			System.out.println("Enter the position of the client at the company");
			employer.setPositionInCompany(sc.next());
			
			//Check if the user qualify
			boolean result = isUserQualified(employer.getCreditScore(), employer.getSalary());
			if(result){
				System.out.println(employer.toString());
				showCredits();
			}else{
				System.out.println("Sorry, you didnt qualify to one of our credits.");
			}
			
		}else if(type.equals(typeB)){
			Employee employee = new Employee();
			System.out.println("Enter the name of the client");
			employee.setName(sc.next());
			System.out.println("Enter the age of the client");
			employee.setAge(sc.nextInt());
			System.out.println("Enter the gender of the client");
			employee.setGender(sc.next());
			System.out.println("Enter the salary of the client");
			employee.setSalary(sc.nextDouble());
			System.out.println("Enter the credit score of the client");
			employee.setCreditScore(sc.nextInt());
			employee.setEmployee(true);
			System.out.println("Enter the company that the client work for");
			employee.setWorkPlace(sc.next());
			System.out.println("Enter the type of work that the client does at the company");
			employee.setTypeWork(sc.next());
			
			//Check if the user qualify
			boolean result = isUserQualified(employee.getCreditScore(), employee.getSalary());
			if(result){
				System.out.println(employee.toString());
				showCredits();
			}else{
				System.out.println("Sorry, you didnt qualify to one of our credits.");
			}
			
		}else{
			System.out.println("Sorry, you didnt qualify to one of our credits.");
		}
	}
	
	public static String getType(int election){
		switch(election){
			case 1: return "employer";
			case 2: return "employee";
			default: return "unemployed";
		}	
	}
	
	public static boolean isUserQualified(int score, double salary){
		if(score >= requiredScore && salary >= requiredSalary){
			return true;
		}
		return false;
	}

	public static void showCredits(){
		System.out.println(" $30.000");
		System.out.println(" $50.000");
		System.out.println(" $100.000");
	}
}