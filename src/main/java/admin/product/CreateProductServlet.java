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
@WebServlet("/CreateProductServlet")
public class CreateProductServlet extends HttpServlet {

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
        DatabaseDAO.init(new Database());
        CategoryDAO categoryDAO =  DatabaseDAO.getInstance().getCategoryDAO();
        List<Category> categoryList = categoryDAO.all();
        
        request.setAttribute("categoryList", categoryList);
        request.getRequestDispatcher("./admin/products/create.jsp").forward(request, response);
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
        String image = request.getParameter("image");
        String name = request.getParameter("name");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        int categories_id = Integer.parseInt(request.getParameter("categories_id"));
        int price = Integer.parseInt(request.getParameter("price"));
        DatabaseDAO.init(new Database());
        ProductDAO productDAO = DatabaseDAO.getInstance().getProductDAO();
        Product product = new Product(name, price, quantity, image, categories_id);
        
        productDAO.insert(product);
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
