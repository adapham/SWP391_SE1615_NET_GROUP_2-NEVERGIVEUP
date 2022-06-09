package Modal;

import Entity.Product;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDao extends ConnectDB {

    //Trả về danh sách tất cả sản phẩm
    public List<Product> getAllProduct() {
        List<Product> listPro = new ArrayList<>();
        String sql = "select * from Product";
        ProductDao dao = new ProductDao();

        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                double price = dao.PriceArterDiscount(rs.getInt(1));
                Product pro = Product.builder()
                        .productID(rs.getInt("ProductID"))
                        .productName(rs.getString("ProductName"))
                        .supplierID(rs.getInt("SupplierID"))
                        .categoryID(rs.getInt("CategoryID"))
                        .quantity(rs.getInt("Quantity"))
                        .unitPrice(rs.getDouble("UnitPrice"))
                        .discount(rs.getDouble("Discount"))
                        .unitInStock(rs.getInt("UnitInStock"))
                        .description(rs.getString("Description"))
                        .imageURL(rs.getString("ImageURL"))
                        .isActive(rs.getInt("IsActive"))
                        .priceAferDiscount(price)
                        .build();
                listPro.add(pro);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listPro;
    }

    //Trả về danh sách sản phẩm theo categoryID
    public List<Product> getProductsByCateID(int iCateId) {
        List<Product> listPro = new ArrayList<>();
        String sql = "select * from Product where CategoryID = ?";
        try {
            ProductDao dao = new ProductDao();
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, iCateId);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                double price = dao.PriceArterDiscount(rs.getInt(1));
                Product pro = Product.builder()
                        .productID(rs.getInt(1))
                        .productName(rs.getString(2))
                        .supplierID(rs.getInt(3))
                        .categoryID(rs.getInt(4))
                        .quantity(rs.getInt(5))
                        .unitPrice(rs.getDouble(6))
                        .discount(rs.getDouble(7))
                        .unitInStock(rs.getInt(8))
                        .description(rs.getString(9))
                        .imageURL(rs.getString(10))
                        .isActive(rs.getInt(11))
                        .priceAferDiscount(price)
                        .build();
                listPro.add(pro);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listPro;
    }

    //Trả về 1 danh sách phần trang theo kích thước
    public List<Product> getProductWithPaging(int page, int PAGE_SIZE) {
        List<Product> listPro = new ArrayList<>();
        String sql = "SELECT * FROM ( SELECT *,\n"
                + " ROW_NUMBER() OVER (ORDER BY ProductID) AS Seq\n"
                + " FROM Product )t\n"
                + " WHERE Seq BETWEEN  (?-1)*?+1 AND ?*?";
        ProductDao dao = new ProductDao();
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, page);
            pre.setInt(2, PAGE_SIZE);
            pre.setInt(3, page);
            pre.setInt(4, PAGE_SIZE);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                double price = dao.PriceArterDiscount(rs.getInt(1));
                Product pro = Product.builder()
                        .productID(rs.getInt(1))
                        .productName(rs.getString(2))
                        .supplierID(rs.getInt(3))
                        .categoryID(rs.getInt(4))
                        .quantity(rs.getInt(5))
                        .unitPrice(rs.getDouble(6))
                        .discount(rs.getDouble(7))
                        .unitInStock(rs.getInt(8))
                        .description(rs.getString(9))
                        .imageURL(rs.getString(10))
                        .isActive(rs.getInt(11))
                        .priceAferDiscount(price)
                        .build();
                listPro.add(pro);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listPro;
    }
    //Trả về tổng số sản phẩm
    public int getTotalProduct() {
        String sql = "select COUNT(*) from Product";
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
            ex.printStackTrace();
        }
        return 0;
    }
    //Trả về Product theo Product ID
    public Product getProductByProductID(int ProductID) {
        ProductDao dao = new ProductDao();
        String sql = "select * from Product where ProductID =?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, ProductID);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                double price = dao.PriceArterDiscount(ProductID);
                Product pro = Product.builder()
                        .productID(rs.getInt(1))
                        .productName(rs.getString(2))
                        .supplierID(rs.getInt(3))
                        .categoryID(rs.getInt(4))
                        .quantity(rs.getInt(5))
                        .unitPrice(rs.getDouble(6))
                        .discount(rs.getDouble(7))
                        .unitInStock(rs.getInt(8))
                        .description(rs.getString(9))
                        .imageURL(rs.getString(10))
                        .isActive(rs.getInt(11))
                        .priceAferDiscount(price)
                        .build();
                return pro;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    //Trả về danh sách tất cả sản phẩm theo sản phầm theo số nhập vào
    public List<Product> getTopNumberProduct(int number) {
        List<Product> listPro = new ArrayList<>();
        String sql = "select top(?) * from Product where Quantity > 5";
        ProductDao dao = new ProductDao();
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, number);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                double price = dao.PriceArterDiscount(rs.getInt(1));
                Product pro = Product.builder()
                        .productID(rs.getInt(1))
                        .productName(rs.getString(2))
                        .supplierID(rs.getInt(3))
                        .categoryID(rs.getInt(4))
                        .quantity(rs.getInt(5))
                        .unitPrice(rs.getDouble(6))
                        .discount(rs.getDouble(7))
                        .unitInStock(rs.getInt(8))
                        .description(rs.getString(9))
                        .imageURL(rs.getString(10))
                        .isActive(rs.getInt(11))
                        .priceAferDiscount(price)
                        .build();
                listPro.add(pro);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listPro;
    }
    //Trả về Giá sau khi discount
    public double PriceArterDiscount(int ProductID) {
        double price = 0;
        String sql = "select UnitPrice,Discount from Product where ProductID =?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, ProductID);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                if (rs.getDouble(2) == 0) {
                    price = rs.getDouble(1);
                } else {
                    price = Math.round((rs.getDouble(1) - rs.getDouble(1) * rs.getDouble(2)) * 100.0 / 100.0);
                }
                return price;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }
    //Trả về danh sách phần trang theo category
    public List<Product> getAllProductsWithPagingByCateID(int iCateId, int page, int PAGE_SIZE) {
        List<Product> listPro = new ArrayList<>();
        String sql = "SELECT * FROM ( SELECT *, ROW_NUMBER() \n"
                + "OVER (ORDER BY ProductID) AS Seq FROM Product \n"
                + "where CategoryID = ?\n"
                + ")t \n"
                + "WHERE Seq BETWEEN  (?-1)*?+1 AND ?*?";
        ProductDao dao = new ProductDao();
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, iCateId);
            pre.setInt(2, page);
            pre.setInt(3, PAGE_SIZE);
            pre.setInt(4, page);
            pre.setInt(5, PAGE_SIZE);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                double price = dao.PriceArterDiscount(rs.getInt(1));
                Product pro = Product.builder()
                        .productID(rs.getInt(1))
                        .productName(rs.getString(2))
                        .supplierID(rs.getInt(3))
                        .categoryID(rs.getInt(4))
                        .quantity(rs.getInt(5))
                        .unitPrice(rs.getDouble(6))
                        .discount(rs.getDouble(7))
                        .unitInStock(rs.getInt(8))
                        .description(rs.getString(9))
                        .imageURL(rs.getString(10))
                        .isActive(rs.getInt(11))
                        .priceAferDiscount(price)
                        .build();
                listPro.add(pro);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listPro;
    }
    //Trả về tổng sản phẩm theo category
    public int getTotalProductByCate(int iCateId) {
        String sql = "select COUNT(*) from Product where CategoryID = ?";
        try {
            //Đưa vào prepare
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, iCateId);
            //Đưa vào ResultSet
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int count = rs.getInt(1);
                return count;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }
    //Trả về danh sách phần trang theo Search Key
    public List<Product> getSearchProductsPagingByName(String keySearch, int page, int PAGE_SIZE) {
        List<Product> listPro = new ArrayList<>();
        String sql = "SELECT * FROM ( SELECT *, ROW_NUMBER() \n"
                + "                OVER (ORDER BY ProductID) AS Seq FROM Product \n"
                + "                where ProductName like ?\n"
                + "                )t \n"
                + "                WHERE Seq BETWEEN  (?-1)*?+1 AND ?*?";
        ProductDao dao = new ProductDao();
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, "%" + keySearch + "%");
            pre.setInt(2, page);
            pre.setInt(3, PAGE_SIZE);
            pre.setInt(4, page);
            pre.setInt(5, PAGE_SIZE);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                double price = dao.PriceArterDiscount(rs.getInt(1));
                Product pro = Product.builder()
                        .productID(rs.getInt(1))
                        .productName(rs.getString(2))
                        .supplierID(rs.getInt(3))
                        .categoryID(rs.getInt(4))
                        .quantity(rs.getInt(5))
                        .unitPrice(rs.getDouble(6))
                        .discount(rs.getDouble(7))
                        .unitInStock(rs.getInt(8))
                        .description(rs.getString(9))
                        .imageURL(rs.getString(10))
                        .isActive(rs.getInt(11))
                        .priceAferDiscount(price)
                        .build();
                listPro.add(pro);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listPro;
    }
    //Trả về tổng sản phẩm theo Search Key
    public int getTotalProductByPName(String keySearch) {
        String sql = "select COUNT(*) from Product where ProductName like ?";
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
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }
}