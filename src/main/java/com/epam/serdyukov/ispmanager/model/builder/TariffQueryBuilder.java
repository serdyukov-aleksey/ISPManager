package com.epam.serdyukov.ispmanager.model.builder;

import com.epam.serdyukov.ispmanager.model.entity.Tariff;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Tariff entity query builder.
 *
 * @author Aleksey Serdyukov.
 */
public class TariffQueryBuilder extends QueryBuilder<Tariff> {
  @Override
  public List<Tariff> getListOfResult(ResultSet rs) throws SQLException {
    List<Tariff> tariffs = new ArrayList<>();
    while (rs.next()) {
      Tariff tariff = new Tariff();
      tariff.setId(rs.getLong("id"));
      tariff.setName(rs.getString("name"));
      tariff.setDescription(rs.getString("description"));
      tariff.setPrice(rs.getDouble("price"));
      tariff.setServiceId(rs.getLong("services_id"));
      tariffs.add(tariff);
    }
    return tariffs;
  }

  @Override
  public Tariff getResult(ResultSet rs) throws SQLException {
    Tariff tariff = new Tariff();
    while (rs.next()) {
      tariff.setId(rs.getLong("id"));
      tariff.setName(rs.getString("name"));
      tariff.setDescription(rs.getString("description"));
      tariff.setPrice(rs.getDouble("price"));
      tariff.setServiceId(rs.getLong("services_id"));
    }
    return tariff;
  }
}
