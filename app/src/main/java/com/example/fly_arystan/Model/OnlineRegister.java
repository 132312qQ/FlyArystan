package com.example.fly_arystan.Model;

public class OnlineRegister {
    private String name;
    private String surname;
    private String number;

    public OnlineRegister(String name, String surname, String number) {
        this.name = name;
        this.surname = surname;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getNumber() {
        return number;
    }
}
