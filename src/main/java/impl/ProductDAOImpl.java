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
import dao.ProductDAO;
import db.MySQLDriver;
import model.Product;

/**
 *
 * @author Admin
 */
public class ProductDAOImpl implements ProductDAO {

	@Override
	public boolean insert(Product product) {
		Connection conn = MySQLDriver.getInstance().getConnection();
		try {
			String sql = "INSERT INTO PRODUCTS VALUES(null,?,?,?,?,?)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, product.getName());
			stmt.setInt(2, product.getQuantity());
			stmt.setString(3, product.getImage());
			stmt.setDouble(4, product.getPrice());
			stmt.setInt(5, product.getCategories_id());
			stmt.execute();
		} catch (SQLException ex) {
			return false;
		}
		return true;
	}

	@Override
	public boolean update(Product product) {
		Connection conn = MySQLDriver.getInstance().getConnection();
		try {
			String sql = "UPDATE PRODUCTS SET NAME=?, DESCRIPTION=?, IMAGE=?, PRICE=?, CATEGORIES_ID=? WHERE ID=?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, product.getName());
			stmt.setInt(2, product.getQuantity());
			stmt.setString(3, product.getImage());
			stmt.setDouble(4, product.getPrice());
			stmt.setInt(5, product.getCategories_id());
			stmt.setInt(6, product.getId());
			stmt.execute();
		} catch (SQLException ex) {
			return false;
		}
		return true;
	}

	@Override
	public boolean delete(int productId) {
		Connection conn = MySQLDriver.getInstance().getConnection();
		try {
			String sql = "DELETE FROM PRODUCTS WHERE ID=?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, productId);
			stmt.execute();
		} catch (SQLException ex) {
			return false;
		}
		return true;
	}

	@Override
	public List<Product> all() {
		List<Product> productList = new ArrayList<>();
		Connection conn = MySQLDriver.getInstance().getConnection();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM PRODUCTS");
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int price = rs.getInt("price");
				int quantity = rs.getInt("quantity");
				String image = rs.getString("image");
				int categories_id = rs.getInt("categories_id");
				productList.add(new Product(id, name, price, quantity, image, categories_id));
			}
		} catch (SQLException ex) {
			Logger.getLogger(CRUDServlet.class.getName()).log(Level.SEVERE, null, ex);
		}
		return productList;
	}

	@Override
	public Product find(int id) {
		Connection conn = MySQLDriver.getInstance().getConnection();
		try {
			String sql = "SELECT * FROM PRODUCTS WHERE ID=? LIMIT 1";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String name = rs.getString("name");
				int price = rs.getInt("price");
				int quantity = rs.getInt("quantity");
				String image = rs.getString("image");
				int categories_id = rs.getInt("categories_id");
				return new Product(id, name, price, quantity, image, categories_id);
			}
		} catch (SQLException ex) {
			return null;
		}
		return null;
	}

	@Override
	public List<Product> findByProperty(String column, Object value) {
		List<Product> productList = new ArrayList<>();
		Connection conn = MySQLDriver.getInstance().getConnection();
		try {
			String sql = "SELECT * FROM PRODUCTS WHERE ?=?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, column);
			stmt.setString(2, value.toString());

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int price = rs.getInt("price");
				int quantity = rs.getInt("quantity");
				String image = rs.getString("image");
				int categories_id = rs.getInt("categories_id");
				
				productList.add(new Product(id, name, price, quantity, image, categories_id));
			}
		} catch (SQLException ex) {
			Logger.getLogger(CRUDServlet.class.getName()).log(Level.SEVERE, null, ex);
		}
		return productList;
	}

	@Override
	public List<Product> findByNameAndCategoryId(String name, int categoryId) {
		List<Product> productList = new ArrayList<>();
		Connection conn = MySQLDriver.getInstance().getConnection();
		try {

			String sql = "SELECT * FROM PRODUCTS WHERE categories_id=? AND name LIKE ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, categoryId);
			stmt.setString(2, name);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String nameprd = rs.getString("name");
				int price = rs.getInt("price");
				int quantity = rs.getInt("quantity");
				String image = rs.getString("image");
				int categories_id = rs.getInt("categories_id");
				
				productList.add(new Product(id, name, price, quantity, image, categories_id));
			}
		} catch (SQLException ex) {
			Logger.getLogger(CRUDServlet.class.getName()).log(Level.SEVERE, null, ex);
		}
		return productList;
	}

	public List<Product> findByName(String name) {
		List<Product> productList = new ArrayList<>();
		Connection conn = MySQLDriver.getInstance().getConnection();
		try {

			String sql = "SELECT * FROM PRODUCTS WHERE name LIKE ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%" + name + "%");

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String nameprd = rs.getString("name");
				int price = rs.getInt("price");
				int quantity = rs.getInt("quantity");
				String image = rs.getString("image");
				int categories_id = rs.getInt("categories_id");
				
				productList.add(new Product(id, nameprd, price, quantity, image, categories_id));
			}
		} catch (SQLException ex) {
			Logger.getLogger(CRUDServlet.class.getName()).log(Level.SEVERE, null, ex);
		}
		return productList;
	}

	@Override
	public List<Product> findByCategoryId(int categoryId) {
		List<Product> productList = new ArrayList<>();
		Connection conn = MySQLDriver.getInstance().getConnection();
		try {

			String sql = "SELECT * FROM PRODUCTS WHERE categories_id=?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, categoryId);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String nameprd = rs.getString("name");
				int price = rs.getInt("price");
				int quantity = rs.getInt("quantity");
				String image = rs.getString("image");
				int categories_id = rs.getInt("categories_id");
				productList.add(new Product(id, nameprd, price, quantity, image, categories_id));
			}
		} catch (SQLException ex) {
			Logger.getLogger(CRUDServlet.class.getName()).log(Level.SEVERE, null, ex);
		}
		return productList;
	}

}
