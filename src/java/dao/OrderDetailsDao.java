
package dao;

import Entity.OrderDetails;
import Entity.Product;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Window 10
 */
public class OrderDetailsDao extends ConnectDB {

    public void saveCart(int orderID, List<Product> listProductCarts) {
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
                int i = pre.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<OrderDetails> getDetailsBill(int OrderID) {
        OrderDetailsDao dao = new OrderDetailsDao();
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
            e.printStackTrace();
        }
        return list;
    }

    public void deleteOrderDetailByProductID(int pID) {
        String sql = "delete from [Order Details] where ProductID = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, pID);
            pre.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    //    public static void main(String[] args) {
//        List<Product> listProductCarts = new ArrayList<>();
//        OrderDetailsDao dao = new OrderDetailsDao();
//        Product pro = Product.builder()
//                .productID(11)
//                .priceAferDiscount(25.3)
//                .quantity(5)
//                .discount(2.5)
//                .build();
//        listProductCarts.add(pro);
//        int i = dao.saveCart(125, listProductCarts);
//        System.out.println(i);
//    }
    public OrderDetails getInfoBill(int OrderID) {
        OrderDetailsDao dao = new OrderDetailsDao();
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
            e.printStackTrace();
        }
        return null;
    }

}
