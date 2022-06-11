package Controller;

import Entity.Account;
import Modal.AccountDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "AdminProfileController", urlPatterns = {"/adminProfile"})
public class AdminProfileController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            AccountDao daoAccount = new AccountDao();

            String service = request.getParameter("do");
            if (service == null) {
                service = "updateAdminProfile";
            }
            if (service.equals("logout")) {
                HttpSession session = request.getSession();
                session.invalidate();
                response.sendRedirect("home");
            }
            if (service.equals("updateAdminProfile")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    HttpSession session = request.getSession();
                    Account acc = (Account) session.getAttribute("Account");
                    int AccountID = acc.getAccountid();
                    List ListAccount = daoAccount.getAccountByID(AccountID);
                    
                    request.setAttribute("list", ListAccount);
                    request.getRequestDispatcher("adminProfile.jsp").forward(request, response);
                } else {
                    HttpSession session = request.getSession();
                    Account acc = (Account) session.getAttribute("Account");
                    int AccountID = acc.getAccountid();
                    String username = acc.getUsername();
                    String password = acc.getPassword();
                    int role = acc.getRole();
                    String DisplayName = request.getParameter("displayname");
                    String Address = request.getParameter("address");
                    String Email = request.getParameter("email");
                    String Phone = request.getParameter("phone");
                    String ImageURL = request.getParameter("imageURL");

                    if (DisplayName == null || DisplayName.isEmpty()) {//Check displayName valid
                        String mess = "DisplayName is not null";
                        List ListAccount = daoAccount.getAccountByID(AccountID);

                        request.setAttribute("mess", mess);
                        request.setAttribute("list", ListAccount);
                        request.getRequestDispatcher("adminProfile.jsp").forward(request, response);
                        return;
                    }
                    if (DisplayName != DisplayName.trim()) {//Check blank valid
                        String mess = "DisplayName invalid";
                        List ListAccount = daoAccount.getAccountByID(AccountID);

                        request.setAttribute("mess", mess);
                        request.setAttribute("list", ListAccount);
                        request.getRequestDispatcher("adminProfile.jsp").forward(request, response);
                        return;
                    }
                    if (DisplayName.length() >= 50) {//Check displayName must <= 50 char
                        String mess = "DisplayName can must <=50 character";
                        List ListAccount = daoAccount.getAccountByID(AccountID);

                        request.setAttribute("mess", mess);
                        request.setAttribute("list", ListAccount);
                        request.getRequestDispatcher("adminProfile.jsp").forward(request, response);
                        return;
                    }
                    if (Address == null || Address.isEmpty()) {
                        String mess = "Address is not null";
                        List ListAccount = daoAccount.getAccountByID(AccountID);

                        request.setAttribute("mess", mess);
                        request.setAttribute("list", ListAccount);
                        request.getRequestDispatcher("adminProfile.jsp").forward(request, response);
                        return;
                    }
                    if (Address.length() > 50) {
                        String mess = "Address can must <=50 character";
                        List ListAccount = daoAccount.getAccountByID(AccountID);

                        request.setAttribute("mess", mess);
                        request.setAttribute("list", ListAccount);
                        request.getRequestDispatcher("adminProfile.jsp").forward(request, response);
                        return;
                    }
                    
                    String reg = "^(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|"
                            + "(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})$";//Format Phone
                    if (!Phone.matches(reg)) {
                        String mess = "Phone invalid";
                        List ListAccount = daoAccount.getAccountByID(AccountID);

                        request.setAttribute("mess", mess);
                        request.setAttribute("list", ListAccount);
                        request.getRequestDispatcher("adminProfile.jsp").forward(request, response);
                        return;
                    }
                    String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";//Format mail
                    if (!Email.matches(EMAIL_PATTERN)) {
                        String mess = "Email invalid";
                        List ListAccount = daoAccount.getAccountByID(AccountID);

                        request.setAttribute("mess", mess);
                        request.setAttribute("list", ListAccount);
                        request.getRequestDispatcher("adminProfile.jsp").forward(request, response);
                        return;
                    }
                    if (Email != Email.trim()) {
                        String mess = "Email invalid";
                        List ListAccount = daoAccount.getAccountByID(AccountID);

                        request.setAttribute("mess", mess);
                        request.setAttribute("list", ListAccount);
                        request.getRequestDispatcher("adminProfile.jsp").forward(request, response);
                        return;
                    }
                    if (ImageURL != ImageURL.trim()) {
                        String mess = "ImageURL invalid";
                        List ListAccount = daoAccount.getAccountByID(AccountID);

                        request.setAttribute("mess", mess);
                        request.setAttribute("list", ListAccount);
                        request.getRequestDispatcher("adminProfile.jsp").forward(request, response);
                        return;
                    }
                    Account accupdate = Account.builder()
                            .accountid(AccountID)
                            .displayname(DisplayName)
                            .address(Address)
                            .email(Email)
                            .phone(Phone)
                            .username(username)
                            .password(password)
                            .imageURL(ImageURL)
                            .role(role)
                            .build();
               
                    session.setAttribute("Account", accupdate);
                    int n = daoAccount.updateAccount(accupdate);
                    String mess = "Update Success";
                    List ListAccount = daoAccount.getAccountByID(AccountID);

                    request.setAttribute("mess", mess);
                    request.setAttribute("list", ListAccount);
                    request.getRequestDispatcher("adminProfile.jsp").forward(request, response);
                }
            }
            if (service.equals("changePass")) {
                HttpSession session = request.getSession();
                Account acc = (Account) session.getAttribute("Account");
                String pass = acc.getPassword();
                int AccountID = acc.getAccountid();
                String oldPass = request.getParameter("oldPassword");
                String newPass = request.getParameter("newPassword");
                String confirmpassword = request.getParameter("comfirmPassword");

                if (!pass.equals(oldPass)) {
                    String mess1 = "Old Password Incorrect";
                    List ListAccount = daoAccount.getAccountByID(AccountID);

                    request.setAttribute("mess1", mess1);
                    request.setAttribute("list", ListAccount);
                    request.getRequestDispatcher("adminProfile.jsp").forward(request, response);
                    return;
                } else if (newPass == null || newPass.isEmpty()) {
                    String mess1 = "New Password not empty";
                    List ListAccount = daoAccount.getAccountByID(AccountID);

                    request.setAttribute("mess1", mess1);
                    request.setAttribute("list", ListAccount);
                    request.getRequestDispatcher("adminProfile.jsp").forward(request, response);
                    return;
                } else if (confirmpassword == null || confirmpassword.isEmpty()) {
                    String mess1 = "confirmpassword not empty";
                    List ListAccount = daoAccount.getAccountByID(AccountID);

                    request.setAttribute("mess1", mess1);
                    request.setAttribute("list", ListAccount);
                    request.getRequestDispatcher("adminProfile.jsp").forward(request, response);
                    return;
                } else if (!newPass.equals(confirmpassword)) {
                    String mess1 = "New Password not same ConfirmPassword";
                    List ListAccount = daoAccount.getAccountByID(AccountID);

                    request.setAttribute("mess1", mess1);
                    request.setAttribute("list", ListAccount);
                    request.getRequestDispatcher("adminProfile.jsp").forward(request, response);
                    return;
                } else {
                    Account accChangePass = Account.builder()
                            .accountid(AccountID)
                            .displayname(acc.getDisplayname())
                            .address(acc.getAddress())
                            .email(acc.getEmail())
                            .phone(acc.getPhone())
                            .username(acc.getUsername())
                            .password(newPass)
                            .imageURL(acc.getImageURL())
                            .role(acc.getRole())
                            .build();
                    session.setAttribute("Account", accChangePass);
                    int n = daoAccount.changePassword(accChangePass);
                    
                    String mess1 = "Change Password Success!";
                    List ListAccount = daoAccount.getAccountByID(AccountID);

                    request.setAttribute("mess1", mess1);
                    request.setAttribute("list", ListAccount);
                    request.getRequestDispatcher("adminProfile.jsp").forward(request, response);
                }
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
