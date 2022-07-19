/*
list toàn bộ account của khách hàng trên trang employee và cho phép nhân viên có thể xem được danh sách employee và chi tiết các đơn hàng của khách hàng đó
 */
package Controller;

import Entity.Account;
import Entity.OrderDetails;
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
import javax.servlet.http.HttpSession;

/**
 *
 * @author admin
 */
@WebServlet(name = "EmployeeAccountController", urlPatterns = {"/employeeaccount"})
public class EmployeeAccountController extends HttpServlet {

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
                service = "EmployeeHome";
            }
            if (service.equals("AccountCustomer")) {
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
                request.getRequestDispatcher("EmployeeAccountCustomer.jsp").forward(request, response);
            }
            if (service.equals("searchCustomer")) {
                String search = request.getParameter("search");
                if (search == null) {
                    response.sendRedirect("employeeaccount?do=AccountCustomer");
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
                request.getRequestDispatcher("EmployeeAccountCustomer.jsp").forward(request, response);
            }
            if (service.equals("DetailOrder")) {
                String accountid = request.getParameter("accountid");
                List<OrderDetails> list = daoAccount.getDetailsBill(Integer.parseInt(accountid));
                List<OrderDetails> listOrder = new ArrayList<>();
                request.setAttribute("list", list);
                List<String> listOrderId = new ArrayList<>();
                for (OrderDetails orderDetails : list) {
                    listOrderId.add(String.valueOf(orderDetails.getOrderID()));
                }
                for (int i = 0; i < listOrderId.size() - 1; i++) {
                    if (listOrderId.get(i).equals(listOrderId.get(i + 1))) {
                        listOrderId.remove(i + 1);
                        i--;
                    }
                }
                double total = 0;
                String address = "";
                int status = 0;
                String orderdate = "";
                request.setAttribute("listOrderId", listOrderId);
                request.setAttribute("list", list);
                request.setAttribute("Total", total);
                request.setAttribute("address", address);
                request.setAttribute("status", status);
                request.setAttribute("orderdate", orderdate);
                request.getRequestDispatcher("EmployeeDetailAccount.jsp").forward(request, response);
            }
            if (service.equals("updateprofile")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    HttpSession session = request.getSession();
                    Account acc = (Account) session.getAttribute("Account");
                    int AccountID = acc.getAccountid();
                    List ListAccount = daoAccount.getAccountByID(AccountID);

                    request.setAttribute("list", ListAccount);
                    request.getRequestDispatcher("employeeProfile.jsp").forward(request, response);
                } else {
                    HttpSession session = request.getSession();
                    Account acc = (Account) session.getAttribute("Account");
                    int AccountID = acc.getAccountid();
                    String username = acc.getUsername();
                    String password = acc.getPassword();
                    int role = acc.getRole();
                    String DisplayName = request.getParameter("displayname").trim();
                    String Address = request.getParameter("address").trim();
                    String Email = request.getParameter("email").trim();
                    String Phone = request.getParameter("phone").trim();
                    String ImageURL = request.getParameter("imageURL").trim();

                    Account accupdateBefore = Account.builder()
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
                    List<Account> ListAccount = new ArrayList<>();
                    ListAccount.add(accupdateBefore);

                    request.setAttribute("list", ListAccount);
                    if (DisplayName == null || DisplayName.isEmpty()) {//Tên không được null
                        String mess = "DisplayName is not null";
                        request.setAttribute("mess", mess);
                        request.getRequestDispatcher("employeeProfile.jsp").forward(request, response);
                        return;
                    }
                    if (DisplayName.length() >= 50) {//Tên không được vượt quá 50 kí tự
                        String mess = "DisplayName can must <=50 character";
                        request.setAttribute("mess", mess);
                        request.getRequestDispatcher("employeeProfile.jsp").forward(request, response);
                        return;
                    }
                    if (Address == null || Address.isEmpty()) {//Tên không được null
                        String mess = "Address is not null";
                        request.setAttribute("mess", mess);
                        request.getRequestDispatcher("employeeProfile.jsp").forward(request, response);
                        return;
                    }
                    if (Address.length() > 50) {//Địa chỉ không được vượt quá 50 kí tự
                        String mess = "Address can must <=50 character";
                        request.setAttribute("mess", mess);
                        request.getRequestDispatcher("employeeProfile.jsp").forward(request, response);
                        return;
                    }
                    String reg = "^(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|"
                            + "(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})$";//Format Phone
                    if (!Phone.matches(reg)) {
                        String mess = "Phone invalid";

                        request.setAttribute("mess", mess);
                        request.getRequestDispatcher("employeeProfile.jsp").forward(request, response);
                        return;
                    }
                    String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";//Format mail
                    if (!Email.matches(EMAIL_PATTERN)) {//Email không đúng format
                        String mess = "Email invalid";
                        request.setAttribute("mess", mess);
                        request.getRequestDispatcher("employeeProfile.jsp").forward(request, response);
                        return;
                    }
                    if (Email == null || Email.isEmpty()) {//Email không được null
                        String mess = "Email is not null";
                        request.setAttribute("mess", mess);
                        request.getRequestDispatcher("employeeProfile.jsp").forward(request, response);
                        return;
                    }

                    Account accupdate = Account.builder()
                            .accountid(AccountID)
                            .displayname(DisplayName.trim())
                            .address(Address.trim())
                            .email(Email.trim())
                            .phone(Phone.trim())
                            .username(username.trim())
                            .password(password.trim())
                            .imageURL(ImageURL.trim())
                            .role(role)
                            .build();

                    session.setAttribute("Account", accupdate);
                    int n = daoAccount.updateAccount(accupdate);
                    String mess = "Update Success";
                    ListAccount = daoAccount.getAccountByID(AccountID);

                    request.setAttribute("mess", mess);
                    request.setAttribute("list", ListAccount);
                    request.getRequestDispatcher("employeeProfile.jsp").forward(request, response);
                }
            }
            if (service.equals("changePass")) {//Change Password and check valid
                HttpSession session = request.getSession();
                Account acc = (Account) session.getAttribute("Account");
                String pass = acc.getPassword();
                int AccountID = acc.getAccountid();
                String oldPass = request.getParameter("oldPassword");
                String newPass = request.getParameter("newPassword");
                String confirmpassword = request.getParameter("comfirmPassword");
                request.setAttribute("oldPass", oldPass);
                request.setAttribute("newPass", newPass);
                request.setAttribute("confirmpassword", confirmpassword);
                if (!pass.equals(oldPass)) {
                    String mess1 = "Old Password Incorrect";
                    List ListAccount = daoAccount.getAccountByID(AccountID);

                    request.setAttribute("mess1", mess1);
                    request.setAttribute("list", ListAccount);
                    request.getRequestDispatcher("employeeProfile.jsp").forward(request, response);
                    return;
                } else if (newPass == null || newPass.isEmpty()) {
                    String mess1 = "New Password not empty";
                    List ListAccount = daoAccount.getAccountByID(AccountID);

                    request.setAttribute("mess1", mess1);
                    request.setAttribute("list", ListAccount);
                    request.getRequestDispatcher("employeeProfile.jsp").forward(request, response);
                    return;
                } else if (confirmpassword == null || confirmpassword.isEmpty()) {
                    String mess1 = "confirmpassword not empty";
                    List ListAccount = daoAccount.getAccountByID(AccountID);

                    request.setAttribute("mess1", mess1);
                    request.setAttribute("list", ListAccount);
                    request.getRequestDispatcher("employeeProfile.jsp").forward(request, response);
                    return;
                } else if (!newPass.equals(confirmpassword)) {
                    String mess1 = "New Password not same ConfirmPassword";
                    List ListAccount = daoAccount.getAccountByID(AccountID);

                    request.setAttribute("mess1", mess1);
                    request.setAttribute("list", ListAccount);
                    request.getRequestDispatcher("employeeProfile.jsp").forward(request, response);
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
                    request.getRequestDispatcher("employeeProfile.jsp").forward(request, response);
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
