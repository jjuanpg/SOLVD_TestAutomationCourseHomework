package com.solvd.secondHomework;

import com.solvd.secondHomework.Enums.*;
import com.solvd.secondHomework.Exceptions.*;
import com.solvd.secondHomework.Threads.Multithread;
import com.solvd.secondHomework.Threads.Multithreading;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@SuppressWarnings("FieldCanBeLocal")
public class BankDemo implements Serializable{

    final static String typeA = "employer";
    final static String typeB = "employee";
    static String creditCard;
    static int loan;

    private static Logger logger = LogManager.getLogger(BankDemo.class);
    private static final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    public static void main(String[] args) throws IOException, UnemployedClientException, BankAlarmException, ComputersOffException, InputMismatchFoundException, MinimumRequirementsException, InvalidEmailException, InterruptedException {
        Date date = new Date();
        logger = LogManager.getRootLogger();
        logger.debug("---------------" + formatter.format(date) + "---------------");

        //Set the file
        final String employerFile = "E:\\INTELLIJ_COURSES\\Homework2\\src\\main\\resources\\Employers.txt";
        final String employeeFile = "E:\\INTELLIJ_COURSES\\Homework2\\src\\main\\resources\\Employee.txt";
        final String employerDebtor = "E:\\INTELLIJ_COURSES\\Homework2\\src\\main\\resources\\EmployerDebtors.txt";
        final String employeeDebtor = "E:\\INTELLIJ_COURSES\\Homework2\\src\\main\\resources\\EmployeeDebtors.txt";

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

        logger.info(("---------------Systems ready---------------\n\n"));
        do {
            MainMenu[] main = MainMenu.values();
            Arrays.stream(main).map(v -> (v.ordinal()+1)+") "+v.toString()).forEach(e -> logger.info(e));
            logger.info("0) Exit.");
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
                    switch (election){
                        case 1 -> {
                            String line;
                            logger.info("-----------------------------------");
                            while ((line = br.readLine()) != null) {
                                logger.info(line);
                            }
                            logger.info("-----------------------------------");
                        }
                        case 2 -> {
                            String line;
                            logger.info("-----------------------------------");
                            while ((line = br1.readLine()) != null) {
                                logger.info(line);
                            }
                            logger.info("-----------------------------------");
                        }
                        default -> {
                            logger.error("ERROR: Input Mismatch.");
                            throw new InputMismatchFoundException("Could not find the selected election " + election);
                        }
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
                    switch (election){
                        case 1 -> employerList.show();
                        case 2 -> employeeList.show();
                        default -> {
                            logger.error("ERROR: Input Mismatch.");
                            throw new InputMismatchFoundException("Could not find the selected election " + election);
                        }
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
                            ClientMenu[] options = ClientMenu.values();
                            Arrays.stream(options).map(v -> (v.ordinal()+1)+") "+v.toString()).forEach(e -> logger.info(e));
                            logger.info("0) Exit.");
                            menu = sc.nextInt();

                            //Print the result
                            if (menu == 1) {
                                logger.info("\r\nThe available credits are: ");
                                Loans[] loans = Loans.values();
                                Arrays.stream(loans).map(v -> (v.ordinal()+1)+") "+ v.toString()).forEach(e -> logger.info(e));
                                loan = Loans.getLoan(sc.nextInt());
                                assert employer != null;
                                boolean qualify = employer.isUserQualified(employer.getCreditScore(), employer.getSalary());
                                if (qualify) {
                                    logger.info("\r\n" + branch.getBranch() + " " + branch.getAddress());
                                    logger.info("Attended by: " + banker.getFirstName() + ", " + banker.getLastName() + " ID: " + banker.getID());
                                    logger.info("We are happy to announce you " + employer.getFirstName() + ", " + employer.getLastName() + " " + employer.getDni() + " that the loan of $" + loan + " will be transferred to your account");
                                    logger.info("If you want to withdraw now the security " + security.getFirstName() + " ID: " + security.getID() + " will help you");

                                    employer.setBalance(employer.getBalance()+loan);
                                    ArrayList<Employer> employers;
                                    employers = StaticMethods.employersArray(employerFile);
                                    int index = StaticMethods.getIndex(employer.getDni());
                                    employers.remove(index);
                                    employers.add(index, employer);

                                    PrintWriter pw = new PrintWriter(employerFile);
                                    pw.close();
                                    employers.stream().map(v -> v.getFirstName()+"|"+v.getLastName()+"|"+v.getDni()+"|"+v.getCreditScore()+"|"+v.getSalary()+"|"+v.getBalance()).forEach(e -> StaticMethods.saveToFile(employerFile, e, true));
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
                                //logger.info(StaticMethods.showCreditCards());
                                CreditCards[] cards = CreditCards.values();
                                Arrays.stream(cards).map(v -> (v.ordinal()+1)+") "+v.toString()).forEach(e -> logger.info(e));
                                creditCard = CreditCards.getCard(sc.nextInt());
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

                                PrintWriter pw = new PrintWriter(employerFile);
                                pw.close();
                                employers.stream().map(v -> v.getFirstName()+"|"+v.getLastName()+"|"+v.getDni()+"|"+v.getCreditScore()+"|"+v.getSalary()+"|"+v.getBalance()).forEach(e -> StaticMethods.saveToFile(employerFile, e, true));
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

                                PrintWriter pw = new PrintWriter(employerFile);
                                pw.close();
                                employers.stream().map(value -> value.getFirstName() + "|" + value.getLastName() + "|" + value.getDni() + "|" + value.getCreditScore() + "|" + value.getSalary() + "|" + value.getBalance()).forEach(e -> StaticMethods.saveToFile(employerFile, e, true));
                            }
                        }while(menu != 0);
                    }

                    if (res1) {
                        logger.info("The employee client exist");
                        Employee employee = StaticMethods.returnEmployee(employeeFile, DNI);
                        int menu;

                        do{
                            //menu with options of the bank
                            ClientMenu[] options = ClientMenu.values();
                            Arrays.stream(options).map(v -> (v.ordinal()+1)+") "+v.toString()).forEach(e -> logger.info(e));
                            logger.info("0) Exit.");
                            menu = sc.nextInt();

                            //Print the result
                            if (menu == 1) {
                                logger.info("\r\nThe available credits are: ");
                                Loans[] loans = Loans.values();
                                Arrays.stream(loans).map(v -> (v.ordinal()+1)+") "+ v.toString()).forEach(e -> logger.info(e));
                                loan = Loans.getLoan(sc.nextInt());
                                assert employee != null;
                                boolean qualify = employee.isUserQualified(employee.getCreditScore(), employee.getSalary());
                                if (qualify) {
                                    logger.info("\r\n" + branch.getBranch() + " " + branch.getAddress());
                                    logger.info("Attended by: " + banker.getFirstName() + ", " + banker.getLastName() + " ID: " + banker.getID());
                                    logger.info("We are happy to announce you " + employee.getFirstName() + ", " + employee.getLastName() + " " + employee.getDni() + " that the loan of $" + loan + " will be transferred to your account");
                                    logger.info("If you want to withdraw now the security " + security.getFirstName() + " ID: " + security.getID() + " will help you");

                                    employee.setBalance(employee.getBalance()+loan);
                                    ArrayList<Employee> employees;
                                    employees = StaticMethods.employeeArray(employeeFile);
                                    int index = StaticMethods.getIndex(employee.getDni());
                                    employees.remove(index);
                                    employees.add(index, employee);

                                    PrintWriter pw = new PrintWriter(employeeFile);
                                    pw.close();
                                    employees.stream().map(v -> v.getFirstName()+"|"+v.getLastName()+"|"+v.getDni()+"|"+v.getCreditScore()+"|"+v.getSalary()+"|"+v.getBalance()).forEach(e -> StaticMethods.saveToFile(employeeFile, e, true));
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
                                //logger.info(StaticMethods.showCreditCards());
                                CreditCards[] cards = CreditCards.values();
                                Arrays.stream(cards).map(v -> (v.ordinal()+1)+") "+v.toString()).forEach(e -> logger.info(e));
                                creditCard = CreditCards.getCard(sc.nextInt());
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

                                PrintWriter pw = new PrintWriter(employeeFile);
                                pw.close();
                                employeeArray.stream().map(value -> value.getFirstName() + "|" + value.getLastName() + "|" + value.getDni() + "|" + value.getCreditScore() + "|" + value.getSalary() + "|" + value.getBalance()).forEach(e -> StaticMethods.saveToFile(employeeFile, e, true));

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

                                PrintWriter pw = new PrintWriter(employeeFile);
                                pw.close();
                                employeeArray.stream().map(value -> value.getFirstName() + "|" + value.getLastName() + "|" + value.getDni() + "|" + value.getCreditScore() + "|" + value.getSalary() + "|" + value.getBalance()).forEach(e -> StaticMethods.saveToFile(employeeFile, e, true));

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
                case 7 -> {
                    ArrayList<Employer> employers;
                    ArrayList<Employee> employee;
                    logger.info("""
                            \n--------------Menu--------------
                            1. Employers debtor list
                            2. Employee debtor list
                            --------------------------------
                            Enter the type of list:\040
                            """);
                    int election = sc.nextInt();
                    switch (election){
                        case 1 -> {
                            employers = (StaticMethods.employersArray(employerFile));
                            employers.stream().filter(v -> v.getBalance() < 0).forEach(logger::info);
                            ArrayList<Employer> employerDebtors = employers.stream().filter(v -> v.getBalance() < 0).collect(Collectors.toCollection(ArrayList::new));

                            PrintWriter pw = new PrintWriter(employerDebtor);
                            pw.close();
                            employerDebtors.stream().map(v -> v.getFirstName()+"|"+v.getLastName()+"|"+v.getDni()+"|"+v.getCreditScore()+"|"+v.getSalary()+"|"+v.getBalance()).forEach(e -> StaticMethods.saveToFile(employerDebtor, e, true));
                        }
                        case 2 -> {
                            employee = (StaticMethods.employeeArray(employeeFile));
                            employee.stream().filter(v -> v.getBalance() < 0).forEach(logger::info);
                            ArrayList<Employee> employeeDebtors = employee.stream().filter(v -> v.getBalance() < 0).collect(Collectors.toCollection(ArrayList::new));

                            PrintWriter pw = new PrintWriter(employeeDebtor);
                            pw.close();
                            employeeDebtors.stream().map(v -> v.getFirstName()+"|"+v.getLastName()+"|"+v.getDni()+"|"+v.getCreditScore()+"|"+v.getSalary()+"|"+v.getBalance()).forEach(e -> StaticMethods.saveToFile(employeeDebtor, e, true));
                        }
                        default -> {
                            logger.error("ERROR: Input Mismatch.");
                            throw new InputMismatchFoundException("Could not find the selected election " + election);
                        }
                    }
                }
                case 8 -> {
                    Branches[] branches = Branches.values();
                    Arrays.stream(branches).map(Branches::toString).forEach(e -> logger.info(e));
                }
                case 9 -> {
                    int menu;
                    ThreadsMenu[] t_menu = ThreadsMenu.values();
                    Arrays.stream(t_menu).map(v -> (v.ordinal()+1)+") "+v.toString()).forEach(logger::info);
                    menu = sc.nextInt();
                    switch (menu){
                        case 1 ->{
                            for(int i= 0; i<5; i++){
                                Multithreading myThing = new Multithreading(i);
                                myThing.start();
                            }
                            TimeUnit.SECONDS.sleep(3);
                        }
                        case 2 ->{
                            //The difference between use Runnable and Thread is that I have to instantiate a Thread.
                            //Another difference is that if I extend the Thread class I wouldn't need to instantiate anything
                            //but there is a major advantage using Runnable. If I extend thread I cannot extend any other class
                            //but if I use Runnable I can implement as much as interfaces I need and extend any other class
                            for(int i= 0; i<5; i++){
                                Multithread myThing = new Multithread(i);
                                Thread myThread = new Thread(myThing);
                                myThread.start();
                            }
                            TimeUnit.SECONDS.sleep(3);
                        }
                        case 3 ->{
                            logger.info("Current thread ID: "+Thread.currentThread().getId());

                            ThreadPoolExecutor service = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
                            for(int i=1; i<8; i++){
                                Multithread myThing = new Multithread(i);
                                service.submit(myThing);
                            }
                            logger.info("Active: "+service.getActiveCount()+" | "+"Queue: "+service.getQueue().size());
                            service.awaitTermination(6, TimeUnit.SECONDS);
                            service.shutdown();
                        }
                    }
                }
            }
        } while (choice != 0);
    }
}