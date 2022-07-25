package Controller;

import Entity.Account;
import Entity.Product;
import dao.MessDAO;
import dao.impl.AccountDAOImpl;
import dao.impl.MessDAOImpl;
import dao.impl.ProductDAOImpl;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class HomeController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        int employeeID = 0;
        Account acc = (Account) session.getAttribute("Account");
        String service = request.getParameter("do");
        String fresh = request.getParameter("fresh");
        try {
            ProductDAOImpl daoProduct = new ProductDAOImpl();
            if (service == null) {
                service = "home";
            }
            if (service.equals("home")) {//Chuyển đến trang home

                if (fresh == null) {

                    AccountDAOImpl dao = new AccountDAOImpl();
                    MessDAOImpl daoMess = new MessDAOImpl();
                    List<Integer> list = dao.ListAllAccountEmpID();

                    ///
                    List<Integer> listIdCusCheck = daoMess.getIdCus();
                    boolean check = false;
                    for (Integer integer : listIdCusCheck) {

                        if (integer == acc.getAccountid()) {

                            check = true;
                        }
                    }
                    if (check) {
                        employeeID = daoMess.getIdEmByIdCus(acc.getAccountid());
                    } else {
                        employeeID = dao.getRandomElemAccountEmpID(list);
                    }
                    List<String> listMess = new ArrayList<>();
                    String str = daoMess.getMessByID(acc.getAccountid(), employeeID);
                    String[] arrOfStr = str.split("~");
                    for (String a : arrOfStr) {
                        listMess.add(a);
                    }
                    request.setAttribute("listMess", listMess);
                    System.out.println(listMess.size());
                    request.setAttribute("employeeID", employeeID);
                    System.out.println(employeeID);
                    List<Product> listProduct = daoProduct.getTopNumberProduct(4);
                    request.setAttribute("listProduct", listProduct);
                    int lengthcus = Integer.toString(acc.getAccountid()).length();
                    int lengthemployee = (String.valueOf(employeeID).toString()).length();
                    System.out.println(lengthcus+" cus");
                    System.out.println(lengthemployee + " emp");
                    request.setAttribute("lengthcus", lengthcus);
                    request.setAttribute("lengthemployee", lengthemployee);
                } else {
                    List<Product> listProduct = daoProduct.getTopNumberProduct(4);
                    request.setAttribute("listProduct", listProduct);
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                }
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
            if (service.equals(fresh)) {

            }
            if (service.equals("about")) {//Chuyển đến trang about
                List<Product> listProduct = daoProduct.getTopNumberProduct(2);
                request.setAttribute("listProduct", listProduct);
                request.getRequestDispatcher("about.jsp").forward(request, response);
            }
            if (service.equals("blog")) {//Chuyển đến trang blog

                request.getRequestDispatcher("blog.jsp").forward(request, response);
            }
            if (service.equals("contact")) {//Chuyển đến trang contact

                request.getRequestDispatcher("contact.jsp").forward(request, response);
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
