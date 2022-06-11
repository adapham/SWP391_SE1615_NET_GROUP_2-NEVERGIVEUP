/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modal;


import Entity.Account;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;
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
 * @author admin
 */
public class AccountDao extends ConnectDB {

    public List ListAllAccount() {
        List<Account> list = new ArrayList<>();
        String sql = "select * from Account1";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                int accountid = rs.getInt("accountid");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String displayname = rs.getString("displayname");
                String address = rs.getString("address");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String imageurl = rs.getString("imageURL");
                int roll = rs.getInt("role");
                Account acc = Account.builder()
                        .accountid(accountid)
                        .username(username)
                        .password(password)
                        .displayname(displayname)
                        .address(address)
                        .email(email)
                        .phone(phone)
                        .imageURL(imageurl)
                        .role(roll)
                        .build();

                list.add(acc);
            }
        } catch (SQLException ex) {
            //ex.printStackTrace();
             return null;
        }
        return list;
    }

    public int checkAccount(String username, String password) {
        AccountDao dao = new AccountDao();
        List<Account> list = dao.ListAllAccount();
        for (Account account : list) {
            if (account.getUsername().equals(username) && account.getPassword().equals(password) && account.getRole() == 1) {
                return 1;
            }
            if (account.getUsername().equals(username) && account.getPassword().equals(password) && account.getRole() == 2) {
                return 2;
            }
            if (account.getUsername().equals(username) && account.getPassword().equals(password) && account.getRole() == 3) {
                return 3;
            }

        }
        return 0;
    }

    public Account GetDisplayNameByUsername(String username) {
        String sql = "select DisplayName from Account where UserName =?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, username);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                Account acc = Account.builder()
                        .displayname(rs.getString(1))
                        .build();
                return acc;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public Account GetImageURLByUsername(String username) {
        String sql = "select ImageURL from Account where UserName =?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, username);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                Account acc = Account.builder()
                        .imageURL(rs.getString(1))
                        .build();
                return acc;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public Account GetAccountIDLByUsername(String username) {
        String sql = "select AccountID from Account where UserName =?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, username);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                Account acc = Account.builder()
                        .accountid(rs.getInt(1))
                        .build();
                return acc;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public Account GetPasswordByUsername(String username) {
        String sql = "select Password from Account where UserName =?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, username);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                Account acc = Account.builder()
                        .password(rs.getString(1))
                        .build();
                return acc;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public List getAccountByID(int AccountID) {
        List<Account> list = new ArrayList<>();
        String sql = "select * from Account where AccountID =?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, AccountID);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                Account acc = Account.builder()
                        .accountid(rs.getInt("accountid"))
                        .username(rs.getString("username"))
                        .password(rs.getString("password"))
                        .displayname(rs.getString("displayname"))
                        .address(rs.getString("address"))
                        .email(rs.getString("email"))
                        .phone(rs.getString("phone"))
                        .imageURL(rs.getString("imageURL"))
                        .role(rs.getInt("role"))
                        .build();
                list.add(acc);

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public int updateAccount(Account acc) {
        int n = 0;
        String sql = "update Account set DisplayName=?, Address =?,Email=?, Phone=?,ImageURL=? where AccountID =?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, acc.getDisplayname());
            pre.setString(2, acc.getAddress());
            pre.setString(3, acc.getEmail());
            pre.setString(4, acc.getPhone());
            pre.setString(5, acc.getImageURL());
            pre.setInt(6, acc.getAccountid());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public int changePassword(Account acc) {
        int n = 0;
        String sql = "update Account set Password=? where AccountID =?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, acc.getPassword());
            pre.setInt(2, acc.getAccountid());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public int updatePasswordByEmail(Account acc) {
        int n = 0;
        String sql = "update Account set Password=? where Email =?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, acc.getPassword());
            pre.setString(2, acc.getEmail());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public List ListAllUserName() {
        List<String> list = new ArrayList<String>();
        String sql = "select UserName from Account";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                String username = rs.getString(1);
                list.add(username);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public List ListAllEmail() {
        List<String> list = new ArrayList<String>();
        String sql = "select Email from Account";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                String Email = rs.getString(1);
                list.add(Email);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public List ListAllPhone() {
        List<String> list = new ArrayList<String>();
        String sql = "select Phone from Account";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                String Phone = rs.getString(1);
                list.add(Phone);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public Account GetAccountByEmail(String email) {
        String sql = "select UserName,Address,Phone from Account where Email =?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, email);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                Account acc = Account.builder()
                        .username(rs.getString(1))
                        .address(rs.getString(2))
                        .phone(rs.getString(3))
                        .email(email)
                        .build();
                return acc;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
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

    public String SendMail(Account acc) {
        AccountDao dao = new AccountDao();
        String username = acc.getUsername();
        String Address = acc.getAddress();
        String phone = acc.getPhone();
        String email = acc.getEmail();
        String password = dao.getRandom();
        String subject = "Forget PassWord.";
        String message = "<!DOCTYPE html>\n"
                + "<html lang=\"en\">\n"
                + "\n"
                + "<head>\n"
                + "</head>\n"
                + "\n"
                + "<body>\n"
                + "    <h3 style=\"color: blue;\">Forget PassWord.</h3>\n"
                + "    <div>Full Name :" + username + "</div>"
                + "    <div>Phone :" + phone + "</div>"
                + "    <div>address:" + Address + "</div>"
                + "   <div> Code :" + password + "</div>"
                + "<h3 style=\"color: blue;\">Thank you very much!</h3>\n"
                + "\n"
                + "</body>\n"
                + "\n"
                + "</html>";
        AccountDao.send(email, subject, message, "khainnhe151295@fpt.edu.vn", "01688219330Khai");
        return password;
    }

    public void RegisterAccount(Account acc) {

        String sql = "INSERT INTO [dbo].[Account]"
                + "           ([UserName]"
                + "           ,[Password]"
                + "           ,[DisplayName]"
                + "           ,[Address]"
                + "           ,[Email]"
                + "           ,[Phone]"
                + "           ,[ImageURL]"
                + "           ,[Role])"
                + "     VALUES\n"
                + "           (?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, acc.getUsername());
            pre.setString(2, acc.getPassword());
            pre.setString(3, acc.getDisplayname());
            pre.setString(4, acc.getAddress());
            pre.setString(5, acc.getEmail());
            pre.setString(6, acc.getPhone());
            pre.setString(7, acc.getImageURL());
            pre.setInt(8, acc.getRole());
            pre.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public String getRandom() {
        Random rnd = new Random();
        int number = rnd.nextInt(999999);
        return String.format("%06d", number);
    }

    public static void main(String[] args) {

        AccountDao dao = new AccountDao();
        List list = dao.ListAllAccount();
        for (Object object : list) {
            System.out.println(object);
        }

    }

}
