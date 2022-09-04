package com.solvd.secondHomework.Enums;

public enum ClientMenu {
    TAKE_A_LOAN("Take a loan."),
    CONTACT_A_TELLER("Talk with a teller."),
    APPLY_CREDIT_CARD("Apply for a credit card."),
    CHECK_BALANCE("Check balance."),
    WITHDRAW("Withdraw."),
    DEPOSIT("Deposit.");

    private final String description;
    ClientMenu(String s) {
        this.description = s;
    }

    @Override
    public String toString(){
       return description;
    }
}
