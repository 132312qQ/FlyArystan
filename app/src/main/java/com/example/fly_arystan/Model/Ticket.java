package com.example.fly_arystan.Model;

public class Ticket {
    private String price;
    private String timeGo;
    private String timeOut;

    public Ticket(String price,String timeGo,String timeOut
    ) {
        this.price = price;
    }
    public String getPrice() {

        return price;
    }

    public String getTimeGo() {
        return timeGo;
    }

    public String getTimeOut() {
        return timeOut;
    }
}