package Controller;

import Entity.Product;
import dao.impl.ProductDAOImpl;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "AddToCartController", urlPatterns = {"/AddToCart"})
public class AddToCartController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            /* TODO output your page here. You may use following sample code. */
            String pid = request.getParameter("pid");
            int iPID = Integer.parseInt(pid);
            ProductDAOImpl dao = new ProductDAOImpl();
            String amout = request.getParameter("amount");
            System.out.println(amout);
            Product temp = dao.getProductByProductID(iPID);
            HttpSession session = request.getSession();
            Product pro = (Product) session.getAttribute(pid);

            String sizeStr;
            try {
                sizeStr = session.getAttribute("size").toString();
            } catch (Exception e) {
                sizeStr = null;
            }
            int size = 0;
            if (sizeStr == null) {
                size = 0;
            } else {
                size = Integer.parseInt(sizeStr);
            }

            if (pro == null) {
                pro = Product.builder()
                        .productID(Integer.parseInt(pid))
                        .imageURL(temp.getImageURL())
                        .productName(temp.getProductName())
                        .quantity(1)
                        .unitPrice(temp.getPriceAferDiscount())
                        .build();
                size++;
            } else {
                pro.setQuantity(pro.getQuantity() + 1);
            }
            session.setAttribute(pid, pro);
            session.setAttribute("size", size);
//            String urlHistory = (String) session.getAttribute("urlHistory");
//            if (urlHistory == null) {
//                urlHistory = "details?do=details&pid=" + iPID;
//            }
            response.sendRedirect("menu");
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
