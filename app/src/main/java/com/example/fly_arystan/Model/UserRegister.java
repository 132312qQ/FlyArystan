package com.example.fly_arystan.Model;

public class UserRegister {
    private String name;
    private String surname;
    private String status;
    private String lang;
    private String date;
    private String expite_date;
    private String mail;
    private String mobile;
    private String password1;
    private String password2;

    public UserRegister(String name, String surname, String status, String lang, String date, String expite_date, String mail, String mobile, String password1, String password2) {
        this.name = name;
        this.surname = surname;
        this.status = status;
        this.lang = lang;
        this.date = date;
        this.expite_date = expite_date;
        this.mail = mail;
        this.mobile = mobile;
        this.password1 = password1;
        this.password2 = password2;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getStatus() {
        return status;
    }

    public String getLang() {
        return lang;
    }

    public String getDate() {
        return date;
    }

    public String getExpite_date() {
        return expite_date;
    }

    public String getMail() {
        return mail;
    }

    public String getMobile() {
        return mobile;
    }

    public String getPassword1() {
        return password1;
    }

    public String getPassword2() {
        return password2;
    }
}
