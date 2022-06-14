package Controller;

import Entity.Category;
import Entity.Product;
import Entity.Supplier;
import Modal.CategoryDao;
import Modal.ProductDao;
import Modal.SupplierDao;
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
            HttpSession session = request.getSession();

            if (service == null) {
                service = "ProductHome";
            }
            if (service.equals("ProductHome")) {
                String pageStr = request.getParameter("page");

                int page = 1;
                final int PAGE_SIZE = 10;
                if (pageStr != null) {
                    page = Integer.parseInt(pageStr);
                }
                List<Product> listProduct = daoProduct.getProductWithPaging(page, PAGE_SIZE);
                int totalProduct = daoProduct.getTotalProduct();//Get total All Product
                int totalPage = totalProduct / PAGE_SIZE;
                if (totalProduct % PAGE_SIZE != 0) {
                    totalPage += 1;
                }

                session.setAttribute("backToUrl", "adminProduct");
                request.setAttribute("totalProduct", totalProduct);
                request.setAttribute("page", page);
                request.setAttribute("totalPage", totalPage);
                request.setAttribute("listProduct", listProduct);
                request.getRequestDispatcher("adminProduct.jsp").forward(request, response);
            }
            if (service.equals("updateProduct")) {//Update Product theo ID
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

                    request.setAttribute("pName", pName);
                    request.setAttribute("supID", supID);
                    request.setAttribute("cateID", cateID);
                    request.setAttribute("quantity", quantity);
                    request.setAttribute("uPrice", uPrice);
                    request.setAttribute("discount", discount);
                    request.setAttribute("uInStock", uInStock);
                    request.setAttribute("des", des);
                    request.setAttribute("images", images);
                    request.setAttribute("isActive", isActive);

                    Product productBefore = Product.builder()//Add Product
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
                    List<Product> listProduct = new ArrayList<>();
                    listProduct.add(productBefore);

                    List<Supplier> listSuppliers = daoSupplier.getAllSupplier();
                    List<Category> listCategories = daoCategory.getAllCategory();
                    request.setAttribute("list", listProduct);
                    request.setAttribute("listSup", listSuppliers);
                    request.setAttribute("listCate", listCategories);
                    if (pName == null != pName.isEmpty()) {//Check Name
                        String mess = "Product name is not empty";

                        request.setAttribute("mess", mess);
                        request.getRequestDispatcher("adminProductUpdate.jsp").forward(request, response);
                        return;
                    }
                    if (pName != pName.trim()) {//Check Name
                        String mess = "Product name invalid";

                        request.setAttribute("mess", mess);
                        request.getRequestDispatcher("adminProductUpdate.jsp").forward(request, response);
                        return;
                    }
                    Product pro = Product.builder()//Add Product
                            .productID(pID)
                            .productName(pName.trim())
                            .supplierID(supID)
                            .categoryID(cateID)
                            .quantity(quantity)
                            .unitPrice(uPrice)
                            .discount(discount)
                            .unitInStock(uInStock)
                            .description(des.trim())
                            .imageURL(images.trim())
                            .isActive(isActive)
                            .build();

                    String mess = "Update successfull";
                    int updateProduct = daoProduct.updateProducts(pro);

                    request.setAttribute("mess", mess);
                    request.setAttribute("list", listProduct);
                    request.setAttribute("listSup", listSuppliers);
                    request.setAttribute("listCate", listCategories);
                    request.getRequestDispatcher("adminProductUpdate.jsp").forward(request, response);
                }
            }
            if (service.equals("createProduct")) {//method create 1 new product
                String submit = request.getParameter("submit");
                if (submit == null) {
                    List<Supplier> listSuppliers = daoSupplier.getAllSupplier();
                    List<Category> listCategories = daoCategory.getAllCategory();

                    request.setAttribute("listSup", listSuppliers);
                    request.setAttribute("listCate", listCategories);
                    request.getRequestDispatcher("adminProductCreate.jsp").forward(request, response);
                } else {
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
                    request.setAttribute("pName", pName);
                    request.setAttribute("supID", supID);
                    request.setAttribute("cateID", cateID);
                    request.setAttribute("quantity", quantity);
                    request.setAttribute("uPrice", uPrice);
                    request.setAttribute("discount", discount);
                    request.setAttribute("uInStock", uInStock);
                    request.setAttribute("des", des);
                    request.setAttribute("images", images);
                    request.setAttribute("isActive", isActive);
                    String mess;
                    List<Supplier> listSuppliers = daoSupplier.getAllSupplier();
                    List<Category> listCategories = daoCategory.getAllCategory();

                    request.setAttribute("listSup", listSuppliers);
                    request.setAttribute("listCate", listCategories);
                    //Check valid
                    if (pName == null != pName.isEmpty()) {//Check Name
                        mess = "Product name is not empty";

                        request.setAttribute("mess", mess);
                        request.getRequestDispatcher("adminProductCreate.jsp").forward(request, response);
                        return;
                    }
                    if (pName != pName.trim()) {//Check Name
                        mess = "Product name invalid";

                        request.setAttribute("mess", mess);
                        request.getRequestDispatcher("adminProductCreate.jsp").forward(request, response);
                        return;
                    }
                    if (images != images.trim()) {
                        mess = "Image URL invalid";

                        request.setAttribute("mess", mess);
                        request.getRequestDispatcher("adminProductCreate.jsp").forward(request, response);
                        return;
                    }

                    Product pro = Product.builder()//Add Product
                            .productName(pName.trim())
                            .supplierID(supID)
                            .categoryID(cateID)
                            .quantity(quantity)
                            .unitPrice(uPrice)
                            .discount(discount)
                            .unitInStock(uInStock)
                            .description(des.trim())
                            .imageURL(images.trim())
                            .isActive(isActive)
                            .build();

                    int createProduct = daoProduct.createProduct(pro);
                    if (createProduct > 0) {
                        mess = "Create successfull";
                        request.setAttribute("mess", mess);
                        request.setAttribute("listSup", listSuppliers);
                        request.setAttribute("listCate", listCategories);
                        request.getRequestDispatcher("adminProductCreate.jsp").forward(request, response);
                    } else {
                        out.println("500");
                    }
                }
            }
            if (service.equals("deleteProduct")) {
                String spID = request.getParameter("pID");
                int pID = Integer.parseInt(spID);
                int delete = daoProduct.deleteProduct(pID);
                //Select JSP
                String mess;
                if (delete > 0) {//Remove Successs
                    String pageStr = request.getParameter("page");

                    int page = 1;
                    final int PAGE_SIZE = 10;
                    if (pageStr != null) {
                        page = Integer.parseInt(pageStr);
                    }
                    List<Product> listProduct = daoProduct.getProductWithPaging(page, PAGE_SIZE);
                    int totalProduct = daoProduct.getTotalProduct();//Get total All Product
                    int totalPage = totalProduct / PAGE_SIZE;
                    if (totalProduct % PAGE_SIZE != 0) {
                        totalPage += 1;
                    }

                    mess = "Delete Successfull!";

                    session.setAttribute("backToUrl", "adminProduct");
                    request.setAttribute("totalProduct", totalProduct);
                    request.setAttribute("page", page);
                    request.setAttribute("totalPage", totalPage);

                    request.setAttribute("mess", mess);
                    request.setAttribute("listProduct", listProduct);
                    request.getRequestDispatcher("adminProduct.jsp").forward(request, response);
                } else {
                    out.println("500");
                }
            }
            if (service.equals("searchProduct")) {//Search Product By Name
                request.setCharacterEncoding("UTF-8");
                response.setCharacterEncoding("UTF-8");

                String keySearch = request.getParameter("searchKey");
                if (keySearch.isEmpty()) {
                    response.sendRedirect("adminProduct");
                    return;
                }

                String pageStr = request.getParameter("page");
                //Phân trang
                int page = 1;
                final int PAGE_SIZE = 10;
                if (pageStr != null) {
                    page = Integer.parseInt(pageStr);
                }

                List<Product> listProduct = daoProduct.getSearchProductsPagingByName(keySearch, page, PAGE_SIZE);
                int totalProduct = daoProduct.getTotalProductByPName(keySearch);
                int totalPage = totalProduct / PAGE_SIZE;
                if (totalProduct % PAGE_SIZE != 0) {
                    totalPage += 1;
                }

                session.setAttribute("backToUrl", "adminProduct?do=searchProduct");
                request.setAttribute("keySearch", keySearch);
                request.setAttribute("totalProduct", totalProduct);
                request.setAttribute("page", page);
                request.setAttribute("totalPage", totalPage);
                request.setAttribute("listProduct", listProduct);
                request.getRequestDispatcher("adminProduct.jsp").forward(request, response);
            }
            if (service.equals("sort")) {//Sort Product
                String type = request.getParameter("type");//Get tpye
                String col = request.getParameter("col");//get column
                String pageStr = request.getParameter("page");//Get page
                //Phân trang
                int page = 1;
                final int PAGE_SIZE = 10;
                if (pageStr != null) {
                    page = Integer.parseInt(pageStr);
                }

                int totalProduct = daoProduct.getTotalProduct();//Get total All Product
                int totalPage = totalProduct / PAGE_SIZE;
                if (totalProduct % PAGE_SIZE != 0) {
                    totalPage += 1;
                }
                List<Product> listProduct = null;
                if (col.equalsIgnoreCase("ProductID")) {//Sort By Product ID
                    if (type.equalsIgnoreCase("asc")) {
                        listProduct = daoProduct.getPagingSortProduct(page, PAGE_SIZE, col, type);
                        request.setAttribute("typePid", "down");
                    }
                    if (type.equalsIgnoreCase("desc")) {
                        listProduct = daoProduct.getPagingSortProduct(page, PAGE_SIZE, col, type);
                        request.setAttribute("typePid", "up");
                    }
                } else if (col.equalsIgnoreCase("ProductName")) {//Sort By Product Name
                    if (type.equalsIgnoreCase("asc")) {
                        listProduct = daoProduct.getPagingSortProduct(page, PAGE_SIZE, col, type);
                        request.setAttribute("typePname", "down");
                    }
                    if (type.equalsIgnoreCase("desc")) {
                        listProduct = daoProduct.getPagingSortProduct(page, PAGE_SIZE, col, type);
                        request.setAttribute("typePname", "up");
                    }
                } else if (col.equalsIgnoreCase("SupplierID")) {//Sort By SupplierID
                    if (type.equalsIgnoreCase("asc")) {
                        listProduct = daoProduct.getPagingSortProduct(page, PAGE_SIZE, col, type);
                        request.setAttribute("typeSup", "down");
                    }
                    if (type.equalsIgnoreCase("desc")) {
                        listProduct = daoProduct.getPagingSortProduct(page, PAGE_SIZE, col, type);
                        request.setAttribute("typeSup", "up");
                    }
                } else if (col.equalsIgnoreCase("CategoryID")) {//Sort By Category ID
                    if (type.equalsIgnoreCase("asc")) {
                        listProduct = daoProduct.getPagingSortProduct(page, PAGE_SIZE, col, type);
                        request.setAttribute("typeCate", "down");
                    }
                    if (type.equalsIgnoreCase("desc")) {
                        listProduct = daoProduct.getPagingSortProduct(page, PAGE_SIZE, col, type);
                        request.setAttribute("typeCate", "up");
                    }
                } else if (col.equalsIgnoreCase("UnitPrice")) {//Sort By Unit Price
                    if (type.equalsIgnoreCase("asc")) {
                        listProduct = daoProduct.getPagingSortProduct(page, PAGE_SIZE, col, type);
                        request.setAttribute("typePrice", "down");
                    }
                    if (type.equalsIgnoreCase("desc")) {
                        listProduct = daoProduct.getPagingSortProduct(page, PAGE_SIZE, col, type);
                        request.setAttribute("typePrice", "up");
                    }
                } else if (col.equalsIgnoreCase("Discount")) {//Sort By Discount
                    if (type.equalsIgnoreCase("asc")) {
                        listProduct = daoProduct.getPagingSortProduct(page, PAGE_SIZE, col, type);
                        request.setAttribute("typeDis", "down");
                    }
                    if (type.equalsIgnoreCase("desc")) {
                        listProduct = daoProduct.getPagingSortProduct(page, PAGE_SIZE, col, type);
                        request.setAttribute("typeDis", "up");
                    }
                }else if (col.equalsIgnoreCase("UnitInStock")) {//Sort By UnitInStock
                    if (type.equalsIgnoreCase("asc")) {
                        listProduct = daoProduct.getPagingSortProduct(page, PAGE_SIZE, col, type);
                        request.setAttribute("typeStock", "down");
                    }
                    if (type.equalsIgnoreCase("desc")) {
                        listProduct = daoProduct.getPagingSortProduct(page, PAGE_SIZE, col, type);
                        request.setAttribute("typeStock", "up");
                    }
                }else if (col.equalsIgnoreCase("IsActive")) {//Sort By Is Active
                    if (type.equalsIgnoreCase("asc")) {
                        listProduct = daoProduct.getPagingSortProduct(page, PAGE_SIZE, col, type);
                        request.setAttribute("typeActive", "down");
                    }
                    if (type.equalsIgnoreCase("desc")) {
                        listProduct = daoProduct.getPagingSortProduct(page, PAGE_SIZE, col, type);
                        request.setAttribute("typeActive", "up");
                    }
                }

                request.setAttribute("totalProduct", totalProduct);
                request.setAttribute("page", page);
                request.setAttribute("totalPage", totalPage);
                request.setAttribute("listProduct", listProduct);
                request.getRequestDispatcher("adminProduct.jsp").forward(request, response);
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
