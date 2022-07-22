package servlet;


import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.util.logging.Logger;
import auth.Auth;
import dao.Database;
import dao.DatabaseDAO;
import dao.OrderDAO;
import dao.OrderDetailDAO;
import model.Order;
import model.OrderDetail;
import model.OrderDetailSession;
import utils.StringHelper;
import utils.URLSite;

/**
 *
 * @author Administrator
 */
@WebServlet("/CheckoutServlet")
public class CheckOutServlet extends HttpServlet {
    Logger logger = Logger.getLogger("CheckOutServlet");

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
        HttpSession session = request.getSession();
        session.setAttribute("urlBack", URLSite.HOME_URL);
        Auth.init(request.getSession());
        if(!Auth.isLogin()) response.sendRedirect("LoginServlet");
        else
        checkOut(request, response);
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
        response.setContentType("text/html;charset=UTF-8");
    }

    /**
     * Checkout
     */
    
    private void checkOut(HttpServletRequest request, HttpServletResponse response) throws IOException{
        /*DatabaseDAO.init(new Database());
        OrderDAO orderDAO = DatabaseDAO.getInstance().getOrderDAO();
        String name = StringHelper.randomString(Order.CODE_LENGHT);
        String description = "Order san pham";
        Order order = new Order(0, name, description, Order.PENDING_STATUS);
        order = orderDAO.insert(order);
        
        OrderDetailDAO orderDetailDAO = DatabaseDAO.getInstance().getOrderDetailDAO();
        HttpSession session = request.getSession();
        List<OrderDetailSession> orderDetailSessionList = null;
        
        boolean isSuccess = true;
        if(session.getAttribute("cart") != null){
            //Ton tai gio hang
            orderDetailSessionList = (List<OrderDetailSession>) session.getAttribute("cart");
            for (OrderDetailSession ods : orderDetailSessionList) {
                OrderDetail orderDetail = new OrderDetail(0, name, order.getId(), ods.getProductId(), ods.getQuantity());
                boolean created = orderDetailDAO.insert(orderDetail);
                if (!created) {
                    isSuccess = false;
                    logger.info("order failed");
                    break;
                }
            }
        }
        
        response.sendRedirect("HomeServlet");
        
        //Xoa gio hang
        if (isSuccess)
            session.removeAttribute("cart");
            */
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
