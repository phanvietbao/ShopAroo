package servlet;

import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dao.Database;
import dao.DatabaseDAO;
import dao.ProductDAO;
import model.Product;

/**
 *
 * @author Administrator
 */
@WebServlet(name="SearchServlet",urlPatterns={"/SearchServlet"})
public class SearchServlet extends HttpServlet {

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
        String name = String.valueOf(request.getParameter("name"));
        int categoryId = 0;
        if (request.getParameter("categoryId") != null) {
            categoryId = Integer.parseInt(request.getParameter("categoryId"));
        }
        
        DatabaseDAO.init(new Database());
        ProductDAO productDAO = DatabaseDAO.getInstance().getProductDAO();
        List<Product> productList = null;
        if (categoryId > 0 && name != null && !name.equals("")) {
            productList = productDAO.findByNameAndCategoryId(name, categoryId);
        } else if (name != null && !name.equals("")) {
            productList = productDAO.findByName(name);
        } else {
            productList = productDAO.all();
        }
        
        request.setAttribute("name", name);
        request.setAttribute("productList", productList);
        request.getRequestDispatcher("search.jsp").forward(request, response);
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