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

}
