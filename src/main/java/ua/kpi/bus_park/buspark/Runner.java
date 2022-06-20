package ua.kpi.bus_park.buspark;

import ua.kpi.bus_park.buspark.connection.PSQLConnector;
import ua.kpi.bus_park.buspark.dao.UserDAO;
import ua.kpi.bus_park.buspark.dao.impl.PSQLUserDAO;
import ua.kpi.bus_park.buspark.enteties.User;
import ua.kpi.bus_park.buspark.dao.UserRoleDAO;
import ua.kpi.bus_park.buspark.dao.impl.PSQLUserRoleDAO;
import ua.kpi.bus_park.buspark.enteties.UserRole;
import ua.kpi.bus_park.buspark.dao.BusDAO;
import ua.kpi.bus_park.buspark.dao.impl.PSQLBusDAO;
import ua.kpi.bus_park.buspark.enteties.Bus;
import ua.kpi.bus_park.buspark.dao.RouteDAO;
import ua.kpi.bus_park.buspark.dao.impl.PSQLRouteDAO;
import ua.kpi.bus_park.buspark.enteties.Route;
import ua.kpi.bus_park.buspark.dao.RoleDAO;
import ua.kpi.bus_park.buspark.dao.impl.PSQLRoleDAO;
import ua.kpi.bus_park.buspark.enteties.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Runner {
    public static void main(String[] args) throws SQLException {
        UserDAO userDAO = new PSQLUserDAO();
        RoleDAO roleDAO = new PSQLRoleDAO();
        UserRoleDAO userroleDAO = new PSQLUserRoleDAO();
        BusDAO busDAO = new PSQLBusDAO();
        RouteDAO routeDAO = new PSQLRouteDAO();

        System.out.println("------------------------User----------------------------");
        System.out.println(userDAO.findAll());
        System.out.println("========================================================");
        User user = new User("Pavel", "parol12345", "Busy");
        System.out.println(userDAO.persist(user));
        System.out.println(userDAO.findAll());
        System.out.println("========================================================");
        user.setPassword("new-parol");
        userDAO.update(user);
        System.out.println(userDAO.findById(user.getUserId()));
        System.out.println(userDAO.findById(10));
        System.out.println("========================================================");
        userDAO.delete(user);
        System.out.println(userDAO.findAll());

        System.out.println("------------------------Role----------------------------");
        System.out.println(roleDAO.findAll());
        System.out.println("========================================================");
        Role role = new Role("driver");
        System.out.println(roleDAO.persist(role));
        System.out.println(roleDAO.findAll());
        System.out.println("========================================================");
        role.setRolename("admin");
        roleDAO.update(role);
        System.out.println(roleDAO.findById(role.getRoleId()));
        System.out.println(roleDAO.findById(10));
        System.out.println("========================================================");
        roleDAO.delete(role);
        System.out.println(roleDAO.findAll());

        System.out.println("-----------------------User Role------------------------");

        System.out.println(userroleDAO.findAll());
        System.out.println("========================================================");
        UserRole userrole = new UserRole(27, 7);
        System.out.println(userroleDAO.persist(userrole));
        System.out.println(userroleDAO.findAll());
        System.out.println("========================================================");
        userrole.setIdrole(8);
        userroleDAO.update(userrole);
        System.out.println(userroleDAO.findById(userrole.getUserroleId()));
        System.out.println(userroleDAO.findById(10));
        System.out.println("========================================================");
        userroleDAO.delete(userrole);
        System.out.println(userroleDAO.findAll());

        System.out.println("-------------------------Bus----------------------------");

        System.out.println(busDAO.findAll());
        System.out.println("========================================================");
        Bus bus = new Bus("Neoplan", 50, 27, "On route");
        System.out.println(busDAO.persist(bus));
        System.out.println(busDAO.findAll());
        System.out.println("========================================================");
        bus.setBusStatus("Free");
        busDAO.update(bus);
        System.out.println(busDAO.findById(bus.getBusId()));
        System.out.println(busDAO.findById(10));
        System.out.println("========================================================");
        busDAO.delete(bus);
        System.out.println(busDAO.findAll());

        System.out.println("-------------------------Route--------------------------");

        System.out.println(routeDAO.findAll());
        System.out.println("========================================================");
        Route route = new Route(45, 10.5, 8, 50);
        System.out.println(routeDAO.persist(route));
        System.out.println(routeDAO.findAll());
        System.out.println("========================================================");
        route.setRouteNumber(50);
        routeDAO.update(route);
        System.out.println(routeDAO.findById(route.getRouteId()));
        System.out.println(routeDAO.findById(2));
        System.out.println("========================================================");
        routeDAO.delete(route);
        System.out.println(routeDAO.findAll());
    }
}
