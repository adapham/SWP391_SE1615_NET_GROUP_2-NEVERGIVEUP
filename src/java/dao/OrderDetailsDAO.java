package dao;

import Entity.OrderDetails;
import Entity.Product;
import java.util.List;

public interface OrderDetailsDAO {
    public void saveCart(int orderID, List<Product> listProductCarts) throws Exception;
    public List<OrderDetails> getDetailsBill(int OrderID) throws Exception;
    public void deleteOrderDetailByProductID(int pID) throws Exception;
    public OrderDetails getInfoBill(int OrderID) throws Exception;
    
}
