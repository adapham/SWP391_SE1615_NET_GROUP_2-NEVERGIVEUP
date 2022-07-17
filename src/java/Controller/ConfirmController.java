package Controller;

import Entity.Account;
import Entity.Order;
import Entity.Product;
import dao.impl.AccountDAOImpl;
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

@WebServlet(name = "confirm", urlPatterns = {"/confirm"})
public class ConfirmController extends HttpServlet {

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
            OrderDAOImpl daoOrder = new OrderDAOImpl();
            OrderDetailsDAOImpl daoOrderDetails = new OrderDetailsDAOImpl();
            AccountDAOImpl daoAccount = new AccountDAOImpl();
            HttpSession session = request.getSession();
            Account acc = (Account) session.getAttribute("Account");
            int accountid = acc.getAccountid();
            Account account = daoAccount.getAccountByAccountID(accountid);

            String totalPrice = request.getParameter("total");
            String accountID = request.getParameter("accountID");
            String displayName = request.getParameter("displayName");
            String address = request.getParameter("address");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String temp = request.getParameter("temp");

            List<Product> listProductCarts = new ArrayList<Product>();
            Enumeration em = session.getAttributeNames();
            while (em.hasMoreElements()) {
                String key = em.nextElement().toString();
                if (!key.equals("urlHistory") && !key.equals("backToUrl") && !key.equals("order") && !key.equals("listCategory") && !key.equals("Account") && !key.equals("size")) {
                    Product pro = (Product) session.getAttribute(key);
                    if (pro == null) {
                        listProductCarts = new ArrayList<>();
                    }
                    listProductCarts.add(pro);
                    session.setAttribute(key, pro);
                }
            }
            double totalMoney = 0;
            for (Product list : listProductCarts) {
                totalMoney += list.getUnitPrice() * list.getQuantity();
                totalMoney *= 100;
                double total = Math.ceil(totalMoney);
                total = (double) total / 100;
                totalMoney = total;
            }
            request.setAttribute("totalMoney", totalMoney);
            Order order = Order.builder()
                    .accountID(accountid)
                    .shipperID(1)
                    .address(address.trim())
                    .email(email)
                    .status(1)
                    .phone(phone.trim())
                    .build();

            request.setAttribute("listProductCarts", listProductCarts);
            session.setAttribute("order", order);
            request.setAttribute("account", account);
            request.getRequestDispatcher("confirm.jsp").forward(request, response);
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
