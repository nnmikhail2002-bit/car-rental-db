package ru.misha.repository;

import ru.misha.config.DatabaseConnection;
import ru.misha.model.Car;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarRepository {

    public List<Car> findAll() {

        String sql = """
                SELECT
                    id,
                    category_id,
                    brand,
                    model,
                    production_year,
                    registration_number,
                    vin,
                    status,
                    mileage
                FROM cars
                ORDER BY id
                """;

        List<Car> cars = new ArrayList<>();

        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()
        ) {

            while (resultSet.next()) {

                Car car = new Car(
                        resultSet.getLong("id"),
                        resultSet.getLong("category_id"),
                        resultSet.getString("brand"),
                        resultSet.getString("model"),
                        resultSet.getInt("production_year"),
                        resultSet.getString("registration_number"),
                        resultSet.getString("vin"),
                        resultSet.getString("status"),
                        resultSet.getInt("mileage")
                );

                cars.add(car);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при получении автомобилей", e);
        }

        return cars;
    }

    public Car findById(long id) {

        String sql = """
            SELECT
                id,
                category_id,
                brand,
                model,
                production_year,
                registration_number,
                vin,
                status,
                mileage
            FROM cars
            WHERE id = ?
            """;

        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setLong(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {
                    return new Car(
                            resultSet.getLong("id"),
                            resultSet.getLong("category_id"),
                            resultSet.getString("brand"),
                            resultSet.getString("model"),
                            resultSet.getInt("production_year"),
                            resultSet.getString("registration_number"),
                            resultSet.getString("vin"),
                            resultSet.getString("status"),
                            resultSet.getInt("mileage")
                    );
                }

                return null;
            }

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при поиске автомобиля", e);
        }
    }

    public long add(Car car) {

        String sql = """
            INSERT INTO cars (
                category_id,
                brand,
                model,
                production_year,
                registration_number,
                vin,
                status,
                mileage
            )
            VALUES (?, ?, ?, ?, ?, ?, ?, ?)
            """;

        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        sql,
                        java.sql.Statement.RETURN_GENERATED_KEYS
                )
        ) {

            statement.setLong(1, car.categoryId());
            statement.setString(2, car.brand());
            statement.setString(3, car.model());
            statement.setInt(4, car.productionYear());
            statement.setString(5, car.registrationNumber());
            statement.setString(6, car.vin());
            statement.setString(7, car.status());
            statement.setInt(8, car.mileage());

            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {

                if (generatedKeys.next()) {
                    return generatedKeys.getLong(1);
                }

                throw new RuntimeException(
                        "PostgreSQL не вернул id созданного автомобиля"
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException(
                    "Ошибка при добавлении автомобиля",
                    e
            );
        }
    }
    public boolean update(Car car) {

        String sql = """
            UPDATE cars
            SET category_id = ?,
                brand = ?,
                model = ?,
                production_year = ?,
                registration_number = ?,
                vin = ?,
                status = ?,
                mileage = ?
            WHERE id = ?
            """;

        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setLong(1, car.categoryId());
            statement.setString(2, car.brand());
            statement.setString(3, car.model());
            statement.setInt(4, car.productionYear());
            statement.setString(5, car.registrationNumber());
            statement.setString(6, car.vin());
            statement.setString(7, car.status());
            statement.setInt(8, car.mileage());
            statement.setLong(9, car.id());

            int updatedRows = statement.executeUpdate();

            return updatedRows > 0;

        } catch (SQLException e) {
            throw new RuntimeException(
                    "Ошибка при обновлении автомобиля",
                    e
            );
        }
    }
    public boolean deleteById(long id) {

        String sql = """
            DELETE FROM cars
            WHERE id = ?
            """;

        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setLong(1, id);

            int deletedRows = statement.executeUpdate();

            return deletedRows > 0;

        } catch (SQLException e) {
            throw new RuntimeException(
                    "Ошибка при удалении автомобиля",
                    e
            );
        }
    }
}