package ua.kpi.bus_park.buspark.connection;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class PSQLConnector {
    private static BasicDataSource dataSource;

    public PSQLConnector() {}

    public static void initDataSource() {
        dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:postgresql://localhost:5432/postgres");
        dataSource.setUsername("postgres");
        dataSource.setPassword("ab230298cd");
        dataSource.setMaxTotal(5);
    }

    public static Connection getConnection() throws SQLException {
        if (dataSource == null) {
            initDataSource();
        }
        return dataSource.getConnection();
    }
}
