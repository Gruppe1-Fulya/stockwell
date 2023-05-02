package org.atom.stockwell;

import javax.swing.*;

import org.atom.stockwell.db.Mitarbeiter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class Main implements CommandLineRunner {

    //Spring Boot will automagically wire this object using application.properties:
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public static void main(String [] args){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(MainFrame::new);

        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("LOL");

        List<Mitarbeiter> mitarbeiters = jdbcTemplate.query("select * from mitarbeiter",
                (rs, rn) -> new Mitarbeiter(rs.getString("username"), rs.getString("password")));

        for (Mitarbeiter mitarbeiter : mitarbeiters) {
            System.out.println(mitarbeiter.toString());
        }

    }

}
