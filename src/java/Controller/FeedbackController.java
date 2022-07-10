/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.Intouch;
import dao.impl.FeedbackDAOImpl;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author admin
 */
@WebServlet(name = "FeedbackController", urlPatterns = {"/feedback"})
public class FeedbackController extends HttpServlet {

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
            FeedbackDAOImpl daofeedback = new FeedbackDAOImpl();
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String subject = request.getParameter("subject");
            String message = request.getParameter("message");
            if(name == null || name.isEmpty()){
                String mess = "Name is not empty";
                request.setAttribute("mess", mess);
                request.getRequestDispatcher("contact.jsp").forward(request, response);
                return;
            }
            if(email == null || email.isEmpty()){
                String mess = "Email is not empty";
                request.setAttribute("mess", mess);
                request.getRequestDispatcher("contact.jsp").forward(request, response);
                return;
            }
            if(subject == null || subject.isEmpty()){
                String mess = "Subject is not empty";
                request.setAttribute("mess", mess);
                request.getRequestDispatcher("contact.jsp").forward(request, response);
                return;
            }
            if(message == null || message.isEmpty()){
                String mess = "Message is not empty";
                request.setAttribute("mess", mess);
                request.getRequestDispatcher("contact.jsp").forward(request, response);
                return;
            }
            Intouch intouch = Intouch.builder()
                    .name(name.trim())
                    .email(email.trim())
                    .subject(subject.trim())
                    .message(message.trim())
                    .build();
            daofeedback.InsertIntouch(intouch);            
            String mess = "Send Intouch success!";
            request.setAttribute("mess", mess);
            request.getRequestDispatcher("contact.jsp").forward(request, response);
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
