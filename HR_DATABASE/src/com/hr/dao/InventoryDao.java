package com.hr.dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.hr.bean.Inventory; 
import com.hr.util.DBUtil;

public class InventoryDao {

    public ArrayList<Inventory> getAllInventory() throws Exception {
        ArrayList<Inventory> list = new ArrayList<Inventory>();
        String sql = "SELECT * FROM inventory";
        ResultSet rs = DBUtil.executeQuery(sql);

        while (rs.next()) {
            Inventory inventory = new Inventory();
            inventory.setBlood_type(rs.getString("blood_type"));
            inventory.setAmount(rs.getInt("amount"));

            list.add(inventory);
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
