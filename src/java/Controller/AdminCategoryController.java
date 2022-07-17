/*
    Hiển thị danh sách tất cả Category có trong DB
    Thêm sửa xoa tìm kiếm và phân trang khi tìm kiếm
    Check valid cho các method update and create
 */
package Controller;

import Entity.Category;
import dao.impl.CategoryDAOImpl;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "AdminCategoryController", urlPatterns = {"/adminCategory"})
public class AdminCategoryController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        try {
            String service = request.getParameter("do");
            CategoryDAOImpl daoCategory = new CategoryDAOImpl();
            HttpSession session = request.getSession();
            if (service == null) {
                service = "CategoryHome";
            }
            if (service.equals("CategoryHome")) {//Home Category and paging
                String pageStr = request.getParameter("page");

                int page = 1;
                final int PAGE_SIZE = 4;
                if (pageStr != null) {
                    page = Integer.parseInt(pageStr);
                }
                List<Category> categoryList = daoCategory.getAllCategoryWithPaging(page, PAGE_SIZE);
                int totalProduct = daoCategory.getTotalCategory();//Get total All Product
                int totalPage = totalProduct / PAGE_SIZE;
                if (totalProduct % PAGE_SIZE != 0) {
                    totalPage += 1;
                }
                session.setAttribute("backToUrl", "adminCategory");
                request.setAttribute("totalProduct", totalProduct);
                request.setAttribute("page", page);
                request.setAttribute("totalPage", totalPage);
                request.setAttribute("categoryList", categoryList);
                request.getRequestDispatcher("adminCategory.jsp").forward(request, response);
            }
            if (service.equals("updateCategory")) {//Update Category and Paging
                String submit = request.getParameter("submit");
                if (submit == null) {
                    String sCateID = request.getParameter("cateID");
                    int cateID = Integer.parseInt(sCateID);
                    List<Category> categoryList = daoCategory.getAllCategoryByCateID(cateID);

                    request.setAttribute("categoryList", categoryList);
                    request.getRequestDispatcher("adminCategoryUpdate.jsp").forward(request, response);
                } else {
                    int cateId = Integer.parseInt(request.getParameter("cateID"));
                    String cateName = request.getParameter("cateName").trim();
                    String description = request.getParameter("description").trim();

                    Category cateBefor = Category.builder()//Lưu giữ lại giá trị lỗi
                            .categoryID(cateId)
                            .categoryName(cateName)
                            .description(description)
                            .build();

                    List<Category> categoryList = new ArrayList<>();
                    categoryList.add(cateBefor);
                    request.setAttribute("categoryList", categoryList);

                    if (cateName == null || cateName.isEmpty()) {//Check Name
                        String mess = "Category name is not empty";
                        request.setAttribute("mess", mess);
                        request.getRequestDispatcher("adminCategoryUpdate.jsp").forward(request, response);
                        return;
                    }
                    
                    if (description == null || description.isEmpty()) {//Check Address
                        String mess = "Decription is not empty";
                        request.setAttribute("mess", mess);
                        request.getRequestDispatcher("adminCategoryUpdate.jsp").forward(request, response);
                        return;
                    }

                    Category cate = Category.builder()//Lưu giữ lại giá trị lỗi
                            .categoryID(cateId)
                            .categoryName(cateName.trim())
                            .description(description.trim())
                            .build();

                    String mess = "Update successfull";
                    int updateCategory = daoCategory.updateCategory(cate);
                    request.setAttribute("mess", mess);
                    request.setAttribute("categoryList", categoryList);
                    request.getRequestDispatcher("adminCategoryUpdate.jsp").forward(request, response);
                }
            }
            if (service.equals("createCategory")) {//Create new Category and check validate
                String submit = request.getParameter("submit");
                if (submit == null) {
                    request.getRequestDispatcher("adminCategoryCreate.jsp").forward(request, response);
                } else {
                    String cateName = request.getParameter("cateName").trim();
                    String description = request.getParameter("description").trim();

                    request.setAttribute("cateName", cateName);
                    request.setAttribute("description", description);

                    if (cateName == null || cateName.isEmpty()) {//Check Name
                        String mess = "Category name is not empty";
                        request.setAttribute("mess", mess);
                        request.getRequestDispatcher("adminCategoryCreate.jsp").forward(request, response);
                        return;
                    }
                   
                    if (description == null || description.isEmpty()) {//Check Address
                        String mess = "Description is not empty";
                        request.setAttribute("mess", mess);
                        request.getRequestDispatcher("adminCategoryCreate.jsp").forward(request, response);
                        return;
                    }
                   

                    Category cate = Category.builder()//Lưu giữ lại giá trị lỗi
                            .categoryName(cateName.trim())
                            .description(description.trim())
                            .build();

                    int createCategory = daoCategory.createCategory(cate);

                    if (createCategory > 0) {
                        String mess = "Create successfull";
                        request.setAttribute("mess", mess);
                        request.getRequestDispatcher("adminCategoryCreate.jsp").forward(request, response);
                    }
                }

            }
            if (service.equals("deleteCategory")) {//Delete Category and Pagings
                String sCateID = request.getParameter("cateID");
                int cateID = Integer.parseInt(sCateID);
                int delete = daoCategory.deleteCategory(cateID);

                String mess;
                if (delete > 0) {//Remove Successs
                    String pageStr = request.getParameter("page");

                    int page = 1;
                    final int PAGE_SIZE = 4;
                    if (pageStr != null) {
                        page = Integer.parseInt(pageStr);
                    }
                    List<Category> categoryList = daoCategory.getAllCategoryWithPaging(page, PAGE_SIZE);
                    int totalProduct = daoCategory.getTotalCategory();//Get total All Product
                    int totalPage = totalProduct / PAGE_SIZE;
                    if (totalProduct % PAGE_SIZE != 0) {
                        totalPage += 1;
                    }
                    mess = "Delete successfull";

                    session.setAttribute("backToUrl", "adminCategory");
                    request.setAttribute("totalProduct", totalProduct);
                    request.setAttribute("page", page);
                    request.setAttribute("mess", mess);
                    request.setAttribute("totalPage", totalPage);
                    request.setAttribute("categoryList", categoryList);
                    request.getRequestDispatcher("adminCategory.jsp").forward(request, response);
                }
            }
            if (service.equals("searchCategory")) {//Search Category and paging
                request.setCharacterEncoding("UTF-8");
                response.setCharacterEncoding("UTF-8");
                String keySearch = request.getParameter("searchKey").trim();
                if (keySearch.isEmpty()) {
                    response.sendRedirect("adminCategory");
                    return;
                }

                String pageStr = request.getParameter("page");
                int page = 1;
                final int PAGE_SIZE = 4;
                if (pageStr != null) {
                    page = Integer.parseInt(pageStr);
                }
                List<Category> categoryList = daoCategory.getsearchCatePagingByCateName(keySearch, page, PAGE_SIZE);
                int totalProduct = daoCategory.getTotalCategoryByName(keySearch);//Get total All Product
                int totalPage = totalProduct / PAGE_SIZE;
                if (totalProduct % PAGE_SIZE != 0) {
                    totalPage += 1;
                }

                session.setAttribute("backToUrl", "adminCategory?do=searchCategory");
                request.setAttribute("keySearch", keySearch);
                request.setAttribute("totalProduct", totalProduct);
                request.setAttribute("page", page);
                request.setAttribute("totalPage", totalPage);
                request.setAttribute("categoryList", categoryList);
                request.getRequestDispatcher("adminCategory.jsp").forward(request, response);
            }
        } catch (Exception ex) {
            request.setAttribute("error", ex);
            request.getRequestDispatcher("error500.jsp").forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
