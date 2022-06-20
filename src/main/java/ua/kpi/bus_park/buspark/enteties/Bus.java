package ua.kpi.bus_park.buspark.enteties;


import java.util.Objects;

public class Bus {
    private Integer busId;
    private String busname;
    private Integer numberSeats;
    private Integer driverId;
    private String busStatus;


    public Bus(int busId, String busname, Integer numberSeats, Integer driverId, String busStatus) {
        this.busId = busId;
        this.busname = busname;
        this.numberSeats = numberSeats;
        this.driverId = driverId;
        this.busStatus = busStatus;
    }

    public Bus(String busname, Integer numberSeats, Integer driverId, String busStatus) {
        this.busname = busname;
        this.numberSeats = numberSeats;
        this.driverId = driverId;
        this.busStatus = busStatus;
    }

    public Integer getBusId() {
        return busId;
    }

    public void setBusId(Integer busId) {
        this.busId = busId;
    }

    public String getBusname() {
        return busname;
    }

    public void setBusname(String busname) {
        this.busname = busname;
    }

    public Integer getNumberSeats() {
        return numberSeats;
    }

    public void setNumberSeats(Integer numberSeats) {
        this.numberSeats = numberSeats;
    }

    public Integer getDriverId() {
        return driverId;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }

    public String getBusStatus() {
        return busStatus;
    }

    public void setBusStatus(String busStatus) {
        this.busStatus = busStatus;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bus bus = (Bus) o;
        return Objects.equals(busId, bus.busId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(busId);
    }

    @Override
    public String toString() {
        return "Bus{" +
                "busId=" + busId +
                ", busname='" + busname + '\'' +
                ", numberSeats='" + numberSeats + '\'' +
                ", driverId='" + driverId + '\'' +
                ", busStatus='" + busStatus + '\'' +
                '}';
    }
}
