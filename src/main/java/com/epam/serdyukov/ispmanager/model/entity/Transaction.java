package com.epam.serdyukov.ispmanager.model.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class Transaction extends Entity{
  private static final long serialVersionUID = 1L;
  private LocalDateTime timestamp;
  private long account;
  private BigDecimal amount;
  private boolean isCredit;
  private String description;

  public LocalDateTime getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(LocalDateTime timestamp) {
    this.timestamp = timestamp;
  }

  public long getAccount() {
    return account;
  }

  public void setAccount(long account) {
    this.account = account;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  public boolean isCredit() {
    return isCredit;
  }

  public void setCredit(boolean credit) {
    isCredit = credit;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Transaction)) return false;
    Transaction that = (Transaction) o;
    return getAccount() == that.getAccount() &&
        isCredit() == that.isCredit() &&
        getTimestamp().equals(that.getTimestamp()) &&
        Objects.equals(getAmount(), that.getAmount()) &&
        Objects.equals(getDescription(), that.getDescription());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getTimestamp(), getAccount(), getAmount(), isCredit(), getDescription());
  }
}
