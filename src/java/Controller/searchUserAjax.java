package Controller;

import Entity.Account;
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

@WebServlet(name = "searchUserAjax", urlPatterns = {"/searchUserAjax"})
public class searchUserAjax extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        String searchKey = request.getParameter("keySearch");
        PrintWriter out = response.getWriter();
        int CustomerID = Integer.parseInt(request.getParameter("CustomerID"));
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
            for (Account account : listAccByEm) {
                if(!account.getDisplayname().contains(searchKey)){
                    listAccByEm.remove(account);
                }
            }
            List<String> listMess = new ArrayList<>();
            String str = daoMess.getMessByID(CustomerID, acc.getAccountid());
            String[] arrOfStr = str.split("~");
            for (String a : arrOfStr) {
                listMess.add(a);
            }
            for (Account ac : listAccByEm) {
                out.println("<div class=\"chat_list active_chat\">\n"
                        + "                            <div class=\"chat_people\">\n"
                        + "                                <div class=\"chat_img\"> <img src=\""+ ac.getImageURL() +"\" alt=\"img\"> </div>\n"
                        + "                                <div class=\"chat_ib\">\n"
                        + "                                    <h5> <a href=\"chat?do=chatEm&CustomerID="+ac.getAccountid()+"\"> "+ ac.getDisplayname()+" </a>  <span class=\"chat_date\">Dec 25</span></h5>\n"
                        + "\n"
                        + "                                    <p>"+listMess.get(listMess.size() - 1).substring(Integer.toString(acc.getAccountid()).length(), listMess.get(listMess.size() - 1).length() - Integer.toString(CustomerID).length())+".</p>\n"
                        + "\n"
                        + "                                </div>\n"
                        + "                            </div>\n"
                        + "                        </div>");
            }
            
        } catch (Exception e) {
            System.out.println("Exception");
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
