package org.atom.types;

public class Client extends Person{
    public String phoneNumber;

    public String lastName;
    public String company;

    public Client(int id, String name, String lastName, String company, String phoneNumber) {
        super(id, name);
        this.lastName = lastName;
        this.company = company;
        this.phoneNumber = phoneNumber;
    }
}
