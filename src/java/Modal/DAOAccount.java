/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modal;

import View.Account;
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
public class DAOAccount extends ConnectDB {

    public List ListAllAccount() {
        List<Account> list = new ArrayList<>();
        String sql = "select * from Account";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                int accountid = rs.getInt(1);
                String username = rs.getString(2);
                String password = rs.getString(3);
                String displayname = rs.getString(4);
                String address = rs.getString(5);
                String email = rs.getString(6);
                String phone = rs.getString(7);
                String imageurl = rs.getString(8);
                int roll = rs.getInt(9);
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
            ex.printStackTrace();
        }
        return list;
    }

    public int checkAccount(String username, String password) {
        DAOAccount dao = new DAOAccount();
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
            while(rs.next()) {
                Account acc = Account.builder()
                        .accountid(rs.getInt(1))
                        .username(rs.getString(2))
                        .password(rs.getString(3))
                        .displayname(rs.getString(4))
                        .address(rs.getString(5))
                        .email(rs.getString(6))
                        .phone(rs.getString(7))
                        .imageURL(rs.getString(8))
                        .role(rs.getInt(9))
                        .build();
                list.add(acc);
                
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
    public int updateAccount(Account acc) {
        int n =0;
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
        int n =0;
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
    public void RegisterAccount(Account acc) {
        
        String sql = "INSERT INTO [dbo].[Account]" +
"           ([UserName]" +
"           ,[Password]" +
"           ,[DisplayName]" +
"           ,[Address]" +
"           ,[Email]" +
"           ,[Phone]" +
"           ,[ImageURL]" +
"           ,[Role])" +
"     VALUES\n" +
"           (?,?,?,?,?,?,?,?)";
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
    
    public static void main(String[] args) {
       
        DAOAccount dao = new DAOAccount();
        Account acc = Account.builder()
                .username("NNK")
                .password("123")
                .displayname("K")
                .address("H")
                .email("ngockhaia11@gmail.com")
                .phone("0385264896")
                .imageURL("")
                .role(1)
                .build();
           // int a =dao.RegisterAccount(acc);
        //System.out.println(a);

    }

}
