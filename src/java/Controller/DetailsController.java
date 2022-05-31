/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Modal.DAOFeedback;
import Modal.DAOProduct;
import View.FeedBack;
import View.Product;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author admin
 */
@WebServlet(name = "DetailsController", urlPatterns = {"/details"})
public class DetailsController extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */
            DAOFeedback dao = new DAOFeedback();

            String service = request.getParameter("do");
            if (service == null) {
                service = "details";
            }
            if (service.equals("details")) {
                int ProductID = Integer.parseInt(request.getParameter("pid"));
                List<FeedBack> list = new ArrayList<>();
                list = dao.ListFeedBackByProductID(ProductID);
                Product pro = dao.getProductByProductID(ProductID);
                request.setAttribute("proID", ProductID);
                request.setAttribute("pro", pro);
                request.setAttribute("list", list);
                request.getRequestDispatcher("details.jsp").forward(request, response);
            }
            if (service.equals("postcomment")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    response.sendRedirect("details");
                } else {
                    LocalDateTime myDateObj = LocalDateTime.now();
                    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                    String formattedDate = myDateObj.format(myFormatObj);
                    String comment = request.getParameter("comment");
                    int ProductID = Integer.parseInt(request.getParameter("proID"));
                    int AccountID = Integer.parseInt(request.getParameter("accID"));
                    FeedBack feedback = FeedBack.builder()
                            .feedbackContent(comment)
                            .productID(ProductID)
                            .accountID(AccountID)
                            .timeComment(formattedDate)
                            .build();
                    dao.InsertFeedBack(feedback);
                    response.sendRedirect("details?pid="+ProductID);
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
