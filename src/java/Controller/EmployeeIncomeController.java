/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.Order;
import Entity.OrderDetails;
import dao.impl.AccountDAOImpl;
import dao.impl.OrderDAOImpl;
import dao.impl.OrderDetailsDAOImpl;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
@WebServlet(name = "EmployeeIncomeController", urlPatterns = {"/employeeincome"})
public class EmployeeIncomeController extends HttpServlet {

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
            OrderDAOImpl daoOrder = new OrderDAOImpl();
            OrderDetailsDAOImpl daoOrderDetails = new OrderDetailsDAOImpl();
            if (service == null) {
                service = "IncomeHome";
            }
            if (service.equals("IncomeHome")) {
                String bill = request.getParameter("bill");
                double total = 0;
                double TotalMoney = 0;
                if (bill == null) {
                    bill = "0";
                }
                if (bill.equals("0")) {
                    List<Order> list = daoOrder.listAllOrders();
                    for (Order order : list) {
                        List<OrderDetails> listTotalByOrderID = daoOrderDetails.listTotalByOrderID(order.getOrderID());
                        for (OrderDetails orderDetails : listTotalByOrderID) {
                            if (order.getStatus() == 3) {
                                TotalMoney += orderDetails.getTotal();
                            }
                            total += orderDetails.getTotal();
                        }
                        order.setTotal(total);
                        total = 0;
                    }
                    request.setAttribute("totalmoney", TotalMoney);
                    request.setAttribute("bill", bill);
                    request.setAttribute("list", list);
                    request.getRequestDispatcher("EmployeeIncome.jsp").forward(request, response);
                }
                if (bill.equals("1")) {
                    LocalDateTime current = LocalDateTime.now();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    String formatted = current.format(formatter);
                    List<Order> list = daoOrder.listAllOrdersByOderDate(formatted);
                    for (Order order : list) {
                        List<OrderDetails> listTotalByOrderID = daoOrderDetails.listTotalByOrderID(order.getOrderID());
                        for (OrderDetails orderDetails : listTotalByOrderID) {
                            if (order.getStatus() == 3) {
                                TotalMoney += orderDetails.getTotal();
                            }
                            total += orderDetails.getTotal();
                        }
                        order.setTotal(total);
                        total = 0;
                    }
                    request.setAttribute("totalmoney", TotalMoney);
                    request.setAttribute("bill", bill);
                    request.setAttribute("list", list);
                    request.getRequestDispatcher("EmployeeIncome.jsp").forward(request, response);
                }
                if (bill.equals("3")) {
                    List<Order> list = new ArrayList<>();
                    LocalDateTime current = LocalDateTime.now();
                    for (int i = 0; i < Integer.parseInt(bill); i++) {
                        LocalDateTime current1 = current.plusDays(-i);
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        String formatted = current1.format(formatter);
                        List<Order> listOrder = daoOrder.listAllOrdersByOderDate(formatted);
                        for (Order order : listOrder) {
                            List<OrderDetails> listTotalByOrderID = daoOrderDetails.listTotalByOrderID(order.getOrderID());
                            for (OrderDetails orderDetails : listTotalByOrderID) {
                                if (order.getStatus() == 3) {
                                    TotalMoney += orderDetails.getTotal();
                                }
                                total += orderDetails.getTotal();
                            }
                            order.setTotal(total);
                            total = 0;
                            list.add(order);
                        }
                    }
                    request.setAttribute("totalmoney", TotalMoney);
                    request.setAttribute("bill", bill);
                    request.setAttribute("list", list);
                    request.getRequestDispatcher("EmployeeIncome.jsp").forward(request, response);
                }
                if (bill.equals("7")) {
                    List<Order> list = new ArrayList<>();
                    LocalDateTime current = LocalDateTime.now();
                    for (int i = 0; i < Integer.parseInt(bill); i++) {
                        LocalDateTime current1 = current.plusDays(-i);
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        String formatted = current1.format(formatter);
                        List<Order> listOrder = daoOrder.listAllOrdersByOderDate(formatted);
                        for (Order order : listOrder) {
                            List<OrderDetails> listTotalByOrderID = daoOrderDetails.listTotalByOrderID(order.getOrderID());
                            for (OrderDetails orderDetails : listTotalByOrderID) {
                                if (order.getStatus() == 3) {
                                    TotalMoney += orderDetails.getTotal();
                                }
                                total += orderDetails.getTotal();
                            }
                            order.setTotal(total);
                            total = 0;
                            list.add(order);
                        }
                    }
                    request.setAttribute("totalmoney", TotalMoney);
                    request.setAttribute("bill", bill);
                    request.setAttribute("list", list);
                    request.getRequestDispatcher("EmployeeIncome.jsp").forward(request, response);
                }
            }
            if(service.equals("details")) {
                String odID = request.getParameter("odID");
                OrderDetailsDAOImpl dao = new OrderDetailsDAOImpl();
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
                request.getRequestDispatcher("detailsBillIncome.jsp").forward(request, response);
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
