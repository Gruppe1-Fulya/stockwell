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
                .setId(rs.getInt("personId"))
                .setName(rs.getString("name"))
                .setPhoneNumber(rs.getString("telefonNo"))
                .setAdress(rs.getString("adresse"))
                .setEmail(rs.getString("email"))
                .doneBuild());
    }

    public boolean createNewPerson(Person person) {
        String sql = "insert into person(personId, name, telefonNo, adresse, email) values(" +
                person.getId() + ", '" +
                person.getName() + "', " +
                person.getPhoneNo() + ", '" +
                person.getAddress() + "', '" +
                person.getEmail() + "')";

        try {
            jdbc.execute(sql);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

}
