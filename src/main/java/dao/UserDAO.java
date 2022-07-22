package dao;
import java.util.List;
import model.User;

/**
 *
 * @author Admin
 */
public interface UserDAO {
    public boolean insert(User user);
    public boolean update(User user);
    public boolean delete(int userId);
    
    public List<User> all();
    public User find(int id);
    public List<User> findByProperty(String column, Object value);
    public User login(String phone, String password);
    public boolean register(String phone, String name, String password, String address);
}