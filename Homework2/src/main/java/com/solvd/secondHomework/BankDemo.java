package com.solvd.secondHomework;
import com.solvd.secondHomework.Exceptions.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.*;
import java.util.*;
import java.io.Serializable;

@SuppressWarnings("FieldCanBeLocal")
public class BankDemo implements Serializable {

    final static String typeA = "employer";
    final static String typeB = "employee";
    static String creditCard;
    static int loan;

    private static Logger logger = LogManager.getLogger(BankDemo.class);
    private static final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    public static void main(String[] args) throws IOException, UnemployedClientException, BankAlarmException, ComputersOffException, InputMismatchFoundException, MinimumRequirementsException, InvalidEmailException {
        Date date = new Date();
        logger = LogManager.getRootLogger();
        logger.debug("---------------" + formatter.format(date) + "---------------");

        //Set the file
        final String employerFile = "E:\\INTELLIJ_COURSES\\Homework2\\src\\main\\resources\\Employers.txt";
        final String employeeFile = "E:\\INTELLIJ_COURSES\\Homework2\\src\\main\\resources\\Employee.txt";

        //Set the branch
        BranchOffice branch = new BranchOffice("Resistencia", "French414", 20);

        //Turn on systems
        Atm atm = new Atm();
        BankAlarm alarm = new BankAlarm();
        Cameras cameras = new Cameras();
        Computers computers = new Computers();
        MetalDetector detector = new MetalDetector();

        logger.info(atm.on());
        logger.info(alarm.off());
        if (alarm.getStatus()) {
            logger.fatal("The alarm status is true.");
            throw new BankAlarmException("Alarm status should be false.");
        }
        logger.info(cameras.cctvRecorder());
        logger.info(cameras.getStatus());
        logger.info(computers.connect());
        logger.info(computers.on());
        if (!computers.getStatus()) {
            logger.fatal("The computers status is false.");
            throw new ComputersOffException("Computers status should be true.");
        }
        logger.info(detector.on());

        //Check in employees of the bank
        Security security = new Security("Michael", "Doe");
        Banker banker = new Banker("Hannah", "Warren");

        int choice;
        Scanner sc = new Scanner(System.in);
        Scanner sc1 = new Scanner(System.in);

        //Employer file
        BufferedReader br = new BufferedReader(new FileReader(employerFile));
        CustomLinkedList<Employer> employerList = new CustomLinkedList<>();

        //Employee file
        BufferedReader br1 = new BufferedReader(new FileReader(employeeFile));
        CustomLinkedList<Employee> employeeList = new CustomLinkedList<>();

        do {
            logger.info("""
                    \n--------------Menu--------------
                    1. Create new client
                    2. Display file
                    3. Read from file
                    4. Display updated linked lists
                    5. Check if Client exist
                    6. Unsubscribe bank account
                    0. Exit
                    --------------------------------
                    Enter your choice:\040
                    """);
            choice = sc.nextInt();
            switch (choice) {
                case 1 -> {
                    //Set the basics for create a new client
                    logger.info("Enter Client First Name: ");
                    String firstName = sc1.next();
                    logger.info("Enter Client Last Name: ");
                    String lastName = sc1.next();
                    logger.info("Enter Client DNI: ");
                    String dni = sc1.next();
                    logger.info("Enter Client Credit Score: ");
                    int creditScore = sc.nextInt();
                    logger.info("Enter Client Salary: ");
                    double salary = sc.nextDouble();
                    logger.info("Enter Client Deposit: ");
                    double balance = sc.nextDouble();
                    //Get type of client
                    logger.info("""
                            \n--------------Menu--------------
                            1. Employer
                            2. Employee
                            3. Unemployed
                            --------------------------------
                            Enter the type of client:\040
                            """);
                    int election = sc.nextInt();
                    String type = StaticMethods.getType(election);

                    //Create te new client based on the type
                    String format = firstName + "|" + lastName + "|" + dni + "|" + creditScore + "|" + salary + "|" + balance;
                    if (type.equals(typeA)) {
                        employerList.insert(new Employer(firstName, lastName, dni, creditScore, salary, balance));
                        StaticMethods.saveToFile(employerFile, format, true);
                    } else if (type.equals(typeB)) {
                        employeeList.insert(new Employee(firstName, lastName, dni, creditScore, salary, balance));
                        StaticMethods.saveToFile(employeeFile, format, true);
                    }
                }
                case 2 -> {
                    logger.info("""
                            \n--------------Menu--------------
                            1. Employer file
                            2. Employee file
                            --------------------------------
                            Enter the type of file:\040
                            """);
                    int election = sc.nextInt();
                    if (election == 1) {
                        String line;
                        logger.info("-----------------------------------");
                        while ((line = br.readLine()) != null) {
                            logger.info(line);
                        }
                        logger.info("-----------------------------------");
                    } else if (election == 2) {
                        String line;
                        logger.info("-----------------------------------");
                        while ((line = br1.readLine()) != null) {
                            logger.info(line);
                        }
                        logger.info("-----------------------------------");
                    } else {
                        logger.error("ERROR: Input Mismatch.");
                        throw new InputMismatchFoundException("Could not find the selected election " + election);
                    }
                }
                case 3 -> {
                    employerList = StaticMethods.employersFromFile(employerFile);
                    employeeList = StaticMethods.employeesFromFile(employeeFile);
                }
                case 4 -> {
                    logger.info("""
                            \n--------------Menu--------------
                            1. Employers list
                            2. Employee list
                            --------------------------------
                            Enter the type of list:\040
                            """);
                    int election = sc.nextInt();
                    if (election == 1) {
                        employerList.show();
                    } else if (election == 2) {
                        employeeList.show();
                    } else {
                        logger.error("ERROR: Input Mismatch.");
                        throw new InputMismatchFoundException("Could not find the selected election " + election);
                    }
                }
                case 5 -> {
                    logger.info("Enter Client DNI: ");
                    String DNI = sc1.next();
                    boolean res = StaticMethods.checkClient(employerFile, DNI);
                    boolean res1 = StaticMethods.checkClient(employeeFile, DNI);

                    if (res) {
                        logger.info("The employer client was found");
                        Employer employer = StaticMethods.returnEmployer(employerFile, DNI);
                        int menu;

                        do{
                            //menu with options of the bank
                            logger.info(StaticMethods.showMenu());
                            menu = sc.nextInt();

                            //Print the result
                            if (menu == 1) {
                                logger.info("\r\nThe available credits are: ");
                                logger.info(StaticMethods.showCredits());
                                int election = sc.nextInt();
                                if (election == 1) {
                                    loan = 30000;
                                } else if (election == 2) {
                                    loan = 50000;
                                } else if (election == 3) {
                                    loan = 100000;
                                } else {
                                    logger.error("ERROR: Input Mismatch.");
                                    throw new InputMismatchFoundException("Could not find the selected election " + election);
                                }

                                assert employer != null;
                                boolean qualify = employer.isUserQualified(employer.getCreditScore(), employer.getSalary());
                                if (qualify) {
                                    logger.info("\r\n" + branch.getBranch() + " " + branch.getAddress());
                                    logger.info("Attended by: " + banker.getFirstName() + ", " + banker.getLastName() + " ID: " + banker.getID());
                                    logger.info("We are happy to announce you " + employer.getFirstName() + ", " + employer.getLastName() + " " + employer.getDni() + " that the loan of $" + loan + " will be transferred to your account");
                                    logger.info("If you want to withdraw now the security " + security.getFirstName() + " ID: " + security.getID() + " will help you");
                                } else {
                                    logger.warn("Minimum requirements not met");
                                    throw new MinimumRequirementsException("The client do not met the requirements " + employer.getFirstName());
                                }
                            } else if (menu == 2) {
                                logger.info("\r\nEnter an email to get in touch with a teller");
                                String email = sc.next();
                                boolean validate = StaticMethods.validate(email);
                                if (validate) {
                                    logger.info("Ok " + email + " on the day you will be receiving an email");
                                    StaticMethods.sendEmail(email);
                                } else {
                                    logger.warn("Invalid email type");
                                    throw new InvalidEmailException("ERROR: Invalid email " + email);
                                }
                            } else if (menu == 3) {
                                logger.info("\r\nThe available credit cards are: ");
                                logger.info(StaticMethods.showCreditCards());
                                int election = sc.nextInt();
                                if (election == 1) {
                                    creditCard = "White card";
                                } else if (election == 2) {
                                    creditCard = "Black card";
                                } else if (election == 3) {
                                    creditCard = "Platinum card";
                                } else {
                                    logger.error("ERROR: Input Mismatch.");
                                    throw new InputMismatchFoundException("Could not find the selected election " + election);
                                }

                                assert employer != null;
                                boolean qualify = employer.isUserQualified(employer.getCreditScore(), employer.getSalary());
                                if (qualify) {
                                    logger.info("\r\n" + branch.getBranch() + " " + branch.getAddress());
                                    logger.info("Attended by: " + banker.getFirstName() + ", " + banker.getLastName() + " ID: " + banker.getID());
                                    logger.info("We are happy to announce you " + employer.getFirstName() + ", " + employer.getLastName() + " " + employer.getDni() + " that your " + creditCard + " will be to your home address");
                                } else {
                                    logger.fatal("Minimum requirements not met");
                                    throw new MinimumRequirementsException("The client do not met the requirements " + employer.getFirstName());
                                }
                            } else if (menu == 4) {
                                assert employer != null;
                                logger.info("\nThe balance of the account is: "+employer.getBalance());
                            } else if (menu == 5) {
                                logger.info("\nEnter the client amount to withdraw: ");
                                Double amount = sc.nextDouble();
                                assert employer != null;
                                Double balance = employer.getBalance();
                                employer.setBalance(balance-amount);

                                logger.info("\r\n" + branch.getBranch() + " " + branch.getAddress());
                                logger.info("Attended by: " + banker.getFirstName() + ", " + banker.getLastName() + " ID: " + banker.getID());
                                logger.info("The remaining balance in the account is: " + employer.getBalance());

                                ArrayList<Employer> employers;
                                employers = (StaticMethods.employersArray(employerFile));
                                int index = StaticMethods.getIndex(employer.getDni());
                                employers.remove(index);
                                employers.add(index, employer);

                                String outputText = employers.get(0).getFirstName()+ "|" + employers.get(0).getLastName() + "|" + employers.get(0).getDni() + "|" + employers.get(0).getCreditScore() + "|" + employers.get(0).getSalary() + "|" + employers.get(0).getBalance();
                                StaticMethods.saveToFile(employerFile, outputText, false);
                                for (int i = 1; i < employers.size(); i++) {
                                    outputText = employers.get(i).getFirstName() + "|" + employers.get(i).getLastName() + "|" + employers.get(i).getDni() + "|" + employers.get(i).getCreditScore() + "|" + employers.get(i).getSalary() + "|" + employers.get(i).getBalance();
                                    StaticMethods.saveToFile(employerFile, outputText, true);
                                }
                            } else if (menu == 6) {
                                logger.info("\nEnter the client amount to deposit: ");
                                Double amount = sc.nextDouble();
                                assert employer != null;
                                Double balance = employer.getBalance();
                                employer.setBalance(balance+amount);

                                logger.info("\r\n" + branch.getBranch() + " " + branch.getAddress());
                                logger.info("Attended by: " + banker.getFirstName() + ", " + banker.getLastName() + " ID: " + banker.getID());
                                logger.info("The remaining balance in the account is: " + employer.getBalance());

                                ArrayList<Employer> employers;
                                employers = (StaticMethods.employersArray(employerFile));
                                int index = StaticMethods.getIndex(employer.getDni());
                                employers.remove(index);
                                employers.add(index, employer);

                                String outputText = employers.get(0).getFirstName()+ "|" + employers.get(0).getLastName() + "|" + employers.get(0).getDni() + "|" + employers.get(0).getCreditScore() + "|" + employers.get(0).getSalary() + "|" + employers.get(0).getBalance();
                                StaticMethods.saveToFile(employerFile, outputText, false);
                                for (int i = 1; i < employers.size(); i++) {
                                    outputText = employers.get(i).getFirstName() + "|" + employers.get(i).getLastName() + "|" + employers.get(i).getDni() + "|" + employers.get(i).getCreditScore() + "|" + employers.get(i).getSalary() + "|" + employers.get(i).getBalance();
                                    StaticMethods.saveToFile(employerFile, outputText, true);
                                }
                            }
                        }while(menu != 0);
                    }

                    if (res1) {
                        logger.info("The employee client exist");
                        Employee employee = StaticMethods.returnEmployee(employeeFile, DNI);
                        int menu;

                        do{
                            //menu with options of the bank
                            logger.info(StaticMethods.showMenu());
                            menu = sc.nextInt();

                            //Print the result
                            if (menu == 1) {
                                logger.info("\r\nThe available credits are: ");
                                logger.info(StaticMethods.showCredits());
                                int election = sc.nextInt();
                                if (election == 1) {
                                    loan = 30000;
                                } else if (election == 2) {
                                    loan = 50000;
                                } else if (election == 3) {
                                    loan = 100000;
                                } else {
                                    logger.error("ERROR: Input Mismatch.");
                                    throw new InputMismatchFoundException("Could not find the selected election " + election);
                                }

                                assert employee != null;
                                boolean qualify = employee.isUserQualified(employee.getCreditScore(), employee.getSalary());
                                if (qualify) {
                                    logger.info("\r\n" + branch.getBranch() + " " + branch.getAddress());
                                    logger.info("Attended by: " + banker.getFirstName() + ", " + banker.getLastName() + " ID: " + banker.getID());
                                    logger.info("We are happy to announce you " + employee.getFirstName() + ", " + employee.getLastName() + " " + employee.getDni() + " that the loan of $" + loan + " will be transferred to your account");
                                    logger.info("If you want to withdraw now the security " + security.getFirstName() + " ID: " + security.getID() + " will help you");
                                } else {
                                    logger.warn("Minimum requirements not met");
                                    throw new MinimumRequirementsException("The client do not met the requirements " + employee.getFirstName());
                                }

                            } else if (menu == 2) {
                                logger.info("\r\nEnter an email to get in touch with a teller");
                                String email = sc.next();
                                boolean validate = StaticMethods.validate(email);
                                if (validate) {
                                    logger.info("Ok " + email + " on the day you will be receiving an email");
                                    StaticMethods.sendEmail(email);
                                } else {
                                    logger.warn("Invalid email type");
                                    throw new InvalidEmailException("ERROR: Invalid email " + email);
                                }

                            } else if (menu == 3) {
                                logger.info("\r\nThe available credit cards are: ");
                                logger.info(StaticMethods.showCreditCards());
                                int election = sc.nextInt();
                                if (election == 1) {
                                    creditCard = "White card";
                                } else if (election == 2) {
                                    creditCard = "Black card";
                                } else if (election == 3) {
                                    creditCard = "Platinum card";
                                } else {
                                    logger.error("ERROR: Input Mismatch.");
                                    throw new InputMismatchFoundException("Could not find the selected election " + election);
                                }

                                assert employee != null;
                                boolean qualify = employee.isUserQualified(employee.getCreditScore(), employee.getSalary());
                                if (qualify) {
                                    logger.info("\r\n" + branch.getBranch() + " " + branch.getAddress());
                                    logger.info("Attended by: " + banker.getFirstName() + ", " + banker.getLastName() + " ID: " + banker.getID());
                                    logger.info("We are happy to announce you " + employee.getFirstName() + ", " + employee.getLastName() + " " + employee.getDni() + " that your " + creditCard + " will be to your home address");
                                } else {
                                    logger.warn("Minimum requirements not met");
                                    throw new MinimumRequirementsException("The client do not met the requirements " + employee.getFirstName());
                                }
                            } else if (menu == 4) {
                                assert employee != null;
                                logger.info("\nThe balance of the account is: "+employee.getBalance());
                            } else if (menu == 5) {
                                logger.info("\nEnter the client amount to withdraw: ");
                                Double amount = sc.nextDouble();
                                assert employee != null;
                                Double balance = employee.getBalance();
                                employee.setBalance(balance-amount);

                                logger.info("\r\n" + branch.getBranch() + " " + branch.getAddress());
                                logger.info("Attended by: " + banker.getFirstName() + ", " + banker.getLastName() + " ID: " + banker.getID());
                                logger.info("The remaining balance in the account is: " + employee.getBalance());

                                ArrayList<Employee> employeeArray;
                                employeeArray = (StaticMethods.employeeArray(employeeFile));
                                int index = StaticMethods.getIndex(employee.getDni());
                                employeeArray.remove(index);
                                employeeArray.add(index, employee);

                                String outputText = employeeArray.get(0).getFirstName()+ "|" + employeeArray.get(0).getLastName() + "|" + employeeArray.get(0).getDni() + "|" + employeeArray.get(0).getCreditScore() + "|" + employeeArray.get(0).getSalary() + "|" + employeeArray.get(0).getBalance();
                                StaticMethods.saveToFile(employeeFile, outputText, false);
                                for (int i = 1; i < employeeArray.size(); i++) {
                                    outputText = employeeArray.get(i).getFirstName() + "|" + employeeArray.get(i).getLastName() + "|" + employeeArray.get(i).getDni() + "|" + employeeArray.get(i).getCreditScore() + "|" + employeeArray.get(i).getSalary() + "|" + employeeArray.get(i).getBalance();
                                    StaticMethods.saveToFile(employeeFile, outputText, true);
                                }
                            } else if (menu == 6) {
                                logger.info("\nEnter the client amount to deposit: ");
                                Double amount = sc.nextDouble();
                                assert employee != null;
                                Double balance = employee.getBalance();
                                employee.setBalance(balance+amount);

                                logger.info("\r\n" + branch.getBranch() + " " + branch.getAddress());
                                logger.info("Attended by: " + banker.getFirstName() + ", " + banker.getLastName() + " ID: " + banker.getID());
                                logger.info("The remaining balance in the account is: " + employee.getBalance());

                                ArrayList<Employee> employeeArray;
                                employeeArray = (StaticMethods.employeeArray(employeeFile));
                                int index = StaticMethods.getIndex(employee.getDni());
                                employeeArray.remove(index);
                                employeeArray.add(index, employee);

                                String outputText = employeeArray.get(0).getFirstName()+ "|" + employeeArray.get(0).getLastName() + "|" + employeeArray.get(0).getDni() + "|" + employeeArray.get(0).getCreditScore() + "|" + employeeArray.get(0).getSalary() + "|" + employeeArray.get(0).getBalance();
                                StaticMethods.saveToFile(employeeFile, outputText, false);
                                for (int i = 1; i < employeeArray.size(); i++) {
                                    outputText = employeeArray.get(i).getFirstName() + "|" + employeeArray.get(i).getLastName() + "|" + employeeArray.get(i).getDni() + "|" + employeeArray.get(i).getCreditScore() + "|" + employeeArray.get(i).getSalary() + "|" + employeeArray.get(i).getBalance();
                                    StaticMethods.saveToFile(employeeFile, outputText, true);
                                }
                            }
                        }while(menu != 0);
                    }
                    if (!res && !res1){
                        logger.error("You have to create a new client");
                        throw new ClientNotFoundException("Could not find client with DNI " + DNI);
                    }
                }
                case 6 -> {
                    logger.info("Enter Client DNI: ");
                    String DNI = sc1.next();
                    boolean res = StaticMethods.checkClient(employerFile, DNI);
                    boolean res1 = StaticMethods.checkClient(employeeFile, DNI);

                    if(res){
                        logger.info("The employer client was found");
                        Employer employer = StaticMethods.returnEmployer(employerFile, DNI);

                        ArrayList<Employer> employerArray;
                        employerArray = (StaticMethods.employersArray(employerFile));
                        assert employer != null;
                        int index = StaticMethods.getIndex(employer.getDni());
                        employerArray.remove(index);

                        PrintWriter pw = new PrintWriter(employerFile);
                        pw.close();

                        /*
                        for(Employer value : employerArray){
                            String outputText = value.getFirstName() + "|" + value.getLastName() + "|" + value.getDni() + "|" + value.getCreditScore() + "|" + value.getSalary() + "|" + value.getBalance();
                            StaticMethods.saveToFile(employerFile, outputText, true);
                        }
                         */
                        //Replace for loop with lambda expression
                        employerArray.stream().map(value -> value.getFirstName() + "|" + value.getLastName() + "|" + value.getDni() + "|" + value.getCreditScore() + "|" + value.getSalary() + "|" + value.getBalance()).forEach(e -> StaticMethods.saveToFile(employerFile, e, true));

                    }
                    if(res1){
                        logger.info("The employee client was found");
                        Employee employee = StaticMethods.returnEmployee(employeeFile, DNI);

                        ArrayList<Employee> employeeArray;
                        employeeArray = (StaticMethods.employeeArray(employeeFile));
                        assert employee != null; //Java assert
                        //Assert.assertNotNull(employee, "This object should not be null"); //TestNG assert
                        int index = StaticMethods.getIndex(employee.getDni());
                        employeeArray.remove(index);

                        //Delete the content of the txt file
                        PrintWriter pw = new PrintWriter(employeeFile);
                        pw.close();

                        //Rewrite the update content
                        employeeArray.stream().map(value -> value.getFirstName() + "|" + value.getLastName() + "|" + value.getDni() + "|" + value.getCreditScore() + "|" + value.getSalary() + "|" + value.getBalance()).forEach(e -> StaticMethods.saveToFile(employeeFile, e, true));

                    }
                    if(!res && !res1){
                        logger.error("You have to create a new client");
                        throw new ClientNotFoundException("Could not find client with DNI " + DNI);
                    }
                }
            }
        } while (choice != 0);
    }
}