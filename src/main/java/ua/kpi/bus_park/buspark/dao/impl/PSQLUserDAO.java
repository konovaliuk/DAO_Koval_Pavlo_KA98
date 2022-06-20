package ua.kpi.bus_park.buspark.dao.impl;

import ua.kpi.bus_park.buspark.connection.PSQLConnector;
import ua.kpi.bus_park.buspark.dao.UserDAO;
import ua.kpi.bus_park.buspark.enteties.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PSQLUserDAO implements UserDAO {
    // COLUMNS
    private static final String COLUMN_ID = "user_id";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_PASSWORD =  "password";
    private static final String COLUMN_USERSTATUS =  "user_status";

    // REQUESTS
    private static final String SELECT = "SELECT * FROM public.\"Users\"";
    private static final String UPDATE = "UPDATE public.\"Users\" SET " + COLUMN_USERNAME + "=?, " + COLUMN_PASSWORD + "=?,"+ COLUMN_USERSTATUS + "=?"+ " WHERE " + COLUMN_ID + "=?";
    private static final String INSERT = "INSERT INTO public.\"Users\" (" + COLUMN_USERNAME + ","
            + COLUMN_PASSWORD + "," + COLUMN_USERSTATUS +") VALUES (?, ?, ?)";
    private static final String DELETE = "DELETE FROM public.\"Users\" WHERE " + COLUMN_ID + "=?";

    @Override
    public User findById(int userId) throws SQLException {
        Connection connection = PSQLConnector.getConnection();
        PreparedStatement statement = connection.prepareStatement(SELECT + " WHERE " + COLUMN_ID + "=" + userId);
        ResultSet rs = statement.executeQuery();
        User user = null;

        if (rs.next()) {
            user = new User(rs.getInt(COLUMN_ID), rs.getString(COLUMN_USERNAME), rs.getString(COLUMN_PASSWORD), rs.getString(COLUMN_USERSTATUS));
        }

        rs.close();
        statement.close();
        connection.close();

        return user;
    }

    @Override
    public List<User> findAll() throws SQLException {
        Connection connection = PSQLConnector.getConnection();
        PreparedStatement statement = connection.prepareStatement(SELECT);
        ResultSet rs = statement.executeQuery();
        List<User> users = new ArrayList<>();

        while (rs.next()) {
            users.add(new User(rs.getInt(COLUMN_ID), rs.getString(COLUMN_USERNAME), rs.getString(COLUMN_PASSWORD), rs.getString(COLUMN_USERSTATUS)));
        }

        rs.close();
        statement.close();
        connection.close();

        return users;
    }

    @Override
    public void update(User user) throws SQLException {
        Connection connection = PSQLConnector.getConnection();
        PreparedStatement statement = connection.prepareStatement(UPDATE);

        statement.setString(1, user.getUsername());
        statement.setString(2, user.getPassword());
        statement.setString(3, user.getUserStatus());
        statement.setInt(4, user.getUserId());

        statement.executeUpdate();
        statement.close();
        connection.close();
    }

    @Override
    public boolean persist(User user) throws SQLException {
        Connection connection = PSQLConnector.getConnection();
        PreparedStatement statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);

        statement.setString(1, user.getUsername());
        statement.setString(2, user.getPassword());
        statement.setString(3, user.getUserStatus());

        if (statement.executeUpdate() == 1) {
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                user.setUserId(rs.getInt(COLUMN_ID));
            }
            rs.close();
        }

        statement.close();
        connection.close();

        return user.getUserId() != null;
    }

    @Override
    public void delete(User user) throws SQLException {
        Connection connection = PSQLConnector.getConnection();
        PreparedStatement statement = connection.prepareStatement(DELETE);

        statement.setInt(1, user.getUserId());
        statement.executeUpdate();

        statement.close();
        connection.close();
    }
}
