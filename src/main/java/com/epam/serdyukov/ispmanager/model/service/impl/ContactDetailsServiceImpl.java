package com.epam.serdyukov.ispmanager.model.service.impl;

import com.epam.serdyukov.ispmanager.model.entity.ContactDetails;
import com.epam.serdyukov.ispmanager.model.repository.impl.ContactDetailsRepoImpl;
import com.epam.serdyukov.ispmanager.model.repository.IContactDetailsRepo;
import com.epam.serdyukov.ispmanager.model.service.IContactDetailsService;

import java.util.List;

/**
 * @author Aleksey Serdyukov
 */
public class ContactDetailsServiceImpl implements IContactDetailsService {
    private final IContactDetailsRepo repo;

    public ContactDetailsServiceImpl(IContactDetailsRepo repo){
        this.repo=repo;
    }

    @Override
    public List<ContactDetails> findAll() {
        return this.repo.getAll();
    }

    @Override
    public ContactDetails find(long id) {
        return this.repo.getById(id);
    }

    @Override
    public void save(ContactDetails account) {
        this.repo.create(account);
    }

    @Override
    public void update(ContactDetails account) {
        this.repo.update(account);
    }

    @Override
    public void remove(int id) {
        this.repo.delete(id);
    }

    @Override
    public long getNextIdValue(){
        return repo.getNextIdValue();
    }
}
