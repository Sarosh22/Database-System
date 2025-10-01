package com.hr.bean;

import java.util.ArrayList;
import java.util.Date;

public class Appointment {
    private int appointment_id;
    private int user_id;
    private int hospital_id;
    private Date appointment_date;
    private String status;

    public int getAppointment_id() {
        return appointment_id;
    }

    public void setAppointment_id(int appointment_id) {
        this.appointment_id = appointment_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getHospital_id() {
        return hospital_id;
    }

    public void setHospital_id(int hospital_id) {
        this.hospital_id = hospital_id;
    }

    public Date getAppointment_date() {
        return appointment_date;
    }

    public void setAppointment_date(Date appointment_date) {
        this.appointment_date = appointment_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

	public ArrayList<Appointment> getAllAppointments1() {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Appointment> getAllAppointments() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getDonor_id() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getNotes() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setNotes(String text) {
		// TODO Auto-generated method stub
		
	}

	public void insertAppointment(Appointment newAppointment) {
		// TODO Auto-generated method stub
		
	}

	public void updateAppointment(int appointmentId) {
		// TODO Auto-generated method stub
		
	}

	public void updateAppointment(Appointment updatedAppointment) {
		// TODO Auto-generated method stub
		
	}
}
