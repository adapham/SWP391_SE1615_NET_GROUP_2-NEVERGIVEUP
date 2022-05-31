/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Modal.DAOAccount;
import View.Account;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author admin
 */
@WebServlet(name = "LoginController", urlPatterns = {"/login"})
public class LoginController extends HttpServlet {

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
                service = "login";
            }
            if (service.equals("login")) {
                HttpSession session = request.getSession();
                String submit = request.getParameter("submit");
                if (submit == null) {
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                } else {
                    String username = request.getParameter("username");
                    String password = request.getParameter("password");
                    DAOAccount dao = new DAOAccount();

                    int checkAccount = dao.checkAccount(username, password);
                    if (checkAccount == 0) {
                        request.setAttribute("mess", "wrong user or pass");
                        request.getRequestDispatcher("login.jsp").forward(request, response);
                    } else if (checkAccount == 1) {
                        Account DisplayName = dao.GetDisplayNameByUsername(username);
                        Account ImageURL = dao.GetImageURLByUsername(username);
                        session.setAttribute("Account", Account.builder()
                                .username(username)
                                .password(password)
                                .displayname(DisplayName.getDisplayname())
                                .imageURL(ImageURL.getImageURL())
                                .build());
                        request.getRequestDispatcher("home.jsp").forward(request, response);
                    } else if (checkAccount == 2) {
                        Account DisplayName = dao.GetDisplayNameByUsername(username);
                        Account ImageURL = dao.GetImageURLByUsername(username);
                        session.setAttribute("Account", Account.builder()
                                .username(username)
                                .password(password)
                                .displayname(DisplayName.getDisplayname())
                                .imageURL(ImageURL.getImageURL())
                                .build());
                        request.getRequestDispatcher("employee.jsp").forward(request, response);

                    } else {
                        Account DisplayName = dao.GetDisplayNameByUsername(username);
                        Account ImageURL = dao.GetImageURLByUsername(username);
                        session.setAttribute("Account", Account.builder()
                                .username(username)
                                .password(password)
                                .displayname(DisplayName.getDisplayname())
                                .imageURL(ImageURL.getImageURL())
                                .build());
                        request.getRequestDispatcher("admin.jsp").forward(request, response);
                    }
                }
            }
            if (service.equals("logout")) {
                HttpSession session = request.getSession();
                session.removeAttribute("Account");
                response.sendRedirect("home");
            }
            if(service.equals("updateprofile")) {
                request.getRequestDispatcher("UpdateProfile.jsp").forward(request, response);
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
