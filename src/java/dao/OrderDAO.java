package dao;

import Entity.Order;
import java.util.List;

public interface OrderDAO {
    public int insertOrderID(Order order) throws Exception;
    public List<Order> listAllOrders() throws Exception;
    public int updateStatus(int status, int orId) throws Exception;
}
