package Controller;

import Entity.Account;
import dao.impl.AccountDAOImpl;
import dao.impl.FeedbackDAOImpl;
import dao.impl.CategoryDAOImpl;
import dao.impl.OrderDAOImpl;
import dao.impl.ProductDAOImpl;
import dao.impl.ShipperDAOImpl;
import dao.impl.SupplierDAOImpl;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "LoginController", urlPatterns = {"/login"})
public class LoginController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            String service = request.getParameter("do");

            AccountDAOImpl daoAccount = new AccountDAOImpl();
            ProductDAOImpl daoProduct = new ProductDAOImpl();
            CategoryDAOImpl daoCategory = new CategoryDAOImpl();

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
                    int checkAccount = daoAccount.checkAccount(username.trim().toLowerCase(), password);
                    String r = request.getParameter("rem");
                    if (checkAccount == 0) {
                        request.setAttribute("mess", "wrong user or pass");
                        request.getRequestDispatcher("login.jsp").forward(request, response);
                    } else if (checkAccount == 1) {
                        Cookie cu = new Cookie("us", username);
                        Cookie pa = new Cookie("pa", password);
                        Cookie cr = new Cookie("rem", r);
                        if (r == null) {
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
                        Account DisplayName = daoAccount.GetDisplayNameByUsername(username);//
                        Account ImageURL = daoAccount.GetImageURLByUsername(username);
                        Account accountID = daoAccount.GetAccountIDLByUsername(username);
                        session.setAttribute("Account", Account.builder()
                                .accountid(accountID.getAccountid())
                                .username(username)
                                .password(password)
                                .displayname(DisplayName.getDisplayname())
                                .imageURL(ImageURL.getImageURL())
                                .build());
                        request.getRequestDispatcher("index.jsp").forward(request, response);
                    } else if (checkAccount == 2) {

                        Cookie cu = new Cookie("us", username);
                        Cookie pa = new Cookie("pa", password);
                        Cookie cr = new Cookie("rem", r);
                        if (r == null) {
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
                        Account DisplayName = daoAccount.GetDisplayNameByUsername(username);
                        Account ImageURL = daoAccount.GetImageURLByUsername(username);
                        Account accountID = daoAccount.GetAccountIDLByUsername(username);
                        session.setAttribute("Account", Account.builder()
                                .accountid(accountID.getAccountid())
                                .username(username)
                                .password(password)
                                .displayname(DisplayName.getDisplayname())
                                .imageURL(ImageURL.getImageURL())
                                .build());
                        request.getRequestDispatcher("employee.jsp").forward(request, response);

                    } else if (checkAccount == 4) {
                        Cookie cu = new Cookie("us", username);
                        Cookie pa = new Cookie("pa", password);
                        Cookie cr = new Cookie("rem", r);
                        if (r == null) {
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
                        Account DisplayName = daoAccount.GetDisplayNameByUsername(username);
                        Account ImageURL = daoAccount.GetImageURLByUsername(username);
                        Account accountID = daoAccount.GetAccountIDLByUsername(username);
                        session.setAttribute("Account", Account.builder()
                                .accountid(accountID.getAccountid())
                                .username(username)
                                .password(password)
                                .displayname(DisplayName.getDisplayname())
                                .imageURL(ImageURL.getImageURL())
                                .build());
                        request.getRequestDispatcher("Shipper.jsp").forward(request, response);
                    } else {
                        Cookie cu = new Cookie("us", username);
                        Cookie pa = new Cookie("pa", password);
                        Cookie cr = new Cookie("rem", r);
                        if (r == null) {
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
                        Account acc = daoAccount.GetDisplayAccountByUsername(username);
                        session.setAttribute("Account", Account.builder()
                                .accountid(acc.getAccountid())
                                .username(username)
                                .password(password)
                                .displayname(acc.getDisplayname())
                                .imageURL(acc.getImageURL())
                                .address(acc.getAddress())
                                .email(acc.getEmail())
                                .phone(acc.getPhone())
                                .role(acc.getRole())
                                .build());

                        int totalProduct = daoProduct.getTotalProduct();//Get total All Product
                        int totalCategory = daoCategory.getTotalCategory();
                        int totalSupplier = new SupplierDAOImpl().getTotalSupplier();
                        int totalOrder = new OrderDAOImpl().getTotalOrder();
                        int totalFeedback = new FeedbackDAOImpl().getTotalFeedBack();
                        int totalCustomer = new AccountDAOImpl().getTotalCustomer();
                        int totalShipper = new ShipperDAOImpl().getTotalShipper();

                        request.setAttribute("totalOrder", totalOrder);
                        request.setAttribute("totalFeedback", totalFeedback);
                        request.setAttribute("totalCustomer", totalCustomer);
                        request.setAttribute("totalShipper", totalShipper);
                        request.setAttribute("totalSupplier", totalSupplier);
                        request.setAttribute("totalProduct", totalProduct);
                        request.setAttribute("totalCategory", totalCategory);
                        request.getRequestDispatcher("admin.jsp").forward(request, response);
                    }
                }
            }
            if (service.equals("logout")) {
                HttpSession session = request.getSession();
                session.removeAttribute("Account");
                response.sendRedirect("login.jsp");
            }
            if (service.equals("submitUpdate")) {

                HttpSession session = request.getSession();
                Account acc = (Account) session.getAttribute("Account");
                int AccountID = acc.getAccountid();
                String mess = request.getParameter("mess");
                String mess1 = request.getParameter("mess1");
                List ListAccount = daoAccount.getAccountByID(AccountID);
                request.setAttribute("list", ListAccount);
                request.setAttribute("mess", mess);
                request.setAttribute("mess1", mess1);
                request.getRequestDispatcher("my-account.jsp").forward(request, response);
            }
            if (service.equals("updateprofile")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    HttpSession session = request.getSession();
                    Account acc = (Account) session.getAttribute("Account");
                    int AccountID = acc.getAccountid();
                    System.out.println(acc);
                    List ListAccount = daoAccount.getAccountByID(AccountID);
                    request.setAttribute("list", ListAccount);
                    request.getRequestDispatcher("my-account.jsp").forward(request, response);

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
                    session.setAttribute("Account", accupdate);
                    int n = daoAccount.updateAccount(accupdate);
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
                    int n = daoAccount.changePassword(accChangePass);
                    String mess = "Change PassWord Success";
                    response.sendRedirect("login?do=submitUpdate&mess1=" + mess);
                }
            }
            if (service.equals("Register")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    response.sendRedirect("register.jsp");
                } else {
                    String username = request.getParameter("username");
                    String password = request.getParameter("password");
                    String DisplayName = request.getParameter("displayname");
                    String Address = request.getParameter("address");
                    String Email = request.getParameter("email");
                    String Phone = request.getParameter("phone");
                    String ImageURL = request.getParameter("imageURL");
                    String gender = request.getParameter("gender");

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
                        return;
                    }
                    //-check username da ton tai
                    List<String> listUserName = daoAccount.ListAllUserName();
                    for (String listusername : listUserName) {
                        if (listusername.equals(username)) {
                            String mess = "username available";
                            request.setAttribute("mess", mess);
                            request.getRequestDispatcher("register.jsp").forward(request, response);
                            return;
                        }
                    }
                    //check username qua 50 ky tu
                    if (username.length() > 50) {
                        String mess = "UserName <50 varchar";
                        request.setAttribute("mess", mess);
                        request.getRequestDispatcher("register.jsp").forward(request, response);
                        return;
                    }

                    //check password 
                    //check password null
                    if (password == null || password.isEmpty()) {
                        String mess = "password is not empty";
                        request.setAttribute("mess", mess);
                        request.getRequestDispatcher("register.jsp").forward(request, response);
                        return;
                    }
                    //check password qua 50 ky tu
                    if (password.length() > 50) {
                        String mess = "Password <50 varchar";
                        request.setAttribute("mess", mess);
                        request.getRequestDispatcher("register.jsp").forward(request, response);
                        return;
                    }
                    //check DisplayName
                    if (DisplayName == null || DisplayName.isEmpty()) {
                        String mess = "DisplayName is not empty";
                        request.setAttribute("mess", mess);
                        request.getRequestDispatcher("register.jsp").forward(request, response);
                        return;
                    }
                    //check DisplayName qua 50 ky tu
                    if (DisplayName.length() > 50) {
                        String mess = "DisplayName <50 varchar";
                        request.setAttribute("mess", mess);
                        request.getRequestDispatcher("register.jsp").forward(request, response);
                        return;
                    }
                    //Check email
                    //check email not null
                    if (Email == null || Email.isEmpty()) {
                        String mess = "Email is not empty";
                        request.setAttribute("mess", mess);
                        request.getRequestDispatcher("register.jsp").forward(request, response);
                        return;
                    }
                    //check email da ton tai
                    List<String> listEmail = daoAccount.ListAllEmail();
                    for (String listemail : listEmail) {
                        if (listemail.equals(Email)) {
                            String mess = "Email available";
                            request.setAttribute("mess", mess);
                            request.getRequestDispatcher("register.jsp").forward(request, response);
                            return;
                        }
                    }
                    //check form email   
                    String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
                    if (!Email.matches(EMAIL_PATTERN)) {
                        String mess = "Email Wrong";
                        request.setAttribute("mess", mess);
                        request.getRequestDispatcher("register.jsp").forward(request, response);
                        return;
                    }
                    //check PhoneNumber
                    //Phone numer form
                    String reg = "^(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|"
                            + "(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})$";
                    if (!Phone.matches(reg)) {
                        String mess = "Phone invalid";
                        request.setAttribute("mess", mess);
                        request.getRequestDispatcher("register.jsp").forward(request, response);
                        return;
                    }
                    //Phone da ton tai
                    List<String> listPhone = daoAccount.ListAllPhone();
                    for (String listphone : listPhone) {
                        if (listphone.equals(Phone)) {
                            String mess = "Phone available";
                            request.setAttribute("mess", mess);
                            request.getRequestDispatcher("register.jsp").forward(request, response);
                            return;
                        }
                    }
                    Account acc = Account.builder()
                            .username(username.toLowerCase())
                            .password(password)
                            .displayname(DisplayName)
                            .address(Address)
                            .email(Email)
                            .phone(Phone)
                            .imageURL(ImageURL)
                            .role(1)
                            .build();

                    daoAccount.RegisterAccount(acc);
                    System.out.println(acc);
                    String mess = "Register success";
                    request.setAttribute("mess", mess);
                    request.getRequestDispatcher("register.jsp").forward(request, response);
                }
            }
            if (service.equals("forgetpassword")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    response.sendRedirect("forgetpassword.jsp");
                } else {
                    String email = request.getParameter("email");
                    List list = daoAccount.ListAllEmail();
                    for (Object o : list) {
                        if (email.equals(o)) {
                            Account acc = daoAccount.GetAccountByEmail(email);
                            String pass = daoAccount.SendMail(acc);
                            request.setAttribute("code", pass);
                            request.setAttribute("email", email);
                            request.getRequestDispatcher("resetpassword.jsp").forward(request, response);
                            return;
                        }
                    }
                    String mess = "Email not exis";
                    request.setAttribute("mess", mess);
                    request.getRequestDispatcher("forgetpassword.jsp").forward(request, response);
                }
            }
            if (service.equals("updatepassword")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    response.sendRedirect("resetpassword.jsp");
                } else {
                    String email = request.getParameter("email");
                    String code = request.getParameter("code");
                    String password = request.getParameter("password");
                    String newpassword = request.getParameter("newpassword");
                    String confirmpassword = request.getParameter("confirmpassword");
                    if (!code.equals(password)) {
                        String mess = "Wrong Password";
                        request.setAttribute("code", code);
                        request.setAttribute("email", email);
                        request.setAttribute("mess", mess);
                        request.getRequestDispatcher("resetpassword.jsp").forward(request, response);
                        return;
                    } else if (newpassword == null || newpassword.isEmpty()) {
                        String mess = "newpassword is not null";
                        request.setAttribute("code", code);
                        request.setAttribute("email", email);
                        request.setAttribute("mess", mess);
                        request.getRequestDispatcher("resetpassword.jsp").forward(request, response);
                        return;
                    } else if (confirmpassword == null || confirmpassword.isEmpty()) {
                        String mess = "confirmpassword is not null";
                        request.setAttribute("code", code);
                        request.setAttribute("email", email);
                        request.setAttribute("mess", mess);
                        request.getRequestDispatcher("resetpassword.jsp").forward(request, response);
                        return;
                    } else if (!newpassword.equals(confirmpassword)) {
                        String mess = "newpassword not same confirmpassword";
                        request.setAttribute("code", code);
                        request.setAttribute("email", email);
                        request.setAttribute("mess", mess);
                        request.getRequestDispatcher("resetpassword.jsp").forward(request, response);
                        return;
                    } else {
                        Account acc = Account.builder()
                                .password(newpassword)
                                .email(email)
                                .build();
                        daoAccount.updatePasswordByEmail(acc);
                        String mess = "Update Success";
                        request.setAttribute("mess", mess);
                        request.getRequestDispatcher("login.jsp").forward(request, response);
                    }
                }
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
