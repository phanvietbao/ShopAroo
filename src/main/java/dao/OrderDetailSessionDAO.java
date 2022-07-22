package dao;

import java.util.List;
import model.OrderDetailSession;

/**
 *
 * @author Administrator
 */
public interface OrderDetailSessionDAO {
    public boolean insert(OrderDetailSession orderDetailSession);
    public boolean update(OrderDetailSession orderDetailSession);
    public boolean delete(OrderDetailSession orderDetailSession);
    
    public List<OrderDetailSession> all();
    public OrderDetailSession find(int productId);
}