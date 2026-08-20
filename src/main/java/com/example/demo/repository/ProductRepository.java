package com.example.demo.repository;

import com.example.demo.model.Product;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepository {

    private final Environment environment;

    public ProductRepository(Environment environment) {
        this.environment = environment;
    }

    private Connection getConnection() throws Exception {
        String url = environment.getRequiredProperty("spring.datasource.url");
        String username = environment.getProperty("spring.datasource.username");
        String password = environment.getProperty("spring.datasource.password");

        return DriverManager.getConnection(url, username, password);
    }

    public Product findById(String id) throws Exception {
        String sql = """
                SELECT id, name, description, unit_price
                FROM product
                WHERE id = ?
                """;

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Product(
                            resultSet.getString("id"),
                            resultSet.getString("name"),
                            resultSet.getString("description"),
                            resultSet.getBigDecimal("unit_price")
                    );
                }
            }
        }

        return null;
    }

    public List<Product> findAll() throws Exception {
        String sql = """
                SELECT id, name, description, unit_price
                FROM product
                """;

        List<Product> products = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                products.add(new Product(
                        resultSet.getString("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getBigDecimal("unit_price")
                ));
            }
        }

        return products;
    }
}
