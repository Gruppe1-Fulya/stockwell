package org.atom.stockwell.db;

import org.atom.stockwell.db.builders.MitarbeiterBuilder;
import org.atom.stockwell.db.builders.PersonBuilder;
import org.atom.stockwell.db.classes.Mitarbeiter;
import org.atom.stockwell.db.classes.Person;
import org.junit.Test;

/*
    HEM TEST ETMEK İÇİN
    HEM DE NASIL KULLANILDIĞINI GÖSTERMEK İÇİN
 */

public class DatabaseTester {

    @Test
    public void createPerson(){
        PersonBuilder builder = new PersonBuilder();

        Person dummy = builder
                .startBuild()
                .setName("TEST_NAME")
                .setAdress("TEST_ADRESS")
                .setEmail("TEST_EMAIL")
                .setPhoneNumber("TEST_PHONE_NUMBER")
                .doneBuild();

        DatabaseManager db = new DatabaseManager();

        db.createNewPerson(dummy);
        db.deletePerson(dummy);

    }

    @Test
    public void createMitarbeiter() {

        PersonBuilder builder = new PersonBuilder();

        Person person = builder
                .startBuild()
                .setName("TEST_NAME")
                .setAdress("TEST_ADRESS")
                .setEmail("TEST_EMAIL")
                .setPhoneNumber("TEST_PHONE_NUMBER")
                .doneBuild();

        MitarbeiterBuilder mitarbeiterBuilder = new MitarbeiterBuilder();

        Mitarbeiter mitarbeiter = mitarbeiterBuilder
                .startBuild()
                .setPerson(person)
                .setUsername("TEST_USERNAME")
                .setPassword("TEST_PASSWORD")
                .doneBuild();

        DatabaseManager db = new DatabaseManager();

        db.createNewMitarbeiter(mitarbeiter);
        db.deletePerson(mitarbeiter);
        db.deleteMitarbeiter(mitarbeiter);

    }
}
