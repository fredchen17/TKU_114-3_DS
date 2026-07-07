public class LoginCheck {
    public static void main(String[] args) {
        String username = "admin";
        String password = "1234";

        String inputUsername = "admin";
        String inputPassword = "1234";

        boolean isLoginSuccess = username.equals(inputUsername) && password.equals(inputPassword);

        System.out.println("輸入的帳號: " + inputUsername);
        System.out.println("輸入的密碼: " + inputPassword);
        System.out.println("登入是否成功: " + isLoginSuccess);
    }
}