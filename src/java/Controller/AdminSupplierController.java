/*
    Hiển thị danh sách tất cả SDupplier có trong DB và phân trang
    Thêm sửa xoa tìm kiếm và phân trang khi tìm kiếm
    Check valid cho các method update and create
 */
package Controller;

import Entity.Supplier;
import dao.impl.SupplierDAOImpl;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "AdminSupplierController", urlPatterns = {"/adminSupplier"})
public class AdminSupplierController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            String service = request.getParameter("do");
            SupplierDAOImpl daoSupplier = new SupplierDAOImpl();
            HttpSession session = request.getSession();

            if (service == null) {
                service = "supplierHome";
            }
            if (service.equals("supplierHome")) {
                String pageStr = request.getParameter("page");
                //Phân trang
                int page = 1;
                final int PAGE_SIZE = 4;
                if (pageStr != null) {
                    page = Integer.parseInt(pageStr);
                }
                List<Supplier> supplierList = daoSupplier.getAllSupplierWithPaging(page, PAGE_SIZE);
                int totalSupplier = daoSupplier.getTotalSupplier();//Get total All Product
                int totalPage = totalSupplier / PAGE_SIZE;
                if (totalSupplier % PAGE_SIZE != 0) {
                    totalPage += 1;
                }
                session.setAttribute("backToUrl", "adminSupplier");
                request.setAttribute("totalSupplier", totalSupplier);
                request.setAttribute("page", page);
                request.setAttribute("totalPage", totalPage);
                request.setAttribute("supplierList", supplierList);
                request.getRequestDispatcher("adminSupplier.jsp").forward(request, response);
            }
            if (service.equals("updateSupplier")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    String sSupID = request.getParameter("supID");
                    int supID = Integer.parseInt(sSupID);
                    List<Supplier> supplierList = daoSupplier.getAllSupplierBySupID(supID);

                    request.setAttribute("supplierList", supplierList);
                    request.getRequestDispatcher("adminSupplierUpdate.jsp").forward(request, response);
                } else {
                    int supID = Integer.parseInt(request.getParameter("supID"));
                    String cpName = request.getParameter("cpName").trim();
                    String address = request.getParameter("address").trim();
                    String phone = request.getParameter("phone").trim();
                    String email = request.getParameter("email").trim();

                    Supplier supplierListBefore = Supplier.builder()//Lưu giữ lại giá trị lỗi
                            .supplierID(supID)
                            .companyName(cpName)
                            .address(address)
                            .phone(phone)
                            .email(email)
                            .build();

                    List<Supplier> supplierList = new ArrayList<>();
                    supplierList.add(supplierListBefore);
                    request.setAttribute("supplierList", supplierList);

                    if (cpName == null || cpName.isEmpty()) {//Check Company Name
                        String mess = "Company name is not empty";

                        request.setAttribute("mess", mess);
                        request.getRequestDispatcher("adminSupplierUpdate.jsp").forward(request, response);
                        return;
                    }

                    if (address == null || address.isEmpty()) {//Địa chỉ không được null
                        String mess = "Address is not null";

                        request.setAttribute("mess", mess);
                        request.getRequestDispatcher("adminSupplierUpdate.jsp").forward(request, response);
                        return;
                    }
                    if (address.length() > 50) {//Địa chỉ không được vượt quá 50 kí tự
                        String mess = "Address can must <=50 character";

                        request.setAttribute("mess", mess);
                        request.getRequestDispatcher("adminSupplierUpdate.jsp").forward(request, response);
                        return;
                    }

                    String reg = "^(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|"
                            + "(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})$";//Format Phone
                    if (!phone.matches(reg)) {
                        String mess = "Phone invalid";

                        request.setAttribute("mess", mess);
                        request.getRequestDispatcher("adminSupplierUpdate.jsp").forward(request, response);
                        return;
                    }
                    String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";//Format mail
                    if (!email.matches(EMAIL_PATTERN)) {//Email không đúng format
                        String mess = "Email invalid";

                        request.setAttribute("mess", mess);
                        request.getRequestDispatcher("adminSupplierUpdate.jsp").forward(request, response);
                        return;
                    }
                    if (email == null || email.isEmpty()) {//Email không được null
                        String mess = "Email is not null";

                        request.setAttribute("mess", mess);
                        request.getRequestDispatcher("adminSupplierUpdate.jsp").forward(request, response);
                        return;
                    }

                    Supplier sup = Supplier.builder()//Lưu giữ lại giá trị hoàn thành
                            .supplierID(supID)
                            .companyName(cpName.trim())
                            .address(address.trim())
                            .phone(phone.trim())
                            .email(email.trim())
                            .build();

                    String mess = "Update successfull";
                    int updateSupplier = daoSupplier.updateSupplier(sup);
                    request.setAttribute("mess", mess);
                    request.setAttribute("supplierList", supplierList);
                    request.getRequestDispatcher("adminSupplierUpdate.jsp").forward(request, response);
                }
            }
            if (service.equals("createSupplier")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    request.getRequestDispatcher("adminSupplierCreate.jsp").forward(request, response);
                } else {
                    String cpName = request.getParameter("cpName").trim();
                    String address = request.getParameter("address").trim();
                    String phone = request.getParameter("phone").trim();
                    String email = request.getParameter("email").trim();
                    request.setAttribute("companyName", cpName);
                    request.setAttribute("address", address);
                    request.setAttribute("phone", phone);
                    request.setAttribute("email", email);

                    if (cpName == null || cpName.isEmpty()) {//Check Company Name
                        String mess = "Company name is not empty";
                        request.setAttribute("mess", mess);
                        request.getRequestDispatcher("adminSupplierCreate.jsp").forward(request, response);
                        return;
                    }
                    String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";//Format mail
                    if (!email.matches(EMAIL_PATTERN)) {//Email không đúng format
                        String mess = "Email invalid";
                        request.setAttribute("mess", mess);
                        request.getRequestDispatcher("adminSupplierCreate.jsp").forward(request, response);
                        return;
                    }
                    if (email == null || email.isEmpty()) {//Email không được null
                        String mess = "Email is not null";
                        request.setAttribute("mess", mess);
                        request.getRequestDispatcher("adminSupplierCreate.jsp").forward(request, response);
                        return;
                    }
                    if (address == null || address.isEmpty()) {//Địa chỉ không được null
                        String mess = "Address is not null";
                        request.setAttribute("mess", mess);
                        request.getRequestDispatcher("adminSupplierCreate.jsp").forward(request, response);
                        return;
                    }
                    if (address.length() > 50) {//Địa chỉ không được vượt quá 50 kí tự
                        String mess = "Address can must <=50 character";
                        request.setAttribute("mess", mess);
                        request.getRequestDispatcher("adminSupplierCreate.jsp").forward(request, response);
                        return;
                    }

                    String reg = "^(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|"
                            + "(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})$";//Format Phone
                    if (!phone.matches(reg)) {
                        String mess = "Phone invalid";
                        request.setAttribute("mess", mess);
                        request.getRequestDispatcher("adminSupplierCreate.jsp").forward(request, response);
                        return;
                    }

                    Supplier sup = Supplier.builder()//Lưu giữ lại giá trị hoàn thành
                            .companyName(cpName.trim())
                            .address(address.trim())
                            .phone(phone.trim())
                            .email(email.trim())
                            .build();

                    int createSupplier = daoSupplier.createSupplier(sup);

                    if (createSupplier > 0) {
                        String mess = "Create successfull";
                        request.setAttribute("mess", mess);
                        //Clear Data after create successfull
                        request.setAttribute("companyName", "");
                        request.setAttribute("address", "");
                        request.setAttribute("phone", "");
                        request.setAttribute("email", "");
                        
                        request.getRequestDispatcher("adminSupplierCreate.jsp").forward(request, response);
                    }
                }
            }
            if (service.equals("deleteSupplier")) {
                String sSupID = request.getParameter("supID");
                int supID = Integer.parseInt(sSupID);
                //int delete = daoSupplier.deleteSupplier(supID);
                String mess;
                int delete = 0;
                try {
                    delete = daoSupplier.deleteSupplier(supID);
                } catch (Exception e) {
                    mess = "Can't delete Supplier";
                }

                if (delete > 0) {//Remove Successs
                    mess = "Delete Successfull!";
                } else {
                    mess = "Can't delete Supplier";
                }

                String pageStr = request.getParameter("page");

                int page = 1;
                final int PAGE_SIZE = 4;
                if (pageStr != null) {
                    page = Integer.parseInt(pageStr);
                }
                List<Supplier> supplierList = daoSupplier.getAllSupplierWithPaging(page, PAGE_SIZE);
                int totalSupplier = daoSupplier.getTotalSupplier();//Get total All Product
                int totalPage = totalSupplier / PAGE_SIZE;
                if (totalSupplier % PAGE_SIZE != 0) {
                    totalPage += 1;
                }

                request.setAttribute("mess", mess);
                session.setAttribute("backToUrl", "adminSupplier");
                request.setAttribute("totalSupplier", totalSupplier);
                request.setAttribute("page", page);
                request.setAttribute("totalPage", totalPage);
                request.setAttribute("supplierList", supplierList);
                request.getRequestDispatcher("adminSupplier.jsp").forward(request, response);
            }
            if (service.equals("searchSupplier")) {
                request.setCharacterEncoding("UTF-8");
                response.setCharacterEncoding("UTF-8");
                String keySearch = request.getParameter("searchKey").trim();
                if (keySearch.isEmpty()) {
                    response.sendRedirect("adminSupplier");
                    return;
                }

                String pageStr = request.getParameter("page");

                int page = 1;
                final int PAGE_SIZE = 4;
                if (pageStr != null) {
                    page = Integer.parseInt(pageStr);
                }

                List<Supplier> supplierList = daoSupplier.getsearchSupplierPagingByName(keySearch, page, PAGE_SIZE);
                int totalSupplier = daoSupplier.getTotalSupplierByName(keySearch);//Get total All Product
                int totalPage = totalSupplier / PAGE_SIZE;
                if (totalSupplier % PAGE_SIZE != 0) {
                    totalPage += 1;
                }

                session.setAttribute("backToUrl", "adminSupplier?do=searchSupplier");
                request.setAttribute("keySearch", keySearch);
                request.setAttribute("totalSupplier", totalSupplier);
                request.setAttribute("page", page);
                request.setAttribute("totalPage", totalPage);
                request.setAttribute("supplierList", supplierList);
                request.getRequestDispatcher("adminSupplier.jsp").forward(request, response);
            }
        } catch (Exception ex) {
            request.setAttribute("error", ex);
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
