/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package admin.order;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dao.Database;
import dao.DatabaseDAO;
import dao.OrderDAO;
import model.Order;
import utils.URLSite;
/**
 *
 * @author Administrator
 */
@WebServlet("/EditOrderServlet")
public class EditOrderServlet extends HttpServlet {

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
        int orderId = Integer.parseInt(request.getParameter("id"));
        
        DatabaseDAO.init(new Database());
        OrderDAO orderDAO = DatabaseDAO.getInstance().getOrderDAO();
        Order order = orderDAO.find(orderId);
        
        request.setAttribute("order", order);
        request.getRequestDispatcher("./admin/orders/edit.jsp").forward(request, response);
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
        int orderId = Integer.parseInt(request.getParameter("id"));
      
        DatabaseDAO.init(new Database());
        OrderDAO orderDAO = DatabaseDAO.getInstance().getOrderDAO();
        Order order = orderDAO.find(orderId);        
    
        String status = request.getParameter("status");
        
        order.setStatus(status);
        
        orderDAO.update(order);
        
        response.sendRedirect(URLSite.ADMIN_INDEX_ORDER_URL);
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
