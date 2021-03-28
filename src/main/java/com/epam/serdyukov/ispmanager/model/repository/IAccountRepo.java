package com.epam.serdyukov.ispmanager.model.repository;

import com.epam.serdyukov.ispmanager.model.entity.Account;

/**
 * @author Aleksey Serdyukov
 */
public interface IAccountRepo extends IEntityRepo<Account> {
    long newNumberContract();
    long getNextIdValue();
}
