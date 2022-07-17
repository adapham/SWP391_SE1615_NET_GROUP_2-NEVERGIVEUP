/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import Entity.Intouch;
import dao.ConnectDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Window 10
 */
public class InTouchDAOImpl extends ConnectDB {

    public Intouch getInTouchByEmail(String email) throws Exception {
        String sql = "select [Name], [Subject], [Message] from InTouch where Email = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, email);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                Intouch in = Intouch.builder()
                        .name(rs.getString("Name"))
                        .subject(rs.getString("Subject"))
                        .message(rs.getString("Message"))
                        .email(email)
                        .build();
                return in;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
        return null;
    }

    public List<Intouch> getInTouchWithPaging(int page, int PAGE_SIZE) throws Exception {
        List<Intouch> listInTouch = new ArrayList<>();
        String sql = "select * from InTouch\n"
                + "order by ID\n"
                + "offset (?-1)*? row fetch next ? rows only";
        InTouchDAOImpl dao = new InTouchDAOImpl();
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, page);
            pre.setInt(2, PAGE_SIZE);
            pre.setInt(3, PAGE_SIZE);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                Intouch inTouch = Intouch.builder()
                        .id(rs.getInt("ID"))
                        .name(rs.getString("Name"))
                        .email(rs.getString("Email"))
                        .subject(rs.getString("Subject"))
                        .message(rs.getString("Message"))
                        .date(rs.getString("Date"))
                        .build();
                listInTouch.add(inTouch);
            }
        } catch (Exception e) {
            throw e;
        }
        return listInTouch;
    }

    public int getTotalInTouch() throws Exception {
        String sql = "select COUNT(*) from InTouch";
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
//    public static void main(String[] args) {
//        InTouchDAOImpl dao = new InTouchDAOImpl();
//        List<Intouch> list = new ArrayList<>();
//        int i;
//        try {
//            i = dao.getTotalInTouch();
//        System.out.println(i);
//        } catch (Exception ex) {
//            Logger.getLogger(InTouchDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

    public List<Intouch> getSearchInTouchPagingByName(String keySearch, int page, int PAGE_SIZE) throws Exception {
        List<Intouch> listInTouch = new ArrayList<>();
        String sql = "SELECT * FROM ( SELECT *, ROW_NUMBER() OVER (ORDER BY ID)\n"
                + "AS Seq FROM InTouch where Name like ? )t WHERE Seq BETWEEN  (?-1)*?+1 AND ?*?";
        InTouchDAOImpl dao = new InTouchDAOImpl();
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, "%" + keySearch + "%");
            pre.setInt(2, page);
            pre.setInt(3, PAGE_SIZE);
            pre.setInt(4, page);
            pre.setInt(5, PAGE_SIZE);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                Intouch inTouch = Intouch.builder()
                        .id(rs.getInt("ID"))
                        .name(rs.getString("Name"))
                        .email(rs.getString("Email"))
                        .subject(rs.getString("Subject"))
                        .message(rs.getString("Message"))
                        .build();
                listInTouch.add(inTouch);
            }
        } catch (Exception e) {
            throw e;
        }
        return listInTouch;
    }

    public int getTotalInTouchByName(String keySearch) throws Exception {
        String sql = "select COUNT(*) from InTouch where Name like ?";
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

    public List<Intouch> getDetailsInTouch(int iD) throws Exception {
        InTouchDAOImpl dao = new InTouchDAOImpl();
        List<Intouch> list = new ArrayList<>();
        String sql = "select [Name], Email, [Subject], [Message], [Date] from InTouch where ID = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, iD);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                Intouch od = Intouch.builder()
                        .name(rs.getString("Name"))
                        .email(rs.getString("Email"))
                        .subject(rs.getString("Subject"))
                        .message(rs.getString("Message"))
                        .date(rs.getString("Date"))
                        .build();
                list.add(od);
            }
        } catch (Exception e) {
            throw e;
        }
        return list;
    }

    public List<Intouch> getAllEmail() throws Exception {
        List<Intouch> list = new ArrayList<>();
        String sql = "select Email from InTouch";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                Intouch in = Intouch.builder()
                        .email(rs.getString("Email"))
                        .build();
                list.add(in);
            }
        } catch (Exception ex) {
            throw ex;
        }
        return list;
    }

    public static void send(String to, String sub,
            String msg, final String user, final String pass) {
        //create an instance of Properties Class   
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        //below mentioned mail.smtp.port is optional
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, pass);
            }
        });
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(sub);
            message.setContent(msg, "text/html");
            /* Transport class is used to deliver the message to the recipients */
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public String replyEmail(Intouch inT) {
        InTouchDAOImpl dao = new InTouchDAOImpl();
        String subject = inT.getSubject();
        String email = inT.getEmail();
        String message = inT.getMessage();
        String message1 = "<!DOCTYPE html>\n"
                + "<html lang=\"en\">\n"
                + "\n"
                + "<head>\n"
                + "</head>\n"
                + "\n"
                + "<body>\n"
                + "   <div>" + message + "</div>"
                + "<h3 style=\"color: blue;\">Thank you very much!</h3>\n"
                + "\n"
                + "</body>\n"
                + "\n"
                + "</html>";
        InTouchDAOImpl.send(email, subject, message1, "tuyennvhe151053@fpt.edu.vn", "tuyen462001");

        return null;
    }
//    public static void main(String[] args) {
//        Intouch inT = Intouch.builder()
//                .email("nvt04062001@gmail.com")
//                .subject("sad")
//                .message("sdasdas")
//                .build();
//        InTouchDAOImpl dao = new InTouchDAOImpl();
//        dao.replyEmail(inT);
//    }

}
