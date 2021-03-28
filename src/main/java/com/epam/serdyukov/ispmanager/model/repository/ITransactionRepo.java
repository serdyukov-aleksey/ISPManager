package com.epam.serdyukov.ispmanager.model.repository;

import com.epam.serdyukov.ispmanager.model.entity.Transaction;

import java.util.List;

public interface ITransactionRepo extends IEntityRepo<Transaction> {

  List<Transaction> getAllByAccount(long id);
}
