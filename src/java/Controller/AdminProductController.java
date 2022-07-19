/*
    Hiển thị danh sách tất cả Product có trong DB và phân trang
    Thêm sửa xoa tìm kiếm và phân trang khi tìm kiếm
    Check valid cho các method update and create
    Lọc theo các trường khác nhau
 */
package Controller;

import Entity.Category;
import Entity.Product;
import Entity.Supplier;
import dao.impl.CategoryDAOImpl;
import dao.impl.ProductDAOImpl;
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

@WebServlet(name = "AdminProductController", urlPatterns = {"/adminProduct"})
public class AdminProductController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            String service = request.getParameter("do");
            ProductDAOImpl daoProduct = new ProductDAOImpl();
            SupplierDAOImpl daoSupplier = new SupplierDAOImpl();
            CategoryDAOImpl daoCategory = new CategoryDAOImpl();
            HttpSession session = request.getSession();

            if (service == null) {
                service = "ProductHome";
            }
            if (service.equals("ProductHome")) {//Product Home Manager and Paging
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
                List<Supplier> listSuppliers = daoSupplier.getAllSupplier();
                List<Category> listCategories = daoCategory.getAllCategory();

                request.setAttribute("listSup", listSuppliers);
                request.setAttribute("listCate", listCategories);

                session.setAttribute("backToUrl", "adminProduct");
                request.setAttribute("totalProduct", totalProduct);
                request.setAttribute("page", page);
                request.setAttribute("totalPage", totalPage);
                request.setAttribute("listProduct", listProduct);
                request.getRequestDispatcher("adminProduct.jsp").forward(request, response);
            }
            if (service.equals("updateProduct")) {//Update Product theo ID and Paging
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
                    String pName = request.getParameter("productName").trim();
                    int supID = Integer.parseInt(request.getParameter("supplierID"));
                    int cateID = Integer.parseInt(request.getParameter("categoryID"));
                    int quantity = Integer.parseInt(request.getParameter("quantity"));
                    double uPrice = Double.parseDouble(request.getParameter("unitPrice"));
                    double discount = Double.parseDouble(request.getParameter("discount"));
                    int uInStock = Integer.parseInt(request.getParameter("unitInStock"));
                    String des = request.getParameter("description").trim();
                    String images = request.getParameter("imageURL").trim();
                    int isActive = Integer.parseInt(request.getParameter("isActive"));

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
                    String mess;
                    //Check valid
                    if (pName == null != pName.isEmpty()) {//Check Name
                        mess = "Product name is not empty";
                        request.setAttribute("mess", mess);
                        request.getRequestDispatcher("adminProductUpdate.jsp").forward(request, response);
                        return;
                    }
                    if (images == null != images.isEmpty()) {//Check Name
                        mess = "Image url is not empty";
                        request.setAttribute("mess", mess);
                        request.getRequestDispatcher("adminProductUpdate.jsp").forward(request, response);
                        return;
                    }
                    if (des == null != des.isEmpty()) {//Check Name
                        mess = "Description is not empty";
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

                    mess = "Update successfull";
                    int updateProduct = daoProduct.updateProducts(pro);

                    request.setAttribute("mess", mess);
                    request.setAttribute("list", listProduct);
                    request.setAttribute("listSup", listSuppliers);
                    request.setAttribute("listCate", listCategories);
                    request.getRequestDispatcher("adminProductUpdate.jsp").forward(request, response);
                }
            }
            if (service.equals("createProduct")) {//Create new Product, check valid and Paging
                String submit = request.getParameter("submit");
                if (submit == null) {
                    List<Supplier> listSuppliers = daoSupplier.getAllSupplier();
                    List<Category> listCategories = daoCategory.getAllCategory();

                    request.setAttribute("listSup", listSuppliers);
                    request.setAttribute("listCate", listCategories);
                    request.getRequestDispatcher("adminProductCreate.jsp").forward(request, response);
                } else {
                    String pName = request.getParameter("productName").trim();
                    int supID = Integer.parseInt(request.getParameter("supplierID"));
                    int cateID = Integer.parseInt(request.getParameter("categoryID"));
                    int quantity = Integer.parseInt(request.getParameter("quantity"));
                    double uPrice = Double.parseDouble(request.getParameter("unitPrice"));
                    double discount = Double.parseDouble(request.getParameter("discount"));
                    int uInStock = Integer.parseInt(request.getParameter("unitInStock"));
                    String des = request.getParameter("description").trim();
                    String images = request.getParameter("imageURL").trim();
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
                    if (images == null != images.isEmpty()) {//Check Name
                        mess = "Images is not empty";
                        request.setAttribute("mess", mess);
                        request.getRequestDispatcher("adminProductCreate.jsp").forward(request, response);
                        return;
                    }
                    if (des == null != des.isEmpty()) {//Check Name
                        mess = "Description is not empty";
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
                        //Clear Data after create successfull
                        request.setAttribute("pName", "");
                        request.setAttribute("supID", 1);
                        request.setAttribute("cateID", 1);
                        request.setAttribute("quantity", 0);
                        request.setAttribute("uPrice", 0);
                        request.setAttribute("discount", 0);
                        request.setAttribute("uInStock", 0);
                        request.setAttribute("des", "");
                        request.setAttribute("images", "");
                        request.setAttribute("isActive", 1);
                        
                        request.setAttribute("mess", mess);
                        request.setAttribute("listSup", listSuppliers);
                        request.setAttribute("listCate", listCategories);
                        request.getRequestDispatcher("adminProductCreate.jsp").forward(request, response);
                    }
                }
            }
            if (service.equals("deleteProduct")) {//Delete Product By ID and Paging
                String spID = request.getParameter("pID");
                int pID = Integer.parseInt(spID);
                //Select JSP
                String mess;
                int delete = 0;
                try {
                    delete = daoProduct.deleteProduct(pID);
                } catch (Exception e) {
                    mess = "Can't delete Product";
                }

                if (delete > 0) {//Remove Successs
                    mess = "Delete Successfull!";
                } else {
                    mess = "Can't delete Product";
                }
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

                List<Supplier> listSuppliers = daoSupplier.getAllSupplier();
                List<Category> listCategories = daoCategory.getAllCategory();

                session.setAttribute("backToUrl", "adminProduct");
                request.setAttribute("listSup", listSuppliers);
                request.setAttribute("listCate", listCategories);
                request.setAttribute("totalProduct", totalProduct);
                request.setAttribute("page", page);
                request.setAttribute("totalPage", totalPage);
                request.setAttribute("mess", mess);
                request.setAttribute("listProduct", listProduct);
                request.getRequestDispatcher("adminProduct.jsp").forward(request, response);
            }
            if (service.equals("searchProduct")) {//Search Product By Name and Paging
                request.setCharacterEncoding("UTF-8");
                response.setCharacterEncoding("UTF-8");

                String keySearch = request.getParameter("searchKey").trim();
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
                List<Supplier> listSuppliers = daoSupplier.getAllSupplier();
                List<Category> listCategories = daoCategory.getAllCategory();

                session.setAttribute("backToUrl", "adminProduct?do=searchProduct");
                request.setAttribute("listSup", listSuppliers);
                request.setAttribute("listCate", listCategories);
                request.setAttribute("keySearch", keySearch);
                request.setAttribute("totalProduct", totalProduct);
                request.setAttribute("page", page);
                request.setAttribute("totalPage", totalPage);
                request.setAttribute("listProduct", listProduct);
                request.getRequestDispatcher("adminProduct.jsp").forward(request, response);
            }
            if (service.equals("sort")) {//Sort Product and Paging
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
                } else if (col.equalsIgnoreCase("UnitInStock")) {//Sort By UnitInStock
                    if (type.equalsIgnoreCase("asc")) {
                        listProduct = daoProduct.getPagingSortProduct(page, PAGE_SIZE, col, type);
                        request.setAttribute("typeStock", "down");
                    }
                    if (type.equalsIgnoreCase("desc")) {
                        listProduct = daoProduct.getPagingSortProduct(page, PAGE_SIZE, col, type);
                        request.setAttribute("typeStock", "up");
                    }
                } else if (col.equalsIgnoreCase("IsActive")) {//Sort By Is Active
                    if (type.equalsIgnoreCase("asc")) {
                        listProduct = daoProduct.getPagingSortProduct(page, PAGE_SIZE, col, type);
                        request.setAttribute("typeActive", "down");
                    }
                    if (type.equalsIgnoreCase("desc")) {
                        listProduct = daoProduct.getPagingSortProduct(page, PAGE_SIZE, col, type);
                        request.setAttribute("typeActive", "up");
                    }
                }
                List<Supplier> listSuppliers = daoSupplier.getAllSupplier();
                List<Category> listCategories = daoCategory.getAllCategory();

                request.setAttribute("listSup", listSuppliers);
                request.setAttribute("listCate", listCategories);
                request.setAttribute("totalProduct", totalProduct);
                request.setAttribute("page", page);
                request.setAttribute("totalPage", totalPage);
                request.setAttribute("listProduct", listProduct);
                request.getRequestDispatcher("adminProduct.jsp").forward(request, response);
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
