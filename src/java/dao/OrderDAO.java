package dao;

import Entity.Order;
import java.util.List;

public interface OrderDAO {
    public int insertOrderID(Order order) throws Exception;
    public List<Order> listAllOrders() throws Exception;
    public int updateStatus(int status, int orId) throws Exception;
    public int getTotalOrder() throws Exception;
    public int TotalBill() throws Exception;
    public List<Order> getOrderWithPaging(int page, int PAGE_SIZE) throws Exception;
    public List<Order> getSearchOrderPagingByAddress(String keySearch, int page, int PAGE_SIZE) throws Exception;
    public int getTotalOrderByAddress(String keySearch) throws Exception;
}
