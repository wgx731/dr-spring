/*
 * Copyright 2017+ the original author or authors.
 */

package com.github.wgx731.service;

import java.io.Serializable;

import org.springframework.util.Assert;

/**
 * Search city by name.
 *
 * @author Wang Eric GaoXiang
 */
public class CitySearchCriteria implements Serializable {

	private static final long serialVersionUID = 102L;

	private String name;

	public CitySearchCriteria() {
	}

	public CitySearchCriteria(String name) {
		Assert.notNull(name, "Name must not be null");
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
