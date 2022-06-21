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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Window 10
 */
public class OrderDao extends ConnectDB {

    public int insertOrderID(Order order) {
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
            int i = pre.executeUpdate();
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

    public List<Order> listAllOrders() {
        List<Order> list = new ArrayList<>();
        try {
            String sql = "select * from [Order]";
            PreparedStatement pre = conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                Order ord = Order.builder()
                        .orderID(rs.getInt(1))
                        .accountID(rs.getInt(2))
                        .shipperID(rs.getInt(3))
                        .orderDate(rs.getString(4))
                        .address(rs.getString(5))
                        .email(rs.getString(6))
                        .status(rs.getInt(7))
                        .phone(rs.getString(8))
                        .build();
                list.add(ord);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void main(String[] args) {
        OrderDao dao = new OrderDao();
        Order order = Order.builder()
                .accountID(2)
                .shipperID(1)
                .address("adidas")
                .email("abc@gmail.com")
                .status(1)
                .phone("0362568743")
                .build();
        System.out.println(dao.insertOrderID(order));
    }

    public int updateStatus(int status, int orId) {
        int n = 0;
        String sql = "update [Order]set status = ? where OrderID = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, status);
            pre.setInt(2, orId);
            n = pre.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(OrderDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

}
