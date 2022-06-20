package ua.kpi.bus_park.buspark.dao;

import ua.kpi.bus_park.buspark.enteties.Route;

import java.sql.SQLException;
import java.util.List;

public interface RouteDAO {
    public Route findById(int id) throws SQLException;
    public List<Route> findAll() throws SQLException;
    public void update(Route route) throws SQLException;
    public boolean persist(Route route) throws SQLException;
    public void delete(Route route) throws SQLException;
}
