package ua.kpi.bus_park.buspark.dao.impl;

import ua.kpi.bus_park.buspark.connection.PSQLConnector;
import ua.kpi.bus_park.buspark.dao.RouteDAO;
import ua.kpi.bus_park.buspark.enteties.Route;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PSQLRouteDAO implements RouteDAO {
    // COLUMNS
    private static final String COLUMN_IDROUTE = "route_id";
    private static final String COLUMN_ROUTENUMBER = "route_number";
    private static final String COLUMN_ROUTELENGTH =  "route_length";
    private static final String COLUMN_BUSID = "id_bus";
    private static final String COLUMN_ROUTETIME =  "route_time";

    // REQUESTS
    private static final String SELECT = "SELECT * FROM public.\"Routes\"";
    private static final String UPDATE = "UPDATE public.\"Routes\" SET " + COLUMN_ROUTENUMBER + "=?, " + COLUMN_ROUTELENGTH + "=?, "
            + COLUMN_BUSID + "=?, " + COLUMN_ROUTETIME + "=?" + " WHERE " + COLUMN_IDROUTE + "=?";
    private static final String INSERT = "INSERT INTO public.\"Routes\" (" + COLUMN_ROUTENUMBER + ","
            + COLUMN_ROUTELENGTH + "," + COLUMN_BUSID + "," + COLUMN_ROUTETIME + ") VALUES (?, ?, ?, ?)";
    private static final String DELETE = "DELETE FROM public.\"Routes\" WHERE " + COLUMN_IDROUTE + "=?";

    @Override
    public Route findById(int routeId) throws SQLException {
        Connection connection = PSQLConnector.getConnection();
        PreparedStatement statement = connection.prepareStatement(SELECT + " WHERE " + COLUMN_IDROUTE + "=" + routeId);
        ResultSet rs = statement.executeQuery();
        Route route = null;

        if (rs.next()) {
            route = new Route(rs.getInt(COLUMN_IDROUTE), rs.getInt(COLUMN_ROUTENUMBER), rs.getDouble(COLUMN_ROUTELENGTH),
                    rs.getInt(COLUMN_BUSID), rs.getInt(COLUMN_ROUTETIME));
        }

        rs.close();
        statement.close();
        connection.close();

        return route;
    }

    @Override
    public List<Route> findAll() throws SQLException {
        Connection connection = PSQLConnector.getConnection();
        PreparedStatement statement = connection.prepareStatement(SELECT);
        ResultSet rs = statement.executeQuery();
        List<Route> routes = new ArrayList<>();

        while (rs.next()) {
            routes.add(new Route(rs.getInt(COLUMN_IDROUTE), rs.getInt(COLUMN_ROUTENUMBER), rs.getDouble(COLUMN_ROUTELENGTH),
                    rs.getInt(COLUMN_BUSID), rs.getInt(COLUMN_ROUTETIME)));
        }

        rs.close();
        statement.close();
        connection.close();

        return routes;
    }

    @Override
    public void update(Route route) throws SQLException {
        Connection connection = PSQLConnector.getConnection();
        PreparedStatement statement = connection.prepareStatement(UPDATE);

        statement.setInt(1, route.getRouteNumber());
        statement.setDouble(2, route.getRouteLength());
        statement.setInt(3, route.getIdbus());
        statement.setInt(4, route.getRouteTime());
        statement.setInt(5, route.getRouteId());

        statement.executeUpdate();
        statement.close();
        connection.close();
    }

    @Override
    public boolean persist(Route route) throws SQLException {
        Connection connection = PSQLConnector.getConnection();
        PreparedStatement statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);

        statement.setInt(1, route.getRouteNumber());
        statement.setDouble(2, route.getRouteLength());
        statement.setInt(3, route.getIdbus());
        statement.setInt(4, route.getRouteTime());

        if (statement.executeUpdate() == 1) {
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                route.setRouteId(rs.getInt(COLUMN_IDROUTE));
            }
            rs.close();
        }

        statement.close();
        connection.close();

        return route.getRouteId() != null;
    }

    @Override
    public void delete(Route route) throws SQLException {
        Connection connection = PSQLConnector.getConnection();
        PreparedStatement statement = connection.prepareStatement(DELETE);

        statement.setInt(1, route.getRouteId());
        statement.executeUpdate();

        statement.close();
        connection.close();
    }
}
