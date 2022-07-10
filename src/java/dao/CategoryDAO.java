package dao;

import Entity.Category;
import java.util.List;

/**
 *
 * @author KhacBao
 */
public interface CategoryDAO {

    public List<Category> getAllCategory() throws Exception;

    public List<Category> getAllCategoryWithPaging(int page, int PAGE_SIZE) throws Exception;

    public int getTotalCategory() throws Exception;

    public String GetCategoryName(int CategoryID) throws Exception;

    public List<Category> getAllCategoryByCateID(int cateID) throws Exception;

    public int updateCategory(Category cate) throws Exception;

    public int createCategory(Category cate) throws Exception;

    public List<Category> getsearchCatePagingByCateName(String keySearch, int page, int PAGE_SIZE) throws Exception;

    public int getTotalCategoryByName(String keySearch) throws Exception;

    public int deleteCategory(int cateID) throws Exception;
}
