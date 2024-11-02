package AccountManager;

enum UserRole {
    ADMIN, DOCTOR, PATIENT, PHARMACIST
}
public class User {
    private String id;
    private String password;
    private String name;
    private String email;
    private UserRole userRole;

    public User(UserRole userRole, String id, String password, String name, String email) {
        this.userRole = userRole;
        this.id = id;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }
}
