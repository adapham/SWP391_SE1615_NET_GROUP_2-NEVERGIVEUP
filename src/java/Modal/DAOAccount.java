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
public class DAOAccount extends ConnectDB{
    public List ListAllAccount() {
        List<Account> list = new ArrayList<>();
        String sql = "select * from Account";
        ResultSet rs = getData(sql);
        try {
            while(rs.next()){
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
            if(account.getUsername().equals(username) && account.getPassword().equals(password)&&account.getRole() ==1) return 1;
            if(account.getUsername().equals(username) && account.getPassword().equals(password)&&account.getRole() ==2) return 2;
            if(account.getUsername().equals(username) && account.getPassword().equals(password)&&account.getRole() ==3) return 3;
            
        }
        return 0;   
    }
    public Account GetDisplayNameByUsername(String username) {
        String sql = "select DisplayName from Account where UserName =?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, username);
            ResultSet rs = pre.executeQuery();
            while(rs.next()) {
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
            while(rs.next()) {
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
    public static void main(String[] args) {
        DAOAccount dao = new DAOAccount();
        
        Account acc = dao.GetDisplayNameByUsername("hmeasor1@ycombinator.com");
        Account acc1 = dao.GetImageURLByUsername("hmeasor1@ycombinator.com");
        System.out.println(acc);
        System.out.println(acc1);
        
    }
    
}
