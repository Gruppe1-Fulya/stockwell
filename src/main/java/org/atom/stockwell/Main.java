package org.atom.stockwell;

import javax.swing.*;
import javax.xml.crypto.Data;

import org.atom.stockwell.db.DatabaseManager;
import org.atom.stockwell.db.Mitarbeiter;
import org.atom.stockwell.db.Person;
import org.atom.stockwell.db.PersonBuilder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class Main {

    public static void main(String [] args){

        var ctx = new SpringApplicationBuilder(Main.class)
                .headless(false).run(args);

        Person person = new PersonBuilder()
                .startBuild()
                .setId(2)
                .setName("name")
                .setEmail("mail")
                .setAdress("adress")
                .setPhoneNumber("5555")
                .doneBuild();

        DatabaseManager db = new DatabaseManager();

        boolean r = db.createNewPerson(person);
        System.out.println(r);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(MainFrame::new);

    }

}
