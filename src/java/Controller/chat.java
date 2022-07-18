package Controller;

import Entity.Account;

import dao.MessDAO;
import dao.impl.AccountDAOImpl;
import dao.impl.MessDAOImpl;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "chat", urlPatterns = {"/chat"})
public class chat extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        int CustomerID = Integer.parseInt(request.getParameter("CustomerID"));
        String service = request.getParameter("do");
        if (service == null) {
            AccountDAOImpl daoAcc = new AccountDAOImpl();
//            List<Integer> list = dao.ListAllAccountEmpID();
//            int employeeID = dao.getRandomElemAccountEmpID(list);
//            request.setAttribute("employeeID", employeeID); 
            HttpSession session = request.getSession();
            Account acc = (Account) session.getAttribute("Account");
            MessDAOImpl daoMess = new MessDAOImpl();
            try {
                List<Integer> listCus = daoMess.getIdCusByEmployee(acc.getAccountid());

                List<Account> listAccByEm = new ArrayList<>();
                for (Integer id : listCus) {
                    listAccByEm.add(daoAcc.getAccountByAccountID(id));
                }
                List<String> listMess = new ArrayList<>();
                String str = daoMess.getMessByID(CustomerID, acc.getAccountid());
                String[] arrOfStr = str.split("~");
                for (String a : arrOfStr) {
                    listMess.add(a);
                }
                request.setAttribute("CustomerID", CustomerID);
                request.setAttribute("listMess", listMess);
                request.setAttribute("listAccByEm", listAccByEm);
                request.getRequestDispatcher("chatOfEmp.jsp").forward(request, response);
            } catch (Exception e) {
                
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
