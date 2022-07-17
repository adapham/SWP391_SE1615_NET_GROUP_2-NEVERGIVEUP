/*
list toàn bộ account của khách hàng trên trang employee và cho phép nhân viên có thể xem được danh sách employee và chi tiết các đơn hàng của khách hàng đó
 */
package Controller;

import Entity.OrderDetails;
import dao.impl.AccountDAOImpl;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author admin
 */
@WebServlet(name = "EmployeeAccountController", urlPatterns = {"/employeeaccount"})
public class EmployeeAccountController extends HttpServlet {

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
            AccountDAOImpl daoAccount = new AccountDAOImpl();
            if (service == null) {
                service = "EmployeeHome";
            }
            if (service.equals("EmployeeHome")) {
                response.sendRedirect("employee.jsp");
            }
            if (service.equals("AccountCustomer")) {
                final int PAGE_SIZE = 6;
                int page = 1;
                String pageStr = request.getParameter("page");
                if (pageStr != null) {
                    page = Integer.parseInt(pageStr);
                }
                List listAccount = daoAccount.ListAccountOfCustomerWithPagging(page, PAGE_SIZE);
                int TotalAccount = daoAccount.TotalAccountOfCustomer();
                int totalPage = TotalAccount / PAGE_SIZE;
                if (TotalAccount % PAGE_SIZE != 0) {
                    totalPage += 1;
                }
                request.setAttribute("page", page);
                request.setAttribute("totalPage", totalPage);
                request.setAttribute("listAccount", listAccount);
                request.getRequestDispatcher("EmployeeAccountCustomer.jsp").forward(request, response);
            }
            if (service.equals("searchCustomer")) {
                String search = request.getParameter("search");
                if (search == null) {
                    response.sendRedirect("employeeaccount?do=AccountCustomer");
                    return;
                }
                final int PAGE_SIZE = 6;
                int page = 1;
                String pageStr = request.getParameter("page");
                if (pageStr != null) {
                    page = Integer.parseInt(pageStr);
                }
                List listAccount = daoAccount.ListAccountOfCustomerWithPaggingByUserName(page, PAGE_SIZE, search);
                int TotalAccount = daoAccount.TotalAccountOfCustomerByUserName(search);
                int totalPage = TotalAccount / PAGE_SIZE;
                if (TotalAccount % PAGE_SIZE != 0) {
                    totalPage += 1;
                }
                String keysearch = "1";
                request.setAttribute("keysearch", keysearch);
                request.setAttribute("search", search);
                request.setAttribute("page", page);
                request.setAttribute("totalPage", totalPage);
                request.setAttribute("listAccount", listAccount);
                request.getRequestDispatcher("EmployeeAccountCustomer.jsp").forward(request, response);
            }
            if (service.equals("DetailOrder")) {
                String accountid = request.getParameter("accountid");
                List<OrderDetails> list = daoAccount.getDetailsBill(Integer.parseInt(accountid));
                List<OrderDetails> listOrder = new ArrayList<>();
                request.setAttribute("list", list);
                List<String> listOrderId = new ArrayList<>();
                for (OrderDetails orderDetails : list) {
                    listOrderId.add(String.valueOf(orderDetails.getOrderID()));
                }
                for (int i = 0; i < listOrderId.size() - 1; i++) {
                    if (listOrderId.get(i).equals(listOrderId.get(i + 1))) {
                        listOrderId.remove(i + 1);
                        i--;
                    }
                }
                double total = 0;
                String address = "";
                int status = 0;
                String orderdate ="";
                request.setAttribute("listOrderId", listOrderId);
                request.setAttribute("list", list);
                request.setAttribute("Total", total);
                request.setAttribute("address", address);
                request.setAttribute("status", status);
                request.setAttribute("orderdate", orderdate);
                request.getRequestDispatcher("EmployeeDetailAccount.jsp").forward(request, response);
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
