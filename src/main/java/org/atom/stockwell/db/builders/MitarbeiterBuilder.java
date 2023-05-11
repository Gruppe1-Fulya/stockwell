package org.atom.stockwell.db.builders;

import org.atom.stockwell.db.classes.Mitarbeiter;
import org.atom.stockwell.db.classes.Person;

public class MitarbeiterBuilder {

    Mitarbeiter currentMitarbeiter;

    public MitarbeiterBuilder startBuild() {
        currentMitarbeiter = new Mitarbeiter();
        return this;
    }

    public MitarbeiterBuilder setPerson(Person person) {
        currentMitarbeiter.setId(person.getId());
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
