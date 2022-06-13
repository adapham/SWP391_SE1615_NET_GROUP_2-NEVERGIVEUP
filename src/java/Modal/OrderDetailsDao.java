/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modal;

import Entity.Product;
import java.sql.PreparedStatement;
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
                pre.executeUpdate();
            }
        } catch (Exception e) {
        }
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

}
