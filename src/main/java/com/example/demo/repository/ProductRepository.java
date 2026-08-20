package com.example.demo.repository;

import com.example.demo.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository {

    private final Connection connection;

    public ProductRepository(Connection connection) {
        this.connection = connection;
    }

    public Product findById(String id) throws Exception {
        String sql = """
                SELECT id, name, description, unit_price
                FROM product
                WHERE id = ?
                """;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
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

        try (PreparedStatement statement = connection.prepareStatement(sql);
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