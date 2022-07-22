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
import dao.OrderDetailDAO;
import db.MySQLDriver;
import model.Order;
import model.OrderDetail;

/**
 *
 * @author Admin
 */
public class OrderDetailDAOImpl implements OrderDetailDAO {

    @Override
    public boolean insert(OrderDetail orderDetail) {
        Connection conn = MySQLDriver.getInstance().getConnection();
        try {
            String sql = "INSERT INTO ORDER_DETAILS VALUES(null,?,?,?,?)";            
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, orderDetail.getOrders_code());
            stmt.setInt(2, orderDetail.getProducts_id());
            stmt.setInt(3, orderDetail.getQuantity());
            stmt.setInt(4, orderDetail.getQuantity());
            
            stmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger("OrderDetailDAO").info(ex.toString());
            return false;
        }
        return true;
    }

    @Override
    public boolean update(OrderDetail orderDetail) {
        Connection conn = MySQLDriver.getInstance().getConnection();
        try {
            String sql = "UPDATE ORDER_DETAILS SET Orders_code=?, Products_id=?, Quantity=? WHERE ID=?";            
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, orderDetail.getOrders_code());
            stmt.setInt(2, orderDetail.getProducts_id());
            stmt.setInt(3, orderDetail.getQuantity());
            stmt.setInt(4, orderDetail.getId());
            stmt.execute();
        } catch (SQLException ex) {
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(int orderDetailId) {
        Connection conn = MySQLDriver.getInstance().getConnection();
        try {
            String sql = "DELETE FROM ORDER_DETAILS WHERE ID=?";            
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, orderDetailId);
            stmt.execute();
        } catch (SQLException ex) {
            return false;
        }
        return true;
    }

    @Override
    public List<OrderDetail> all() {
         List<OrderDetail> orderDetailList = new ArrayList<>();
        Connection conn = MySQLDriver.getInstance().getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM ORDER_DETAILS");
            while(rs.next()){
                int id = rs.getInt("id");
                String orders_code = rs.getString("orders_code");
                int products_id = rs.getInt("products_id");
                int quantity = rs.getInt("quantity");
                orderDetailList.add(new OrderDetail(id, orders_code, products_id, quantity));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CRUDServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        return orderDetailList;
    }

    @Override
    public OrderDetail find(int id) {
        Connection conn = MySQLDriver.getInstance().getConnection();
        try {
            String sql = "SELECT * FROM ORDER_DETAILS WHERE ID=? LIMIT 1";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                String orders_code = rs.getString("orders_code");
                int products_id = rs.getInt("products_id");
                int quantity = rs.getInt("quantity");
                return new OrderDetail(id, orders_code, products_id, quantity);
            }
        } catch (SQLException ex) {
            return null;
        }
        return null;
    }

    @Override
    public List<OrderDetail> findByProperty(String column, Object value) {
        List<OrderDetail> orderDetailList = new ArrayList<>();
        Connection conn = MySQLDriver.getInstance().getConnection();
        try {
            String sql = "SELECT * FROM ORDER_DETAILS WHERE ?=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, column);
            stmt.setString(2, value.toString());
            
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
            	int id = rs.getInt("id");
                String orders_code = rs.getString("orders_code");
                int products_id = rs.getInt("products_id");
                int quantity = rs.getInt("quantity");
                orderDetailList.add(new OrderDetail(id, orders_code, products_id, quantity));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CRUDServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        return orderDetailList;
    }

    @Override
    public List<OrderDetail> findByOrderName(String orderName) {
        List<OrderDetail> orderDetailList = new ArrayList<>();
        Connection conn = MySQLDriver.getInstance().getConnection();
        try {
            String sql = "SELECT * FROM ORDER_DETAILS WHERE ORDERS_NAME=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, orderName);
            
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
            	int id = rs.getInt("id");
                String orders_code = rs.getString("orders_code");
                int products_id = rs.getInt("products_id");
                int quantity = rs.getInt("quantity");
                orderDetailList.add(new OrderDetail(id, orders_code, products_id, quantity));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CRUDServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        return orderDetailList;
    }
    
}