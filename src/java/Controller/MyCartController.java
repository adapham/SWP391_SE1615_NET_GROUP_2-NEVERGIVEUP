package Controller;

import Entity.Account;
import Entity.Order;
import Entity.OrderDetails;
import dao.impl.OrderDAOImpl;
import dao.impl.OrderDetailsDAOImpl;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;
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
        try {
            String service = request.getParameter("do");
            OrderDAOImpl dao = new OrderDAOImpl();
            OrderDAOImpl daoOrder = new OrderDAOImpl();
            OrderDetailsDAOImpl daoDetail = new OrderDetailsDAOImpl();
            if (service == null) {
                service = "orders";
            }
            if (service.equals("orders")) {
                String pageStr = request.getParameter("page");
                HttpSession session = request.getSession();
                Account acc = (Account) session.getAttribute("Account");
                int AccID = acc.getAccountid();
                    List<Order> list;
                    String keySearch = request.getParameter("keySearch");
                    int page = 1;
                    final int PAGE_SIZE = 3;
                    if (pageStr != null) {
                        page = Integer.parseInt(pageStr);
                    }
                    int totalOrder;
                    if (keySearch == null || keySearch == "") {
                        list = dao.getOrderWithPaging(page, PAGE_SIZE);
                        totalOrder = dao.getTotalOrder();
                    } else {
                        totalOrder = dao.getTotalOrderByDate(keySearch);
                        list = dao.getSearchOrderPagingByDate(keySearch, page, PAGE_SIZE);
                    }
                    int totalPage = totalOrder / PAGE_SIZE;
                    if (totalOrder % PAGE_SIZE != 0) {
                        totalPage += 1;
                    }
                    request.setAttribute("keySearch", keySearch);
                    request.setAttribute("totalOrder", totalOrder);
                    request.setAttribute("page", page);
                    request.setAttribute("totalPage", totalPage);
                    request.setAttribute("list", list);
                    request.getRequestDispatcher("mycart.jsp").forward(request, response);
                
            }

            if (service.equals("ordersDetail")) {
                int orderID = Integer.parseInt(request.getParameter("orderID"));
                List<OrderDetails> list = daoDetail.getListAllDetail(orderID);
                request.setAttribute("li", list);
//                for (OrderDetails orderDetails : listOrderDetail) {
//                    out.print(orderDetails);
//                }
                request.getRequestDispatcher("listOrderDetail.jsp").forward(request, response);
            }
        } catch (Exception ex) {

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
