package ua.kpi.bus_park.buspark.dao.impl;

import ua.kpi.bus_park.buspark.connection.PSQLConnector;
import ua.kpi.bus_park.buspark.dao.UserRoleDAO;
import ua.kpi.bus_park.buspark.enteties.UserRole;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PSQLUserRoleDAO implements UserRoleDAO {
    // COLUMNS
    private static final String COLUMN_IDUSERROLE = "userrole_id";
    private static final String COLUMN_IDUSER = "id_user";
    private static final String COLUMN_ROLEID =  "id_role";


    // REQUESTS
    private static final String SELECT = "SELECT * FROM public.\"UserRole\"";
    private static final String UPDATE = "UPDATE public.\"UserRole\" SET " + COLUMN_IDUSER + "=?, " + COLUMN_ROLEID + "=?" + " WHERE " + COLUMN_IDUSERROLE + "=?";
    private static final String INSERT = "INSERT INTO public.\"UserRole\" (" + COLUMN_IDUSER + ","
            + COLUMN_ROLEID +  ") VALUES (?, ?)";
    private static final String DELETE = "DELETE FROM public.\"UserRole\" WHERE " + COLUMN_IDUSERROLE + "=?";

    @Override
    public UserRole findById(int userroleId) throws SQLException {
        Connection connection = PSQLConnector.getConnection();
        PreparedStatement statement = connection.prepareStatement(SELECT + " WHERE " + COLUMN_IDUSERROLE + "=" + userroleId);
        ResultSet rs = statement.executeQuery();
        UserRole userrole = null;

        if (rs.next()) {
            userrole = new UserRole(rs.getInt(COLUMN_IDUSERROLE), rs.getInt(COLUMN_IDUSER), rs.getInt(COLUMN_ROLEID));
        }

        rs.close();
        statement.close();
        connection.close();

        return userrole;
    }

    @Override
    public List<UserRole> findAll() throws SQLException {
        Connection connection = PSQLConnector.getConnection();
        PreparedStatement statement = connection.prepareStatement(SELECT);
        ResultSet rs = statement.executeQuery();
        List<UserRole> userrole = new ArrayList<>();

        while (rs.next()) {
            userrole.add(new UserRole(rs.getInt(COLUMN_IDUSERROLE), rs.getInt(COLUMN_IDUSER), rs.getInt(COLUMN_ROLEID)));
        }

        rs.close();
        statement.close();
        connection.close();

        return userrole;
    }

    @Override
    public void update(UserRole userrole) throws SQLException {
        Connection connection = PSQLConnector.getConnection();
        PreparedStatement statement = connection.prepareStatement(UPDATE);

        statement.setInt(1, userrole.getIduser());
        statement.setInt(2, userrole.getIdrole());
        statement.setInt(3, userrole.getUserroleId());

        statement.executeUpdate();
        statement.close();
        connection.close();
    }

    @Override
    public boolean persist(UserRole userrole) throws SQLException {
        Connection connection = PSQLConnector.getConnection();
        PreparedStatement statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);

        statement.setInt(1, userrole.getIduser());
        statement.setInt(2, userrole.getIdrole());

        if (statement.executeUpdate() == 1) {
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                userrole.setUserroleId(rs.getInt(COLUMN_IDUSERROLE));
            }
            rs.close();
        }

        statement.close();
        connection.close();

        return userrole.getUserroleId() != null;
    }

    @Override
    public void delete(UserRole userrole) throws SQLException {
        Connection connection = PSQLConnector.getConnection();
        PreparedStatement statement = connection.prepareStatement(DELETE);

        statement.setInt(1, userrole.getUserroleId());
        statement.executeUpdate();

        statement.close();
        connection.close();
    }
}