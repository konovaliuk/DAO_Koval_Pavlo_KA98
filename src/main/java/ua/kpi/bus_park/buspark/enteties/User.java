package ua.kpi.bus_park.buspark.enteties;

import java.util.Objects;

public class User {
    private Integer userId;
    private String username;
    private String password;
    private String userStatus;

    public User(int userId, String username, String password, String userStatus) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.userStatus = userStatus;
    }

    public User(String username, String password, String userStatus) {
        this.username = username;
        this.password = password;
        this.userStatus = userStatus;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", userStatus='" + userStatus + '\'' +
                '}';
    }
}
