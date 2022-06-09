/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modal;

import Entity.FeedBack;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class FeedbackDao extends ConnectDB {

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
                        .disPlayName(rs.getString("disPlayName"))
                        .imageURL(rs.getString("imageURL"))
                        .feedbackContent(rs.getString("feedbackContent"))
                        .accountID(rs.getInt("accountID"))
                        .timeComment(rs.getString("Feedbacktime"))
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
        FeedbackDao dao = new FeedbackDao();
        List list = dao.ListFeedBackByProductID(2);
        for (Object object : list) {
            System.out.println(object);
        }
    }
}