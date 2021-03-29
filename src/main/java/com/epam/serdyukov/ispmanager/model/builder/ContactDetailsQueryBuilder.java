package com.epam.serdyukov.ispmanager.model.builder;

import com.epam.serdyukov.ispmanager.model.entity.ContactDetails;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Contact details entity query builder.
 *
 * @author Aleksey Serdyukov.
 */
public class ContactDetailsQueryBuilder extends QueryBuilder<ContactDetails> {
  @Override
  public List<ContactDetails> getListOfResult(ResultSet rs) throws SQLException {
    List<ContactDetails> details1 = new ArrayList<>();
    while (rs.next()) {
      ContactDetails details = new ContactDetails();
      mapDetails(rs, details);
      details1.add(details);
    }
    return details1;
  }

  private void mapDetails(ResultSet rs, ContactDetails details) throws SQLException {
    details.setId(rs.getLong("id"));
    details.setCity(rs.getString("city"));
    details.setStreet(rs.getString("street"));
    details.setHome(rs.getString("home"));
    details.setApartment(rs.getString("apartment"));
    details.setEmail(rs.getString("email"));
    details.setPhone(rs.getString("phone"));
  }

  @Override
  public ContactDetails getResult(ResultSet rs) throws SQLException {
    ContactDetails detail = new ContactDetails();
    while (rs.next()) {
      mapDetails(rs, detail);
    }
    return detail;
  }
}
