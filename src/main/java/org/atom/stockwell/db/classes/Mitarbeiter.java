package org.atom.stockwell.db.classes;

public class Mitarbeiter extends Person{

    private String username;
    private String password;
    public String title;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }

    @Override
    public String toString() {
        return "Mitarbeiter { name " + getName() + " username = " + username + " password = " + password + " }";
    }

}
