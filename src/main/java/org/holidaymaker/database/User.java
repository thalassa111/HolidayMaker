package org.holidaymaker.database;

public class User {
    private int id;
    private String name;
    private String type;
    private String email;

    public User(int id, String name, String type, String email) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.email = email;
    }

    public int id() {
        return id;
    }

    public String name() {
        return name;
    }

    public String type() {
        return type;
    }

    public String email() {
        return email;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name +
                ", type='" + type +
                ", email='" + email +
                '}';
    }
}
