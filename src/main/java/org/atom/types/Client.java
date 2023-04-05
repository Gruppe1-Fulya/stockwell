package org.atom.types;

public class Client extends Person{
    public String phoneNumber;
    public Client(int id, String name, String phoneNumber) {
        super(id, name);
        this.phoneNumber = phoneNumber;
    }
}
