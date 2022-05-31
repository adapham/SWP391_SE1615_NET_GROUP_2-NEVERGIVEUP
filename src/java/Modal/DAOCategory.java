/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modal;

import View.Category;
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
public class DAOCategory extends ConnectDB{

    public List<Category> getAllCategory() {
        List<Category> listCate = new ArrayList<>();
        String sql = "select * from Category";
        ResultSet rs = getData(sql);
        try {
            while(rs.next()){
                Category cate = Category.builder()
                        .categoryID(rs.getInt(1))
                        .categoryName(rs.getString(2))
                        .build();
                listCate.add(cate);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listCate;
    }
    
    public static void main(String[] args) {
        DAOCategory dao = new DAOCategory();
        List<Category> list = dao.getAllCategory();
        for (Category category : list) {
            System.out.println(category);
        }
    }
}
