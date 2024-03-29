package org.atom.stockwell.db.builders;

import org.atom.stockwell.db.classes.Person;

public class PersonBuilder {

    Person currentPerson;

    public PersonBuilder startBuild() {
        currentPerson = new Person();
        return this;
    }

    public PersonBuilder setName(String name) {
        currentPerson.setName(name);
        return this;
    }

    public PersonBuilder setPhoneNumber(String phoneNumber) {
        currentPerson.setPhoneNo(phoneNumber);
        return this;
    }

    public PersonBuilder setAdress(String adress) {
        currentPerson.setAddress(adress);
        return this;
    }

    public PersonBuilder setEmail(String email) {
        currentPerson.setEmail(email);
        return this;
    }

    public PersonBuilder setId(String id) {
        currentPerson.setId(id);
        return this;
    }

    public PersonBuilder setActive(boolean active) {
        currentPerson.setActive(active);
        return this;
    }

    public Person doneBuild() {
        return currentPerson;
    }

}
