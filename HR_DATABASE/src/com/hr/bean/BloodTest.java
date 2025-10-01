package com.hr.bean;

import java.util.Date;

public class BloodTest {
    private int test_id;
    private int donation_id;
    private Date test_date;
    private String results;

    public int getTest_id() {
        return test_id;
    }

    public void setTest_id(int test_id) {
        this.test_id = test_id;
    }

    public int getDonation_id() {
        return donation_id;
    }

    public void setDonation_id(int donation_id) {
        this.donation_id = donation_id;
    }

    public Date getTest_date() {
        return test_date;
    }

    public void setTest_date(Date test_date) {
        this.test_date = test_date;
    }

    public String getResults() {
        return results;
    }

    public void setResults(String results) {
        this.results = results;
    }
}
