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
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
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

            DAOAccount dao = new DAOAccount();
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
                    int checkAccount = dao.checkAccount(username, password);
                    String r = request.getParameter("rem");
                    if (checkAccount == 0) {
                        request.setAttribute("mess", "wrong user or pass");
                        request.getRequestDispatcher("login.jsp").forward(request, response);
                    } else if (checkAccount == 1) {
                        Cookie cu = new Cookie("us", username);
                        Cookie pa = new Cookie("pa", password);
                        Cookie cr = new Cookie("rem", r);
                        if (cr == null) {
                            //time life =0
                            cu.setMaxAge(0);
                            pa.setMaxAge(0);
                            cr.setMaxAge(0);
                        } else {
                            cu.setMaxAge(60 * 60 * 24);
                            pa.setMaxAge(60 * 60 * 24);
                            cr.setMaxAge(60 * 60 * 24);
                        }
                        response.addCookie(cu);
                        response.addCookie(pa);
                        response.addCookie(cr);
                        Account DisplayName = dao.GetDisplayNameByUsername(username);
                        Account ImageURL = dao.GetImageURLByUsername(username);
                        Account accountID = dao.GetAccountIDLByUsername(username);
                        session.setAttribute("Account", Account.builder()
                                .accountid(accountID.getAccountid())
                                .username(username)
                                .password(password)
                                .displayname(DisplayName.getDisplayname())
                                .imageURL(ImageURL.getImageURL())
                                .build());
                        //request.getRequestDispatcher("home").forward(request, response);
                    } else if (checkAccount == 2) {
                       
                        Cookie cu = new Cookie("us", username);
                        Cookie pa = new Cookie("pa", password);
                        Cookie cr = new Cookie("rem", r);
                        if (cr == null) {
                            //time life =0
                            cu.setMaxAge(0);
                            pa.setMaxAge(0);
                            cr.setMaxAge(0);
                        } else {
                            cu.setMaxAge(60 * 60 * 24);
                            pa.setMaxAge(60 * 60 * 24);
                            cr.setMaxAge(60 * 60 * 24);
                        }
                        response.addCookie(cu);
                        response.addCookie(pa);
                        response.addCookie(cr);
                        Account DisplayName = dao.GetDisplayNameByUsername(username);
                        Account ImageURL = dao.GetImageURLByUsername(username);
                        Account accountID = dao.GetAccountIDLByUsername(username);
                        session.setAttribute("Account", Account.builder()
                                .accountid(accountID.getAccountid())
                                .username(username)
                                .password(password)
                                .displayname(DisplayName.getDisplayname())
                                .imageURL(ImageURL.getImageURL())
                                .build());
                        request.getRequestDispatcher("employee.jsp").forward(request, response);

                    } else {
                        
                        Cookie cu = new Cookie("us", username);
                        Cookie pa = new Cookie("pa", password);
                        Cookie cr = new Cookie("rem", r);
                        if (cr == null) {
                            //time life =0
                            cu.setMaxAge(0);
                            pa.setMaxAge(0);
                            cr.setMaxAge(0);
                        } else {
                            cu.setMaxAge(60 * 60 * 24);
                            pa.setMaxAge(60 * 60 * 24);
                            cr.setMaxAge(60 * 60 * 24);
                        }
                        response.addCookie(cu);
                        response.addCookie(pa);
                        response.addCookie(cr);
                        Account DisplayName = dao.GetDisplayNameByUsername(username);
                        Account ImageURL = dao.GetImageURLByUsername(username);
                        Account accountID = dao.GetAccountIDLByUsername(username);
                        session.setAttribute("Account", Account.builder()
                                .accountid(accountID.getAccountid())
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
            if (service.equals("submitUpdate")) {

                HttpSession session = request.getSession();
                Account acc = (Account) session.getAttribute("Account");
                int AccountID = acc.getAccountid();
                String mess = request.getParameter("mess");
                String mess1 = request.getParameter("mess1");
                System.out.println(mess);
                List ListAccount = dao.getAccountByID(AccountID);
                request.setAttribute("list", ListAccount);
                request.setAttribute("mess", mess);
                request.setAttribute("mess1", mess1);
                request.getRequestDispatcher("UpdateProfile.jsp").forward(request, response);
            }
            if (service.equals("updateprofile")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    HttpSession session = request.getSession();
                    Account acc = (Account) session.getAttribute("Account");
                    int AccountID = acc.getAccountid();
                    List ListAccount = dao.getAccountByID(AccountID);
                    request.setAttribute("list", ListAccount);
                    request.getRequestDispatcher("UpdateProfile.jsp").forward(request, response);

                } else {
                    HttpSession session = request.getSession();
                    Account acc = (Account) session.getAttribute("Account");
                    int AccountID = acc.getAccountid();
                    String DisplayName = request.getParameter("displayname");
                    String Address = request.getParameter("address");
                    String Email = request.getParameter("email");
                    String Phone = request.getParameter("phone");
                    String ImageURL = request.getParameter("imageURL");
                    //check vadidate
                    if (DisplayName == null || DisplayName.isEmpty()) {
                        String mess = "DisplayName is not null";
                        response.sendRedirect("login?do=submitUpdate&mess=" + mess);
                        return;
                    }
                    if (DisplayName.length() >= 50) {
                        String mess = "DisplayName can must <=50 char";
                        response.sendRedirect("login?do=submitUpdate&mess=" + mess);
                        return;
                    }
                    if (Address == null || Address.isEmpty()) {
                        String mess = "Address is not null";
                        response.sendRedirect("login?do=submitUpdate&mess=" + mess);
                        return;
                    }
                    if (Address.length() > 50) {
                        String mess = "Address can must <=50 char";
                        response.sendRedirect("login?do=submitUpdate&mess=" + mess);
                        return;
                    }
                    //dinh dang SDT
                    String reg = "^(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|"
                            + "(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})$";
                    if (!Phone.matches(reg)) {
                        String mess = "Phone invalid";
                        response.sendRedirect("login?do=submitUpdate&mess=" + mess);
                        return;
                    }
                    //dinh dang mail
                    String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
                    if (!Email.matches(EMAIL_PATTERN)) {
                        String mess = "Email invalid";
                        response.sendRedirect("login?do=submitUpdate&mess=" + mess);
                        return;
                    }
                    Account accupdate = Account.builder()
                            .accountid(AccountID)
                            .displayname(DisplayName)
                            .address(Address)
                            .email(Email)
                            .phone(Phone)
                            .imageURL(ImageURL)
                            .build();
                    int n = dao.updateAccount(accupdate);
                    String mess = "update Success";
                    response.sendRedirect("login?do=submitUpdate&mess=" + mess);

                }
            }
            if (service.equals("changepassword")) {
                HttpSession session = request.getSession();
                Account acc = (Account) session.getAttribute("Account");
                String pass = acc.getPassword();
                int AccountID = acc.getAccountid();
                String password = request.getParameter("password");
                String newpassword = request.getParameter("newpassword");
                String confirmpassword = request.getParameter("confirmpassword");
                if (!pass.equals(password)) {
                    String mess = "password incorrect";
                    response.sendRedirect("login?do=submitUpdate&mess1=" + mess);
                    return;
                } else if (newpassword == null || newpassword.isEmpty()) {
                    String mess = "New Password not empty";
                    response.sendRedirect("login?do=submitUpdate&mess1=" + mess);
                    return;
                } else if (confirmpassword == null || confirmpassword.isEmpty()) {
                    String mess = "confirmpassword not empty";
                    response.sendRedirect("login?do=submitUpdate&mess1=" + mess);
                    return;
                } else if (!newpassword.equals(confirmpassword)) {
                    String mess = "New Password not same ConfirmPassword";
                    response.sendRedirect("login?do=submitUpdate&mess1=" + mess);
                    return;
                } else {
                    Account accChangePass = Account.builder()
                            .password(newpassword)
                            .accountid(AccountID)
                            .build();
                    System.out.println(newpassword);
                    int n = dao.changePassword(accChangePass);
                    String mess = "Change PassWord Success";
                    response.sendRedirect("login?do=submitUpdate&mess1=" + mess);
                }
            }
            if (service.equals("Register")) {
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                String DisplayName = request.getParameter("displayname");
                String Address = request.getParameter("address");
                String Email = request.getParameter("email");
                String Phone = request.getParameter("phone");
                String ImageURL = request.getParameter("imageURL");

                request.setAttribute("username", username);
                request.setAttribute("password", password);
                request.setAttribute("displayname", DisplayName);
                request.setAttribute("address", Address);
                request.setAttribute("email", Email);
                request.setAttribute("phone", Phone);
                request.setAttribute("imageURL", ImageURL);
                //check UserName
                //- check username null
                if (username == null || username.isEmpty()) {
                    String mess = "UserName is not empty";
                    request.setAttribute("mess", mess);
                    request.getRequestDispatcher("register.jsp").forward(request, response);
                }
                //-check username da ton tai
                List<String> listUserName = dao.ListAllUserName();
                for (String listusername : listUserName) {
                    if (listusername.equals(username)) {
                        String mess = "username available";
                        request.setAttribute("mess", mess);
                        request.getRequestDispatcher("register.jsp").forward(request, response);
                    }
                }
                //check username qua 50 ky tu
                if (username.length() > 50) {
                    String mess = "UserName <50 varchar";
                    request.setAttribute("mess", mess);
                    request.getRequestDispatcher("register.jsp").forward(request, response);
                }

                //check password 
                //check password null
                if (password == null || password.isEmpty()) {
                    String mess = "password is not empty";
                    request.setAttribute("mess", mess);
                    request.getRequestDispatcher("register.jsp").forward(request, response);
                }
                //check password qua 50 ky tu
                if (password.length() > 50) {
                    String mess = "Password <50 varchar";
                    request.setAttribute("mess", mess);
                    request.getRequestDispatcher("register.jsp").forward(request, response);
                }
                //check DisplayName
                if (DisplayName == null || DisplayName.isEmpty()) {
                    String mess = "DisplayName is not empty";
                    request.setAttribute("mess", mess);
                    request.getRequestDispatcher("register.jsp").forward(request, response);
                }
                //check DisplayName qua 50 ky tu
                if (DisplayName.length() > 50) {
                    String mess = "DisplayName <50 varchar";
                    request.setAttribute("mess", mess);
                    request.getRequestDispatcher("register.jsp").forward(request, response);
                }
                //Check email
                //check email not null
                if (Email == null || Email.isEmpty()) {
                    String mess = "Email is not empty";
                    request.setAttribute("mess", mess);
                    request.getRequestDispatcher("register.jsp").forward(request, response);
                }
                //check email da ton tai
                List<String> listEmail = dao.ListAllEmail();
                for (String listemail : listEmail) {
                    if (listemail.equals(Email)) {
                        String mess = "Email available";
                        request.setAttribute("mess", mess);
                        request.getRequestDispatcher("register.jsp").forward(request, response);
                    }
                }
                //check form email   
                String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
                if (!Email.matches(EMAIL_PATTERN)) {
                    String mess = "Email Wrong";
                    request.setAttribute("mess", mess);
                    request.getRequestDispatcher("register.jsp").forward(request, response);
                }
                //check PhoneNumber
                //Phone numer form
                String reg = "^(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|"
                        + "(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})$";
                if (!Phone.matches(reg)) {
                    String mess = "Phone invalid";
                    request.setAttribute("mess", mess);
                    request.getRequestDispatcher("register.jsp").forward(request, response);
                }
                //Phone da ton tai
                List<String> listPhone = dao.ListAllPhone();
                for (String listphone : listPhone) {
                    if (listphone.equals(Phone)) {
                        String mess = "Phone available";
                        request.setAttribute("mess", mess);
                        request.getRequestDispatcher("register.jsp").forward(request, response);
                    }
                }
                Account acc = Account.builder()
                        .username(username)
                        .password(password)
                        .displayname(DisplayName)
                        .address(Address)
                        .email(Email)
                        .phone(Phone)
                        .imageURL(ImageURL)
                        .role(1)
                        .build();
                dao.RegisterAccount(acc);
                String mess = "Register success";
                request.setAttribute("mess", mess);
                request.getRequestDispatcher("register.jsp").forward(request, response);
            }
            if (service.equals("forgotpass")) {
                response.sendRedirect("ForgotPass.jsp");
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
