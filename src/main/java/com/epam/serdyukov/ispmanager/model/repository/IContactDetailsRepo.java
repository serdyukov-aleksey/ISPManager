package com.epam.serdyukov.ispmanager.model.repository;

import com.epam.serdyukov.ispmanager.model.entity.ContactDetails;

/**
 * Contact details repository interface.
 *
 * @author Aleksey Serdyukov.
 */
public interface IContactDetailsRepo extends IEntityRepo<ContactDetails> {
  long getNextIdValue();
}
