/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.Product;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author admin
 */
@WebServlet(name = "CartController", urlPatterns = {"/cart"})
public class CartController extends HttpServlet {

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
            List<Product> listProductCarts = new ArrayList<>();
            HttpSession session = request.getSession();
//            Product prod = Product.builder()
//                    .productName("alo")
//                    .quantity(123)
//                    .build();
//            listProductCarts.add(prod);
            
            Enumeration em = session.getAttributeNames();
            int sum=0;
            while(em.hasMoreElements()){
                String key = em.nextElement().toString();
                if(!key.equals("urlHistory") && !key.equals("backToUrl") && !key.equals("Account")&& !key.equals("size") && !key.equals("listCategory")){
                    Product pro = (Product) session.getAttribute(key);
                    sum+=pro.getQuantity();
                    listProductCarts.add(pro);
                    session.setAttribute(key, pro);
                }
            }
            
//            for(Product list : listProductCarts){
//                out.print(list);
//            }
            //session.setAttribute("size", sum);
            double totalMoney = 0;
            for(Product list : listProductCarts){
                totalMoney+=list.getUnitPrice()*list.getQuantity();
                totalMoney*=100;
                double total=Math.ceil(totalMoney);
                total= (double) total/100;
                totalMoney=total;
                
                
            }
            request.setAttribute("totalMoney", totalMoney);
            request.setAttribute("listProductCarts", listProductCarts);
            request.getRequestDispatcher("cart.jsp").forward(request, response);
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
