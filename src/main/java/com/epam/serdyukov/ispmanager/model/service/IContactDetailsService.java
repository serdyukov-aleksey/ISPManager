package com.epam.serdyukov.ispmanager.model.service;

import com.epam.serdyukov.ispmanager.model.entity.ContactDetails;
import java.util.List;

/**
 * Contact details service interface .
 *
 * @author Aleksey Serdyukov.
 */
public interface IContactDetailsService {

  List<ContactDetails> findAll();

  ContactDetails find(long id);

  void save(ContactDetails account);

  void update(ContactDetails account);

  void remove(int id);

  long getNextIdValue();
}
