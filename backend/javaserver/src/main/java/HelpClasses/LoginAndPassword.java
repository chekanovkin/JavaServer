package HelpClasses;

public class LoginAndPassword {

    String signin_email;
    String signin_password;
    String remember_me;

   public LoginAndPassword(){}

    public LoginAndPassword(String signin_email, String signin_password, String remember_me) {
        this.signin_email = signin_email;
        this.signin_password = signin_password;
        this.remember_me = remember_me;
    }

    public String getsignin_email() {
        return signin_email;
    }

    public void setsignin_email(String signin_email) {
        this.signin_email = signin_email;
    }

    public String getsignin_password() {
        return signin_password;
    }

    public void setsignin_password(String signin_password) {
        this.signin_password = signin_password;
    }

    public String getRemember_me() {
        return remember_me;
    }

    public void setRemember_me(String remember_me) {
        this.remember_me = remember_me;
    }
}
