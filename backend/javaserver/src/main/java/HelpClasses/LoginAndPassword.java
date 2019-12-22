package HelpClasses;

public class LoginAndPassword {

    String signin_email;
    String signin_password;
    String remember_me;

   public LoginAndPassword(){}

    public LoginAndPassword(String singin_email, String singin_password, String remember_me) {
        this.signin_email = singin_email;
        this.signin_password = singin_password;
        this.remember_me = remember_me;
    }

    public String getSingin_email() {
        return signin_email;
    }

    public void setSingin_email(String singin_email) {
        this.signin_email = singin_email;
    }

    public String getSingin_password() {
        return signin_password;
    }

    public void setSingin_password(String singin_password) {
        this.signin_password = singin_password;
    }

    public String getRemember_me() {
        return remember_me;
    }

    public void setRemember_me(String remember_me) {
        this.remember_me = remember_me;
    }
}
