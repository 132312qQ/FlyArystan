package com.example.fly_arystan.Model;

public class BuyTicket {
    private String to;
    private String from;
    private String name;
    private String surname;
    private String day;
    private String month;
    private String year;
    private String docnumber;
    private String tel;
    private String email;
    private String carduser;
    private String cardnum;
    private String cardMonth;
    private String cardYear;
    private String cvv;

    public BuyTicket(String to, String from, String name, String surname, String day, String month, String year, String docnumber, String tel, String email, String carduser, String cardnum, String cardMonth, String cardYear, String cvv) {
        this.to = to;
        this.from = from;
        this.name = name;
        this.surname = surname;
        this.day = day;
        this.month = month;
        this.year = year;
        this.docnumber = docnumber;
        this.tel = tel;
        this.email = email;
        this.carduser = carduser;
        this.cardnum = cardnum;
        this.cardMonth = cardMonth;
        this.cardYear = cardYear;
        this.cvv = cvv;
    }

    public String getTo() {
        return to;
    }

    public String getFrom() {
        return from;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getDay() {
        return day;
    }

    public String getMonth() {
        return month;
    }

    public String getYear() {
        return year;
    }

    public String getDocnumber() {
        return docnumber;
    }

    public String getTel() {
        return tel;
    }

    public String getEmail() {
        return email;
    }

    public String getCarduser() {
        return carduser;
    }

    public String getCardnum() {
        return cardnum;
    }

    public String getCardMonth() {
        return cardMonth;
    }

    public String getCardYear() {
        return cardYear;
    }

    public String getCvv() {
        return cvv;
    }
}
