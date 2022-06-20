package ua.kpi.bus_park.buspark.enteties;

import java.util.Objects;

public class Route {

    private Integer routeId;
    private Integer routeNumber;
    private Double routeLength;
    private Integer Idbus;
    private Integer routeTime;

    public Route(int routeId, Integer routeNumber, Double routeLength, Integer Idbus, Integer routeTime) {
        this.routeId = routeId;
        this.routeNumber = routeNumber;
        this.routeLength = routeLength;
        this.Idbus = Idbus;
        this.routeTime = routeTime;

    }

    public Route(Integer routeNumber, Double routeLength, Integer Idbus, Integer routeTime) {
        this.routeNumber = routeNumber;
        this.routeLength = routeLength;
        this.Idbus = Idbus;
        this.routeTime = routeTime;
    }

    public Integer getRouteId() {
        return routeId;
    }

    public void setRouteId(Integer routeId) {
        this.routeId = routeId;
    }

    public Integer getRouteNumber() {
        return routeNumber;
    }

    public void setRouteNumber(Integer routeNumber) {
        this.routeNumber = routeNumber;
    }

    public Double getRouteLength() {
        return routeLength;
    }

    public void setRouteLength(Double routeLength) {
        this.routeLength = routeLength;
    }

    public Integer getIdbus() {
        return Idbus;
    }

    public void setIdbus(Integer idbus) {
        Idbus = idbus;
    }

    public Integer getRouteTime() {
        return routeTime;
    }

    public void setRouteTime(Integer routeTime) {
        this.routeTime = routeTime;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Route route = (Route) o;
        return Objects.equals(routeId, route.routeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(routeId);
    }

    @Override
    public String toString() {
        return "Route{" +
                "routeId=" + routeId +
                ", routeNumber='" + routeNumber + '\'' +
                ", routeLength='" + routeLength + '\'' +
                ", Idbus='" + Idbus + '\'' +
                ", routeTime='" + routeTime+ '\'' +
                '}';
    }
}