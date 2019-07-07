package com.example.fly_arystan.Model;

public class Contact {
    private String name;
    private String mail;
    private String ticket_number;
    private String message;

    public Contact(String name, String mail, String ticket_number, String message) {
        this.name = name;
        this.mail = mail;
        this.ticket_number = ticket_number;
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public String getMail() {
        return mail;
    }

    public String getTicket_number() {
        return ticket_number;
    }

    public String getMessage() {
        return message;
    }
}
