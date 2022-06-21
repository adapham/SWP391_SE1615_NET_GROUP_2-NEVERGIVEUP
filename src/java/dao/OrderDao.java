/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Entity.Order;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Window 10
 */
public class OrderDao extends ConnectDB {

    public int returnOrderID(Order order) {
        String sql = "INSERT INTO [FoodOrderOnline].[dbo].[Order]\n"
                    + "           ([AccountID]\n"
                    + "           ,[ShipperID]\n"
                    + "           ,[Address]\n"
                    + "           ,[Email]\n"
                    + "           ,[Status]\n"
                    + "           ,[Phone])\n"
                    + "     VALUES\n"
                    + "           (?,?,?,?,?,?)";
        try {
            
            PreparedStatement pre = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pre.setInt(1, order.getAccountID());
            pre.setInt(2, order.getShipperID());
            pre.setString(3, order.getAddress());
            pre.setString(4, order.getEmail());
            pre.setInt(5, order.getStatus());
            pre.setString(6, order.getPhone());
            pre.executeUpdate();

            ResultSet rs = pre.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getTotalOrder() {
        String sql = "select COUNT(*) from [Order]";
        try {
            //Đưa vào prepare
            PreparedStatement pre = conn.prepareStatement(sql);

            //Đưa vào ResultSet
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int count = rs.getInt(1);
                return count;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }

}
