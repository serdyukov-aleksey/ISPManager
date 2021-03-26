package com.epam.serdyukov.ispmanager.model.repository.impl;

import com.epam.serdyukov.ispmanager.model.builder.ContactDetailsQueryBuilder;
import com.epam.serdyukov.ispmanager.model.builder.QueryBuilder;
import com.epam.serdyukov.ispmanager.model.entity.ContactDetails;
import com.epam.serdyukov.ispmanager.model.connectionpool.DBManager;
import com.epam.serdyukov.ispmanager.model.repository.IContactDetailsRepo;

import java.util.List;

/**
 * @author Aleksey Serdyukov
 */
public class ContactDetailsRepoImpl implements IContactDetailsRepo {
    private static final String CREATE = "INSERT INTO contact_details (id, city, street, home, apartment, email, phone) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String GET_ALL = "SELECT * FROM contact_details";
    private static final String GET_BY_ID = "SELECT id, city, street, home, apartment, email, phone FROM contact_details WHERE id = ?";
    private static final String UPDATE = "UPDATE contact_details SET email = ?, phone = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM contact_details WHERE id = ?";
    private static final String GET_NEXT_AUTO_INCREMENT = "SELECT MAX(id)+1 FROM contact_details";

    private DBManager instance = DBManager.getInstance();
    private QueryBuilder queryBuilder = new ContactDetailsQueryBuilder();

    @Override
    public List<ContactDetails> getAll() {
        return this.queryBuilder.executeAndReturnList(instance, GET_ALL);
    }

    @Override
    public ContactDetails getById(long id) {
        return (ContactDetails) this.queryBuilder.executeAndReturn(instance, GET_BY_ID, id);
    }

    @Override
    public void create(ContactDetails details) {
        long id = queryBuilder.getNextAutoIncrement(instance, GET_NEXT_AUTO_INCREMENT);
        queryBuilder.execute(instance, CREATE, id, details.getCity(), details.getStreet(), details.getHome(), details.getApartment(), details.getEmail(), details.getPhone());
    }

    @Override
    public void update(ContactDetails details) {
        this.queryBuilder.execute(instance, UPDATE, details.getEmail(), details.getPhone(), details.getId());
    }

    @Override
    public void delete(long id) {
        this.queryBuilder.execute(instance, DELETE, id);
    }

    @Override
    public long getNextIdValue() {
        return queryBuilder.getNextAutoIncrement(instance, GET_NEXT_AUTO_INCREMENT);
    }
}
