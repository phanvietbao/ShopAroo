package dao;

import impl.CategoryDAOImpl;
import impl.OrderDAOImpl;
import impl.OrderDetailDAOImpl;
import impl.ProductDAOImpl;
import impl.UserDAOImpl;

/**
 *
 * @author Admin
 */
public class Database extends DatabaseDAO {

    
    @Override
    public UserDAO getUserDAO() {
        return new UserDAOImpl();
    }

    @Override
    public CategoryDAO getCategoryDAO() {
        return new CategoryDAOImpl();
    }

    @Override
    public ProductDAO getProductDAO() {
        return new ProductDAOImpl();
    }

    @Override
    public OrderDAO getOrderDAO() {
      return new OrderDAOImpl();
    }

    @Override
    public OrderDetailDAO getOrderDetailDAO() {
      return new OrderDetailDAOImpl();
    }
    
}