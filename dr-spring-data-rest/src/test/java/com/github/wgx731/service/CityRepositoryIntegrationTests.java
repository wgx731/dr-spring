/*
 * Copyright 2017+ the original author or authors.
 */

package com.github.wgx731.service;

import com.github.wgx731.domain.City;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Integration tests for {@link CityRepository}.
 *
 * @author Wang Eric GaoXiang
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CityRepositoryIntegrationTests {

	@Autowired
	CityRepository repository;

	@Test
	public void findsFirstPageOfCities() {

		Page<City> cities = this.repository.findAll(new PageRequest(0, 10));
		assertThat(cities.getTotalElements()).isGreaterThan(20L);
	}

	@Test
	public void findByNameAndCountry() {
		City city = this.repository.findByNameAndCountryAllIgnoringCase("Melbourne",
				"Australia");
		assertThat(city).isNotNull();
		assertThat(city.getName()).isEqualTo("Melbourne");
	}

	@Test
	public void findContaining() {
		Page<City> cities = this.repository
				.findByNameContainingAndCountryContainingAllIgnoringCase("", "UK",
						new PageRequest(0, 10));
		assertThat(cities.getTotalElements()).isEqualTo(3L);
	}
}
