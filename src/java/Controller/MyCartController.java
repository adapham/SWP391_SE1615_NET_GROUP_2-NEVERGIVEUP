package Controller;

import Entity.Account;
import Entity.Order;
import Entity.OrderDetails;
import dao.OrderDao;
import dao.OrderDetailsDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "MyCartController", urlPatterns = {"/MyCartController"})
public class MyCartController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String service = request.getParameter("do");
            HttpSession session = request.getSession();
            Account acc = (Account) session.getAttribute("Account");
            int AccID = acc.getAccountid();
            OrderDao daoOrder = new OrderDao();
            List<Order> listOrder = daoOrder.listAllOrdersbyID(AccID);
            for (Order order : listOrder) {
                System.out.println(order);
            }
            OrderDetailsDao daoDetail = new OrderDetailsDao();
            if (service == null) {
                service = "orders";
            }
            if (service.equals("orders")) {
                
                request.setAttribute("list", listOrder);
                request.getRequestDispatcher("mycart.jsp").forward(request, response);
            }
            if (service.equals("ordersDetail")) {
                int orderID = Integer.parseInt(request.getParameter("orderID"));
                List<OrderDetails> list= daoDetail.getListAllDetail(orderID);
                request.setAttribute("li", list);
//                for (OrderDetails orderDetails : listOrderDetail) {
//                    out.print(orderDetails);
//                }
                request.getRequestDispatcher("listOrderDetail.jsp").forward(request, response);
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
