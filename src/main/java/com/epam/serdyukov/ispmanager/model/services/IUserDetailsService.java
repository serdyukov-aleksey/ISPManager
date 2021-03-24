package com.epam.serdyukov.ispmanager.model.services;

import com.epam.serdyukov.ispmanager.model.entity.ContactDetails;

import java.util.List;

public interface IUserDetailsService {

    List<ContactDetails> findAll();

    ContactDetails find(long id);

    void save(ContactDetails account);

    void update(ContactDetails account);

    void remove(int id);
}
