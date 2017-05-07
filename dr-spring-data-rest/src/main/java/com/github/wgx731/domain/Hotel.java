/*
 * Copyright 2017+ the original author or authors.
 */

package com.github.wgx731.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.NaturalId;

/**
 * Model Hotel Table.
 *
 * @author Wang Eric GaoXiang
 * TODO: use Project Lombok
 */
@Entity
public class Hotel implements Serializable {

	private static final long serialVersionUID = 101L;

	@Id
	@GeneratedValue
	private Long id;

	@NaturalId
	@ManyToOne(optional = false)
	private City city;

	@NaturalId
	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String address;

	@Column(nullable = false)
	private String zip;

	protected Hotel() {
	}

	public Hotel(City city, String name) {
		this.city = city;
		this.name = name;
	}

	public City getCity() {
		return this.city;
	}

	public String getName() {
		return this.name;
	}

	public String getAddress() {
		return this.address;
	}

	public String getZip() {
		return this.zip;
	}
}
