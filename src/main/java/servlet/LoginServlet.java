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
import model.User;
import utils.URLSite;

/**
 *
 * @author Administrator
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {


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
        HttpSession session = request.getSession(true);
        if (session.getAttribute("logged") != null) {
            response.sendRedirect(URLSite.HOME_URL);
        } else {
        	HttpSession ss = request.getSession();
        	ss.setAttribute("acc", session);
            request.getRequestDispatcher("login.jsp").forward(request, response);
        	

        }
      
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
        String password = request.getParameter("password");
        DatabaseDAO.init(new Database());
        UserDAO userDAO = DatabaseDAO.getInstance().getUserDAO();
        User user = userDAO.login(phone, password);
        if (user != null) {
            System.out.println("Login successfully.");
            HttpSession session = request.getSession(true);
            
            session.setAttribute("logged", true);
            session.setAttribute("user", user);
            String url = (String) session.getAttribute("urlBack");
            if (url != null) response.sendRedirect(url);
            else
            response.sendRedirect(URLSite.ADMIN_INDEX_DASHBOARD_URL);
        } else {
            System.out.println("Login failed.");
            response.sendRedirect(URLSite.LOGIN_URL);
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
