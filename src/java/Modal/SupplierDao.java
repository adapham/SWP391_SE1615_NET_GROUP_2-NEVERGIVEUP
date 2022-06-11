package Modal;

import Entity.Product;
import Entity.Supplier;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierDao extends ConnectDB{
    
    //Trả về danh sách tất cả nhà cung cấp
    public List<Supplier> getAllSupplier() {
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
            ex.printStackTrace();
        }
        return listSup;
    }
    
    public static void main(String[] args) {
        SupplierDao dao = new SupplierDao();
        
        List list = dao.getAllSupplier();
        for (Object object : list) {
            System.out.println(object);
        }
    }
}
