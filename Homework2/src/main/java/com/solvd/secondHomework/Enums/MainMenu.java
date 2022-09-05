package com.solvd.secondHomework.Enums;

public enum MainMenu {
    CREATE_CLIENT("Create new client."),
    DISPLAY_FILE("Display file."),
    READ_FILE("Read from file."),
    DISPLAY_LINKED_LIST("Display updated linked lists."),
    CHECK_CLIENT("Check if Client exist."),
    UNSUBSCRIBE("Unsubscribe bank account."),
    DEBTOR_LIST("Display list of debtor."),
    BRANCHES("Display available branches."),
    THREADS("Show a thread example");

    private final String description;
    MainMenu(String s) {
        this.description = s;
    }

    @Override
    public String toString(){
        return description;
    }
}
