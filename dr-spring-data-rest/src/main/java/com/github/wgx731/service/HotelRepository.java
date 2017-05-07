/*
 * Copyright 2017+ the original author or authors.
 */

package com.github.wgx731.service;

import com.github.wgx731.domain.City;
import com.github.wgx731.domain.Hotel;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Hotel Search API.
 *
 * @author Wang Eric GaoXiang
 */
@RepositoryRestResource(collectionResourceRel = "hotels", path = "hotels")
interface HotelRepository extends PagingAndSortingRepository<Hotel, Long> {

	Hotel findByCityAndName(City city, String name);

}
