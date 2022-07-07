package dao.impl;

import Entity.Product;
import dao.ConnectDB;
import dao.ProductDAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl extends ConnectDB implements ProductDAO{

    //Trả về danh sách tất cả sản phẩm
    public List<Product> getAllProduct() throws Exception{
        List<Product> listPro = new ArrayList<>();
        String sql = "select * from Product";
        ProductDAOImpl dao = new ProductDAOImpl();

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
            throw ex;
        }
        return listPro;
    }

    //Trả về danh sách tất cả sản phẩm theo ProductID
    public List<Product> getAllProductByProductID(int ProductID) throws Exception{
        List<Product> listPro = new ArrayList<>();
        String sql = "select * from Product where ProductID = ?";
        ProductDAOImpl dao = new ProductDAOImpl();

        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, ProductID);
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
            throw ex;
        }
        return listPro;
    }

    //Trả về danh sách sản phẩm theo categoryID
    public List<Product> getProductsByCateID(int iCateId) throws Exception{
        List<Product> listPro = new ArrayList<>();
        String sql = "select * from Product where CategoryID = ?";
        try {
            ProductDAOImpl dao = new ProductDAOImpl();
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
            throw ex;
        }
        return listPro;
    }
    //Lấy ra 4 sản phẩm đầu tiên theo CateID
    public List<Product> getProductsByCateIDTop4(int iCateId) throws Exception{
        List<Product> listPro = new ArrayList<>();
        String sql = "select top 4 * from Product where CategoryID = ? and Quantity >=2";
        try {
            ProductDAOImpl dao = new ProductDAOImpl();
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
            throw ex;
        }
        return listPro;
    }

    //Trả về 1 danh sách phần trang theo kích thước
    public List<Product> getProductWithPaging(int page, int PAGE_SIZE) throws Exception{
        List<Product> listPro = new ArrayList<>();
        String sql = "SELECT * FROM ( SELECT *,\n"
                + " ROW_NUMBER() OVER (ORDER BY ProductID) AS Seq\n"
                + " FROM Product )t\n"
                + " WHERE Seq BETWEEN  (?-1)*?+1 AND ?*?";
        ProductDAOImpl dao = new ProductDAOImpl();
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
            throw ex;
        }
        return listPro;
    }

    //Trả về 1 danh sách phân trang theo tăng dần, giảm dần
    public List<Product> getPagingSortProduct(int page, int PAGE_SIZE, String col, String type) throws Exception{
        List<Product> listPro = new ArrayList<>();
        String sql = "SELECT * FROM ( SELECT *, ROW_NUMBER() \n"
                + "                              OVER (ORDER BY ProductID) AS Seq FROM Product \n"
                + "                              )t \n"
                + "                              WHERE Seq BETWEEN  (?-1)*?+1 AND ?*?\n"
                + "                              order by "+col+ " " + type;
        ProductDAOImpl dao = new ProductDAOImpl();
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
           throw ex;
        }
        return listPro;
    }

    //Trả về tổng số sản phẩm
    public int getTotalProduct() throws Exception{
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
            throw ex;
        }
        return 0;
    }

    //Trả về Product theo Product ID
    public Product getProductByProductID(int ProductID) throws Exception{
        ProductDAOImpl dao = new ProductDAOImpl();
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
            throw ex;
        }
        return null;
    }

    //Trả về danh sách tất cả sản phẩm theo sản phầm theo số nhập vào
    public List<Product> getTopNumberProduct(int number) throws Exception{
        List<Product> listPro = new ArrayList<>();
        String sql = "select top(?) * from Product where Quantity > 5";
        ProductDAOImpl dao = new ProductDAOImpl();
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
            throw ex;
        }
        return listPro;
    }

    //Trả về Giá sau khi discount
    public double PriceArterDiscount(int ProductID) throws Exception{
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
            throw ex;
        }
        return 0;
    }

    //Trả về danh sách phần trang theo category
    public List<Product> getAllProductsWithPagingByCateID(int iCateId, int page, int PAGE_SIZE) throws Exception{
        List<Product> listPro = new ArrayList<>();
        String sql = "SELECT * FROM ( SELECT *, ROW_NUMBER() \n"
                + "OVER (ORDER BY ProductID) AS Seq FROM Product \n"
                + "where CategoryID = ?\n"
                + ")t \n"
                + "WHERE Seq BETWEEN  (?-1)*?+1 AND ?*?";
        ProductDAOImpl dao = new ProductDAOImpl();
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
            throw ex;
        }
        return listPro;
    }

    //Trả về tổng sản phẩm theo category
    public int getTotalProductByCate(int iCateId) throws Exception{
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
            throw ex;
        }
        return 0;
    }

    //Trả về danh sách phần trang theo Search Key
    public List<Product> getSearchProductsPagingByName(String keySearch, int page, int PAGE_SIZE) throws Exception{
        List<Product> listPro = new ArrayList<>();
        String sql = "SELECT * FROM ( SELECT *, ROW_NUMBER() \n"
                + "                OVER (ORDER BY ProductID) AS Seq FROM Product \n"
                + "                where ProductName like ?\n"
                + "                )t \n"
                + "                WHERE Seq BETWEEN  (?-1)*?+1 AND ?*?";
        ProductDAOImpl dao = new ProductDAOImpl();
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
            throw ex;
        }
        return listPro;
    }

    //Trả về tổng sản phẩm theo Search Key
    public int getTotalProductByPName(String keySearch) throws Exception{
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
        } catch (SQLException ex) {
            throw ex;
        }
        return 0;
    }

    //Update Product
    public int updateProducts(Product pro) throws Exception{
        int n = 0;
        String sql = "UPDATE [Product]\n"
                + "   SET [ProductName] = ?\n"
                + "      ,[SupplierID] = ?\n"
                + "      ,[CategoryID] = ?\n"
                + "      ,[Quantity] = ?\n"
                + "      ,[UnitPrice] = ?\n"
                + "      ,[Discount] = ?\n"
                + "      ,[UnitInStock] = ?\n"
                + "      ,[Description] = ?\n"
                + "      ,[ImageURL] = ?\n"
                + "      ,[IsActive] = ?\n"
                + " WHERE ProductID = ?";

        try {
            PreparedStatement pre = conn.prepareStatement(sql);

            pre.setString(1, pro.getProductName());
            pre.setInt(2, pro.getSupplierID());
            pre.setInt(3, pro.getCategoryID());
            pre.setInt(4, pro.getQuantity());
            pre.setDouble(5, pro.getUnitPrice());
            pre.setDouble(6, pro.getDiscount());
            pre.setInt(7, pro.getUnitInStock());
            pre.setString(8, pro.getDescription());
            pre.setString(9, pro.getImageURL());
            pre.setInt(10, pro.getIsActive());
            pre.setInt(11, pro.getProductID());

            n = pre.executeUpdate();
        } catch (SQLException ex) {
            throw ex;
        }
        return n;
    }

    public int createProduct(Product pro) throws Exception{
        int n = 0;
        String sql = "INSERT INTO [Product]\n"
                + "           ([ProductName]\n"
                + "           ,[SupplierID]\n"
                + "           ,[CategoryID]\n"
                + "           ,[Quantity]\n"
                + "           ,[UnitPrice]\n"
                + "           ,[Discount]\n"
                + "           ,[UnitInStock]\n"
                + "           ,[Description]\n"
                + "           ,[ImageURL]\n"
                + "           ,[IsActive])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement pre = conn.prepareStatement(sql);

            pre.setString(1, pro.getProductName());
            pre.setInt(2, pro.getSupplierID());
            pre.setInt(3, pro.getCategoryID());
            pre.setInt(4, pro.getQuantity());
            pre.setDouble(5, pro.getUnitPrice());
            pre.setDouble(6, pro.getDiscount());
            pre.setInt(7, pro.getUnitInStock());
            pre.setString(8, pro.getDescription());
            pre.setString(9, pro.getImageURL());
            pre.setInt(10, pro.getIsActive());

            n = pre.executeUpdate();
        } catch (SQLException ex) {
            throw ex;
        }
        return n;
    }

    public static void main(String[] args) {
        try {
            ProductDAOImpl dao = new ProductDAOImpl();
            List list = dao.getAllProductByProductID(2);
//            List list = dao.searchProductByNameAndCategoryId("a", 1);
            for (Object s : list) {
                System.out.println(s);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public int deleteProduct(int pID) throws Exception{
        int n = 0;
        String sql = "delete from Product where ProductID = ?";
//        FeedbackDao daoFeedback = new FeedbackDao();
//        OrderDetailsDao daoOdDetail = new OrderDetailsDao();
//        daoFeedback.deleteFeedbackByProductID(pID);
//        daoOdDetail.deleteOrderDetailByProductID(pID);

        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, pID);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            throw ex;
        }
        return n;
    }

    public List<Product> searchProductByNameAndCategoryId(String searchKey, int cateId, int pId) throws Exception{
        List<Product> listPro = new ArrayList<>();
        String sql = "select * from Product where ProductName like ? and CategoryID = ? and ProductID != ?";
        try {
            ProductDAOImpl dao = new ProductDAOImpl();
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, "%" + searchKey + "%");
            pre.setInt(2, cateId);
            pre.setInt(3, pId);
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
            throw ex;
        }
        return listPro;
    }
}
