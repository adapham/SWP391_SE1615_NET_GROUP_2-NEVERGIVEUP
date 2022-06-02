/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modal;

import View.Product;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class DAOProduct extends ConnectDB {

    //Get All Prpduct
    public List<Product> getAllProduct() {
        List<Product> listPro = new ArrayList<>();
        String sql = "select * from Product";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                Product pro = Product.builder()
                        .productID(rs.getInt(1))
                        .productName(rs.getString(2))
                        .supplierID(rs.getInt(3))
                        .categoryID(rs.getInt(4))
                        .quantity(rs.getInt(5))
                        .unitPrice(rs.getDouble(6))
                        .unitInStock(rs.getInt(7))
                        .description(rs.getString(8))
                        .imageURL(rs.getString(9))
                        .isActive(rs.getInt(10))
                        .build();
                listPro.add(pro);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listPro;
    }

    //Get All Product By ID
    public List<Product> getProductsByID(int iCateId) {
        List<Product> listPro = new ArrayList<>();
        String sql = "select * from Product where CategoryID = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, iCateId);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                Product pro = Product.builder()
                        .productID(rs.getInt(1))
                        .productName(rs.getString(2))
                        .supplierID(rs.getInt(3))
                        .categoryID(rs.getInt(4))
                        .quantity(rs.getInt(5))
                        .unitPrice(rs.getDouble(6))
                        .unitInStock(rs.getInt(7))
                        .description(rs.getString(8))
                        .imageURL(rs.getString(9))
                        .isActive(rs.getInt(10))
                        .build();
                listPro.add(pro);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listPro;
    }

    //Get Top 3 Product
    public List<Product> getTop8() {
        List<Product> listPro = new ArrayList<>();
        String sql = "select top 8 * from Product";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                Product pro = Product.builder()
                        .productID(rs.getInt(1))
                        .productName(rs.getString(2))
                        .supplierID(rs.getInt(3))
                        .categoryID(rs.getInt(4))
                        .quantity(rs.getInt(5))
                        .unitPrice(rs.getDouble(6))
                        .unitInStock(rs.getInt(7))
                        .description(rs.getString(8))
                        .imageURL(rs.getString(9))
                        .isActive(rs.getInt(10))
                        .build();
                listPro.add(pro);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listPro;
    }

    //Get Next 3 Product
    public List<Product> getProductWithPaging(int page, int PAGE_SIZE) {
        List<Product> listPro = new ArrayList<>();
        String sql = "SELECT * FROM ( SELECT *,\n"
                + " ROW_NUMBER() OVER (ORDER BY ProductID) AS Seq\n"
                + " FROM Product )t\n"
                + " WHERE Seq BETWEEN  (?-1)*?+1 AND ?*?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, page);
            pre.setInt(2, PAGE_SIZE);
            pre.setInt(3, page);
            pre.setInt(4, PAGE_SIZE);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                Product pro = Product.builder()
                        .productID(rs.getInt(1))
                        .productName(rs.getString(2))
                        .supplierID(rs.getInt(3))
                        .categoryID(rs.getInt(4))
                        .quantity(rs.getInt(5))
                        .unitPrice(rs.getDouble(6))
                        .unitInStock(rs.getInt(7))
                        .description(rs.getString(8))
                        .imageURL(rs.getString(9))
                        .isActive(rs.getInt(10))
                        .build();
                listPro.add(pro);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listPro;
    }

    //Main
    public static void main(String[] args) {
        DAOProduct dao = new DAOProduct();
        List<Product> list = dao.getProductsByID(1);
        for (Product category : list) {
            System.out.println(category);
        }
    }

    public int getTotalProduct() {
        String sql = "select COUNT(*) from Product";
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
    public Product getProductById(String pid) {
        List<Product> List = new ArrayList<>();
        try {
            String sql = "select * from Product where ProductID  = ? ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, pid);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Product pro = Product.builder()
                        .productID(rs.getInt(1))
                        .productName(rs.getString(2))
                        .supplierID(rs.getInt(3))
                        .categoryID(rs.getInt(4))
                        .quantity(rs.getInt(5))
                        .unitPrice(rs.getDouble(6))
                        .unitInStock(rs.getInt(7))
                        .description(rs.getString(8))
                        .imageURL(rs.getString(9))
                        .isActive(rs.getInt(10))
                        .build();
                return pro;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
