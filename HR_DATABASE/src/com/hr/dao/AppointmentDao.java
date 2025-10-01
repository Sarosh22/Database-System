package com.hr.dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.hr.bean.Appointment; 
import com.hr.util.DBUtil;

public class AppointmentDao {

    public ArrayList<Appointment> getAllAppointments() throws Exception {
        ArrayList<Appointment> list = new ArrayList<Appointment>();
        String sql = "SELECT * FROM appointments";
        ResultSet rs = DBUtil.executeQuery(sql);

        while (rs.next()) {
            Appointment appointment = new Appointment();
            appointment.setAppointment_id(rs.getInt("appointment_id"));
            appointment.setUser_id(rs.getInt("user_id"));
            appointment.setHospital_id(rs.getInt("hospital_id"));
            appointment.setAppointment_date(rs.getDate("appointment_date"));
            appointment.setStatus(rs.getString("status"));

            list.add(appointment);
        }

        try {
            DBUtil.conn.close();
            System.out.println("Connection closed successfully");
        } catch (Exception e) {
            // Handle exception
        }

        return list;
    }

    // Add more methods as needed
}
