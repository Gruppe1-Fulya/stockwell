package org.atom.stockwell.db;

import org.atom.stockwell.db.builders.*;
import org.atom.stockwell.db.classes.*;

import org.junit.Test;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;

import java.time.LocalDate;
import java.util.Date;

/*
    HEM TEST ETMEK İÇİN
    HEM DE NASIL KULLANILDIĞINI GÖSTERMEK İÇİN
 */

@DisplayName("Database Test Module to test All Functions")
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

        Assert.assertSame(
                true,
                db.getPersonList().stream().anyMatch(p -> p.getId().equals(dummy.getId()))
        );

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

        Assert.assertSame(
                true,
                db.getPersonList().stream().anyMatch(p -> p.getId().equals(mitarbeiter.getId()))
        );

        Assert.assertSame(
                true,
                db.getMitarbeiterList().stream().anyMatch(m -> m.getId().equals(mitarbeiter.getId()))
        );

        db.deletePerson(mitarbeiter);
        db.deleteMitarbeiter(mitarbeiter);

    }

    @Test
    public void createProduct() {
        ProductBuilder productBuilder = new ProductBuilder();

        Product product = productBuilder
                .startBuild()
                .setName("TEST_NAME")
                .setBarcodeId("TEST_BARCODE_ID")
                .setCategory("TEST_CATEGORY")
                .doneBuild();

        DatabaseManager db = new DatabaseManager();

        db.createNewProduct(product);

        Assert.assertSame(
                true,
                db.getProductList().stream().anyMatch(p -> p.getId().equals(product.getId()))
        );

        db.deleteProduct(product);

    }

    @Test
    public void createTransaktion() {

    /*
        Burada test amaçlı satıcı ile alıcı aynı kişi
     */

        ProductBuilder productBuilder = new ProductBuilder();

        Product product = productBuilder
                .startBuild()
                .setName("TEST_NAME")
                .setBarcodeId("TEST_BARCODE_ID")
                .setCategory("TEST_CATEGORY")
                .doneBuild();

        PersonBuilder personBuilder = new PersonBuilder();

        Person person = personBuilder
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

        TransaktionBuilder transaktionBuilder = new TransaktionBuilder();

        Transaktion transaktion = transaktionBuilder
                .startBuild()
                .setKunde(person)
                .setMitarbeiter(mitarbeiter)
                .setProduct(product)
                .setAmount(1)
                .setCost(1)
                .setType("TEST_TYPE")
                .setDate(new Date())
                .doneBuild();

        DatabaseManager db = new DatabaseManager();

        db.createNewMitarbeiter(mitarbeiter);

        Assert.assertSame(
                true,
                db.getPersonList().stream().anyMatch(p -> p.getId().equals(mitarbeiter.getId()))
        );

        Assert.assertSame(
                true,
                db.getMitarbeiterList().stream().anyMatch(m -> m.getId().equals(mitarbeiter.getId()))
        );

        db.createNewProduct(product);

        Assert.assertSame(
                true,
                db.getProductList().stream().anyMatch(p -> p.getId().equals(product.getId()))
        );

        db.createNewTransaktion(transaktion);

        Assert.assertSame(
                true,
                db.getTransaktions().stream().anyMatch(t -> t.getId().equals(transaktion.getId()))
        );

        db.deleteTransaktion(transaktion);
        db.deleteProduct(product);
        db.deleteMitarbeiter(mitarbeiter);
        db.deletePerson(person);

    }

    @Test
    public void addProductLager() {
        DatabaseManager db = new DatabaseManager();

        Lager lager = db.getLager();

        // New Product
        ProductBuilder productBuilder = new ProductBuilder();
        Product product = productBuilder
                .startBuild()
                .setName("TEST_NAME")
                .setBarcodeId("TEST_BARCODE_ID")
                .setCategory("TEST_CATEGORY")
                .doneBuild();

        db.createNewProduct(product);

        Assert.assertSame(
                true,
                db.getProductList().stream().anyMatch(p -> p.getId().equals(product.getId()))
        );


        LagerProductBuilder lagerProductBuilder = new LagerProductBuilder();
        LagerProduct lagerProduct = lagerProductBuilder
                .startBuild()
                .setProduct(product)
                .setAmount(10)
                .setCost(100)
                .setDate(new Date())
                .doneBuild();

        lager.addProduct(lagerProduct);

        db.updateLager(lager);

        Assert.assertSame(
                true,
                db.getLager().lagerProducts().stream().anyMatch(lp -> lp.getId().equals(lagerProduct.getId()))
        );

        lagerProduct.setAmount(101);

        db.updateLager(lager);

        Assert.assertSame(
                101,
                db.getLager().lagerProducts().stream().filter(lp -> lp.getId().equals(lagerProduct.getId()))
                        .findFirst().get().getAmount()
        );

        lager.removeProduct(lagerProduct);

        db.updateLager(lager);

        Assert.assertSame(
                false,
                db.getLager().lagerProducts().stream().anyMatch(lp -> lp.getId().equals(lagerProduct.getId()))
                );

        db.deleteProduct(product);
    }

}
