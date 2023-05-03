package org.atom.stockwell.db;

public class MitarbeiterBuilder {

    Mitarbeiter currentMitarbeiter;

    public MitarbeiterBuilder startBuild() {
        currentMitarbeiter = new Mitarbeiter();
        return this;
    }

    public MitarbeiterBuilder setPerson(Person person) {
        currentMitarbeiter.setName(person.getName());
        currentMitarbeiter.setEmail(person.getEmail());
        currentMitarbeiter.setAddress(person.getAddress());
        currentMitarbeiter.setPhoneNo(person.getPhoneNo());
        return this;
    }

    public MitarbeiterBuilder setUsername(String username) {
        currentMitarbeiter.setUsername(username);
        return this;
    }

    public MitarbeiterBuilder setPassword(String password) {
        currentMitarbeiter.setPassword(password);
        return this;
    }

    public Mitarbeiter doneBuild() {
        return currentMitarbeiter;
    }
}
