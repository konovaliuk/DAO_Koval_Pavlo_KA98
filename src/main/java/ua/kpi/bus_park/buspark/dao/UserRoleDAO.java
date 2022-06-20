package ua.kpi.bus_park.buspark.dao;

import ua.kpi.bus_park.buspark.enteties.UserRole;

import java.sql.SQLException;
import java.util.List;

public interface UserRoleDAO {
    public UserRole findById(int id) throws SQLException;
    public List<UserRole> findAll() throws SQLException;
    public void update(UserRole userrole) throws SQLException;
    public boolean persist(UserRole userrole) throws SQLException;
    public void delete(UserRole userrole) throws SQLException;
}