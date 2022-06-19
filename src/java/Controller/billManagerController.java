/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.Order;
import Entity.OrderDetails;
import Modal.OrderDao;
import Modal.OrderDetailsDao;
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
 * @author Window 10
 */
@WebServlet(name = "managerBillController", urlPatterns = {"/billManager"})
public class billManagerController extends HttpServlet {

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
            String service = request.getParameter("do");
            if (service == null) {
                request.getRequestDispatcher("admin.jsp").forward(request, response);
            }
            if (service.equals("bill")) {
                OrderDao dao = new OrderDao();
                List<Order> list = dao.listAllOrders();
                request.setAttribute("list", list);
                request.getRequestDispatcher("listBill.jsp").forward(request, response);
            }
            if (service.equals("updateStatus")) {
                OrderDao dao = new OrderDao();
                String id = request.getParameter("odId");
                //Convert
                int odID = Integer.parseInt(id);
                int Status = Integer.parseInt(request.getParameter("status"));
                int n = dao.updateStatus(Status, odID);
                response.sendRedirect("billManager?do=bill");

            }
            if (service.equals("details")) {
                OrderDetailsDao dao = new OrderDetailsDao();
                String odID = request.getParameter("odID");
                int oID = Integer.parseInt(odID);
                List<OrderDetails> list = dao.getDetailsBill(oID);
                OrderDetails info = dao.getInfoBill(oID);
                request.setAttribute("info", info);
                request.setAttribute("list", list);
                request.setAttribute("orderID", oID);
                request.getRequestDispatcher("detailsBill.jsp").forward(request, response);
            }
            if (service.equals("updateStatusDetails")) {
                OrderDao dao = new OrderDao();
                String id = request.getParameter("odId");
                //Convert
                int odID = Integer.parseInt(id);
                int Status = Integer.parseInt(request.getParameter("status"));
                int n = dao.updateStatus(Status, odID);
                request.getRequestDispatcher("billManager?do=details&odID=" + id).forward(request, response);
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
