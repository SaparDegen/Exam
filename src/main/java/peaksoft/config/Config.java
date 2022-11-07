package peaksoft.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Config {
    private final String login = "postgres";
    private final String password = "1234";
    private final String url = "jdbc:postgresql://localhost:5432/java7";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, login, password);
    }
}
