/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Modal.DAOCategory;
import Modal.DAOProduct;
import View.Category;
import View.Product;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author KhacBao
 */
@WebServlet(name = "HomeController", urlPatterns = {"/home"})
public class HomeController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String service = request.getParameter("do");
            DAOProduct daoPro = new DAOProduct();
            DAOCategory daoCate = new DAOCategory();
            HttpSession session = request.getSession();
            
            if (service == null) {
                service = "home";
            }
            if (service.equals("home")) {
                String pageStr = request.getParameter("page");
                //Category
                List<Category> listCategory = daoCate.getAllCategory();
                session.setAttribute("listCategory", listCategory);
                
                //Phân trang
                int page = 1;
                final int PAGE_SIZE = 8;
                if (pageStr != null) {
                    page = Integer.parseInt(pageStr);
                }
                List<Product> listProduct = daoPro.getProductWithPaging(page, PAGE_SIZE);

                //Total Paging
                int totalProduct = daoPro.getTotalProduct();
                int totalPage = totalProduct / PAGE_SIZE;
                if (totalProduct % PAGE_SIZE != 0) {
                    totalPage += 1;
                }

                //Set Data For JSP
                request.setAttribute("page", page);
                request.setAttribute("totalPage", totalPage);
//                request.setAttribute("listCategory", listCategory);
                request.setAttribute("listProduct", listProduct);
                request.getRequestDispatcher("home.jsp").forward(request, response);
            }
            if (service.equals("fillCategory")) {
                String cateId = request.getParameter("categoryID");
                int iCateId = Integer.parseInt(cateId);
                List<Product> listProduct;
                if (iCateId == -1) {
                    response.sendRedirect("home");
                    return;
                } else {
                    listProduct = daoPro.getProductsByID(iCateId);
                }
                //Get Data
                //Category
                List<Category> listCategory = daoCate.getAllCategory();

                //Set Data
                request.setAttribute("cateID", iCateId);
//                request.setAttribute("listCategory", listCategory);
                request.setAttribute("listProduct", listProduct);
                request.getRequestDispatcher("home.jsp").forward(request, response);
            }
            if (service.equals("about")) {
                response.sendRedirect("about.jsp");
            }
            if(service.equals("blogList")) {
                response.sendRedirect("blogList.jsp");
            }
            if(service.equals("contact")){
                response.sendRedirect("contact.jsp");
            }
            if (service.equals("search")) {
                request.setCharacterEncoding("UTF-8");
                response.setCharacterEncoding("UTF-8");

                String searchKey = request.getParameter("searchKey");
                if (searchKey.isEmpty()) {
                    response.sendRedirect("home");
                    return;
                }

                List<Product> listProduct = daoPro.searchByName(searchKey);
                List<Category> listCategory = daoCate.getAllCategory();
                
                
//                request.setAttribute("listCategory", listCategory);
                request.setAttribute("listProduct", listProduct);
                request.getRequestDispatcher("home.jsp").forward(request, response);
            }
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
