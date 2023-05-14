package org.atom.stockwell.db;

import org.atom.stockwell.db.builders.*;
import org.atom.stockwell.db.classes.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {

    @Autowired
    Environment environment;

    private JdbcTemplate jdbc;
    SimpleDateFormat dateFormat;

    public DatabaseManager() {
        var dataSource = new SimpleDriverDataSource();
        dataSource.setDriver(new org.sqlite.JDBC());
        dataSource.setUrl("jdbc:sqlite:datas.db");
        jdbc = new JdbcTemplate(dataSource);
        dateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
    }

    /*
        SELECT FUNCTIONS
     */

    public List<Person> getPersonList() {
        String sql = """
                select * from person
                """;
        PersonBuilder personBuilder = new PersonBuilder();
        return (List<Person>) jdbc.query(sql, (rs, rn) -> personBuilder
                .startBuild()
                .setId(rs.getString("personId"))
                .setName(rs.getString("name"))
                .setPhoneNumber(rs.getString("telefonNo"))
                .setAdress(rs.getString("adresse"))
                .setEmail(rs.getString("email"))
                .doneBuild());
    }

    public List<Mitarbeiter> getMitarbeiterList() {
        String sql = """
                select * from mitarbeiter
                """;
        MitarbeiterBuilder mitarbeiterBuilder = new MitarbeiterBuilder();

        List<Person> personList = getPersonList();

        return (List<Mitarbeiter>) jdbc.query(sql, (rs, rowNum) ->
            mitarbeiterBuilder
                    .startBuild()
                    .setPerson(
                            personList
                                    .stream()
                                    .filter(person -> {
                                        try {
                                            return person.getId().equals(rs.getString("personId"));
                                        } catch (SQLException e) {
                                            throw new RuntimeException(e);
                                        }
                                    })
                                    .findFirst().get()
                    )
                    .setUsername(rs.getString("username")
                    ).setPassword(rs.getString("password")
                    ).doneBuild());
    }

    public List<Product> getProductList() {
        String sql = """
                select * from product
                """;
        ProductBuilder productBuilder = new ProductBuilder();
        return (List<Product>) jdbc.query(sql, (rs, rn) -> productBuilder
                .startBuild()
                .setId(rs.getString("id"))
                .setBarcodeId(rs.getString("barcode"))
                .setName(rs.getString("name"))
                .setCategory(rs.getString("category"))
                .doneBuild());
    }

    public List<Transaktion> getTransaktions() {
        String sql = """
                select * from transaktions
                """;

        List<Product> productList = getProductList();
        List<Mitarbeiter> mitarbeiterList = getMitarbeiterList();
        List<Person> personList = getPersonList();

        TransaktionBuilder transaktionBuilder = new TransaktionBuilder();

        return (List<Transaktion>) jdbc.query(sql, (rs, rn) -> {
            try {
                return transaktionBuilder
                        .startBuild()
                        .setId(rs.getString("transaktionId"))
                        .setType(rs.getString("type"))
                        .setAmount(rs.getInt("amount"))
                        .setCost(rs.getInt("cost"))
                        .setDate(dateFormat.parse(rs.getString("date")))
                        .setProduct(
                                productList.stream()
                                        .filter(product -> {
                                            try {
                                                return product.getId().equals(rs.getString("productId"));
                                            } catch (SQLException e) {
                                                throw new RuntimeException(e);
                                            }
                                        })
                                        .findFirst().get()
                        )
                        .setKunde(
                                personList.stream()
                                        .filter(person -> {
                                            try {
                                                return person.getId().equals(rs.getString("kundeId"));
                                            } catch (SQLException e) {
                                                throw new RuntimeException(e);
                                            }
                                        })
                                        .findFirst().get()
                        )
                        .setMitarbeiter(
                                mitarbeiterList.stream()
                                        .filter(mitarbeiter -> {
                                            try {
                                                return mitarbeiter.getId().equals(rs.getString("mitarbeiterId"));
                                            } catch (SQLException e) {
                                                throw new RuntimeException(e);
                                            }
                                        })
                                        .findFirst().get()
                        )
                        .doneBuild();
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        });

    }

    public Lager getLager() {
        String sql = """
                select * from lager
                """;

        LagerProductBuilder lagerProductBuilder = new LagerProductBuilder();

        List<Product> products = getProductList();

        Lager lager = new Lager();

        List<LagerProduct> lagerProducts = jdbc.query(sql,
                (rs, rn) -> {
                    Product product = products.stream()
                            .filter(p -> {
                                try {
                                    return p.getId().equals(rs.getString("productId"));
                                } catch (SQLException e) {
                                    throw new RuntimeException(e);
                                }
                            })
                            .findFirst().get();

                    try {
                        return lagerProductBuilder
                                .startBuild()
                                .setId(rs.getString("id"))
                                .setAmount(rs.getInt("amount"))
                                .setCost(rs.getInt("cost"))
                                .setDate(dateFormat.parse(rs.getString("date")))
                                .setProduct(product)
                                .doneBuild();
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                });

        for (LagerProduct lagerProduct: lagerProducts) {
            lager.addProduct(lagerProduct);
        }

        return lager;
    }

    /*
        CREATE FUNCTIONS
     */

    public boolean createNewPerson(Person person) {
        String sql = "insert into " +
                "person(personId, name, telefonNo, adresse, email) " +
                "values('%s', '%s', '%s', '%s', '%s')";

        sql = String.format(sql,
                person.getId(),
                person.getName(),
                person.getPhoneNo(),
                person.getAddress(),
                person.getEmail());

        try {
            jdbc.execute(sql);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean createNewMitarbeiter(Mitarbeiter mitarbeiter) {

        String sql;

        if (!this.getPersonList()
                .stream()
                .anyMatch(person -> person.getId().equals(mitarbeiter.getId()))) {

            sql = "insert into " +
                    "person(personId, name, telefonNo, adresse, email) " +
                    "values('%s', '%s', '%s', '%s', '%s')";

            sql = String.format(sql,
                    mitarbeiter.getId(),
                    mitarbeiter.getName(),
                    mitarbeiter.getPhoneNo(),
                    mitarbeiter.getAddress(),
                    mitarbeiter.getEmail());

            try {
                jdbc.execute(sql);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return false;
            }

        }


        sql = "insert into " +
            "mitarbeiter(personId, username, password) " +
            "values('%s', '%s', '%s')";

        sql = String.format(sql,
                mitarbeiter.getId(),
                mitarbeiter.getUsername(),
                mitarbeiter.getPassword());

        try {
            jdbc.execute(sql);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean createNewProduct(Product product) {
        String sql = "insert into " +
                "product(id, barcode, name, category) " +
                "values('%s', '%s', '%s', '%s')";

        sql = String.format(sql,
                product.getId(),
                product.getBarcodeId(),
                product.getName(),
                product.getCategory());

        try {
            jdbc.execute(sql);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean createNewTransaktion(Transaktion transaktion) {
        String sql = "insert into " +
                "transaktions(transaktionId, productId, type, amount, date, cost, kundeId, mitarbeiterId) " +
                "values('%s', '%s', '%s', '%d', '%s', '%d', '%s', '%s')";

        sql = String.format(sql,
                transaktion.getId(),
                transaktion.getProduct().getId(),
                transaktion.getType(),
                transaktion.getAmount(),
                dateFormat.format(transaktion.getDate()),
                transaktion.getCost(),
                transaktion.getKunde().getId(),
                transaktion.getMitarbeiter().getId());

        try {
            jdbc.execute(sql);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /*
        UPDATE FUNCTIONS
     */

    public boolean updatePerson(Person person) {
        String sql = "update person set " +
                "name = '%s'," +
                "telefonNo = '%s'," +
                "adresse = '%s'," +
                "email = '%s'" +
                "where personId = '%s'";

        sql = String.format(sql,
                person.getName(),
                person.getPhoneNo(),
                person.getAddress(),
                person.getEmail(),
                person.getId());

        try {
            jdbc.execute(sql);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean updateMitarbeiter(Mitarbeiter mitarbeiter) {
        String sql = "update mitarbeiter set " +
                "username = '%s'," +
                "password = '%s'," +
                "where personId = '%s'";

        sql = String.format(sql,
                mitarbeiter.getUsername(),
                mitarbeiter.getPassword(),
                mitarbeiter.getId()
        );

        try {
            jdbc.execute(sql);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean updateProduct(Product product) {
        String sql = "update product set " +
                "barcode = '%s'," +
                "name = '%s'," +
                "category = '%s" +
                "where id = '%s'";

        sql = String.format(sql,
                product.getBarcodeId(),
                product.getName(),
                product.getCategory(),
                product.getId()
        );

        try {
            jdbc.execute(sql);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean updateTransaktion(Transaktion transaktion) {
        String sql = "update transaktions set " +
                "productId = '%s', " +
                "type = '%s', " +
                "amount = '%d', " +
                "date = '%s', " +
                "cost = '%d'," +
                "kundeId = '%s', " +
                "mitarbeiterId = '%s' " +
                "where transaktionId = '%s'";

        sql = String.format(sql,
                transaktion.getProduct().getId(),
                transaktion.getType(),
                transaktion.getAmount(),
                transaktion.getDate(),
                transaktion.getCost(),
                transaktion.getKunde().getId(),
                transaktion.getMitarbeiter().getId(),
                transaktion.getId());

        try {
            jdbc.execute(sql);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

    }

    public boolean updateLager(Lager lager) {

        Lager sqlager = getLager();

        List<LagerProduct> updateProducts = new ArrayList<>();
        List<LagerProduct> insertProducts = new ArrayList<>();
        List<LagerProduct> deleteProducts = new ArrayList<>();

        for (LagerProduct product : lager.lagerProducts()) {

            if (sqlager.lagerProducts()
                    .stream()
                    .anyMatch(lp -> lp.getId().equals(product.getId()))) {
                updateProducts.add(product);
                continue;
            }
            insertProducts.add(product);
        }

        for (LagerProduct product : sqlager.lagerProducts()) {
            if (!lager.lagerProducts()
                    .stream()
                    .anyMatch(p -> p.getId().equals(product.getId()))) {
                deleteProducts.add(product);
            }
        }

        final String updateSql = "update lager set " +
                "productId = '%s', " +
                "date = '%s', " +
                "amount = '%d', " +
                "cost = '%d' " +
                "where id = '%s'";

        final String insertSql = "insert into " +
                "lager(id, productId, date, amount, cost) " +
                "values('%s', '%s', '%s', '%d', '%d')";

        final String deleteSql = "delete from lager " +
                "where id = '%s'";

        for (LagerProduct product: updateProducts) {
            String sql = String.format(updateSql,
                    product.getProduct().getId(),
                    dateFormat.format(product.getDate()),
                    product.getAmount(),
                    product.getCost(),
                    product.getId());

            try {
                jdbc.execute(sql);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return false;
            }

        }

        for (LagerProduct product: insertProducts) {
            String sql = String.format(insertSql,
                    product.getId(),
                    product.getProduct().getId(),
                    dateFormat.format(product.getDate()),
                    product.getAmount(),
                    product.getCost()
            );

            try {
                jdbc.execute(sql);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return false;
            }
        }

        for (LagerProduct product: deleteProducts) {
            String sql = String.format(deleteSql,
                    product.getId());

            try {
                jdbc.execute(sql);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return false;
            }
        }

        return true;

    }

    /*
        DELETE FUNCTIONS
     */

    public boolean deletePerson(Person person) {
        String sql = "delete from person " +
                "where personId = '%s'";
        sql = String.format(sql, person.getId());

        try {
            jdbc.execute(sql);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean deleteMitarbeiter(Mitarbeiter mitarbeiter) {
        String sql = "delete from mitarbeiter " +
                "where personId = '%s'";
        sql = String.format(sql, mitarbeiter.getId());

        try {
            jdbc.execute(sql);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean deleteProduct(Product product) {
        String sql = "delete from product " +
                "where id = '%s'";
        sql = String.format(sql, product.getId());

        try {
            jdbc.execute(sql);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean deleteTransaktion(Transaktion transaktion) {
        String sql = "delete from transaktions " +
                "where transaktionId = '%s'";
        sql = String.format(sql, transaktion.getId());

        try {
            jdbc.execute(sql);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

}
