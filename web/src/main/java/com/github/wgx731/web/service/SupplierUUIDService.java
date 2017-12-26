package com.github.wgx731.web.service;

import com.github.wgx731.supplier.UUIDStringSupplier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class SupplierUUIDService implements UUIDService {

	private UUIDStringSupplier supplier = new UUIDStringSupplier();

	@Override
	public String getUUID() {
		return supplier.get();
	}

}
