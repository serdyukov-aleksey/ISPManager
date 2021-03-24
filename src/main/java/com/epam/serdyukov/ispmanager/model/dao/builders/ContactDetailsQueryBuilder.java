package com.epam.serdyukov.ispmanager.model.dao.builders;


import com.epam.serdyukov.ispmanager.model.entity.ContactDetails;
import com.epam.serdyukov.ispmanager.model.dao.builders.QueryBuilder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContactDetailsQueryBuilder extends QueryBuilder<ContactDetails> {
    @Override
    public List<ContactDetails> getListOfResult(ResultSet rs) throws SQLException {
        List<ContactDetails> details1 = new ArrayList<>();
        while (rs.next()) {
            ContactDetails details = new ContactDetails();
            details.setId(rs.getLong("id"));
            details.setFirstName(rs.getString("first_name"));
            details.setLastName(rs.getString("last_name"));
            details.setCity(rs.getString("city"));
            details.setStreet(rs.getString("street"));
            details.setBuilding(rs.getString("building"));
            details.setFlat(rs.getString("flat"));
            details.setEmail(rs.getString("email"));
            details.setPhone(rs.getString("phone"));
            details1.add(details);
        }
        return details1;
    }

    @Override
    public ContactDetails getResult(ResultSet rs) throws SQLException {
        ContactDetails details = new ContactDetails();
        while (rs.next()) {
            details.setId(rs.getLong("id"));
            details.setFirstName(rs.getString("first_name"));
            details.setLastName(rs.getString("last_name"));
            details.setCity(rs.getString("city"));
            details.setStreet(rs.getString("street"));
            details.setBuilding(rs.getString("building"));
            details.setFlat(rs.getString("flat"));
            details.setEmail(rs.getString("email"));
            details.setPhone(rs.getString("phone"));
        }
        return details;
    }
    private ContactDetails mapToDetails (ResultSet rs) throws SQLException {
        ContactDetails details = new ContactDetails();
        details.setId(rs.getLong("id"));
        details.setFirstName(rs.getString("first_name"));
        details.setLastName(rs.getString("last_name"));
        details.setCity(rs.getString("city"));
        details.setStreet(rs.getString("street"));
        details.setBuilding(rs.getString("building"));
        details.setFlat(rs.getString("flat"));
        details.setEmail(rs.getString("email"));
        details.setPhone(rs.getString("phone"));
        return details;
    }
}
