/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package admin.product;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.logging.Logger;
import dao.CategoryDAO;
import dao.Database;
import dao.DatabaseDAO;
import dao.ProductDAO;
import model.Category;
import model.Product;
import utils.URLSite;

/**
 *
 * @author Administrator
 */
@WebServlet("/EditProductServlet")
public class EditProductServlet extends HttpServlet {

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
        int productId = Integer.parseInt(request.getParameter("id"));
        
        DatabaseDAO.init(new Database());
        CategoryDAO categoryDAO =  DatabaseDAO.getInstance().getCategoryDAO();
        List<Category> categoryList = categoryDAO.all();
        
        DatabaseDAO.init(new Database());
        ProductDAO productDAO = DatabaseDAO.getInstance().getProductDAO();
        Product product = productDAO.find(productId);
        
        Category category = null;
        for (Category cat: categoryList) {
            if(cat.getId() == product.getCategories_id()) {
                category = cat;
            } 
        }
        
        request.setAttribute("categoryList", categoryList);
        request.setAttribute("categorySelected", category);
        request.setAttribute("product", product);
        request.getRequestDispatcher("./admin/products/edit.jsp").forward(request, response);
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
        int productId = Integer.parseInt(request.getParameter("id"));
      
        DatabaseDAO.init(new Database());
        ProductDAO productDAO = DatabaseDAO.getInstance().getProductDAO();
        Product product = productDAO.find(productId);        
    
        String image = request.getParameter("image");
        String name = request.getParameter("name");
        int price = Integer.parseInt(request.getParameter("price"));
        int quatity = Integer.parseInt(request.getParameter("quatity"));
        int categoryId = Integer.parseInt(request.getParameter("category"));
        Logger.getLogger("Product").info(categoryId + ": categoryId");
        product.setImage(image);
        product.setName(name);
        product.setQuantity(quatity);
        product.setPrice(price);
        product.setCategories_id(categoryId);
        
        productDAO.update(product);
        
        response.sendRedirect(URLSite.ADMIN_INDEX_PRODUCT_URL);
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
