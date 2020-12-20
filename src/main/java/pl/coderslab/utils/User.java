package pl.coderslab.utils;

import org.mindrot.jbcrypt.BCrypt;

public class User {
    private int id;
    private String username;
    private String email;
    private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    //nadpisujemy metodę toString, żeby nie wyświetlały się hashkody
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    //// Hash a password for the first time
//String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
//// gensalt's log_rounds parameter determines the complexity
//// the work factor is 2**log_rounds, and the default is 10
//String hashed = BCrypt.hashpw(password, BCrypt.gensalt(12));
//// Check that an unencrypted password matches one that has
//// previously been hashed
//if (BCrypt.checkpw(candidate, hashed))
//	System.out.println("It matches");
//else
//	System.out.println("It does not match");
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password=password;
//        można też zahaszować hasło tutaj
//        this.password=BCrypt.hashpw(password, BCrypt.gensalt());

    }
}