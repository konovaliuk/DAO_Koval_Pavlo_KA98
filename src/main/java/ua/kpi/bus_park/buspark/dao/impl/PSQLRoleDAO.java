package ua.kpi.bus_park.buspark.dao.impl;

import ua.kpi.bus_park.buspark.connection.PSQLConnector;
import ua.kpi.bus_park.buspark.dao.RoleDAO;
import ua.kpi.bus_park.buspark.enteties.Role;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PSQLRoleDAO implements RoleDAO {
    // COLUMNS
    private static final String COLUMN_IDROLE = "role_id";
    private static final String COLUMN_ROLENAME = "rolename";

    // REQUESTS
    private static final String SELECT = "SELECT * FROM public.\"Roles\"";
    private static final String UPDATE = "UPDATE public.\"Roles\" SET " + COLUMN_ROLENAME + "=?" + " WHERE " + COLUMN_IDROLE + "=?";
    private static final String INSERT = "INSERT INTO public.\"Roles\" (" + COLUMN_ROLENAME + ") VALUES (?)";
    private static final String DELETE = "DELETE FROM public.\"Roles\" WHERE " + COLUMN_IDROLE + "=?";

    @Override
    public Role findById(int roleId) throws SQLException {
        Connection connection = PSQLConnector.getConnection();
        PreparedStatement statement = connection.prepareStatement(SELECT + " WHERE " + COLUMN_IDROLE + "=" + roleId);
        ResultSet rs = statement.executeQuery();
        Role role = null;

        if (rs.next()) {
            role = new Role(rs.getInt(COLUMN_IDROLE), rs.getString(COLUMN_ROLENAME));
        }

        rs.close();
        statement.close();
        connection.close();

        return role;
    }

    @Override
    public List<Role> findAll() throws SQLException {
        Connection connection = PSQLConnector.getConnection();
        PreparedStatement statement = connection.prepareStatement(SELECT);
        ResultSet rs = statement.executeQuery();
        List<Role> roles = new ArrayList<>();

        while (rs.next()) {
            roles.add(new Role(rs.getInt(COLUMN_IDROLE), rs.getString(COLUMN_ROLENAME)));
        }

        rs.close();
        statement.close();
        connection.close();

        return roles;
    }

    @Override
    public void update(Role role) throws SQLException {
        Connection connection = PSQLConnector.getConnection();
        PreparedStatement statement = connection.prepareStatement(UPDATE);

        statement.setString(1, role.getRolename());
        statement.setInt(2, role.getRoleId());

        statement.executeUpdate();
        statement.close();
        connection.close();
    }

    @Override
    public boolean persist(Role role) throws SQLException {
        Connection connection = PSQLConnector.getConnection();
        PreparedStatement statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);

        statement.setString(1, role.getRolename());

        if (statement.executeUpdate() == 1) {
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                role.setRoleId(rs.getInt(COLUMN_IDROLE));
            }
            rs.close();
        }

        statement.close();
        connection.close();

        return role.getRoleId() != null;
    }

    @Override
    public void delete(Role role) throws SQLException {
        Connection connection = PSQLConnector.getConnection();
        PreparedStatement statement = connection.prepareStatement(DELETE);

        statement.setInt(1, role.getRoleId());
        statement.executeUpdate();

        statement.close();
        connection.close();
    }
}
