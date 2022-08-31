package com.solvd.secondHomework;

import com.solvd.secondHomework.Exceptions.UnemployedClientException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.*;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StaticMethods {
    private static final Logger logger = LogManager.getLogger(StaticMethods.class);
    public static int index;

    public static int getIndex(String dni) throws FileNotFoundException {
        File file = new File("E:\\INTELLIJ_COURSES\\Homework2\\src\\main\\resources\\Employers.txt");
        Scanner sc = new Scanner(file);
        File file1 = new File("E:\\INTELLIJ_COURSES\\Homework2\\src\\main\\resources\\Employee.txt");
        Scanner sc1 = new Scanner(file1);
        index = 0;

        while(sc.hasNextLine()){
            String line = sc.nextLine();
            String[] items = line.split("\\|");
            String fDni = items[2];

            if(fDni.equals(dni)){
                return index;
            }
            index++;
        }
        index = 0;
        while(sc1.hasNextLine()){
            String line = sc1.nextLine();
            String[] items = line.split("\\|");
            String fDni = items[2];

            if(fDni.equals(dni)){
                return index;
            }
            index++;
        }
        return index;
    }

    public static int setTurn(int UPPERBOUND){
        Random rand = new Random(); //instance of random class
        return rand.nextInt(UPPERBOUND);
    }

    public static String showMenu(){
        return("""
                \n---------- MENU ----------
                Take a loan: 1
                Talk with the teller: 2
                Apply for a credit card: 3
                Check balance : 4
                Withdraw : 5
                Deposit : 6
                Exit : 0
                --------------------------""");
    }

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }

    public static String getType(int election) throws UnemployedClientException {
        return switch (election) {
            case 1 -> "employer";
            case 2 -> "employee";
            default -> {
                logger.fatal("Unemployed client type found.");
                throw new UnemployedClientException("Sorry, we can not offer any service");
            }
        };
    }

    public static CustomLinkedList<Employer> employersFromFile(String fileName) throws FileNotFoundException {

        File file = new File(fileName);
        Scanner sc = new Scanner(file);
        CustomLinkedList<Employer> employersFile = new CustomLinkedList<>();

        while(sc.hasNextLine()){
            String line = sc.nextLine();
            String[] items = line.split("\\|");

            String fName = items[0];
            String lName = items[1];
            String dni = items[2];
            int creditScore = Integer.parseInt(items[3]);
            double salary = Double.parseDouble(items[4]);
            double balance = Double.parseDouble(items[5]);

            employersFile.insert(new Employer(fName, lName, dni, creditScore, salary, balance));
        }
            return employersFile;
    }

    public static CustomLinkedList<Employee> employeesFromFile(String fileName) throws FileNotFoundException {

        File file = new File(fileName);
        Scanner sc = new Scanner(file);
        CustomLinkedList<Employee> employeesFile = new CustomLinkedList<>();

        while(sc.hasNextLine()){
            String line = sc.nextLine();
            String[] items = line.split("\\|");

            String fName = items[0];
            String lName = items[1];
            String dni = items[2];
            int creditScore = Integer.parseInt(items[3]);
            double salary = Double.parseDouble(items[4]);
            double balance = Double.parseDouble(items[5]);

            employeesFile.insert(new Employee(fName, lName, dni, creditScore, salary, balance));
        }
        return employeesFile;
    }

    public static void saveToFile(String fileName, String text, boolean append) {
        //1: Create a file
        File file = new File(fileName);

        //2: Create a file writer class
        FileWriter fw;
        try {
            fw = new FileWriter(file, append);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //3: Create a print writer class
        PrintWriter pw = new PrintWriter(fw);

        pw.println(text);

        pw.close();
    }

    public static boolean checkClient(String fileName, String dni) throws FileNotFoundException {
        File file = new File(fileName);
        Scanner sc = new Scanner(file);
        index = 0;

        while(sc.hasNextLine()){
            index++;
            String line = sc.nextLine();
            String[] items = line.split("\\|");

            String dniFile = items[2];
            if(dniFile.equals(dni)){
                return true;
            }
        }
        return false;
    }

    public static Employer returnEmployer(String fileName, String dni) throws FileNotFoundException {
        File file = new File(fileName);
        Scanner sc = new Scanner(file);

        while(sc.hasNextLine()){
            String line = sc.nextLine();
            String[] items = line.split("\\|");

            String fName = items[0];
            String lName = items[1];
            String fDni = items[2];
            int creditScore = Integer.parseInt(items[3]);
            double salary = Double.parseDouble(items[4]);
            double balance = Double.parseDouble(items[5]);

            if(fDni.equals(dni)){
                return new Employer(fName, lName, fDni, creditScore, salary, balance);
            }
        }
        return null;
    }

    public static Employee returnEmployee(String fileName, String dni) throws FileNotFoundException {
        File file = new File(fileName);
        Scanner sc = new Scanner(file);

        while(sc.hasNextLine()){
            String line = sc.nextLine();
            String[] items = line.split("\\|");

            String fName = items[0];
            String lName = items[1];
            String fDni = items[2];
            int creditScore = Integer.parseInt(items[3]);
            double salary = Double.parseDouble(items[4]);
            double balance = Double.parseDouble(items[5]);

            if(fDni.equals(dni)){
                return new Employee(fName, lName, fDni, creditScore, salary, balance);
            }
        }
        return null;
    }

    public static void sendEmail(String email){
        // Sender's email ID needs to be mentioned
        String from = "bankdemojava@gmail.com";
        // Assuming you are sending email from through gmail smtp
        String host = "smtp.gmail.com";
        // Get system properties
        Properties properties = System.getProperties();
        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");
        // Get the Session object.// and pass username and password
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, "buchbuvywtuefnbz");
            }
        });
        // Used to debug SMTP issues
        session.setDebug(true);
        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));

            // Set Subject: header field
            message.setSubject("Talk to a teller");

            // Now set the actual message
            message.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque massa enim," +
                            " varius ut elit sit amet, feugiat consequat magna. tear cursus elit magna. " +
                            "Aenean ut ex lacus. Nunc sit amet volutpat sapien. Nunc luctus lobortis tincidunt. " +
                            "Cras nec convallis justo. Maecenas nec arch at lectus ports ornare ut et sem. Cras sed " +
                            "quam efficitur, posuere sem a, elementum ante. Vestibulum tempus finibus erat, eu aliquam est " +
                            "eleifend eget. Nullam luctus tellus a rhoncus maximus. Nulla facilisi. Sed et massa consequat, " +
                            "pellentesque mauris ut, accumsan neque.");

            logger.info("sending...");
            // Send message
            Transport.send(message);
            logger.info("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }

    public static ArrayList<Employer> employersArray(String fileName) throws FileNotFoundException {

        File file = new File(fileName);
        Scanner sc = new Scanner(file);
        ArrayList<Employer> employersArray = new ArrayList<>();

        while(sc.hasNextLine()){
            String line = sc.nextLine();
            String[] items = line.split("\\|");

            String fName = items[0];
            String lName = items[1];
            String dni = items[2];
            int creditScore = Integer.parseInt(items[3]);
            double salary = Double.parseDouble(items[4]);
            double balance = Double.parseDouble(items[5]);

            employersArray.add(new Employer(fName, lName, dni, creditScore, salary, balance));
        }
            return employersArray;
    }

    public static ArrayList<Employee> employeeArray(String fileName) throws FileNotFoundException {

        File file = new File(fileName);
        Scanner sc = new Scanner(file);
        ArrayList<Employee> employeeArray = new ArrayList<>();

        while(sc.hasNextLine()){
            String line = sc.nextLine();
            String[] items = line.split("\\|");

            String fName = items[0];
            String lName = items[1];
            String dni = items[2];
            int creditScore = Integer.parseInt(items[3]);
            double salary = Double.parseDouble(items[4]);
            double balance = Double.parseDouble(items[5]);

            employeeArray.add(new Employee(fName, lName, dni, creditScore, salary, balance));
        }
        return employeeArray;
    }
}