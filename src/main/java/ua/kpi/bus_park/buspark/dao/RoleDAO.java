package ua.kpi.bus_park.buspark.dao;

import ua.kpi.bus_park.buspark.enteties.Role;

import java.sql.SQLException;
import java.util.List;

public interface RoleDAO {
    public Role findById(int id) throws SQLException;
    public List<Role> findAll() throws SQLException;
    public void update(Role role) throws SQLException;
    public boolean persist(Role role) throws SQLException;
    public void delete(Role role) throws SQLException;
}
