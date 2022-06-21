package dao.impl;

import Entity.Supplier;
import dao.ConnectDB;
import dao.SupplierDAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SupplierDAOImpl extends ConnectDB implements SupplierDAO {

    //Trả về danh sách tất cả nhà cung cấp
    public List<Supplier> getAllSupplier() throws Exception {
        List<Supplier> listSup = new ArrayList<>();
        String sql = "select * from Supplier";

        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                Supplier sup = Supplier.builder()
                        .supplierID(rs.getInt("supplierID"))
                        .companyName(rs.getString("companyName"))
                        .address(rs.getString("address"))
                        .phone(rs.getString("phone"))
                        .build();
                listSup.add(sup);
            }
        } catch (SQLException ex) {
            throw ex;
        }
        return listSup;
    }

    public static void main(String[] args) {
        try {
            SupplierDAOImpl dao = new SupplierDAOImpl();

            List list = dao.getAllSupplier();
            for (Object object : list) {
                System.out.println(object);
            }
        } catch (Exception ex) {
            Logger.getLogger(SupplierDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getTotalSupplier() throws Exception {
        String sql = "select COUNT(*) from Supplier";
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
}
