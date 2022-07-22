package dao;

import java.util.List;
import model.Product;

/**
 *
 * @author Admin
 */
public interface ProductDAO {
    public boolean insert(Product product);
    public boolean update(Product product);
    public boolean delete(int productId);
    
    public List<Product> all();
    public Product find(int id);
    public List<Product> findByProperty(String column, Object value);
    public List<Product> findByNameAndCategoryId(String name, int categoryId);
    public List<Product> findByName(String name);
    public List<Product> findByCategoryId(int categoryId);
}