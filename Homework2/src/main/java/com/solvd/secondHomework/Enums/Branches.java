package com.solvd.secondHomework.Enums;

public enum Branches {
    CHACO("CHACO: French 414", "+54 3624-543125"),
    FORMOSA("FORMOSA: Madariaga 200", "+54 3704-842536"),
    CORRIENTES("CORRIENTES: Belgrano 700", "+54 3794-642574"),
    BUENOS_AIRES("BUENOS AIRES: Humboldt 1347", "+54 9 11-187542");

    private final String address;
    private final String phone;
    Branches(String s, String s1) {
        this.address = s;
        this.phone = s1;
    }

    @Override
    public String toString() {
        return address+". "+"PHONE: "+phone+".";
    }
}
