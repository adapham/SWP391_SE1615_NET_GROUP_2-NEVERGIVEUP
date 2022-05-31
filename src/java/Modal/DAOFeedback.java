/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modal;

import View.FeedBack;
import View.Product;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class DAOFeedback extends ConnectDB {

    public Product getProductByProductID(int ProductID) {
        String sql = "select * from Product where ProductID =?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, ProductID);
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
                return pro;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public List ListFeedBackByProductID(int productID) {
        List<FeedBack> list = new ArrayList<>();
        String sql = "select Account.DisplayName ,Account.ImageURL,Feedback.FeedbackContent,"
                + "Account.AccountID, Feedback.Feedbacktime from Feedback join Account \n"
                + "on Feedback.AccountID = Account.AccountID\n"
                + "where Feedback.ProductID =? order by Feedback.Feedbacktime desc";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, productID);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                FeedBack feedback = FeedBack.builder()
                        .disPlayName(rs.getString(1))
                        .imageURL(rs.getString(2))
                        .feedbackContent(rs.getString(3))
                        .accountID(rs.getInt(4))
                        .timeComment(rs.getString(5))
                        .build();
                list.add(feedback);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public void InsertFeedBack(FeedBack feeback) {

        String sql = "INSERT INTO [dbo].[Feedback]\n"
                + "           ([FeedbackContent]\n"
                + "           ,[ProductID]\n"
                + "           ,[AccountID]\n"
                + "           ,[Feedbacktime])\n"
                + "     VALUES\n"
                + "           (?,?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, feeback.getFeedbackContent());
            pre.setInt(2, feeback.getProductID());
            pre.setInt(3, feeback.getAccountID());
            pre.setString(4, feeback.getTimeComment());
            pre.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        DAOFeedback dao = new DAOFeedback();
        List list = dao.ListFeedBackByProductID(2);
        for (Object object : list) {
            System.out.println(object);
        }
    }
}
