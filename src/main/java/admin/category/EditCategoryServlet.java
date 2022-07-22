/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package admin.category;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dao.CategoryDAO;
import dao.Database;
import dao.DatabaseDAO;
import model.Category;
import utils.URLSite;

/**
 *
 * @author Administrator
 */
@WebServlet("/EditCategoryServlet")
public class EditCategoryServlet extends HttpServlet {

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
        int categoryId = Integer.parseInt(request.getParameter("id"));
        
        DatabaseDAO.init(new Database());
        CategoryDAO categoryDAO = DatabaseDAO.getInstance().getCategoryDAO();
        Category category = categoryDAO.find(categoryId);
        
        request.setAttribute("category", category);
        request.getRequestDispatcher("./admin/categories/edit.jsp").forward(request, response);
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
        
        int categoryId = Integer.parseInt(request.getParameter("id"));
      
        DatabaseDAO.init(new Database());
        CategoryDAO categoryDAO = DatabaseDAO.getInstance().getCategoryDAO();
        Category category = categoryDAO.find(categoryId);        
        
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        
        category.setName(name);
        category.setDescription(description);
        
        categoryDAO.update(category);
        
        response.sendRedirect(URLSite.ADMIN_INDEX_CATEGORY_URL);
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
