package ua.kpi.bus_park.buspark.dao;

import ua.kpi.bus_park.buspark.enteties.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {
    public User findById(int id) throws SQLException;
    public List<User> findAll() throws SQLException;
    public void update(User user) throws SQLException;
    public boolean persist(User user) throws SQLException;
    public void delete(User user) throws SQLException;
}