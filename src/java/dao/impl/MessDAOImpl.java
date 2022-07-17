package dao.impl;

import Entity.Order;
import com.sun.el.lang.ELArithmetic;
import dao.ConnectDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MessDAOImpl extends ConnectDB {

    public int insertMess(int IdCustomer, int IdEmployee, String content) {
        int i = 0;
        String sql = "INSERT INTO [dbo].[Mess]\n"
                + "           ([IdCustomer]\n"
                + "           ,[IdEmployee]\n"
                + "           ,[Content])\n"
                + "     VALUES\n"
                + "           (?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pre.setInt(1, IdCustomer);
            pre.setInt(2, IdEmployee);
            pre.setString(3, content);
            i = pre.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }

    public int updateMess(int IdCustomer, int IdEmployee, String content) {
        int n = 0;
        String sql = "UPDATE [Mess]\n"
                + "   SET [Content] = ?\n"
                + " WHERE [IdCustomer]=? and IdEmployee =?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(2, IdCustomer);
            pre.setInt(3, IdEmployee);
            pre.setString(1, content);
            n = pre.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public String getMessByID(int IdCustomer, int IdEmployee) {
        String sql = "SELECT [Content]\n"
                + "  FROM [Mess] WHERE [IdCustomer]=? and IdEmployee =?";
        try {
            //Đưa vào prepare
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, IdCustomer);
            pre.setInt(2, IdEmployee);
            //Đưa vào ResultSet
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                String temp = rs.getString(1);
                return temp;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "";
    }

    public int getIdEmByIdCus(int IdCustomer) {
        String sql = "SELECT IdEmployee \n"
                + "  FROM [Mess] WHERE [IdCustomer]=?";
        try {
            //Đưa vào prepare
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, IdCustomer);
            //Đưa vào ResultSet
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int temp = rs.getInt(1);
                return temp;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public List getIdCus() {
        String sql = "SELECT * from [Mess]";
        List<Integer> list = new ArrayList<>();
        try {
            //Đưa vào prepare
            PreparedStatement pre = conn.prepareStatement(sql);
            //pre.setInt(1, IdCustomer);
            //Đưa vào ResultSet
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int temp = rs.getInt(1);
                list.add(temp);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public List getIdCusByEmployee(int employeeID) throws Exception {
        String sql = "SELECT * from [Mess] where IdEmployee =" + employeeID;
        List<Integer> list = new ArrayList<>();
        try {
            //Đưa vào prepare
            PreparedStatement pre = conn.prepareStatement(sql);
            //pre.setInt(1, IdCustomer);
            //Đưa vào ResultSet
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int temp = rs.getInt(1);
                list.add(temp);
            }
        } catch (SQLException ex) {
            throw ex;
        }
        return list;
    }

    public static boolean isNumeric(char str) {
        try {
            Integer.parseInt(Character.toString(str));
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void main(String[] args) {

        try {
            MessDAOImpl dao = new MessDAOImpl();
//        String myStr = "44Hello";
//        int k= 33;
//        char ck[] = myStr.toCharArray();
//        int index = 0;
//        for (int i = 0; i < ck.length; i++) {
//            if (isNumeric(ck[i])){
//            } 
//            else{
//                index=i;
//                 break;
//            }    
//        }
// System.out.println( dao.insertMess(1, 6, "ewe"));
//for (String a : arrOfStr)
//        List<String> listMess = new ArrayList<>();
//           String str=dao.getMessByID(3, 6);
//           String[] arrOfStr = str.split("~");
// 
//            for (String a : arrOfStr){
//                listMess.add(a);
//            }
//             for (String a1 : listMess){
//                 System.out.println(a1);
//            }
            List<String> listMess = new ArrayList<>();
                String str = dao.getMessByID(10, 6);
                String[] arrOfStr = str.split("~");
                for (String a : arrOfStr) {
                    listMess.add(a);
                }
            
               
               
            
        } catch (Exception ex) {
            Logger.getLogger(MessDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
