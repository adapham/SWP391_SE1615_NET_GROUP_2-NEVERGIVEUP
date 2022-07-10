package Controller;

import Entity.Product;
import dao.impl.ProductDAOImpl;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "CompareProductAjaxController", urlPatterns = {"/compareProduct"})
public class CompareProductController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            String service = request.getParameter("do");
            ProductDAOImpl daoProduct = new ProductDAOImpl();
            if (service == null) {
                service = "compareProduct";
            }
            if (service.equals("compareProduct")) {
                int pId = Integer.parseInt(request.getParameter("pId"));
                List<Product> productList = daoProduct.getAllProductByProductID(pId);
                Product pro = daoProduct.getProductByProductID(pId);
                
                request.setAttribute("listProduct", productList);
                request.setAttribute("product", pro);
                request.getRequestDispatcher("compareProduct.jsp").forward(request, response);
            }
            if(service.equals("compareResult")){
                int pId1 = Integer.parseInt(request.getParameter("pId1"));
                int pId2 = Integer.parseInt(request.getParameter("pId2"));
                
                Product product1 = daoProduct.getProductByProductID(pId1);
                Product product2 = daoProduct.getProductByProductID(pId2);
                
                request.setAttribute("product1", product1);
                request.setAttribute("product2", product2);
                request.getRequestDispatcher("compareProductResult.jsp").forward(request, response);
            }
        } catch (Exception ex) {
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
