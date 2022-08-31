package com.solvd.secondHomework.Enums;

import com.solvd.secondHomework.Exceptions.InputMismatchFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public enum Loans {
    THIRTY_THOUSAND("$30.000"),
    FIFTY_THOUSAND("$50.000"),
    HUNDRED_THOUSAND("$100.000");

    private static final Logger logger = LogManager.getLogger(Loans.class);
    private final String Loan;
    Loans(String s) {
        this.Loan = s;
    }

    public static int getLoan(int choice) throws InputMismatchFoundException {
        switch (choice){
            case 1 -> {return 30000;}
            case 2 -> {return 50000;}
            case 3 -> {return 100000;}
            default -> {
                logger.error("ERROR: Input Mismatch.");
                throw new InputMismatchFoundException("Could not find the selected election " + choice);
            }
        }
    }

    @Override
    public String toString() {
        return Loan;
    }
}
