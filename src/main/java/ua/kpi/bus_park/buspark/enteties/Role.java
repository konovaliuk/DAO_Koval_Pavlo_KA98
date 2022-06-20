package ua.kpi.bus_park.buspark.enteties;

import java.util.Objects;

public class Role {
    private Integer roleId;
    private String rolename;

    public Role(int roleId, String rolename) {
        this.roleId = roleId;
        this.rolename = rolename;
    }

    public Role(String rolename) {
        this.rolename = rolename;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(roleId, role.roleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId);
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleId=" + roleId +
                ", rolename='" + rolename + '\'' +
                '}';
    }
}
