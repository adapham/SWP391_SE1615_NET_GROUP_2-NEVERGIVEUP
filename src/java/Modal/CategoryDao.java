/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modal;

import Entity.Category;
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
public class CategoryDao extends ConnectDB {
    //Trả về 1 danh sách category có trong DB
    public List<Category> getAllCategory() {
        List<Category> listCate = new ArrayList<>();
        String sql = "select * from Category";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                Category cate = Category.builder()
                        .categoryID(rs.getInt("categoryID"))
                        .categoryName(rs.getString("categoryName"))
                        .build();
                listCate.add(cate);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listCate;
    }
    //Lấy ra tên Category theo CategoryID
    public String GetCategoryName(int CategoryID) {
        String CategoryName;
        String sql = "select CategoryName from Category where CategoryID =?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, CategoryID);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                CategoryName = rs.getString("categoryName");
             return CategoryName; 
            }
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;

    }
    public static void main(String[] args) {
        CategoryDao dao = new CategoryDao();
        List list = dao.getAllCategory();
        for (Object object : list) {
            System.out.println(object);
        }
    }
}
