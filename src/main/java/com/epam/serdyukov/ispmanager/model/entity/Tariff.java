package com.epam.serdyukov.ispmanager.model.entity;

import java.util.Objects;

/**
 * @author Aleksey Serdyukov
 */
public class Tariff extends Entity {
    private static final long serialVersionUID = 1L;
    private String name;
    private String description;
    private double price;
    private long serviceId;

    public Tariff(){}

    public Tariff(String name, String description, double price, long serviceId) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.serviceId = serviceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public long getServiceId() {
        return serviceId;
    }

    public void setServiceId(long serviceId) {
        this.serviceId = serviceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tariff)) return false;
        Tariff tariff = (Tariff) o;
        return Objects.equals(name, tariff.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
