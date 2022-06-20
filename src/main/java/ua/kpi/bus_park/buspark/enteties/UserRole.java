package ua.kpi.bus_park.buspark.enteties;
import java.util.Objects;

public class UserRole {

    private Integer userroleId;
    private Integer Iduser;
    private Integer Idrole;

    public UserRole(int userroleId, Integer Iduser, Integer Idrole) {
        this.userroleId = userroleId;
        this.Iduser = Iduser;
        this.Idrole = Idrole;
    }

    public UserRole(Integer Iduser, Integer Idrole) {
        this.Iduser = Iduser;
        this.Idrole = Idrole;
    }

    public Integer getUserroleId() {
        return userroleId;
    }
    public void setUserroleId(Integer userroleId) {
        this.userroleId = userroleId;
    }

    public Integer getIduser() {
        return Iduser;
    }

    public void setIduser(Integer iduser) {
        Iduser = iduser;
    }

    public Integer getIdrole() {
        return Idrole;
    }

    public void setIdrole(Integer idrole) {
        Idrole = idrole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRole userrole = (UserRole) o;
        return Objects.equals(userroleId, userrole.userroleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userroleId);
    }

    @Override
    public String toString() {
        return "User{" +
                "userroleId=" + userroleId +
                ", Iduser='" + Iduser + '\'' +
                ", Idrole='" + Idrole + '\'' +
                '}';
    }
}
