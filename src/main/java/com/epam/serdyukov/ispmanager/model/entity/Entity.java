package com.epam.serdyukov.ispmanager.model.entity;

import java.io.Serializable;

/**
 * Root of all entities which have identifier field.
 * 
 * @author D.Kolesnikov
 * 
 */
public abstract class Entity implements Serializable {
	private static final long serialVersionUID = 1L;
	private long id;

	public Entity() {
	}

	public Entity(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
