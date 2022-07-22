package dao;

import java.util.List;
import model.Order;

/**
 *
 * @author Admin
 */
public interface OrderDAO {
    public Order insert(Order order);
    public boolean update(Order order);
    public boolean delete(int orderId);
    
    public List<Order> all();
    public Order find(int id);
    public List<Order> findByProperty(String column, Object value);
}