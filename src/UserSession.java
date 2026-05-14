import java.util.HashMap;

public class UserSession {

    public static String fullName = "";
    public static String email = "";
    public static String password = "";

    // email → full name mapping (IMPORTANT for login display name)
    public static HashMap<String, String> userNames = new HashMap<>();
}