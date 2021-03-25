package com.epam.serdyukov.ispmanager.model.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * User entity.
 * 
 * @author D.Kolesnikov
 * 
 */
public class User extends Entity {

	private static final long serialVersionUID = 1L;
	private String login;
	private String password;
	private boolean blocked;
	private int roleId;
	private Account account;
	private ContactDetails details;
	private Set<Tariff> tariffs;

	public User() {
		this.tariffs = new HashSet<>();
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isBlocked() {
		return blocked;
	}

	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public ContactDetails getDetails() {
		return details;
	}

	public void setDetails(ContactDetails details) {
		this.details = details;
	}

	public Set<Tariff> getTariffs() {
		return tariffs;
	}

	public void setTariffs(Set<Tariff> tariffs) {
		this.tariffs = tariffs;
	}

	public String getFirstName(){
		return details.getFirstName();
	}

}
