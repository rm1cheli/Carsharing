package com.example.carsharing.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "Car")
public class Car {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "brand")
    @NotNull
    private String brand;

    @ManyToOne
    @JoinColumn(name = "driver", referencedColumnName = "id")
    private Client driver;

    public Car() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        brand = brand;
    }

    public Client getClient() {
        return driver;
    }

    public void setClient(Client client) {
        this.driver = client;
    }

    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", client=" + driver +
                '}';
    }
}
