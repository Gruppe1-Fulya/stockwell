package org.atom.stockwell;

import javax.swing.*;
import javax.xml.crypto.Data;

import org.atom.stockwell.db.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class Main {

    public static void main(String [] args){

        var ctx = new SpringApplicationBuilder(Main.class)
                .headless(false).run(args);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(MainFrame::new);

    }

}
