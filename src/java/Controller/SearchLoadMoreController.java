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

/**
 *
 * @author KhacBao
 */
@WebServlet(name = "SearchLoadMoreController", urlPatterns = {"/searchLoad"})
public class SearchLoadMoreController extends HttpServlet {

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
            DAOProduct daoPro = new DAOProduct();
            DAOCategory daoCate = new DAOCategory();

            request.setCharacterEncoding("UTF-8");
            String searchKey = request.getParameter("searchKey");

            List<Product> listProduct = daoPro.searchByName(searchKey);

            for (Product pro : listProduct) {
                out.println("<div class=\"product col mb-5\">\n"
                        + "                            <div class=\"card h-100\" style=\"background: #f1f2f3;\">");
                if (pro.getDiscount() != 0) {
                    out.println("<div class=\"badge bg-dark text-white position-absolute\" style=\"top: 0.5rem; right: 0.5rem\">Sale" + pro.getDiscount() * 100 + "%</div> ");
                }
                out.println("<!-- Product image-->\n"
                        + "                                <div class=\"img-box d-flex justify-content-center\" style=\"height: 100%; width: 80%;margin-left: auto; margin-right: auto; margin-top: 10%; margin-bottom: 10%;\">\n"
                        + "                                    <a href=\"details?pid=" + pro.getProductID() + "\">\n"
                        + "                                        <img class=\"card-img-top\" src=\"" + pro.getImageURL() + "\" alt=\"...\"/>\n"
                        + "                                    </a>\n"
                        + "                                </div>\n"
                        + "                                <!-- Product details-->\n"
                        + "                                <div class=\"card-body\" style=\"background: #232831; color: white;\">\n"
                        + "                                    <div class=\"text-center\">\n"
                        + "                                        <!-- Product name-->\n"
                        + "                                        <a href=\"details?pid=" + pro.getProductID() + "\">\n"
                        + "                                            <h5 class=\"fw-bolder\">" + pro.getProductName() + "</h5>\n"
                        + "                                        </a>\n"
                        + "                                        <!-- Product reviews-->\n"
                        + "                                        <div class=\"d-flex justify-content-center\" style=\"overflow: hidden; max-height: 75px; margin-top: 5px;\">\n"
                        + "                                            <span style=\"text-align: left;\">" + pro.getDescription() + "</span>\n"
                        + "                                        </div>\n"
                        + "                                        <!--Cart-->\n"
                        + "                                        <div class=\"d-flex justify-content-between\" style=\"margin-top: 15px;\">\n"
                        + "                                            <!-- Product price-->\n"
                        + "                                            <div class=\"d-flex align-items-center\">");
                if (pro.getDiscount() != pro.getPriceAferDiscount()) {
                    out.println("<span class=\"text-muted text-decoration-line-through\">" + pro.getUnitPrice() + "</span>");
                }
                out.println(" " + pro.getPriceAferDiscount() + "\n"
                        + "                                            </div>\n"
                        + "                                            <!--Cart-->\n"
                        + "                                            <a class=\"btn btn-outline-dark mt-auto rounded-circle\" href=\"#\" style=\"font-size: 20px; background-color: #f4bd36;\"><i class=\"bi bi-cart-plus\"></i></a>\n"
                        + "                                        </div>\n"
                        + "                                    </div>\n"
                        + "                                </div>\n"
                        + "                            </div>\n"
                        + "                        </div>");
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
