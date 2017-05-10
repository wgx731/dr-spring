/*
 * Copyright 2017+ the original author or authors.
 */

package com.github.wgx731.service;

import com.github.wgx731.domain.City;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * City Search API.
 *
 * @author Wang Eric GaoXiang
 */
@RepositoryRestResource(collectionResourceRel = "cities", path = "cities")
interface CityRepository extends JpaRepository<City, Long> {

	Page<City> findByNameContainingAndCountryContainingAllIgnoringCase(
			@Param("name") String name, @Param("country") String country,
			Pageable pageable);

	City findByNameAndCountryAllIgnoringCase(@Param("name") String name,
			@Param("country") String country);

}
