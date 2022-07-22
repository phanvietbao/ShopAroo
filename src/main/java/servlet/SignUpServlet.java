package servlet;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import dao.Database;
import dao.DatabaseDAO;
import dao.UserDAO;
import utils.URLSite;

/**
 *
 * @author Administrator
 */
@WebServlet("/SignUpServlet")
public class SignUpServlet extends HttpServlet {

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
         request.getRequestDispatcher("register.jsp").forward(request, response);
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
        String phone = request.getParameter("phone");
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String address = request.getParameter("address");
        DatabaseDAO.init(new Database());
        UserDAO userDAO = DatabaseDAO.getInstance().getUserDAO();
        boolean isRegister = userDAO.register(phone, name, password, address);
        if (isRegister) {
            System.out.println("Register successfully.");
            HttpSession session = request.getSession(true);
//            session.setAttribute("logged", true);
            response.sendRedirect(URLSite.LOGIN_URL);
        } else {
            System.out.println("Register failed.");
            response.sendRedirect(URLSite.SIGNUP_URL);
        }
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