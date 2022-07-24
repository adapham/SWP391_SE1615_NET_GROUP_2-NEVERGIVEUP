/*
 Quan li Account cua trang Admin bao gom update,delete cua nhan vien, khach hang hoac shipper, create account cua employee va shipper
 */
package Controller;

import Entity.Account;
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

@WebServlet(name = "AccountManagerController", urlPatterns = {"/accountmanager"})
public class AccountManagerController extends HttpServlet {

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
                service = "listAccountCustomer";
            }
            if (service.equals("listAccountCustomer")) {
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
                request.getRequestDispatcher("adminCustomerManager.jsp").forward(request, response);
            }
            if (service.equals("updateCustomer")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    String accountid = request.getParameter("accountid");
                    List list = daoAccount.getAccountByID(Integer.parseInt(accountid));
                    request.setAttribute("list", list);
                    request.getRequestDispatcher("adminUpdateAccountCustomer.jsp").forward(request, response);
                } else {
                    String username = request.getParameter("username");
                    String password = request.getParameter("password");
                    String DisplayName = request.getParameter("displayname");
                    String address = request.getParameter("address");
                    String Email = request.getParameter("email");
                    String Phone = request.getParameter("phone");
                    String gender = request.getParameter("gender");
                    String imageURL = request.getParameter("imageURL");
                    String accountid = request.getParameter("accountid");
                    String emailold = request.getParameter("emailold");
                    String phoneold = request.getParameter("phoneold");

                    request.setAttribute("username", username);
                    request.setAttribute("displayname", DisplayName);
                    request.setAttribute("address", address);
                    request.setAttribute("email", Email);
                    request.setAttribute("phone", Phone);
                    request.setAttribute("imageURL", imageURL);
                    request.setAttribute("gender", gender);
                    Account acc = Account.builder()
                            .accountid(Integer.parseInt(accountid))
                            .username(username)
                            .displayname(DisplayName)
                            .address(address)
                            .email(Email)
                            .phone(Phone)
                            .imageURL(imageURL)
                            .gender(Integer.parseInt(gender))
                            .build();
                    List list = new ArrayList();
                    list.add(acc);
                    request.setAttribute("list", list);
                    //check DisplayName
                    if (DisplayName.trim() == null || DisplayName.trim().isEmpty()) {
                        String mess = "Displayname is not empty";
                        request.setAttribute("mess", mess);
                        request.getRequestDispatcher("adminUpdateAccountCustomer.jsp").forward(request, response);
                        return;
                    }
                    //Check email
                    //check email not null
                    if (Email == null || Email.isEmpty()) {
                        String mess = "Email is not empty";
                        request.setAttribute("mess", mess);
                        request.getRequestDispatcher("adminUpdateAccountCustomer.jsp").forward(request, response);
                        return;
                    }
                    //chap nhan email cu
                    daoAccount.updateEmailCustomerByEmail(accountid);
                    //check email da ton tai
                    List<String> listEmail = daoAccount.ListAllEmail();
                    for (String listemail : listEmail) {
                        if (listemail.equals(Email)) {
                            String mess = "Email available";
                            request.setAttribute("mess", mess);
                            request.getRequestDispatcher("adminUpdateAccountCustomer.jsp").forward(request, response);
                            return;
                        }
                    }
                    daoAccount.updateEmailOldCustomerByEmail(Email, accountid);

                    //check PhoneNumber
                    //Phone numer form
                    String reg = "^(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|"
                            + "(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})$";
                    if (!Phone.matches(reg)) {
                        String mess = "Phone invalid";
                        request.setAttribute("mess", mess);
                        request.getRequestDispatcher("adminUpdateAccountCustomer.jsp").forward(request, response);
                        return;
                    }
                    daoAccount.updatePhoneCustomerByPhone(accountid);

                    //Phone da ton tai
                    List<String> listPhone = daoAccount.ListAllPhone();
                    for (String listphone : listPhone) {
                        if (listphone.equals(Phone)) {
                            String mess = "Phone available";
                            request.setAttribute("mess", mess);
                            request.getRequestDispatcher("adminUpdateAccountCustomer.jsp").forward(request, response);
                            return;
                        }
                    }
                    daoAccount.updatePhoneOldCustomerByPhone(phoneold, accountid);

                    String mess = "Update successful";
                    int AccountID = Integer.parseInt(accountid);
                    Account accoutupdate = Account.builder()
                            .accountid(AccountID)
                            .displayname(DisplayName.trim())
                            .address(address.trim())
                            .email(Email.trim())
                            .phone(Phone)
                            .imageURL(imageURL.trim())
                            .gender(Integer.parseInt(gender))
                            .build();
                    int n = daoAccount.updateAccount(accoutupdate);
                    request.setAttribute("mess", mess);
                    request.getRequestDispatcher("adminUpdateAccountCustomer.jsp").forward(request, response);
                    return;
                }

            }
            if (service.equals("deleteCustomer")) {
                String search = request.getParameter("search");
                String accountid = request.getParameter("accountid");
                String page = request.getParameter("page");
                int n = daoAccount.deleteAccountById(accountid);
                if (search != null) {
                    response.sendRedirect("accountmanager?do=searchCustomer&page=" + page + "&search=" + search);
                } else {
                    response.sendRedirect("accountmanager?page=" + page);
                }
            }
            if (service.equals("searchCustomer")) {
                String search = request.getParameter("search");
                if (search == null) {
                    response.sendRedirect("accountmanager");
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
                request.getRequestDispatcher("adminCustomerManager.jsp").forward(request, response);
            }
            if (service.equals("listAccountEmployee")) {
                final int PAGE_SIZE = 6;
                int page = 1;
                String pageStr = request.getParameter("page");
                if (pageStr != null) {
                    page = Integer.parseInt(pageStr);
                }
                List listAccount = daoAccount.ListAccountOfEmployeeWithPagging(page, PAGE_SIZE);
                int TotalAccount = daoAccount.TotalAccountOfEmployee();
                int totalPage = TotalAccount / PAGE_SIZE;
                if (TotalAccount % PAGE_SIZE != 0) {
                    totalPage += 1;
                }
                request.setAttribute("page", page);
                request.setAttribute("totalPage", totalPage);
                request.setAttribute("listAccount", listAccount);
                request.getRequestDispatcher("adminEmployeeManager.jsp").forward(request, response);
            }
            if (service.equals("updateEmployee")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    String accountid = request.getParameter("accountid");
                    List list = daoAccount.getAccountByID(Integer.parseInt(accountid));
                    request.setAttribute("list", list);
                    request.getRequestDispatcher("adminUpdateAccountEmployee.jsp").forward(request, response);
                } else {
                    String username = request.getParameter("username");
                    String password = request.getParameter("password");
                    String DisplayName = request.getParameter("displayname");
                    String address = request.getParameter("address");
                    String Email = request.getParameter("email");
                    String Phone = request.getParameter("phone");
                    String imageURL = request.getParameter("imageURL");
                    String accountid = request.getParameter("accountid");
                    String emailold = request.getParameter("emailold");
                    String phoneold = request.getParameter("phoneold");
                    request.setAttribute("username", username);
                    request.setAttribute("displayname", DisplayName);
                    request.setAttribute("address", address);
                    request.setAttribute("email", Email);
                    request.setAttribute("phone", Phone);
                    request.setAttribute("imageURL", imageURL);
                    Account acc = Account.builder()
                            .accountid(Integer.parseInt(accountid))
                            .username(username)
                            .displayname(DisplayName)
                            .address(address)
                            .email(Email)
                            .phone(Phone)
                            .imageURL(imageURL)
                            .build();
                    List list = new ArrayList();
                    list.add(acc);
                    request.setAttribute("list", list);
                    //check DisplayName
                    if (DisplayName == null || DisplayName.isEmpty()) {
                        String mess = "Displayname is not empty";
                        request.setAttribute("mess", mess);
                        request.getRequestDispatcher("adminUpdateAccountEmployee.jsp").forward(request, response);
                        return;
                    }
                    //Check email
                    //check email not null
                    if (Email == null || Email.isEmpty()) {
                        String mess = "Email is not empty";
                        request.setAttribute("mess", mess);
                        request.getRequestDispatcher("adminUpdateAccountEmployee.jsp").forward(request, response);
                        return;
                    }
                    //chap nhan email cu
                    daoAccount.updateEmailCustomerByEmail(accountid);
                    //check email da ton tai
                    List<String> listEmail = daoAccount.ListAllEmail();
                    for (String listemail : listEmail) {
                        if (listemail.equals(Email)) {
                            String mess = "Email available";
                            request.setAttribute("mess", mess);
                            request.getRequestDispatcher("adminUpdateAccountCustomer.jsp").forward(request, response);
                            return;
                        }
                    }
                    daoAccount.updateEmailOldCustomerByEmail(Email, accountid);

                    //check PhoneNumber
                    //Phone numer form
                    String reg = "^(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|"
                            + "(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})$";
                    if (!Phone.matches(reg)) {
                        String mess = "Phone invalid";
                        request.setAttribute("mess", mess);
                        request.getRequestDispatcher("adminUpdateAccountCustomer.jsp").forward(request, response);
                        return;
                    }
                    daoAccount.updatePhoneCustomerByPhone(accountid);

                    //Phone da ton tai
                    List<String> listPhone = daoAccount.ListAllPhone();
                    for (String listphone : listPhone) {
                        if (listphone.equals(Phone)) {
                            String mess = "Phone available";
                            request.setAttribute("mess", mess);
                            request.getRequestDispatcher("adminUpdateAccountCustomer.jsp").forward(request, response);
                            return;
                        }
                    }
                    daoAccount.updatePhoneOldCustomerByPhone(phoneold, accountid);
                    String mess = "Update successful";
                    int AccountID = Integer.parseInt(accountid);
                    Account accoutupdate = Account.builder()
                            .accountid(AccountID)
                            .displayname(DisplayName)
                            .address(address)
                            .email(Email)
                            .phone(Phone)
                            .imageURL(imageURL)
                            .build();
                    int n = daoAccount.updateAccount(accoutupdate);
                    request.setAttribute("mess", mess);
                    request.getRequestDispatcher("adminUpdateAccountEmployee.jsp").forward(request, response);
                    return;
                }

            }
            if (service.equals("deleteEmployee")) {
                String search = request.getParameter("search");
                String accountid = request.getParameter("accountid");
                String page = request.getParameter("page");
                int n = daoAccount.deleteAccountById(accountid);
                if (search != null) {
                    response.sendRedirect("accountmanager?do=searchEmployee&page=" + page + "&search=" + search);
                } else {
                    response.sendRedirect("accountmanager?do=listAccountEmployee&page=" + page);
                }
            }
            if (service.equals("searchEmployee")) {
                String search = request.getParameter("search");
                if (search == null) {
                    response.sendRedirect("accountmanager?do=listAccountEmployee");
                    return;
                }
                final int PAGE_SIZE = 6;
                int page = 1;
                String pageStr = request.getParameter("page");
                if (pageStr != null) {
                    page = Integer.parseInt(pageStr);
                }
                List listAccount = daoAccount.ListAccountOfEmployeeWithPaggingByUserName(page, PAGE_SIZE, search);
                int TotalAccount = daoAccount.TotalAccountOfEmployee();
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
                request.getRequestDispatcher("adminEmployeeManager.jsp").forward(request, response);
            }
            if (service.equals("createAccount")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    request.getRequestDispatcher("adminCreateAccount.jsp").forward(request, response);
                } else {

                    String username = request.getParameter("username");
                    String password = request.getParameter("password");
                    String DisplayName = request.getParameter("displayname");
                    String address = request.getParameter("address");
                    String Email = request.getParameter("email");
                    String Phone = request.getParameter("phone");
                    String imageURL = request.getParameter("imageURL");
                    String role = request.getParameter("role");
                    String gender = request.getParameter("gender");

                    request.setAttribute("username", username.trim());
                    request.setAttribute("password", password.trim());
                    request.setAttribute("displayname", DisplayName.trim());
                    request.setAttribute("address", address.trim());
                    request.setAttribute("email", Email);
                    request.setAttribute("phone", Phone);
                    request.setAttribute("imageURL", imageURL);
                    request.setAttribute("role", role);
                    request.setAttribute("gender", gender);
                    //- check username null
                    if (username.trim() == null || username.trim().isEmpty()) {
                        String mess = "User name is not empty";
                        request.setAttribute("mess", mess);
                        request.getRequestDispatcher("adminCreateAccount.jsp").forward(request, response);
                        return;
                    }
                    //-check username da ton tai
                    List<String> listUserName = daoAccount.ListAllUserName();
                    for (String listusername : listUserName) {
                        if (listusername.equals(username.trim())) {
                            String mess = "User name available";
                            request.setAttribute("mess", mess);
                            request.getRequestDispatcher("adminCreateAccount.jsp").forward(request, response);
                            return;
                        }
                    }
                    //check password 
                    //check password null
                    if (password.trim() == null || password.trim().isEmpty()) {
                        String mess = "password is not empty";
                        request.setAttribute("mess", mess);
                        request.getRequestDispatcher("adminCreateAccount.jsp").forward(request, response);
                        return;
                    }
                    //check DisplayName
                    if (DisplayName.trim() == null || DisplayName.trim().isEmpty()) {
                        String mess = "Displayname is not empty";
                        request.setAttribute("mess", mess);
                        request.getRequestDispatcher("adminCreateAccount.jsp").forward(request, response);
                        return;
                    }
                    //check Adreess
                    if (address.trim() == null || address.trim().isEmpty()) {
                        String mess = "Address is not empty";
                        request.setAttribute("mess", mess);
                        request.getRequestDispatcher("adminCreateAccount.jsp").forward(request, response);
                        return;
                    }
                    //Check email
                    //check email not null
                    if (Email == null || Email.isEmpty()) {
                        String mess = "Email is not empty";
                        request.setAttribute("mess", mess);
                        request.getRequestDispatcher("adminCreateAccount.jsp").forward(request, response);
                        return;
                    }
                    //check email da ton tai
                    List<String> listEmail = daoAccount.ListAllEmail();
                    for (String listemail : listEmail) {
                        if (listemail.equals(Email)) {
                            String mess = "Email available";
                            request.setAttribute("mess", mess);
                            request.getRequestDispatcher("adminCreateAccount.jsp").forward(request, response);
                            return;
                        }
                    }
                    //check form email   
                    String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
                    if (!Email.matches(EMAIL_PATTERN)) {
                        String mess = "Email wrong";
                        request.setAttribute("mess", mess);
                        request.getRequestDispatcher("adminCreateAccount.jsp").forward(request, response);
                        return;
                    }
                    //check PhoneNumber
                    //Phone numer form
                    String reg = "^(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|"
                            + "(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})$";
                    if (!Phone.matches(reg)) {
                        String mess = "Phone invalid";
                        request.setAttribute("mess", mess);
                        request.getRequestDispatcher("adminCreateAccount.jsp").forward(request, response);
                        return;
                    }
                    //Phone da ton tai
                    List<String> listPhone = daoAccount.ListAllPhone();
                    for (String listphone : listPhone) {
                        if (listphone.equals(Phone)) {
                            String mess = "Phone available";
                            request.setAttribute("mess", mess);
                            request.getRequestDispatcher("adminCreateAccount.jsp").forward(request, response);
                            return;
                        }
                    }
                    Account acc = Account.builder()
                            .username(username.trim())
                            .password(password)
                            .displayname(DisplayName.trim())
                            .address(address.trim())
                            .email(Email)
                            .phone(Phone)
                            .imageURL(imageURL.trim())
                            .role(Integer.parseInt(role))
                            .gender(Integer.parseInt(gender))
                            .build();
                    daoAccount.RegisterAccount(acc);
                    String mess = "Create account successful";
                    request.setAttribute("mess", mess);
                    request.getRequestDispatcher("adminCreateAccount.jsp").forward(request, response);
                }

            }
            if (service.equals("listAccountShipper")) {
                final int PAGE_SIZE = 6;
                int page = 1;
                String pageStr = request.getParameter("page");
                if (pageStr != null) {
                    page = Integer.parseInt(pageStr);
                }
                List listAccount = daoAccount.ListAccountOfShipperWithPagging(page, PAGE_SIZE);
                int TotalAccount = daoAccount.TotalAccountOfShipper();
                int totalPage = TotalAccount / PAGE_SIZE;
                if (TotalAccount % PAGE_SIZE != 0) {
                    totalPage += 1;
                }
                request.setAttribute("page", page);
                request.setAttribute("totalPage", totalPage);
                request.setAttribute("listAccount", listAccount);
                request.getRequestDispatcher("adminShippersManager.jsp").forward(request, response);
            }
            if (service.equals("updateShipper")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    String accountid = request.getParameter("accountid");
                    List list = daoAccount.getAccountByID(Integer.parseInt(accountid));
                    request.setAttribute("list", list);
                    request.getRequestDispatcher("adminUpdateAccountShipper.jsp").forward(request, response);
                } else {
                    String username = request.getParameter("username");
                    String password = request.getParameter("password");
                    String DisplayName = request.getParameter("displayname");
                    String address = request.getParameter("address");
                    String Email = request.getParameter("email");
                    String Phone = request.getParameter("phone");
                    String imageURL = request.getParameter("imageURL");
                    String accountid = request.getParameter("accountid");
                    String emailold = request.getParameter("emailold");
                    String phoneold = request.getParameter("phoneold");
                    request.setAttribute("username", username);
                    request.setAttribute("displayname", DisplayName);
                    request.setAttribute("address", address);
                    request.setAttribute("email", Email);
                    request.setAttribute("phone", Phone);
                    request.setAttribute("imageURL", imageURL);
                    Account acc = Account.builder()
                            .accountid(Integer.parseInt(accountid))
                            .username(username)
                            .displayname(DisplayName)
                            .address(address)
                            .email(Email)
                            .phone(Phone)
                            .imageURL(imageURL)
                            .build();
                    List list = new ArrayList();
                    list.add(acc);
                    request.setAttribute("list", list);
                    //check DisplayName
                    if (DisplayName == null || DisplayName.isEmpty()) {
                        String mess = "Displayname is not empty";
                        request.setAttribute("mess", mess);
                        request.getRequestDispatcher("adminUpdateAccountShipper.jsp").forward(request, response);
                        return;
                    }
                    //Check email
                    //check email not null
                    if (Email == null || Email.isEmpty()) {
                        String mess = "Email is not empty";
                        request.setAttribute("mess", mess);
                        request.getRequestDispatcher("adminUpdateAccountShipper.jsp").forward(request, response);
                        return;
                    }
                    //chap nhan email cu
                    daoAccount.updateEmailCustomerByEmail(accountid);
                    //check email da ton tai
                    List<String> listEmail = daoAccount.ListAllEmail();
                    for (String listemail : listEmail) {
                        if (listemail.equals(Email)) {
                            String mess = "Email available";
                            request.setAttribute("mess", mess);
                            request.getRequestDispatcher("adminUpdateAccountCustomer.jsp").forward(request, response);
                            return;
                        }
                    }
                    daoAccount.updateEmailOldCustomerByEmail(Email, accountid);

                    //check PhoneNumber
                    //Phone numer form
                    String reg = "^(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|"
                            + "(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})$";
                    if (!Phone.matches(reg)) {
                        String mess = "Phone invalid";
                        request.setAttribute("mess", mess);
                        request.getRequestDispatcher("adminUpdateAccountCustomer.jsp").forward(request, response);
                        return;
                    }
                    daoAccount.updatePhoneCustomerByPhone(accountid);

                    //Phone da ton tai
                    List<String> listPhone = daoAccount.ListAllPhone();
                    for (String listphone : listPhone) {
                        if (listphone.equals(Phone)) {
                            String mess = "Phone available";
                            request.setAttribute("mess", mess);
                            request.getRequestDispatcher("adminUpdateAccountCustomer.jsp").forward(request, response);
                            return;
                        }
                    }
                    daoAccount.updatePhoneOldCustomerByPhone(phoneold, accountid);
                    String mess = "Update successful";
                    int AccountID = Integer.parseInt(accountid);
                    Account accoutupdate = Account.builder()
                            .accountid(AccountID)
                            .displayname(DisplayName)
                            .address(address)
                            .email(Email)
                            .phone(Phone)
                            .imageURL(imageURL)
                            .build();
                    int n = daoAccount.updateAccount(accoutupdate);
                    request.setAttribute("mess", mess);
                    request.getRequestDispatcher("adminUpdateAccountShipper.jsp").forward(request, response);
                    return;
                }

            }
            if (service.equals("deleteShipper")) {
                String search = request.getParameter("search");
                String accountid = request.getParameter("accountid");
                String page = request.getParameter("page");
                int n = daoAccount.deleteAccountById(accountid);
                if (search != null) {
                    response.sendRedirect("accountmanager?do=searchShipper&page=" + page + "&search=" + search);
                } else {
                    response.sendRedirect("accountmanager?do=listAccountShipper&page=" + page);
                }
            }
            if (service.equals("searchShipper")) {
                String search = request.getParameter("search");
                if (search == null) {
                    response.sendRedirect("accountmanager?do=listAccountShipper");
                    return;
                }
                final int PAGE_SIZE = 6;
                int page = 1;
                String pageStr = request.getParameter("page");
                if (pageStr != null) {
                    page = Integer.parseInt(pageStr);
                }
                List listAccount = daoAccount.ListAccountOfShipperWithPaggingByUserName(page, PAGE_SIZE, search);
                int TotalAccount = daoAccount.TotalAccountOfShipper();
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
                request.getRequestDispatcher("adminShippersManager.jsp").forward(request, response);
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
