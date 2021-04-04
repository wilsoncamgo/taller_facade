package account;

import java.util.Arrays;

public class Account {

    private String email;
    private char[] password;

    public Account(String email, char[] password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public char[] getPassword() {
        return password;
    }
    public void setPassword(char[] password) {
        this.password = password;
    }

    public boolean checkEmail(String email) {
        return this.email.equals(email);
    }

    public boolean checkPassword(char[] password) {
        return Arrays.equals(this.password, password);
    }
    
    public boolean login(String email, char[] password) {
        return checkEmail(email) && checkPassword(password);
    }

    public String getUserType() {
        return getClass().getSimpleName();
    }

    public static boolean emailValid(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }

    public static boolean passwordValid(char[] password) {
        return  8 <= password.length && password.length <= 24;
    }

}
