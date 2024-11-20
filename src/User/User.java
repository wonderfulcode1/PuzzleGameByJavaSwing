package User;

public class User {
    public String name;
    public String password;
    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }
    public User(){
        name = "";
        password = "";
    }

    public String getName() {
        return name;
    }
    public String getPassword() {
        return password;
    }
    public void showUser() {
        System.out.println("Name: " + name);
        System.out.println("Password: " + password);
    }
    public void set(String name, String password) {
        this.name = name;
        this.password = password;
    }
}
