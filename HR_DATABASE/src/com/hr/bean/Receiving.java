package com.hr.bean;

import java.util.Date;

public class Receiving {
    private String receiving_id;
    private int user_id;
    private java.sql.Date receiving_date;
    private String blood_type;
    private int amount;
	private String storage_location;
	private String status;
	private int hospital_id;
	private String donorName;
	private int quantity;

    public String getReceiving_id() {
        return receiving_id;
    }

    public void setReceiving_id(String receivingId) {
        this.receiving_id = receivingId;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public java.sql.Date getReceiving_date() {
        return receiving_date;
    }

    public void setReceiving_date(java.sql.Date receivingDate) {
        this.receiving_date = receivingDate;
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
    public int getHospital_id() {
        return hospital_id;
    }

    public void setHospital_id(int hospital_id) {
        this.hospital_id = hospital_id;
    }

    // Getter and setter for status
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Getter and setter for storage_location
    public String getStorage_location() {
        return storage_location;
    }

    public void setStorage_location(String storage_location) {
        this.storage_location = storage_location;
    }

    // Other getters and setters
    public String getReceiving_id1() {
        return receiving_id;
    }

    public void setReceiving_id1(String receivingId) {
        this.receiving_id = receivingId;
    }

    public int getUser_id1() {
        return user_id;
    }

    public void setUser_id1(int user_id) {
        this.user_id = user_id;
    }

    public Date getReceiving_date1() {
        return receiving_date;
    }

    public void setReceiving_date(Date receiving_date) {
        this.receiving_date = (java.sql.Date) receiving_date;
    }

    public String getBlood_type1() {
        return blood_type;
    }

    public void setBlood_type1(String blood_type) {
        this.blood_type = blood_type;
    }

    public int getAmount1() {
        return amount;
    }

    public void setAmount1(int amount) {
        this.amount = amount;
    }

    public int getReceivingId() {
        return getReceivingId();
    }

    public void setReceivingId(String receivingId) {
        this.receiving_id = receivingId;
    }

    public String getDonorName() {
        return getDonorName();
    }

    public void setDonorName(String donorName) {
        this.donorName = donorName;
    }
    public int getQuantity() {
        return getQuantity();
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

