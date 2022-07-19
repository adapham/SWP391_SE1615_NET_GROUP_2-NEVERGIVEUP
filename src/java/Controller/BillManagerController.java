/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.Order;
import Entity.OrderDetails;
import Entity.Product;
import dao.impl.OrderDAOImpl;
import dao.impl.OrderDetailsDAOImpl;
import java.io.IOException;
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
 * @author Window 10
 */
@WebServlet(name = "managerBillController", urlPatterns = {"/billManager"})
public class BillManagerController extends HttpServlet {

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
            String service = request.getParameter("do");
            if (service == null) {
                request.getRequestDispatcher("admin.jsp").forward(request, response);
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
                response.sendRedirect("billManager?do=pageBill&page=" + page);

            }
            if (service.equals("pageBill")) {
                OrderDAOImpl dao = new OrderDAOImpl();
                HttpSession session = request.getSession();
                String pageStr = request.getParameter("page");
                int page = 1;
                final int PAGE_SIZE = 5;
                if (pageStr != null) {
                    page = Integer.parseInt(pageStr);
                }
                List<Order> listOrder = dao.getOrderWithPaging(page, PAGE_SIZE);
                int totalOrder = dao.getTotalOrder();
                int totalPage = totalOrder / PAGE_SIZE;
                if (totalOrder % PAGE_SIZE != 0) {
                    totalPage += 1;
                }
                request.setAttribute("page", page);
                request.setAttribute("totalPage", totalPage);
                request.setAttribute("listOrder", listOrder);
                request.getRequestDispatcher("listBill.jsp").forward(request, response);
            }
            if (service.equals("searchBill")) {
                OrderDAOImpl dao = new OrderDAOImpl();
                HttpSession session = request.getSession();
                String keySearch = request.getParameter("keySearch");
                if (keySearch.isEmpty()) {
                    response.sendRedirect("billManager?do=pageBill");
                    return;
                }
                String pageStr = request.getParameter("page");
                int page = 1;
                final int PAGE_SIZE = 5;
                if (pageStr != null) {
                    page = Integer.parseInt(pageStr);
                }
                List<Order> listOrder = dao.getSearchOrderPagingByAddress(keySearch, page, PAGE_SIZE);
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
                request.setAttribute("listOrder", listOrder);
                request.getRequestDispatcher("listBill.jsp").forward(request, response);
            }
            if (service.equals("details")) {
                OrderDetailsDAOImpl dao = new OrderDetailsDAOImpl();
                String odID = request.getParameter("odID");
                int oID = Integer.parseInt(odID);
                List<OrderDetails> list = dao.getDetailsBill(oID);
                OrderDetails info = dao.getInfoBill(oID);
                double totalMoney = 0;
                for (OrderDetails list1 : list) {
                    totalMoney += list1.getPrice()* list1.getQuantity();
                    totalMoney *= 100;
                    double total = Math.ceil(totalMoney);
                    total = (double) total / 100;
                    totalMoney = total;

                }
                request.setAttribute("totalMoney", totalMoney);
                request.setAttribute("info", info);
                request.setAttribute("list", list);
                request.setAttribute("orderID", oID);
                request.getRequestDispatcher("detailsBill.jsp").forward(request, response);
            }
            if (service.equals("updateStatusDetails")) {
                OrderDAOImpl dao = new OrderDAOImpl();
                String id = request.getParameter("odId");
                //Convert
                int odID = Integer.parseInt(id);
                int Status = Integer.parseInt(request.getParameter("status"));
                int n = dao.updateStatus(Status, odID);
                request.getRequestDispatcher("billManager?do=details&odID=" + id).forward(request, response);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
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
