package org.atom.stockwell;

import javax.swing.*;
import javax.xml.crypto.Data;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.intellijthemes.FlatArcDarkIJTheme;
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

//        DatabaseManager db = new DatabaseManager();
//
//        Product dummy = new ProductBuilder()
//                .startBuild()
//                .setName("dummy")
//                .setBarcodeId("0001")
//                .setCategory("dummy category")
//                .doneBuild();
//
//        db.createNewProduct(dummy);

        try {
            //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            FlatArcDarkIJTheme.setup();
        } catch (Exception e) {
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(MainFrame::new);

    }

}
