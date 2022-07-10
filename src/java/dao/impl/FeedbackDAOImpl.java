/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import Entity.FeedBack;
import Entity.Intouch;
import dao.ConnectDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import dao.FeedbackDAO;

/**
 *
 * @author admin
 */
public class FeedbackDAOImpl extends ConnectDB implements FeedbackDAO {

    public List ListFeedBackByProductID(int productID) throws Exception{
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
            throw ex;
        }
        return list;
    }

    public void InsertFeedBack(FeedBack feeback) throws Exception{

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
        } catch (SQLException ex) {
            throw ex;
        }
    }

    public void InsertIntouch(Intouch intouch) throws Exception{

        String sql = "INSERT INTO [dbo].[InTouch]\n"
                + "           ([Name]\n"
                + "           ,[Email]\n"
                + "           ,[Subject]\n"
                + "           ,[Message]\n"
                + "           ,[Date])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, intouch.getName());
            pre.setString(2, intouch.getEmail());
            pre.setString(3, intouch.getSubject());
            pre.setString(4, intouch.getMessage());
            pre.setString(5, intouch.getDate());
            pre.executeUpdate();
        } catch (SQLException ex) {
           throw ex;
        }
    }

    public void deleteFeedbackByProductID(int pID) throws Exception{
        String sql = "delete from Feedback where ProductID = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, pID);
            pre.executeUpdate();
        } catch (SQLException ex) {
            throw ex;
        }
    }
    public int getTotalFeedBack() throws Exception{
        String sql = "select COUNT(*) from Feedback";
        try {
            //Đưa vào prepare
            PreparedStatement pre = conn.prepareStatement(sql);

            //Đưa vào ResultSet
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int count = rs.getInt(1);
                return count;
            }
        } catch (SQLException ex) {
            throw ex;
        }
        return 0;
    }

//    public static void main(String[] args) {
//        FeedbackDAOImpl dao = new FeedbackDAOImpl();
//        Intouch in = Intouch.builder()
//                .name("Nguyen Van Tuyen")
//                .email("nvt04062001@gmail.com")
//                .subject("Order")
//                .message("Mon an rat ngon")
//                .build();
//        System.out.println(dao.InsertIntouch(in));
//        
//    }

    public List<FeedBack> getFeedBackWithPaging(int page, int PAGE_SIZE) throws Exception {
        List<FeedBack> listFeedBack = new ArrayList<>();
        String sql = "select f.FeedbackID, ac.DisplayName ,p.ProductName , f.Feedbacktime from Feedback f\n"
                + "join Account ac on ac.AccountID = f.AccountID\n"
                + "join Product p on p.ProductID = f.ProductID\n"
                + "order by f.FeedbackID\n"
                + "offset (?-1)*? row fetch next ? rows only";
        FeedbackDAOImpl dao = new FeedbackDAOImpl();
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, page);
            pre.setInt(2, PAGE_SIZE);
            pre.setInt(3, PAGE_SIZE);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                FeedBack feeback = FeedBack.builder()
                        .feedbackID(rs.getInt("FeedbackID"))
                        .disPlayName(rs.getString("DisplayName"))
                        .productName(rs.getString("ProductName"))
                        .timeComment(rs.getString("Feedbacktime"))
                        .build();
                listFeedBack.add(feeback);
            }
        } catch (Exception e) {
            throw e;
        }
        return listFeedBack;
    }

    public List<FeedBack> getSearchFeedBackPagingByName(String keySearch, int page, int PAGE_SIZE) throws Exception {
        List<FeedBack> listFeedBack = new ArrayList<>();
        String sql = "SELECT * FROM ( SELECT f.FeedbackID, ac.DisplayName ,p.ProductName , f.Feedbacktime, ROW_NUMBER() OVER (ORDER BY FeedbackID)\n"
                + "AS Seq FROM Feedback f\n"
                + "inner join Account ac on ac.AccountID = f.AccountID\n"
                + "inner join Product p on p.ProductID = f.ProductID where p.ProductName like ? ) temp WHERE Seq BETWEEN (?-1)*?+1 AND ?*?";
        FeedbackDAOImpl dao = new FeedbackDAOImpl();
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, "%" + keySearch + "%");
            pre.setInt(2, page);
            pre.setInt(3, PAGE_SIZE);
            pre.setInt(4, page);
            pre.setInt(5, PAGE_SIZE);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                FeedBack feeback = FeedBack.builder()
                        .feedbackID(rs.getInt("FeedbackID"))
                        .disPlayName(rs.getString("DisplayName"))
                        .productName(rs.getString("ProductName"))
                        .timeComment(rs.getString("Feedbacktime"))
                        .build();
                listFeedBack.add(feeback);
            }
        } catch (Exception e) {
            throw e;
        }
        return listFeedBack;
    }

    public int getTotalFeedBackByName(String keySearch) throws Exception {
        String sql = "select COUNT(*) from Feedback f\n"
                + "join Account ac on ac.AccountID = f.AccountID\n"
                + "join Product p on p.ProductID = f.ProductID\n"
                + "where p.ProductName like ?";
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

    public int deleteFeedBackByID(String feedbackid) throws Exception {
        int n = 0;
        String sql = "delete from Feedback where FeedbackID = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, feedbackid);
            n = ps.executeUpdate();
        } catch (Exception ex) {
            throw ex;
        }
        return n;
    }
}
