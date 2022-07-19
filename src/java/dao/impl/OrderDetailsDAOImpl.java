
package dao.impl;

import Entity.OrderDetails;
import Entity.Product;
import dao.ConnectDB;
import dao.OrderDetailsDAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Window 10
 */
public class OrderDetailsDAOImpl extends ConnectDB implements OrderDetailsDAO{

    public void saveCart(int orderID, List<Product> listProductCarts) throws Exception{
        try {
            String sql = "INSERT INTO [FoodOrderOnline].[dbo].[Order Details]\n"
                    + "           ([OrderID]\n"
                    + "           ,[ProductID]\n"
                    + "           ,[Price]\n"
                    + "           ,[Quantity]\n"
                    + "           ,[Discount])\n"
                    + "     VALUES\n"   
                    + "           (?,?,?,?,?)";
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, orderID);
            for (Product list : listProductCarts) {
                pre.setInt(2, list.getProductID());
                pre.setDouble(3, list.getUnitPrice());
                pre.setInt(4, list.getQuantity());
                pre.setDouble(5, list.getDiscount());
                pre.executeUpdate();
            }
        } catch (Exception e) {
            throw e;
        }
    }
    public List<OrderDetails> getListAllDetail(int OrderID) {
        OrderDetailsDAOImpl dao = new OrderDetailsDAOImpl();
        List<OrderDetails> list = new ArrayList<>();
        String sql = "select p.ProductName,p.ImageURL,od.Price,od.Quantity,(od.Price*od.Quantity)'Total' from [Order] o\n" +
"                join [Order Details] od on od.OrderID = o.OrderID\n" +
"                join Product p on p.ProductID = od.ProductID\n" +
"				join Status s on s.StatusID = o.[Status]\n" +
"                where o.OrderID = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, OrderID);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                OrderDetails od1 = OrderDetails.builder()
                        .productName(rs.getString("ProductName"))
                        .ImageURL(rs.getString("ImageURL"))
                        .price(rs.getDouble("Price"))
                        .quantity(rs.getInt("Quantity"))
                        .total(rs.getDouble("Total"))
                        .build();
                list.add(od1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<OrderDetails> getDetailsBill(int OrderID) throws Exception{
        OrderDetailsDAOImpl dao = new OrderDetailsDAOImpl();
        List<OrderDetails> list = new ArrayList<>();
        String sql = "select ac.DisplayName, ac.[Address], ac.Email, ac.Phone, o.OrderDate, od.OrderID, p.ProductName, od.Price, od.Quantity, od.Discount, (od.Price*od.Quantity)'Total', s.StatusID from [Order] o\n"
                + "join Account ac on ac.AccountID = o.AccountID\n"
                + "join [Order Details] od on od.OrderID = o.OrderID\n"
                + "join Product p on p.ProductID = od.ProductID\n"
                + "join [Status] s on s.StatusID = o.Status \n"
                + "where od.OrderID = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, OrderID);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                OrderDetails od = OrderDetails.builder()
                        .displayname(rs.getString("DisplayName"))
                        .address(rs.getString("Address"))
                        .email(rs.getString("Email"))
                        .phone(rs.getString("Phone"))
                        .orderDate(rs.getString("OrderDate"))
                        .orderID(rs.getInt("OrderID"))
                        .productName(rs.getString("ProductName"))
                        .price(rs.getDouble("Price"))
                        .quantity(rs.getInt("Quantity"))
                        .discount(rs.getDouble("Discount"))
                        .total(rs.getDouble("Total"))
                        .status(rs.getInt("StatusID"))
                        .build();
                list.add(od);
            }
        } catch (Exception e) {
            throw e;
        }
        return list;
    }

    public void deleteOrderDetailByProductID(int pID) throws Exception{
        String sql = "delete from [Order Details] where ProductID = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, pID);
            pre.executeUpdate();
        } catch (Exception ex) {
            throw ex;
        }
    }
//        public static void main(String[] args) {
//        List<Product> listProductCarts = new ArrayList<>();
//        OrderDetailsDAOImpl dao = new OrderDetailsDAOImpl();
//        Product pro = Product.builder()
//                .productID(11)
//                .priceAferDiscount(25.3)
//                .quantity(5)
//                .discount(2.5)
//                .build();
//        listProductCarts.add(pro);
//        try {
//            dao.saveCart(17, listProductCarts);
//        } catch (Exception ex) {
//            Logger.getLogger(OrderDetailsDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    public OrderDetails getInfoBill(int OrderID) throws Exception{
        OrderDetailsDAOImpl dao = new OrderDetailsDAOImpl();
        String sql = "select ac.DisplayName, ac.[Address], ac.Email, ac.Phone, o.OrderDate, od.OrderID, p.ProductName, od.Price, od.Quantity, od.Discount, (od.Price*od.Quantity)'Total', s.StatusID from [Order] o\n"
                + "join Account ac on ac.AccountID = o.AccountID\n"
                + "join [Order Details] od on od.OrderID = o.OrderID\n"
                + "join Product p on p.ProductID = od.ProductID\n"
                + "join Status s on s.StatusID = o.[Status]\n"
                + "where od.OrderID = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, OrderID);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                OrderDetails info = OrderDetails.builder()
                        .displayname(rs.getString("DisplayName"))
                        .address(rs.getString("Address"))
                        .email(rs.getString("Email"))
                        .phone(rs.getString("Phone"))
                        .orderDate(rs.getString("OrderDate"))
                        .status(rs.getInt("StatusID"))
                        .build();
                return info;

            }
        } catch (Exception e) {
            throw e;
        }
        return null;
    }
    public static void main(String[] args) {
//       OrderDetailsDao dao = new OrderDetailsDao();
//       OrderDetails info = dao.getInfoBill(1);
//        System.out.println(info);
    }

}
