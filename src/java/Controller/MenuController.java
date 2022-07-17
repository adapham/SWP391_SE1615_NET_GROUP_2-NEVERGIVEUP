/*
    Hiển thị danh sách tất cả sản phẩm 
    Lọc sản phẩm theo Category và phân trang khi lọc
    Tìm kiếm sản phẩm theo tên sản phẩm và phân trang khi tìm kiếm
 */
package Controller;

import Entity.Category;
import Entity.Product;
import dao.impl.CategoryDAOImpl;
import dao.impl.ProductDAOImpl;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MenuController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            String service = request.getParameter("do");
            ProductDAOImpl daoProduct = new ProductDAOImpl();
            CategoryDAOImpl daoCategory = new CategoryDAOImpl();
            HttpSession session = request.getSession();

            if (service == null) {
                service = "menu";
            }
            //Lấy danh sách sản phẩm theo và phân trang
            if (service.equals("menu")) {
                String pageStr = request.getParameter("page");

                List<Category> listCategory = daoCategory.getAllCategory();
                session.setAttribute("listCategory", listCategory);
                //Phân trang
                String viewPage = request.getParameter("viewPage");
                int page = 1;
                if (viewPage == null) {
                    viewPage = "9";
                }
                final int PAGE_SIZE = Integer.parseInt(viewPage);
                if (pageStr != null) {
                    page = Integer.parseInt(pageStr);
                }
                List<Product> listProduct = daoProduct.getProductWithPaging(page, PAGE_SIZE);
                int totalProduct = daoProduct.getTotalProduct();//Get total All Product
                int totalPage = totalProduct / PAGE_SIZE;
                if (totalProduct % PAGE_SIZE != 0) {
                    totalPage += 1;
                }
                //Set Data For JSP
                request.setAttribute("PAGE_SIZE", PAGE_SIZE);
                session.setAttribute("backToUrl", "menu");
                request.setAttribute("totalProduct", totalProduct);
                request.setAttribute("page", page);
                request.setAttribute("totalPage", totalPage);
                request.setAttribute("listProduct", listProduct);
                request.getRequestDispatcher("shop.jsp").forward(request, response);
            }
            if (service.equals("fillCategory")) {//Xử lý lọc theo category
                String pageStr = request.getParameter("page");
                String cateId = request.getParameter("categoryID");
                int iCateId = Integer.parseInt(cateId);
                List<Product> listProduct;
                if (iCateId == -1) {
                    response.sendRedirect("menu");
                    return;
                }
                //Phân trang
                String viewPage = request.getParameter("viewPage");
                int page = 1;
                if (viewPage == null) {
                    viewPage = "6";
                }
                final int PAGE_SIZE = Integer.parseInt(viewPage);
                if (pageStr != null) {
                    page = Integer.parseInt(pageStr);
                }
                listProduct = daoProduct.getAllProductsWithPagingByCateID(iCateId, page, PAGE_SIZE);//Get Product By CateID
                int totalProduct = daoProduct.getTotalProductByCate(iCateId);//Get total page
                int totalPage = totalProduct / PAGE_SIZE;
                if (totalProduct % PAGE_SIZE != 0) {
                    totalPage += 1;
                }

                session.setAttribute("backToUrl", "menu?do=fillCategory");
                request.setAttribute("PAGE_SIZE", PAGE_SIZE);
                request.setAttribute("totalProduct", totalProduct);
                request.setAttribute("page", page);
                request.setAttribute("totalPage", totalPage);
                request.setAttribute("cateID", iCateId);
                request.setAttribute("listProduct", listProduct);
                request.getRequestDispatcher("shop.jsp").forward(request, response);
            }
            if (service.equals("search")) {//Tìm kiếm và trả về danh sách Product theo key
                request.setCharacterEncoding("UTF-8");
                response.setCharacterEncoding("UTF-8");

                String keySearch = request.getParameter("searchKey").trim();
                if (keySearch.isEmpty()) {
                    response.sendRedirect("menu");
                    return;
                }

                String pageStr = request.getParameter("page");
                //Phân trang
                String viewPage = request.getParameter("viewPage");
                int page = 1;
                if (viewPage == null) {
                    viewPage = "6";
                }
                final int PAGE_SIZE = Integer.parseInt(viewPage);
                if (pageStr != null) {
                    page = Integer.parseInt(pageStr);
                }

                List<Product> listProduct = daoProduct.getSearchProductsPagingByName(keySearch, page, PAGE_SIZE);
                int totalProduct = daoProduct.getTotalProductByPName(keySearch);
                int totalPage = totalProduct / PAGE_SIZE;
                if (totalProduct % PAGE_SIZE != 0) {
                    totalPage += 1;
                }

                session.setAttribute("backToUrl", "menu?do=search");
                request.setAttribute("PAGE_SIZE", PAGE_SIZE);
                request.setAttribute("totalProduct", totalProduct);
                request.setAttribute("page", page);
                request.setAttribute("totalPage", totalPage);
                request.setAttribute("keySearch", keySearch);
                request.setAttribute("listProduct", listProduct);
                request.getRequestDispatcher("shop.jsp").forward(request, response);
            }
        } catch (Exception ex) {
            request.setAttribute("error", ex);
            request.getRequestDispatcher("error500.jsp").forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
