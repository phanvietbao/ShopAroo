package auth;

import jakarta.servlet.http.HttpSession;
import model.User;

public class Auth {

    private static HttpSession session;
    
    public static void init(HttpSession _session){
        session = _session;
    }
    public static User user(){
        if(session.getAttribute("user") != null){
            return (User) session.getAttribute("user");
        }
        return null;
    }
    
    public static boolean isLogin(){
        return user() == null ? false : true;
    }
    
    public static boolean isAdmin(){
        if(user() == null) return false;
        return user().role.equals("admin");
    }
}
