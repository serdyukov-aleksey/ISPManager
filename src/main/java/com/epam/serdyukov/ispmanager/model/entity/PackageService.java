package com.epam.serdyukov.ispmanager.model.entity;

import java.util.HashSet;
import java.util.Set;

public class PackageService extends Entity {
    private static final long serialVersionUID = 1L;
    private String name;
    private String description;
    private Set<Tariff> tariffs;

    public PackageService() {
        this.tariffs = new HashSet<>();
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

    public Set<Tariff> getTariffs() {
        return tariffs;
    }

    public void setTariffs(Set<Tariff> tariffs) {
        this.tariffs = tariffs;
    }
}
