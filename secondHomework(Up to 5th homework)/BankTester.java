package secondHomework;
import java.util.*;
import java.io.*;
import java.util.concurrent.TimeUnit;

public class BankTester {
	
	static Scanner sc = new Scanner(System.in);
	static String typeA = "employer";
	static String typeB = "employee";
	static String typeC = "unemployed";
	static String creditCard;
	static int election;
	static int turn;
	static int loan;
	static boolean disabilitie;
	

	
	public static void main(String[] args) throws IOException, FileNotFoundException, InterruptedException {
		
		//Set the branch
		BranchOffice branch = new BranchOffice("Resistencia", "French414", 20);
		
		//Turn on systems
		Atm atm = new Atm();
		BankAlarm alarm = new BankAlarm();
		Cameras cameras = new Cameras();
		Computers computers = new Computers();
		MetalDetector detector = new MetalDetector();
		
		atm.on();
		alarm.off();
		cameras.cctvRecorder();
		System.out.println(cameras.getStatus());
		System.out.println(computers.connect());
		computers.on();
		System.out.println(computers.getStatus());
		detector.on();
		System.out.println("");
		
		//Check in employees of the bank
		Security security = new Security("Michael","Doe", 00432);
		Banker banker = new Banker("Hannah","Warren", 01333);
		
		//Check if client exist
		System.out.println("Enter the first name");
		String firstName = sc.next();
		System.out.println("Enter the last name");
		String lastName = sc.next();;
		System.out.println("Enter the client DNI");
		String dni = sc.next();
		boolean result = false;
		result = checkClient(dni);
	
		if(result){
			System.out.println("The client was found.");
		}else{
	    	System.out.println("The client do not exist.");
	    }
		
		//Else create a new one
		if(!result){
			
			//Get the type of client
			System.out.println(" ");
			System.out.println("Enter one of the next types of clients: employer(1) | employee(2) | unemployed(3)");
			election = sc.nextInt();
			String type = getType(election);
			
			//Set the client based on the type
			if(type.equals(typeA)){
				Employer employer = new Employer(firstName, lastName, dni);
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
				
				//Set turn
				int upperbound = 50;
				turn = setTurn(upperbound);
				
				//Check if disabilities advantage is needed
				System.out.println(" ");
				System.out.println("The client have a disabilitie?: YES=1 NO=0");//Set a priority turn if needed
				if(sc.nextInt() == 1){
					System.out.println("Enter the client handicap");
					String handicap = sc.next();
					
					PrioritaryClient client = new PrioritaryClient(handicap, employer.getAge());
					disabilitie = client.getDisabilitie(client.getDisabilitie(), client.getAge());
				}
				//Else wait for the turn
				if(disabilitie){
					turn = 1;
				}
				for(int i = 0; i < turn; i++){
					System.out.println("Actual turn: " + i + ", your turn: " + turn);
					TimeUnit.SECONDS.sleep(1);
				}
				System.out.println(""); 
				
				//menu with options of the bank
				System.out.println(" ");
				System.out.println("---------- MENU ----------");
				System.out.println("Take a loan: 1");
				System.out.println("Talk with the teller: 2");
				System.out.println("Apply for a credit card: 3");
				int menu = sc.nextInt();
				
				//Print the result
				if(menu == 1){
					System.out.println(" ");
					System.out.println("The available credits are: ");
					showCredits();
					election = sc.nextInt();
					if(election == 1){
						loan = 30000;
					}else if(election == 2){
						loan = 50000;
					}else if(election == 3){
						loan = 100000;
					}else{
						System.out.println("ERROR: Input mismatch");
					}
					
					boolean qualify = employer.isUserQualified(employer.getCreditScore(), employer.getSalary());
					if(qualify){
						System.out.println(" ");
						System.out.println(branch.getBranch() + " " + branch.getAddress());
						System.out.println("Attended by: " + banker.getFirstName() + ", " + banker.getLastName() + " " + banker.getID());
						System.out.println("We are happy to announce you " + employer.getFirstName() + ", " + employer.getLastName() + " " + employer.getDni() + " that the loan of $" + loan + " will be transferred to your account");
						System.out.println("If you want to withdraw now the security " + security.getFirstName() + " " + security.getID() + " will help you");
					}else{
						System.out.println("Sorry, you didn't qualify to one of our credit cards.");
					}
				}else if(menu == 2){
					System.out.println(" ");
					System.out.println("Enter an email to get in touch with a teller");
					String email = sc.next();
					System.out.println("Ok " + email + " on the day you will be receiving an email");
					
				}else if(menu == 3){
					System.out.println(" ");
					System.out.println("The available credit cards are: ");
					showCreditCards();
					election = sc.nextInt();
					if(election == 1){
						creditCard = "White card";
					}else if(election == 2){
						creditCard = "Black card";
					}else if(election == 3){
						creditCard = "Platinum card";
					}else{
						System.out.println("ERROR: Input mismatch");
					}
					
					boolean qualify = employer.isUserQualified(employer.getCreditScore(), employer.getSalary());
					if(qualify){
						System.out.println(" ");
						System.out.println(branch.getBranch() + " " + branch.getAddress());
						System.out.println("Attended by: " + banker.getFirstName() + ", " + banker.getLastName() + " " + banker.getID());
						System.out.println("We are happy to announce you " + employer.getFirstName() + ", " + employer.getLastName() + " " + employer.getDni() + " that your card " + creditCard + " will be to your home address");
					}else{
						System.out.println("Sorry, you didn't qualify to one of our credit cards.");
					}
				}
				
			}else if(type.equals(typeB)){
				Employee employee = new Employee(firstName, lastName, dni);
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
				
				//Set turn
				int upperbound = 50;
				turn = setTurn(upperbound);
				
				//Check if disabilities advantage is needed
				System.out.println(" ");
				System.out.println("The client have a disabilitie?: YES=1 NO=0");//Set a priority turn if needed
				if(sc.nextInt() == 1){
					System.out.println("Enter the client handicap");
					String handicap = sc.next();
					System.out.println(" ");
					
					PrioritaryClient client = new PrioritaryClient(handicap, employee.getAge());
					disabilitie = client.getDisabilitie(client.getDisabilitie(), client.getAge());
				}
				//Else wait for the turn
				if(disabilitie){
					turn = 1;
				}
				for(int i = 0; i < turn; i++){
					System.out.println("Actual turn: " + i + ", your turn: " + turn);
					TimeUnit.SECONDS.sleep(1);
				}
				
				//menu with options of the bank
				System.out.println(" ");
				System.out.println("---------- MENU ----------");
				System.out.println("Take a loan: 1");
				System.out.println("Talk with the teller: 2");
				System.out.println("Apply for a credit card: 3");
				int menu = sc.nextInt();
				
				//Print the result
				if(menu == 1){
					System.out.println(" ");
					System.out.println("The available credits are: ");
					showCredits();
					election = sc.nextInt();
					if(election == 1){
						loan = 30000;
					}else if(election == 2){
						loan = 50000;
					}else if(election == 3){
						loan = 100000;
					}else{
						System.out.println("ERROR: Input mismatch");
					}
					
					boolean qualify = employee.isUserQualified(employee.getCreditScore(), employee.getSalary());
					if(qualify){
						System.out.println(" ");
						System.out.println(branch.getBranch() + " " + branch.getAddress());
						System.out.println("Attended by: " + banker.getFirstName() + ", " + banker.getLastName() + banker.getID());
						System.out.println("We are happy to announce you " + employee.getFirstName() + ", " + employee.getLastName() + " " + employee.getDni() + " that the loan of $" + loan + " will be transferred to your account");
						System.out.println("If you want to withdraw now the security " + security.getFirstName() + " " + security.getID() + " will help you");
					}else{
						System.out.println("Sorry, you didn't qualify to one of our credit cards.");
					}
				}else if(menu == 2){
					System.out.println(" ");
					System.out.println("Enter an email to get in touch with a teller");
					String email = sc.next();
					System.out.println("Ok " + email + " on the day you will be receiving an email");
					
				}else if(menu == 3){
					System.out.println(" ");
					System.out.println("The available credit cards are: ");
					showCreditCards();
					election = sc.nextInt();
					if(election == 1){
						creditCard = "White card";
					}else if(election == 2){
						creditCard = "Black card";
					}else if(election == 3){
						creditCard = "Platinum card";
					}else{
						System.out.println("ERROR: Input mismatch");
					}
					
					boolean qualify = employee.isUserQualified(employee.getCreditScore(), employee.getSalary());
					if(qualify){
						System.out.println(" ");
						System.out.println(branch.getBranch() + " " + branch.getAddress());
						System.out.println("Attended by: " + banker.getFirstName() + ", " + banker.getLastName() + " " + banker.getID());
						System.out.println("We are happy to announce you " + employee.getFirstName() + ", " + employee.getLastName() + " " + employee.getDni() + " that your card " + creditCard + " will be to your home address");
					}else{
						System.out.println("Sorry, you didn't qualify to one of our credit cards.");
					}
				}
				
			}else{
				System.out.println("Sorry, you didnt qualify to one of our loans or credit cards.");
			}
			
		}
		
		if(result) {
			//Set turn
			int upperbound = 50;
			turn = setTurn(upperbound);
		
			//Check if disabilities advantage is needed
			System.out.println(" ");
			System.out.println("The client have a disabilitie?: YES=1 NO=0"); //Set a priority turn if needed
			if(sc.nextInt() == 1){
				System.out.println("Enter the client handicap");
				String handicap = sc.next();
				System.out.println("Enter the client age");
				int age = sc.nextInt();
			
				PrioritaryClient client = new PrioritaryClient(handicap, age);
				disabilitie = client.getDisabilitie(client.getDisabilitie(), client.getAge());
			}
			//Else wait for the turn
			if(disabilitie){
				turn = 1;
			}
			System.out.println(" ");
			for(int i = 0; i < turn; i++){
				System.out.println("Actual turn: " + i + ", your turn: " + turn);
				TimeUnit.SECONDS.sleep(1);
			}
		
			//menu with options of the bank
			System.out.println(" ");
			System.out.println("---------- MENU ----------");
			System.out.println("Take a loan: 1");
			System.out.println("Talk with the teller: 2");
			System.out.println("Apply for a credit card: 3");
			int menu = sc.nextInt();
		
			//Print the result
			if(menu == 1){
				System.out.println(" ");
				System.out.println("The available credits are: ");
				showCredits();
				election = sc.nextInt();
				if(election == 1){
					loan = 30000;
				}else if(election == 2){
					loan = 50000;
				}else if(election == 3){
					loan = 100000;
				}else{
					System.out.println("ERROR: Input mismatch");
				}
				
				System.out.println(" ");
				System.out.println(branch.getBranch() + " " + branch.getAddress());
				System.out.println("Attended by: " + banker.getFirstName() + ", " + banker.getLastName() + " " + banker.getID());
				System.out.println("We are happy to announce you " + firstName + ", " + lastName + " " + dni + " that the loan of $" + loan + " will be transferred to your account");
				System.out.println("If you want to withdraw now the security " + security.getFirstName() + " " + security.getID() + " will help you");
				
			}else if(menu == 2){
				System.out.println(" ");
				System.out.println("Enter an email to get in touch with a teller");
				String email = sc.next();
				System.out.println("Ok " + email + " on the day you will be receiving an email");
				
			}else if(menu == 3){
				System.out.println(" ");
				System.out.println("The available credit cards are: ");
				showCreditCards();
				election = sc.nextInt();
				if(election == 1){
					creditCard = "White card";
				}else if(election == 2){
					creditCard = "Black card";
				}else if(election == 3){
					creditCard = "Platinum card";
				}else{
					System.out.println("ERROR: Input mismatch");
				}
				
				System.out.println(" ");
				System.out.println(branch.getBranch() + " " + branch.getAddress());
				System.out.println("Attended by: " + banker.getFirstName() + ", " + banker.getLastName() + banker.getID());
				System.out.println("We are happy to announce you " + firstName + lastName + dni + " that your card " + creditCard + " will be to your home address");
			}
		}
	}
	
	public static boolean checkClient(String dni) throws IOException{
		try {
			File file = new File("E:\\Eclipse-Workspace\\Homeworks\\src\\secondHomework\\resources\\clients.txt");
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line;
			while ((line = br.readLine()) != null) {
			    if(line.equals(dni)){
			    	return true;
			    }
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static String getType(int election){
		switch(election){
			case 1: return "employer";
			case 2: return "employee";
			default: return "unemployed";
		}	
	}
	
	public static void showCredits(){
		System.out.println(" $30.000  (1)");
		System.out.println(" $50.000  (2)");
		System.out.println(" $100.000 (3)");
	}
	
	public static void showCreditCards(){
		System.out.println(" White Card with a limit of $3.000 per month  (1)");
		System.out.println(" Black Card with a limit of $10.000 per month (2)");
		System.out.println(" Platinum Card without limits per month       (3)");
	}
	
	
	public static int setTurn(int upperbound){
		Random rand = new Random(); //instance of random class
		int int_random = rand.nextInt(upperbound);
		return int_random;
	}
}