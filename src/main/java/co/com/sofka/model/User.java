package co.com.sofka.model;

public class User {
    private String emailUser;
    private String passwordUser;

    public void setEmailUser(String email) {
        this.emailUser = email;
    }

    public void setPasswordUser(String passwordUser) {
        this.passwordUser = passwordUser;
    }

    @Override
    public String toString() {
        return "{\n" +
                " \"email\": \"" + emailUser + "\",\n" +
                " \"password\": \"" + passwordUser + "\"\n" +
                "}";
    }
}
