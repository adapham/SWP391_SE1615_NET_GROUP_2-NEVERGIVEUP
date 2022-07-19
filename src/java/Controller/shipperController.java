package Controller;

import Entity.Order;
import Entity.OrderDetails;
import dao.impl.OrderDAOImpl;
import dao.impl.OrderDetailsDAOImpl;
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
 * @author ADA
 */
@WebServlet(name = "shipperController", urlPatterns = {"/shipperController"})
public class shipperController extends HttpServlet {

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
        try {
            /* TODO output your page here. You may use following sample code. */
            String service = request.getParameter("do");
            if (service.equals("load")) {
                OrderDAOImpl dao = new OrderDAOImpl();
                String pageStr = request.getParameter("page");
                int page = 1;
                final int PAGE_SIZE = 3;
                if (pageStr != null) {
                    page = Integer.parseInt(pageStr);
                }
                List<Order> list = dao.getOrderWithPaging(page, PAGE_SIZE);
                int totalOrder = dao.getTotalOrder();
                int totalPage = totalOrder / PAGE_SIZE;
                if (totalOrder % PAGE_SIZE != 0) {
                    totalPage += 1;
                }
                System.out.println(list);
                request.setAttribute("page", page);
                request.setAttribute("totalPage", totalPage);
                request.setAttribute("list", list);
                request.getRequestDispatcher("shipper.jsp").forward(request, response);
            }
            if (service.equals("searchOrder")) {
                OrderDAOImpl dao = new OrderDAOImpl();
                String keySearch = request.getParameter("keySearch");
                if (keySearch.isEmpty()) {
                    response.sendRedirect("shipperController?do=load");
                    return;
                }
                String pageStr = request.getParameter("page");
                int page = 1;
                final int PAGE_SIZE = 3;
                if (pageStr != null) {
                    page = Integer.parseInt(pageStr);
                }
                List<Order> list = dao.getSearchOrderPagingByAddress(keySearch, page, PAGE_SIZE);
                int totalOrder = dao.getTotalOrderByAddress(keySearch);
                int totalPage = totalOrder / PAGE_SIZE;
                if (totalOrder % PAGE_SIZE != 0) {
                    totalPage += 1;
                }
                String search = "1";
                request.setAttribute("search", search);
                request.setAttribute("keySearch", keySearch);
                request.setAttribute("totalOrder", totalOrder);
                request.setAttribute("page", page);
                request.setAttribute("totalPage", totalPage);
                request.setAttribute("list", list);
                request.getRequestDispatcher("shipper.jsp").forward(request, response);
            }
            if (service.equals("updateStatus")) {
                OrderDAOImpl dao = new OrderDAOImpl();
                String id = request.getParameter("odId");
                String pageStr = request.getParameter("page");
                int page = 1;
                final int PAGE_SIZE = 5;
                if (pageStr != null) {
                    page = Integer.parseInt(pageStr);
                }
                int totalOrder = dao.getTotalOrder();
                int totalPage = totalOrder / PAGE_SIZE;
                if (totalOrder % PAGE_SIZE != 0) {
                    totalPage += 1;
                }
                //Convert
                int odID = Integer.parseInt(id);
                int Status = Integer.parseInt(request.getParameter("status"));
                int n = dao.updateStatus(Status, odID);
                request.setAttribute("page", page);
                request.setAttribute("totalPage", totalPage);
                response.sendRedirect("shipperController?do=load&page=" + page);
            }
            if (service.equals("details")) {
                OrderDetailsDAOImpl dao = new OrderDetailsDAOImpl();
                String odID = request.getParameter("odID");
                int oID = Integer.parseInt(odID);

                List<OrderDetails> list = dao.getDetailsBill(oID);
                OrderDetails info = dao.getInfoBill(oID);

                for (OrderDetails orderDetails : list) {

                }
                request.setAttribute("info", info);
                request.setAttribute("list", list);
                request.setAttribute("orderID", oID);
                request.getRequestDispatcher("detailofCustomerInShip.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
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
