package com.epam.serdyukov.ispmanager.model.builder;

import com.epam.serdyukov.ispmanager.model.entity.PackageService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Aleksey Serdyukov
 */
public class ServiceQueryBuilder extends QueryBuilder<PackageService> {
    @Override
    public List<PackageService> getListOfResult(ResultSet rs) throws SQLException {
        List<PackageService> services = new ArrayList<>();
        while (rs.next()) {
            PackageService packageService = new PackageService();
            packageService.setId(rs.getLong("id"));
            packageService.setName(rs.getString("name"));
            packageService.setDescription(rs.getString("description"));
            services.add(packageService);
        }
        return services;
    }

    @Override
    public PackageService getResult(ResultSet rs) throws SQLException {
        PackageService packageService = new PackageService();
        while (rs.next()) {
            packageService.setId(rs.getLong("id"));
            packageService.setName(rs.getString("name"));
            packageService.setDescription(rs.getString("description"));

        }
        return packageService;
    }
}
