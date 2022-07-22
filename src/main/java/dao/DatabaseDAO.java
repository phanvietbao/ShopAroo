package dao;

public abstract class DatabaseDAO {
    private static DatabaseDAO instance;
    public static DatabaseDAO getInstance(){
        return instance;
    }
    
    public static void init(DatabaseDAO _instance){
        instance = _instance;
    }
    public abstract UserDAO getUserDAO();
    public abstract CategoryDAO getCategoryDAO();
    public abstract ProductDAO getProductDAO();
    public abstract OrderDAO getOrderDAO();
    public abstract OrderDetailDAO getOrderDetailDAO();
  
}
