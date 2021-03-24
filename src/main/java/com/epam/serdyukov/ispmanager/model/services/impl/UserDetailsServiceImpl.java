package com.epam.serdyukov.ispmanager.model.services.impl;
import com.epam.serdyukov.ispmanager.model.entity.ContactDetails;
import com.epam.serdyukov.ispmanager.model.repo.impl.UserDetailsRepoImpl;
import com.epam.serdyukov.ispmanager.model.repo.IUserDetailsRepo;
import com.epam.serdyukov.ispmanager.model.services.IUserDetailsService;

import java.util.List;

public class UserDetailsServiceImpl implements IUserDetailsService {
    private final IUserDetailsRepo repo = new UserDetailsRepoImpl();

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
}
