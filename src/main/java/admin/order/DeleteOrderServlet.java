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
import java.util.List;
import dao.Database;
import dao.DatabaseDAO;
import dao.OrderDAO;
import dao.OrderDetailDAO;
import model.Order;
import model.OrderDetail;
import utils.URLSite;

/**
 *
 * @author Administrator
 */
@WebServlet("/DeleteOrderServlet")
public class DeleteOrderServlet extends HttpServlet {

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
        OrderDetailDAO orderDetailDAO = DatabaseDAO.getInstance().getOrderDetailDAO();
        Order order = orderDAO.find(orderId);
        if (order != null) {
            List<OrderDetail> orderDetailList = orderDetailDAO.findByProperty("code", order.getCode());
            if (orderDetailList != null && !orderDetailList.isEmpty()) {
                for(OrderDetail orderDetail: orderDetailList) {
                    orderDetailDAO.delete(orderDetail.getId());
                }
            }
        }
        log(orderId + ": order Id");
        boolean isDelete = orderDAO.delete(orderId);
         log(isDelete + ": delete");
        
        response.sendRedirect(URLSite.ADMIN_INDEX_ORDER_URL);
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
