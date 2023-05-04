package org.atom.stockwell.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.stereotype.Repository;

import java.util.List;

public class DatabaseManager {

    @Autowired
    Environment environment;

    private JdbcTemplate jdbc;

    public DatabaseManager() {
        var dataSource = new SimpleDriverDataSource();
        dataSource.setDriver(new org.sqlite.JDBC());
        dataSource.setUrl("jdbc:sqlite:datas.db");
        jdbc = new JdbcTemplate(dataSource);
    }

    /*
        SELECT FUNCTIONS
     */

    public List<Mitarbeiter> getMitarbeiterList() {
        String sql = """
                select * from mitarbeiter
                """;
        PersonBuilder personBuilder = new PersonBuilder();
        MitarbeiterBuilder mitarbeiterBuilder = new MitarbeiterBuilder();
        return (List<Mitarbeiter>) jdbc.query(sql, (rs, rowNum) ->
            mitarbeiterBuilder
                    .startBuild()
                    .setPerson(
                        personBuilder.startBuild()
                            .setName("Test")
                            .doneBuild()
                    )
                    .setUsername(rs.getString("username")
                    ).setPassword(rs.getString("password")
                    ).doneBuild());
    }

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

        String sql = new String();

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

}
