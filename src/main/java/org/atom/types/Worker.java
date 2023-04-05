package org.atom.types;

public class Worker extends Person{

    public String userName;

    public Worker(int id, String name, String userName) {
        super(id, name);
        this.userName = userName;
    }
}
