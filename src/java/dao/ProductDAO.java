package dao;

import Entity.Product;
import java.util.List;

public interface ProductDAO {

    public List<Product> getAllProduct() throws Exception;

    public List<Product> getAllProductByProductID(int ProductID) throws Exception;

    public List<Product> getProductsByCateID(int iCateId) throws Exception;

    public List<Product> getProductWithPaging(int page, int PAGE_SIZE) throws Exception;

    //Trả về 1 danh sách phân trang theo tăng dần, giảm dần
    public List<Product> getPagingSortProduct(int page, int PAGE_SIZE, String col, String type) throws Exception;

    //Trả về tổng số sản phẩm
    public int getTotalProduct() throws Exception;

    //Trả về Product theo Product ID
    public Product getProductByProductID(int ProductID) throws Exception;

    public List<Product> getProductsByCateIDTop4(int iCateId) throws Exception;

    //Trả về danh sách tất cả sản phẩm theo sản phầm theo số nhập vào
    public List<Product> getTopNumberProduct(int number) throws Exception;

    //Trả về Giá sau khi discount
    public double PriceArterDiscount(int ProductID) throws Exception;

    //Trả về danh sách phần trang theo category
    public List<Product> getAllProductsWithPagingByCateID(int iCateId, int page, int PAGE_SIZE) throws Exception;

    //Trả về tổng sản phẩm theo category
    public int getTotalProductByCate(int iCateId) throws Exception;

    //Trả về danh sách phần trang theo Search Key
    public List<Product> getSearchProductsPagingByName(String keySearch, int page, int PAGE_SIZE) throws Exception;

    //Trả về tổng sản phẩm theo Search Key
    public int getTotalProductByPName(String keySearch) throws Exception;

    //Update Create Delete Product
    public int updateProducts(Product pro) throws Exception;

    public int createProduct(Product pro) throws Exception;

    public int deleteProduct(int pID) throws Exception;
    
    public List<Product> searchProductByNameAndCategoryId(String searchKey, int cateId, int pId) throws Exception;

}
