package servlet;


import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.List;
import dao.Database;
import dao.DatabaseDAO;
import dao.ProductDAO;
import model.OrderDetailSession;
import model.Product;

/**
 *
 * @author Administrator
 */
@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {

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
        String action = request.getParameter("action");
        switch (action) {
            case "create":
                createOrder(request, response);
                break;
            case "delete":
                deleteOrder(request, response);
                break;                
            case "update":
                updateOrder(request, response);
                break;
            default:
                throw new AssertionError();
        }
        
    }
    
    private void createOrder(HttpServletRequest request, HttpServletResponse response) throws IOException{
        int productId = Integer.parseInt(request.getParameter("productId"));
        String productName = request.getParameter("productName");
        double price = Double.parseDouble(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        
        HttpSession session = request.getSession();
        List<OrderDetailSession> orderDetailSessionList = null;
        
        boolean isProductExist = false;
        if(session.getAttribute("cart") != null){
            //Ton tai gio hang
            orderDetailSessionList = (List<OrderDetailSession>) session.getAttribute("cart");
            for (OrderDetailSession ods : orderDetailSessionList) {
                if(ods.getProductId() == productId){
                    ods.setQuantity(ods.getQuantity() + quantity);
                    isProductExist = true;
                    break;
                }
            }
        }else{
            //Gio hang chua ton tai
            orderDetailSessionList = new ArrayList<OrderDetailSession>();
        }
        
        if(!isProductExist){
            OrderDetailSession orderDetailSession = new OrderDetailSession(productId, productName, quantity, price);
            orderDetailSessionList.add(orderDetailSession);
        }
        
        session.setAttribute("cart", orderDetailSessionList);
        response.sendRedirect("CartServlet");
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

    private void deleteOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int productId = Integer.parseInt(request.getParameter("productId"));
        HttpSession session = request.getSession();
        List<OrderDetailSession> orderDetailSessionList = null;
        if(session.getAttribute("cart") != null){
            //Ton tai gio hang
            orderDetailSessionList = (List<OrderDetailSession>) session.getAttribute("cart");
            for (OrderDetailSession ods : orderDetailSessionList) {
                if(ods.getProductId() == productId){
                    orderDetailSessionList.remove(ods);
                    break;
                }
            }
        }
        
        session.setAttribute("cart", orderDetailSessionList);
        response.sendRedirect("CartServlet");
        
    }

    private void updateOrder(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}