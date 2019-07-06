package com.example.fly_arystan.Model;

public class UserRegister {
    private String name;
    private String surname;
    private String birth;
    private String national_id;
    private String passport;
    private String expite_date;
    private String mail;
    private String mobile;
    private String password1;
    private String password2;

    public UserRegister(String name, String surname,String birth,String national_id,String passport,String expite_date, String mail, String mobile, String password1, String password2) {
        this.name = name;
        this.surname = surname;
        this.birth = birth;
        this.national_id = national_id;
        this.passport = passport;
        this.expite_date = expite_date;
        this.mail = mail;
        this.mobile = mobile;
        this.password1 = password1;
        this.password2 = password2;
    }


    public String getName() {
        return name;
    }

    public String getNational_id() {
        return national_id;
    }

    public String getSurname() {
        return surname;
    }

    public String getBirth() {
        return birth;
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
