package ua.kpi.bus_park.buspark.dao.impl;

import ua.kpi.bus_park.buspark.connection.PSQLConnector;
import ua.kpi.bus_park.buspark.dao.BusDAO;
import ua.kpi.bus_park.buspark.enteties.Bus;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PSQLBusDAO implements BusDAO {
    // COLUMNS
    private static final String COLUMN_IDBUS = "bus_id";
    private static final String COLUMN_BUSNAME = "busname";
    private static final String COLUMN_NUMBERSEATS =  "number_of_seats";
    private static final String COLUMN_DRIVERID = "driver_id";
    private static final String COLUMN_BUSSTATUS =  "bus_status";

    // REQUESTS
    private static final String SELECT = "SELECT * FROM public.\"Buses\"";
    private static final String UPDATE = "UPDATE public.\"Buses\" SET " + COLUMN_BUSNAME + "=?, " + COLUMN_NUMBERSEATS + "=?, "
            + COLUMN_DRIVERID + "=?, " + COLUMN_BUSSTATUS + "=?" + " WHERE " + COLUMN_IDBUS + "=?";
    private static final String INSERT = "INSERT INTO public.\"Buses\" (" + COLUMN_BUSNAME + ","
            + COLUMN_NUMBERSEATS + "," + COLUMN_DRIVERID + "," + COLUMN_BUSSTATUS + ") VALUES (?, ?, ?, ?)";
    private static final String DELETE = "DELETE FROM public.\"Buses\" WHERE " + COLUMN_IDBUS + "=?";

    @Override
    public Bus findById(int busId) throws SQLException {
        Connection connection = PSQLConnector.getConnection();
        PreparedStatement statement = connection.prepareStatement(SELECT + " WHERE " + COLUMN_IDBUS + "=" + busId);
        ResultSet rs = statement.executeQuery();
        Bus bus = null;

        if (rs.next()) {
            bus = new Bus(rs.getInt(COLUMN_IDBUS), rs.getString(COLUMN_BUSNAME), rs.getInt(COLUMN_NUMBERSEATS),
                    rs.getInt(COLUMN_DRIVERID), rs.getString(COLUMN_BUSSTATUS));
        }

        rs.close();
        statement.close();
        connection.close();

        return bus;
    }

    @Override
    public List<Bus> findAll() throws SQLException {
        Connection connection = PSQLConnector.getConnection();
        PreparedStatement statement = connection.prepareStatement(SELECT);
        ResultSet rs = statement.executeQuery();
        List<Bus> buses = new ArrayList<>();

        while (rs.next()) {
            buses.add(new Bus(rs.getInt(COLUMN_IDBUS), rs.getString(COLUMN_BUSNAME), rs.getInt(COLUMN_NUMBERSEATS),
                    rs.getInt(COLUMN_DRIVERID), rs.getString(COLUMN_BUSSTATUS)));
        }

        rs.close();
        statement.close();
        connection.close();

        return buses;
    }

    @Override
    public void update(Bus bus) throws SQLException {
        Connection connection = PSQLConnector.getConnection();
        PreparedStatement statement = connection.prepareStatement(UPDATE);

        statement.setString(1, bus.getBusname());
        statement.setInt(2, bus.getNumberSeats());
        statement.setInt(3, bus.getDriverId());
        statement.setString(4, bus.getBusStatus());
        statement.setInt(5, bus.getBusId());

        statement.executeUpdate();
        statement.close();
        connection.close();
    }

    @Override
    public boolean persist(Bus bus) throws SQLException {
        Connection connection = PSQLConnector.getConnection();
        PreparedStatement statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);

        statement.setString(1, bus.getBusname());
        statement.setInt(2, bus.getNumberSeats());
        statement.setInt(3, bus.getDriverId());
        statement.setString(4, bus.getBusStatus());

        if (statement.executeUpdate() == 1) {
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                bus.setBusId(rs.getInt(COLUMN_IDBUS));
            }
            rs.close();
        }

        statement.close();
        connection.close();

        return bus.getBusId() != null;
    }

    @Override
    public void delete(Bus bus) throws SQLException {
        Connection connection = PSQLConnector.getConnection();
        PreparedStatement statement = connection.prepareStatement(DELETE);

        statement.setInt(1, bus.getBusId());
        statement.executeUpdate();

        statement.close();
        connection.close();
    }
}
