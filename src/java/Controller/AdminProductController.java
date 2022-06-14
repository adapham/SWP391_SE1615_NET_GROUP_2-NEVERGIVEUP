package Controller;

import Entity.Category;
import Entity.Product;
import Entity.Supplier;
import Modal.CategoryDao;
import Modal.ProductDao;
import Modal.SupplierDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "AdminProductController", urlPatterns = {"/adminProduct"})
public class AdminProductController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String service = request.getParameter("do");
            ProductDao daoProduct = new ProductDao();
            SupplierDao daoSupplier = new SupplierDao();
            CategoryDao daoCategory = new CategoryDao();

            if (service == null) {
                service = "ProductHome";
            }
            if (service.equals("ProductHome")) {
                List<Product> listProduct = daoProduct.getAllProduct();

                request.setAttribute("listProduct", listProduct);
                request.getRequestDispatcher("adminProduct.jsp").forward(request, response);
            }
            if (service.equals("updateProduct")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    String pID = request.getParameter("pID");
                    int ipID = Integer.parseInt(pID);
                    List<Product> listProduct = daoProduct.getAllProductByProductID(ipID);
                    List<Supplier> listSuppliers = daoSupplier.getAllSupplier();
                    List<Category> listCategories = daoCategory.getAllCategory();

                    request.setAttribute("list", listProduct);
                    request.setAttribute("listSup", listSuppliers);
                    request.setAttribute("listCate", listCategories);
                    request.getRequestDispatcher("adminProductUpdate.jsp").forward(request, response);
                } else {
                    int pID = Integer.parseInt(request.getParameter("pID"));
                    String pName = request.getParameter("productName");
                    int supID = Integer.parseInt(request.getParameter("supplierID"));
                    int cateID = Integer.parseInt(request.getParameter("categoryID"));
                    int quantity = Integer.parseInt(request.getParameter("quantity"));
                    double uPrice = Double.parseDouble(request.getParameter("unitPrice"));
                    double discount = Double.parseDouble(request.getParameter("discount"));
                    int uInStock = Integer.parseInt(request.getParameter("unitInStock"));
                    String des = request.getParameter("description");
                    String images = request.getParameter("imageURL");
                    int isActive = Integer.parseInt(request.getParameter("isActive"));
                    //Check valid

                    Product pro = Product.builder()
                            .productID(pID)
                            .productName(pName)
                            .supplierID(supID)
                            .categoryID(cateID)
                            .quantity(quantity)
                            .unitPrice(uPrice)
                            .discount(discount)
                            .unitInStock(uInStock)
                            .description(des)
                            .imageURL(images)
                            .isActive(isActive)
                            .build();
                    
                    String mess = "Update successfull";
                    int updateProduct = daoProduct.updateProducts(pro);
                    
                    List<Product> listProduct = daoProduct.getAllProductByProductID(pID);
                    List<Supplier> listSuppliers = daoSupplier.getAllSupplier();
                    List<Category> listCategories = daoCategory.getAllCategory();

                    request.setAttribute("mess", mess);
                    request.setAttribute("list", listProduct);
                    request.setAttribute("listSup", listSuppliers);
                    request.setAttribute("listCate", listCategories);
                    request.getRequestDispatcher("adminProductUpdate.jsp").forward(request, response);
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
