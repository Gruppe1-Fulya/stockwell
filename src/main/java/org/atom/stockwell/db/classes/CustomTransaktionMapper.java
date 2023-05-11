package org.atom.stockwell.db.classes;

import org.atom.stockwell.db.builders.TransaktionBuilder;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class CustomTransaktionMapper implements RowMapper<Transaktion> {

    List<Product> productList;
    SimpleDateFormat dateFormat;

    public CustomTransaktionMapper(List<Product> products) {
        productList = products;
        dateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
    }

    @Override
    public Transaktion mapRow(ResultSet rs, int rowNum) throws SQLException {

        TransaktionBuilder transaktionBuilder = new TransaktionBuilder();

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
                    .doneBuild();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
