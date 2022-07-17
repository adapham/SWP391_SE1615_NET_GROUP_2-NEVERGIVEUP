package Controller;

import Entity.FeedBack;
import Entity.Intouch;
import dao.impl.FeedbackDAOImpl;
import dao.impl.InTouchDAOImpl;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "FeedbackManagerController", urlPatterns = {"/feedbackManager"})
public class FeedbackManagerController extends HttpServlet {

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
            if (service == null) {
                request.getRequestDispatcher("admin.jsp").forward(request, response);
            }
            if (service.equals("getInTouch")) {
                InTouchDAOImpl dao = new InTouchDAOImpl();
                String pageStr = request.getParameter("page");
                int page = 1;
                final int PAGE_SIZE = 3;
                if (pageStr != null) {
                    page = Integer.parseInt(pageStr);
                }
                List<Intouch> listInTouch = dao.getInTouchWithPaging(page, PAGE_SIZE);
                int totalInTouch = dao.getTotalInTouch();
                int totalPage = totalInTouch / PAGE_SIZE;
                if (totalInTouch % PAGE_SIZE != 0) {
                    totalPage += 1;
                }
                request.setAttribute("page", page);
                request.setAttribute("totalPage", totalPage);
                request.setAttribute("listInTouch", listInTouch);
                request.getRequestDispatcher("inTouch.jsp").forward(request, response);
            }
            if (service.equals("searchFeedback")) {
                InTouchDAOImpl dao = new InTouchDAOImpl();
                String keySearch = request.getParameter("keySearch");
                if (keySearch.isEmpty()) {
                    response.sendRedirect("feedbackManager?do=getInTouch");
                    return;
                }
                String pageStr = request.getParameter("page");
                int page = 1;
                final int PAGE_SIZE = 3;
                if (pageStr != null) {
                    page = Integer.parseInt(pageStr);
                }
                List<Intouch> listInTouch = dao.getSearchInTouchPagingByName(keySearch, page, PAGE_SIZE);
                int totalInTouch = dao.getTotalInTouchByName(keySearch);
                int totalPage = totalInTouch / PAGE_SIZE;
                if (totalInTouch % PAGE_SIZE != 0) {
                    totalPage += 1;
                }
                String search = "1";
                request.setAttribute("search", search);
                request.setAttribute("keySearch", keySearch);
                request.setAttribute("totalInTouch", totalInTouch);
                request.setAttribute("page", page);
                request.setAttribute("totalPage", totalPage);
                request.setAttribute("listInTouch", listInTouch);
                request.getRequestDispatcher("inTouch.jsp").forward(request, response);
            }
            if (service.equals("detailinTouch")) {
                InTouchDAOImpl dao = new InTouchDAOImpl();
                String id = request.getParameter("iD");
                int iD = Integer.parseInt(id);
                List<Intouch> listInTouch = dao.getDetailsInTouch(iD);

                request.setAttribute("listInTouch", listInTouch);
                request.setAttribute("iD", iD);
                request.getRequestDispatcher("detailInTouch.jsp").forward(request, response);
            }
            if (service.equals("reply")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    String email = request.getParameter("email");
                    request.setAttribute("email", email);
                    request.getRequestDispatcher("reply.jsp").forward(request, response);
                } else {
                    //System.out.println("123");
                    InTouchDAOImpl dao = new InTouchDAOImpl();
                    String email = request.getParameter("email");
                    String subject = request.getParameter("subject");
                    String message = request.getParameter("message");
                    if (subject == null || subject.isEmpty()) {
                        String mess = "Subject is not empty";
                        request.setAttribute("mess", mess);
                        request.getRequestDispatcher("reply.jsp").forward(request, response);
                        return;
                    }
                    if (message == null || message.isEmpty()) {
                        String mess = "Message is not empty";
                        request.setAttribute("mess", mess);
                        request.getRequestDispatcher("reply.jsp").forward(request, response);
                        return;
                    }
                    Intouch inT = Intouch.builder()
                            .email(email.trim())
                            .subject(subject.trim())
                            .message(message.trim())
                            .build();
                    dao.replyEmail(inT);
                    String mess = "Send success!";
                    request.setAttribute("mess", mess);
                    request.getRequestDispatcher("reply.jsp").forward(request, response);
                    return;
                }

            }
            if (service.equals("listFeedback")) {
                FeedbackDAOImpl dao = new FeedbackDAOImpl();
                String pageStr = request.getParameter("page");
                int page = 1;
                final int PAGE_SIZE = 3;
                if (pageStr != null) {
                    page = Integer.parseInt(pageStr);
                }
                List<FeedBack> listFeedBack = dao.getFeedBackWithPaging(page, PAGE_SIZE);
                int totalFeedBack = dao.getTotalFeedBack();
                int totalPage = totalFeedBack / PAGE_SIZE;
                if (totalFeedBack % PAGE_SIZE != 0) {
                    totalPage += 1;
                }
                request.setAttribute("page", page);
                request.setAttribute("totalPage", totalPage);
                request.setAttribute("listFeedBack", listFeedBack);
                request.getRequestDispatcher("feedbackManager.jsp").forward(request, response);
            }
            if (service.equals("searchFeedbacks")) {
                FeedbackDAOImpl dao = new FeedbackDAOImpl();
                String keySearch = request.getParameter("keySearch");
                if (keySearch.isEmpty()) {
                    response.sendRedirect("feedbackManager?do=listFeedback");
                    return;
                }
                String pageStr = request.getParameter("page");
                int page = 1;
                final int PAGE_SIZE = 3;
                if (pageStr != null) {
                    page = Integer.parseInt(pageStr);
                }
                List<FeedBack> listFeedBack = dao.getSearchFeedBackPagingByName(keySearch, page, PAGE_SIZE);
                int totalFeedBack = dao.getTotalFeedBackByName(keySearch);
                int totalPage = totalFeedBack / PAGE_SIZE;
                if (totalFeedBack % PAGE_SIZE != 0) {
                    totalPage += 1;
                }
                String search = "1";
                request.setAttribute("search", search);
                request.setAttribute("keySearch", keySearch);
                request.setAttribute("totalFeedBack", totalFeedBack);
                request.setAttribute("page", page);
                request.setAttribute("totalPage", totalPage);
                request.setAttribute("listFeedBack", listFeedBack);
                request.getRequestDispatcher("feedbackManager.jsp").forward(request, response);
            }
//            if (service.equals("detailFeedBack")) {
//                FeedbackDAOImpl dao = new FeedbackDAOImpl();
//                String id = request.getParameter("iD");
//                int iD = Integer.parseInt(id);
//                //List<FeedBack> listFeedBack = dao.getDetailsFeedBack(iD);
//
//                //request.setAttribute("listFeedBack", listFeedBack);
//                request.setAttribute("iD", iD);
//                request.getRequestDispatcher("detailFeedBack.jsp").forward(request, response);
//            }
            if(service.equals("deleteFeedback")){
                FeedbackDAOImpl dao = new FeedbackDAOImpl();
                String keySearch = request.getParameter("keySearch");
                String feedbackid = request.getParameter("feedbackid");
                String page = request.getParameter("page");
                int n = dao.deleteFeedBackByID(feedbackid);
                if (keySearch != null) {
                    response.sendRedirect("feedbackManager?do=searchFeedbacks&page=" + page + "&keySearch=" + keySearch);
                } else {
                    response.sendRedirect("feedbackManager?do=listFeedback&page=" + page);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
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
