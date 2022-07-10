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
                        .email(rs.getString("email"))
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
            ex.printStackTrace();
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

    public List<Supplier> getAllSupplierWithPaging(int page, int PAGE_SIZE) throws Exception {
        List<Supplier> listSup = new ArrayList<>();
        String sql = "SELECT * FROM ( SELECT *,ROW_NUMBER() OVER (ORDER BY SupplierID) AS Seq\n"
                + "FROM Supplier )t\n"
                + "WHERE Seq BETWEEN  (?-1)*?+1 AND ?*?";

        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, page);
            pre.setInt(2, PAGE_SIZE);
            pre.setInt(3, page);
            pre.setInt(4, PAGE_SIZE);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                Supplier sup = Supplier.builder()
                        .supplierID(rs.getInt("supplierID"))
                        .companyName(rs.getString("companyName"))
                        .address(rs.getString("address"))
                        .phone(rs.getString("phone"))
                        .email(rs.getString("email"))
                        .build();
                listSup.add(sup);
            }
        } catch (SQLException ex) {
            throw ex;
        }
        return listSup;
    }

    public List<Supplier> getAllSupplierBySupID(int supID) throws Exception {
        List<Supplier> listSup = new ArrayList<>();
        String sql = "select * from Supplier where SupplierID = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, supID);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                Supplier sup = Supplier.builder()
                        .supplierID(rs.getInt("supplierID"))
                        .companyName(rs.getString("companyName"))
                        .address(rs.getString("address"))
                        .phone(rs.getString("phone"))
                        .email(rs.getString("email"))
                        .build();
                listSup.add(sup);
            }
        } catch (SQLException ex) {
            throw ex;
        }
        return listSup;
    }

    public int updateSupplier(Supplier sup) throws Exception {
        int n = 0;
        String sql = "UPDATE [Supplier]\n"
                + "   SET [CompanyName] = ?\n"
                + "      ,[Address] = ?\n"
                + "      ,[Phone] = ?\n"
                + "      ,[Email] = ?\n"
                + " WHERE SupplierID = ?";

        try {
            PreparedStatement pre = conn.prepareStatement(sql);

            pre.setString(1, sup.getCompanyName());
            pre.setString(2, sup.getAddress());
            pre.setString(3, sup.getPhone());
            pre.setString(4, sup.getEmail());
            pre.setInt(5, sup.getSupplierID());

            n = pre.executeUpdate();
        } catch (SQLException ex) {
            throw ex;
        }
        return n;
    }

    public int createSupplier(Supplier sup) throws Exception {
        int n = 0;
        String sql = "INSERT INTO [Supplier]\n"
                + "           ([CompanyName]\n"
                + "           ,[Address]\n"
                + "           ,[Phone]\n"
                + "           ,[Email])\n"
                + "     VALUES\n"
                + "           (?,?,?,?)";

        try {
            PreparedStatement pre = conn.prepareStatement(sql);

            pre.setString(1, sup.getCompanyName());
            pre.setString(2, sup.getAddress());
            pre.setString(3, sup.getPhone());
            pre.setString(4, sup.getEmail());

            n = pre.executeUpdate();
        } catch (SQLException ex) {
            throw ex;
        }
        return n;
    }

    public int deleteSupplier(int supID) throws Exception {
        int n = 0;
        String sql = "DELETE FROM [Supplier]\n"
                + "      WHERE SupplierID = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, supID);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            throw ex;
        }
        return n;
    }

    public List<Supplier> getsearchSupplierPagingByName(String keySearch, int page, int PAGE_SIZE) throws Exception {
        List<Supplier> listSup = new ArrayList<>();
        String sql = "SELECT * FROM ( SELECT *,ROW_NUMBER() OVER (ORDER BY SupplierID) AS Seq\n"
                + "                FROM Supplier where CompanyName like ?)t\n"
                + "                WHERE Seq BETWEEN  (?-1)*?+1 AND ?*?";

        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, "%" + keySearch + "%");
            pre.setInt(2, page);
            pre.setInt(3, PAGE_SIZE);
            pre.setInt(4, page);
            pre.setInt(5, PAGE_SIZE);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                Supplier sup = Supplier.builder()
                        .supplierID(rs.getInt("supplierID"))
                        .companyName(rs.getString("companyName"))
                        .address(rs.getString("address"))
                        .phone(rs.getString("phone"))
                        .email(rs.getString("email"))
                        .build();
                listSup.add(sup);
            }
        } catch (SQLException ex) {
            throw ex;
        }
        return listSup;
    }

    public int getTotalSupplierByName(String keySearch) throws Exception {
        String sql = "select COUNT(*) from Supplier where CompanyName like ?";
        try {
            //Đưa vào prepare
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, "%" + keySearch + "%");

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
}
