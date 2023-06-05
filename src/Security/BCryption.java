package Security;

import org.mindrot.jbcrypt.BCrypt;

public class BCryption {
	public static String hashPassword(String password) {
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        return hashedPassword;
    }
}
