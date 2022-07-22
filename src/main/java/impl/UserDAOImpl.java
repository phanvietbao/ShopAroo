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
import dao.UserDAO;
import db.MySQLDriver;
import model.User;

/**
 *
 * @author Admin
 */
public class UserDAOImpl implements UserDAO{

    @Override
    public boolean insert(User user) {
        Connection conn = MySQLDriver.getInstance().getConnection();
        try {
            String sql = "INSERT INTO USERS VALUES(null,?,?,?,?,?)";            
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, user.getPhone());
            stmt.setString(2, user.getName());
            stmt.setString(3, user.getPassword());
            stmt.setString(4, user.getAddress());
            stmt.setString(5, user.getRole());
            stmt.execute();
        } catch (SQLException ex) {
            return false;
        }
        return true;
    }

    @Override
    public boolean update(User user) {
        Connection conn = MySQLDriver.getInstance().getConnection();
        try {
            String sql = "UPDATE USERS SET PHONE=?, NAME=?, PASSWORD=?, ADDRESS=?, ROLE=? WHERE ID=?";            
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, user.getPhone());
            stmt.setString(2, user.getName());
            stmt.setString(3, user.getPassword());
            stmt.setString(4, user.getAddress());
            stmt.setInt(5, user.getId());
            stmt.execute();
        } catch (SQLException ex) {
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(int id) {
        Connection conn = MySQLDriver.getInstance().getConnection();
        try {
            String sql = "DELETE FROM USERS WHERE ID=?";            
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
        } catch (SQLException ex) {
            return false;
        }
        return true;
    }

    @Override
    public List<User> all() { 
        List<User> userList = new ArrayList<User>();
        Connection conn = MySQLDriver.getInstance().getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM USERS");
            while(rs.next()){
                int id = rs.getInt("id");
                String phone = rs.getString("phone");
                String name = rs.getString("name");
                String password = rs.getString("password");
                String address = rs.getString("address");
                String role = rs.getString("role");
                
                userList.add(new User(id, phone, name, password, address, role));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CRUDServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userList;
    }

    @Override
    public User find(int id) {
        Connection conn = MySQLDriver.getInstance().getConnection();
        try {
            String sql = "SELECT * FROM USERS WHERE ID=? LIMIT 1";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
            	String phone = rs.getString("phone");
            	String name = rs.getString("name");
                String password = rs.getString("password");
                String address = rs.getString("address");
                String role = rs.getString("role");
                
                return new User(id, phone, name, password, address, role);
            }
        } catch (SQLException ex) {
            return null;
        }
        return null;
    }

    @Override
    public List<User> findByProperty(String column, Object value) {
        List<User> userList = new ArrayList<User>();
        Connection conn = MySQLDriver.getInstance().getConnection();
        try {
            String sql = "SELECT * FROM users WHERE ?=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, column);
            stmt.setString(2, value.toString());
            
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String phone = rs.getString("phone");
            	String name = rs.getString("name");
                String password = rs.getString("password");
                String address = rs.getString("address");
                String role = rs.getString("role");
                
                userList.add(new User(id, phone, name, password, address, role));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CRUDServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userList;
    }

    @Override
    public User login(String phone, String password) {
      Connection conn = MySQLDriver.getInstance().getConnection();
        try {
            String sql = "SELECT * FROM USERS WHERE PHONE=? AND PASSWORD=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, phone);
            stmt.setString(2, password);
          
           ResultSet rs = stmt.executeQuery();
             if(rs.next()){
                int id = rs.getInt("id");
                String role = rs.getString("role");
                
                return new User(id, phone, password, role);
            }
        } catch (SQLException ex) {
            return null;
        }
        return null;
    }

    @Override
    public boolean register(String phone, String name, String password, String address) {
        if(checkUserExists(phone)) return false;
        insert(new User(phone, name,  password, address,  "user"));
        return true;
    }
    
    private boolean checkUserExists(String phone) {
        Connection conn = MySQLDriver.getInstance().getConnection();
        try {
            String sql = "SELECT * FROM USERS WHERE USERNAME=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, phone);
          
            ResultSet rs = stmt.executeQuery();
             if(rs.next()){
               return true;
            }
        } catch (SQLException ex) {
            return false;
        }
        return false;
    }

	
	
}