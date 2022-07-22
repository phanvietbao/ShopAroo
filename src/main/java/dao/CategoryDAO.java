package dao;

import java.util.List;
import model.Category;

/**
 *
 * @author Admin
 */
public interface CategoryDAO {
    public boolean insert(Category category);
    public boolean update(Category category);
    public boolean delete(int categoryId);
    
    public List<Category> all();
    public Category find(int id);
    public List<Category> findByProperty(String column, Object value);
}
