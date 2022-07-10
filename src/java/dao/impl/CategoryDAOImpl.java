package dao.impl;

import Entity.Category;
import dao.CategoryDAO;
import dao.ConnectDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class CategoryDAOImpl extends ConnectDB implements CategoryDAO {

    //Trả về 1 danh sách category có trong DB
    public List<Category> getAllCategory() throws Exception {
        List<Category> listCate = new ArrayList<>();
        String sql = "select * from Category";
        try {
            ResultSet rs = getData(sql);
            while (rs.next()) {
                Category cate = Category.builder()
                        .categoryID(rs.getInt("categoryID"))
                        .categoryName(rs.getString("categoryName"))
                        .description(rs.getString("description"))
                        .build();
                listCate.add(cate);
            }
        } catch (SQLException ex) {
            throw ex;
        }
        return listCate;
    }

    //Trả về 1 danh sách phần trang theo kích thước
    public List<Category> getAllCategoryWithPaging(int page, int PAGE_SIZE) throws Exception {
        List<Category> listCate = new ArrayList<>();
        String sql = "SELECT * FROM ( SELECT *,\n"
                + "                 ROW_NUMBER() OVER (ORDER BY CategoryID) AS Seq\n"
                + "                 FROM Category )t\n"
                + "                 WHERE Seq BETWEEN  (?-1)*?+1 AND ?*?";

        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, page);
            pre.setInt(2, PAGE_SIZE);
            pre.setInt(3, page);
            pre.setInt(4, PAGE_SIZE);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                Category cate = Category.builder()
                        .categoryID(rs.getInt("categoryID"))
                        .categoryName(rs.getString("categoryName"))
                        .description(rs.getString("description"))
                        .build();
                listCate.add(cate);
            }
        } catch (SQLException ex) {
            throw ex;
        }
        return listCate;
    }

    //Trả về tổng số sản phẩm
    public int getTotalCategory() throws Exception {
        String sql = "select COUNT(*) from Category";
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

    //Lấy ra tên Category theo CategoryID
    public String GetCategoryName(int CategoryID) throws Exception {
        String CategoryName;
        String sql = "select CategoryName from Category where CategoryID =?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, CategoryID);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                CategoryName = rs.getString("categoryName");
                return CategoryName;
            }

        } catch (SQLException ex) {
            throw ex;
        }
        return null;

    }

    public static void main(String[] args) {

        try {
            CategoryDAOImpl dao = new CategoryDAOImpl();
            Category cate = Category.builder()
                    .categoryID(1)
                    .categoryName("Update")
                    .description("Update Description")
                    .build();
            int n = dao.updateCategory(cate);
            if (n > 0) {
                System.out.println("Succes");
            } else {
                System.out.println("Fail");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public List<Category> getAllCategoryByCateID(int cateID) throws Exception {
        List<Category> listCate = new ArrayList<>();
        String sql = "select * from Category where CategoryID = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, cateID);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                Category cate = Category.builder()
                        .categoryID(rs.getInt("categoryID"))
                        .categoryName(rs.getString("categoryName"))
                        .description(rs.getString("description"))
                        .build();
                listCate.add(cate);
            }
        } catch (SQLException ex) {
            throw ex;
        }
        return listCate;
    }

    //Update Category
    public int updateCategory(Category cate) throws Exception {
        int n = 0;
        String sql = "UPDATE [Category]\n"
                + "   SET [CategoryName] = ?\n"
                + "      ,[Description] = ?\n"
                + " WHERE CategoryID = ?";

        try {
            PreparedStatement pre = conn.prepareStatement(sql);

            pre.setString(1, cate.getCategoryName());
            pre.setString(2, cate.getDescription());
            pre.setInt(3, cate.getCategoryID());

            n = pre.executeUpdate();
        } catch (SQLException ex) {
            throw ex;
        }
        return n;
    }

    //Create Category
    public int createCategory(Category cate) throws Exception {
        int n = 0;
        String sql = "INSERT INTO [Category]\n"
                + "           ([CategoryName]\n"
                + "           ,[Description])\n"
                + "     VALUES\n"
                + "           (?,?)";

        try {
            PreparedStatement pre = conn.prepareStatement(sql);

            pre.setString(1, cate.getCategoryName());
            pre.setString(2, cate.getDescription());

            n = pre.executeUpdate();
        } catch (SQLException ex) {
            throw ex;
        }
        return n;
    }

    public List<Category> getsearchCatePagingByCateName(String keySearch, int page, int PAGE_SIZE) throws Exception {
        List<Category> listCate = new ArrayList<>();
        String sql = "SELECT * FROM ( SELECT *,\n"
                + "                                ROW_NUMBER() OVER (ORDER BY CategoryID) AS Seq\n"
                + "                                FROM Category where CategoryName like ?)t\n"
                + "                                WHERE Seq BETWEEN  (?-1)*?+1 AND ?*?";

        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, "%" + keySearch + "%");
            pre.setInt(2, page);
            pre.setInt(3, PAGE_SIZE);
            pre.setInt(4, page);
            pre.setInt(5, PAGE_SIZE);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                Category cate = Category.builder()
                        .categoryID(rs.getInt("categoryID"))
                        .categoryName(rs.getString("categoryName"))
                        .description(rs.getString("description"))
                        .build();
                listCate.add(cate);
            }
        } catch (SQLException ex) {
            throw ex;
        }
        return listCate;
    }

    public int getTotalCategoryByName(String keySearch) throws Exception {
        String sql = "select COUNT(*) from Category where CategoryName like ?";
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

    public int deleteCategory(int cateID) throws Exception {
        int n = 0;
        String sql = "DELETE FROM [Category]\n"
                + "      WHERE CategoryID = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, cateID);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            throw ex;
        }
        return n;
    }
}
