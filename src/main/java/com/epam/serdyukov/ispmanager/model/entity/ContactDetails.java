package com.epam.serdyukov.ispmanager.model.entity;


public class ContactDetails extends Entity {
  private static final long serialVersionUID = 1L;
  private String firstName;
  private String LastName;
  private String city;
  private String street;
  private String building;
  private String flat;
  private String email;
  private String phone;

  public ContactDetails() {
    super();
  }

  public ContactDetails(long id) {
    super(id);
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return LastName;
  }

  public void setLastName(String lastName) {
    LastName = lastName;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public String getBuilding() {
    return building;
  }

  public String getFlat() {
    return flat;
  }

  public void setFlat(String flat) {
    this.flat = flat;
  }

  public void setBuilding(String building) {
    this.building = building;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

}
