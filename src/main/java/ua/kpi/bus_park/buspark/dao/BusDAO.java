package ua.kpi.bus_park.buspark.dao;

import ua.kpi.bus_park.buspark.enteties.Bus;

import java.sql.SQLException;
import java.util.List;

public interface BusDAO {
    public Bus findById(int id) throws SQLException;
    public List<Bus> findAll() throws SQLException;
    public void update(Bus bus) throws SQLException;
    public boolean persist(Bus bus) throws SQLException;
    public void delete(Bus bus) throws SQLException;
}
