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
public class OrderDAOImpl extends ConnectDB implements OrderDAO {

    public int insertOrderID(Order order) throws Exception {
        String sql = "INSERT INTO [dbo].[Order]\n"
                + "           ([AccountID]\n"
                + "           ,[ShipperID]\n"
                + "           ,[OrderDate]\n"
                + "           ,[Address]\n"
                + "           ,[Email]\n"
                + "           ,[Status]\n"
                + "           ,[Phone])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?,?,?)";
        try {

            PreparedStatement pre = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pre.setInt(1, order.getAccountID());
            pre.setInt(2, order.getShipperID());
            pre.setString(3, order.getOrderDate());
            pre.setString(4, order.getAddress());
            pre.setString(5, order.getEmail());
            pre.setInt(6, order.getStatus());
            pre.setString(7, order.getPhone());
            pre.executeUpdate();
            ResultSet rs = pre.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException e) {
            throw e;
        }
        return 0;
    }

    public int getTotalOrder() throws Exception {
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
            throw ex;
        }
        return 0;
    }

    public List<Order> listAllOrders() throws Exception {
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

    public static void main(String[] args) {
        OrderDAOImpl dao = new OrderDAOImpl();
        List<Order> list = null;
        try {
            list = dao.getOrderWithPaging(1, 3);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
//        for (Order o : list) {
//            Order order = Order.builder()
//                    .orderDate(o.getOrderDate())
//                    .address(o.getAddress())
//                    .email(o.getEmail())
//                    .status(1)
//                    .phone(o.getPhone())
//                    .build();
//            list.add(order);
//        }
        System.out.println(list.size());
//        try {
//            System.out.println(dao.listAllOrders());
//        } catch (Exception ex) {
//            Logger.getLogger(OrderDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
        
    }

    public int updateStatus(int status, int orId) throws Exception {
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

    public int TotalBill() throws Exception {
        String sql = "select COUNT(*) from [Order]";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int count = rs.getInt(1);
                return count;
            }
        } catch (Exception e) {
            throw e;
        }
        return 0;
    }

    public List<Order> getOrderWithPaging(int page, int PAGE_SIZE) throws Exception {
        List<Order> listOrd = new ArrayList<>();
        String sql = "select o.OrderID, a.DisplayName, s.ShipperName, o.OrderDate, o.[Address], o.Email, o.Status, o.Phone from [Order] o\n"
                + "join Account a on a.AccountID = o.AccountID\n"
                + "join Shipper s on s.ShipperID = o.ShipperID\n"
                + "order by OrderID\n"
                + "offset (?-1)*? row fetch next ? rows only";
        OrderDAOImpl dao = new OrderDAOImpl();
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, page);
            pre.setInt(2, PAGE_SIZE);
            pre.setInt(3, PAGE_SIZE);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                Order ord = Order.builder()
                        .orderID(rs.getInt("OrderID"))
                        .displayName(rs.getString("DisplayName"))
                        .shipperName(rs.getString("ShipperName"))
                        .orderDate(rs.getString("OrderDate"))
                        .address(rs.getString("Address"))
                        .email(rs.getString("Email"))
                        .status(rs.getInt("Status"))
                        .phone(rs.getString("Phone"))
                        .build();
                listOrd.add(ord);
            }
        } catch (Exception e) {
            throw e;
        }
        return listOrd;
    }

    public List<Order> getSearchOrderPagingByAddress(String keySearch, int page, int PAGE_SIZE) throws Exception {
        List<Order> listOrd = new ArrayList<>();
        String sql = "SELECT * FROM ( SELECT *, ROW_NUMBER() OVER (ORDER BY OrderID)\n"
                + "AS Seq FROM [Order] where [Address] like ? )t WHERE Seq BETWEEN  (?-1)*?+1 AND ?*?";
        OrderDAOImpl dao = new OrderDAOImpl();
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, "%" + keySearch + "%");
            pre.setInt(2, page);
            pre.setInt(3, PAGE_SIZE);
            pre.setInt(4, page);
            pre.setInt(5, PAGE_SIZE);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                Order ord = Order.builder()
                        .orderID(rs.getInt("OrderID"))
                        .accountID(rs.getInt("AccountID"))
                        .shipperID(rs.getInt("ShipperID"))
                        .orderDate(rs.getString("OrderDate"))
                        .address(rs.getString("Address"))
                        .email(rs.getString("Email"))
                        .status(rs.getInt("Status"))
                        .phone(rs.getString("Phone"))
                        .build();
                listOrd.add(ord);
            }
        } catch (Exception e) {
            throw e;
        }
        return listOrd;
    }
    public List<Order> getSearchOrderPagingByDate(String keySearch, int page, int PAGE_SIZE) throws Exception {
        List<Order> listOrd = new ArrayList<>();
        String sql = "SELECT * FROM ( SELECT *, ROW_NUMBER() OVER (ORDER BY OrderID)\n"
                + "AS Seq FROM [Order] where [OrderDate] like ? )t WHERE Seq BETWEEN  (?-1)*?+1 AND ?*?";
        OrderDAOImpl dao = new OrderDAOImpl();
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, "%" + keySearch + "%");
            pre.setInt(2, page);
            pre.setInt(3, PAGE_SIZE);
            pre.setInt(4, page);
            pre.setInt(5, PAGE_SIZE);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                Order ord = Order.builder()
                        .orderID(rs.getInt("OrderID"))
                        .accountID(rs.getInt("AccountID"))
                        .shipperID(rs.getInt("ShipperID"))
                        .orderDate(rs.getString("OrderDate"))
                        .address(rs.getString("Address"))
                        .email(rs.getString("Email"))
                        .status(rs.getInt("Status"))
                        .phone(rs.getString("Phone"))
                        .build();
                listOrd.add(ord);
            }
        } catch (Exception e) {
            throw e;
        }
        return listOrd;
    }
    public int getTotalOrderByDate(String keySearch) throws Exception {
        
        String sql = "select COUNT(*) from [Order] where [OrderDate] like ?";
        try {
            
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, "%" + keySearch + "%");
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int count = rs.getInt(1);
                return count;
            }
        } catch (Exception e) {
            throw e;
        }
        return 0;
    }
    
    public int getTotalOrderByAddress(String keySearch) throws Exception {
        String sql = "select COUNT(*) from [Order] where [Address] like ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, "%" + keySearch + "%");
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int count = rs.getInt(1);
                return count;
            }
        } catch (Exception e) {
            throw e;
        }
        return 0;
    }

    public List<Order> listAllOrdersbyID(int AccID) throws Exception {
        List<Order> list = new ArrayList<>();
        try {
            String sql = "select * from [Order] where AccountID=" + AccID;
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

    public int deleteOrderByID(int id) throws Exception{
        int n = 0;
        String sql = "delete from [Order] where OrderID = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            n = ps.executeUpdate();
        } catch (Exception ex) {
            throw ex;
        }
        return n;
    }

    public int deleteOrderDetailsByID(int id) throws Exception{
        int n = 0;
        String sql = "delete from [Order Details] where OrderID = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            n = ps.executeUpdate();
        } catch (Exception ex) {
            throw ex;
        }
        return n;
        
    }

}
