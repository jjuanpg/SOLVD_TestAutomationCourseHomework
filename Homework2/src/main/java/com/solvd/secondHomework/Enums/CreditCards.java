package com.solvd.secondHomework.Enums;

import com.solvd.secondHomework.Exceptions.InputMismatchFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public enum CreditCards {
    WHITE_CARD("Limit of $3.000 per month", "White Card"),
    BLACK_CARD("Limit of $10.000 per month", "Black Card"),
    PLATINUM_CARD("Without limits per month", "Platinum Card");

    private static final Logger logger = LogManager.getLogger(CreditCards.class);
    private final String CHOICE;
    private final String DESCRIPTION;

    CreditCards(String a, String s) {
        this.DESCRIPTION = a;
        this.CHOICE = s;
    }

    public static String getCard(int choice) throws InputMismatchFoundException {
        switch (choice){
            case 1 -> {return "WHITE CARD";}
            case 2 -> {return "BLACK CARD";}
            case 3 -> {return "PLATINUM CARD";}
            default -> {
                logger.error("ERROR: Input Mismatch.");
                throw new InputMismatchFoundException("Could not find the selected election " + choice);
            }
        }
    }

    @Override
    public String toString() {
        return CHOICE+" "+DESCRIPTION;
    }
}
