package impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import servlet.CRUDServlet;
import dao.OrderDAO;
import db.MySQLDriver;
import model.Order;

/**
 *
 * @author Admin
 */
public class OrderDAOImpl implements OrderDAO {
    
    @Override
    public Order insert(Order order) {
       Connection conn = MySQLDriver.getInstance().getConnection();
        try {
            String sql = "INSERT INTO orders VALUES(null,?,?,?,?)";            
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, order.getCode());
            stmt.setString(2, order.getDescription());
            stmt.setString(3, order.getStatus());
            stmt.setInt(4, order.getUser_id());
            stmt.execute();
            
            //Query select order inserted.
            sql = "SELECT * FROM Orders WHERE name=? LIMIT 1";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, order.getCode());
            
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String code = rs.getString("code");
                String des = rs.getString("description");
                String status = rs.getString("status");
                int user_id = rs.getInt("user_id");
                
                return new Order(id, code, des, status, user_id);
            }
            
        } catch (SQLException ex) {
            return null;
        }
        return null;
    }

    @Override
    public boolean update(Order order) {
      Connection conn = MySQLDriver.getInstance().getConnection();
        try {
            String sql = "UPDATE ORDERS SET NAME=?, DESCRIPTION=?, STATUS=? , USER_ID=? WHERE ID=?";            
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, order.getCode());
            stmt.setString(2, order.getDescription());
            stmt.setString(3, order.getStatus());
            stmt.setInt(4, order.getUser_id());
            stmt.setInt(5, order.getId());
            stmt.execute();
        } catch (SQLException ex) {
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(int orderId) {
        Logger.getLogger("OrderDAO").info(orderId + ": order delete");
       Connection conn = MySQLDriver.getInstance().getConnection();
        try {
            String sql = "DELETE FROM ORDERS WHERE ID=?";            
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, orderId);
            stmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger("OrderDAO").info(ex.toString());
            return false;
        }
        return true;
    }

    @Override
    public List<Order> all() {
        List<Order> orderList = new ArrayList<>();
        Connection conn = MySQLDriver.getInstance().getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM ORDERS");
            while(rs.next()){
                int id = rs.getInt("id");
                String code = rs.getString("code");
                String description = rs.getString("description");
                String status = rs.getString("status");
                int user_id = rs.getInt("user_id");
                orderList.add(new Order(id, code, description, status, user_id));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CRUDServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        return orderList;
    }

    @Override
    public Order find(int id) {
        Connection conn = MySQLDriver.getInstance().getConnection();
        try {
            String sql = "SELECT * FROM ORDERS WHERE ID=? LIMIT 1";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
            	String code = rs.getString("code");
                String description = rs.getString("description");
                String status = rs.getString("status");
                int user_id = rs.getInt("user_id");
                
                return new Order(id, code, description, status, user_id);
            }
        } catch (SQLException ex) {
            return null;
        }
        return null;
    }

    @Override
    public List<Order> findByProperty(String column, Object value) {
        List<Order> orderList = new ArrayList<>();
        Connection conn = MySQLDriver.getInstance().getConnection();
        try {
            String sql = "SELECT * FROM ORDERS WHERE ?=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, column);
            stmt.setString(2, value.toString());
            
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
            	String code = rs.getString("code");
                String description = rs.getString("description");
                String status = rs.getString("status");
                int user_id = rs.getInt("user_id");
                
                orderList.add(new Order(id, code, description, status, user_id));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CRUDServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        return orderList;
    }
    
}
