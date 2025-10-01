package com.hr.bean;

import java.util.Date;

public class Donation {
    private int donation_id;
    private int user_id;
    private Date donation_date;
    private String blood_type;
    private int amount;

    public int getDonation_id() {
        return donation_id;
    }

    public void setDonation_id(int donation_id) {
        this.donation_id = donation_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public Date getDonation_date() {
        return donation_date;
    }

    public void setDonation_date(Date donation_date) {
        this.donation_date = donation_date;
    }

    public String getBlood_type() {
        return blood_type;
    }

    public void setBlood_type(String blood_type) {
        this.blood_type = blood_type;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
