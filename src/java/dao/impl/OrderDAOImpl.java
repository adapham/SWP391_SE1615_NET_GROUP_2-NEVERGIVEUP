/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import Entity.Order;
import dao.ConnectDB;
import dao.OrderDAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Window 10
 */
public class OrderDAOImpl extends ConnectDB implements OrderDAO{

    public int insertOrderID(Order order) throws Exception{
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

        } catch (SQLException e) {
            throw e;
        }
        return 0;
    }

    public List<Order> listAllOrders() throws Exception{
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
        } catch (SQLException e) {
            throw e;
        }
        return list;
    }

//    public static void main(String[] args) {
//        OrderDAOImpl dao = new OrderDAOImpl();        
//        Order order = Order.builder()
//                .accountID(2)
//                .shipperID(1)
//                .address("adidas")
//                .email("abc@gmail.com")
//                .status(1)
//                .phone("0362568743")
//                .build();
//        try {
//            System.out.println(dao.insertOrderID(order));
//        } catch (Exception ex) {
//            Logger.getLogger(OrderDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    public int updateStatus(int status, int orId) throws Exception{
        int n = 0;
        String sql = "update [Order]set status = ? where OrderID = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, status);
            pre.setInt(2, orId);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            throw ex;
            //Logger.getLogger(OrderDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

}
