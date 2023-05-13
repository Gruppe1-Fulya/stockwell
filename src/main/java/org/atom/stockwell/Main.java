package org.atom.stockwell;

import javax.swing.*;
import javax.xml.crypto.Data;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.intellijthemes.FlatArcDarkIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatCyanLightIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatLightFlatIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatSolarizedLightIJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatAtomOneLightIJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatLightOwlContrastIJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatLightOwlIJTheme;
import org.atom.stockwell.db.*;
import org.atom.stockwell.db.builders.TransaktionBuilder;
import org.atom.stockwell.db.classes.Mitarbeiter;
import org.atom.stockwell.db.classes.Person;
import org.atom.stockwell.db.classes.Product;
import org.atom.stockwell.db.classes.Transaktion;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class Main {

    public static void main(String [] args){
        var ctx = new SpringApplicationBuilder(Main.class)
                .headless(false).run(args);


        DatabaseManager db = new DatabaseManager();

//        Mitarbeiter caglar = db.getMitarbeiterList().stream()
//                .filter(person -> person.getName().equals("caglar"))
//                .findFirst()
//                .get();
//
//        Person burak = db.getPersonList().stream()
//                .filter(person -> person.getName().equals("burak"))
//                .findFirst()
//                .get();
//
//        Product product = db.getProductList().stream().findFirst().get();
//
//        TransaktionBuilder builder = new TransaktionBuilder();
//
//        Transaktion transaktion = builder
//                .startBuild()
//                .setProduct(product)
//                .setAmount(10)
//                .setCost(1000)
//                .setKunde(burak)
//                .setMitarbeiter(caglar)
//                .setType("SALE")
//                .setDate(Calendar.getInstance().getTime())
//                .doneBuild();
//
//        db.createNewTransaktion(transaktion);

        try {
            //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            FlatArcDarkIJTheme.setup();
        } catch (Exception e) {
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(MainFrame::new);

    }

}
