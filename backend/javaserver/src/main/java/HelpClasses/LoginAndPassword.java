package HelpClasses;

public class LoginAndPassword {

    String singin_email;
    String singin_password;
    String remember_me;

   public LoginAndPassword(){}

    public LoginAndPassword(String singin_email, String singin_password, String remember_me) {
        this.singin_email = singin_email;
        this.singin_password = singin_password;
        this.remember_me = remember_me;
    }

    public String getSingin_email() {
        return singin_email;
    }

    public void setSingin_email(String singin_email) {
        this.singin_email = singin_email;
    }

    public String getSingin_password() {
        return singin_password;
    }

    public void setSingin_password(String singin_password) {
        this.singin_password = singin_password;
    }

    public String getRemember_me() {
        return remember_me;
    }

    public void setRemember_me(String remember_me) {
        this.remember_me = remember_me;
    }
}
