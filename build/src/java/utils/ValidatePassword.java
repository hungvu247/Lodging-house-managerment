package utils;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 *
 * @author ASUS
 */
public class ValidatePassword {
    public static boolean validatePassword(String password) {
        // Kiểm tra độ dài mật khẩu
        if (password.length() < 8 || password.length() > 32) {
            return false;
        }
        // Định nghĩa mẫu regex để kiểm tra các yêu cầu của mật khẩu
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!])(?=\\S+$).{8,32}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        
        return matcher.matches();
    }
}
