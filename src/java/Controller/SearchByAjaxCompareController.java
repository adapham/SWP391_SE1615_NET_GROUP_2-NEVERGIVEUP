package Controller;

import Entity.Product;
import dao.impl.ProductDAOImpl;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SearchByAjaxCompareController", urlPatterns = {"/searchCompare"})
public class SearchByAjaxCompareController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            request.setCharacterEncoding("UTF-8");
            ProductDAOImpl daoProduct = new ProductDAOImpl();
            PrintWriter out = response.getWriter();

            String searchKey = request.getParameter("keySearch");
            int cateId = Integer.parseInt(request.getParameter("cateId"));
            int pId = Integer.parseInt(request.getParameter("pId"));
            System.out.println("Check");
            System.out.println(searchKey);
            System.out.println(cateId);
            System.out.println(pId);
            List<Product> productList;
            if (searchKey == null || searchKey.isEmpty()) {
                productList = null;
            } else {
                productList = daoProduct.searchProductByNameAndCategoryId(searchKey, cateId, pId);
            }

            if (productList.size() != 0) {
                out.println("<div id=\"contentCompare\" class=\"row\" style=\"background-color: #ccc;max-height: 500px; height: 500px; width: 100%;overflow: scroll;\">");
            }

            for (Product pro : productList) {
                out.println("<div class=\"col-xl-4 col-lg-4 col-md-6 col-sm-6 col-12\">\n"
                        + "                                                    <div class=\"single-product-wrap mb-35\">\n"
                        + "                                                        <div class=\"product-img product-img-zoom mb-15\">\n"
                        + "                                                            <a href=\"compareProduct?do=compareResult&pId1=" + pro.getProductID() + "&pId2=" + pId + "\">\n"
                        + "                                                                <img src=\"" + pro.getImageURL() + "\" alt=\"\" style=\"min-height: 100px; max-height: 100px; width: 100%; \">\n"
                        + "                                                            </a>\n"
                        + "                                                        </div>\n"
                        + "                                                        <div class=\"text-center\" style=\"\">\n"
                        + "                                                            <h3><a href=\"compareProduct?do=compareResult&pId1=" + pro.getProductID() + "&pId2=" + pId + "\">" + pro.getProductName() + "</a></h3>\n"
                        + "                                                        </div>\n"
                        + "                                                    </div>\n"
                        + "                                                </div>");
            }

            if (productList.size() != 0) {
                out.println("</div>");
            }

        } catch (Exception ex) {
            System.out.println("Exception");
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
