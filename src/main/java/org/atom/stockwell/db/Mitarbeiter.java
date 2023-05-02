package org.atom.stockwell.db;

public class Mitarbeiter extends Person{

    private String username;
    private String password;

    public Mitarbeiter(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Mitarbeiter { name = " + username + " password = " + password + " }";
    }

}
