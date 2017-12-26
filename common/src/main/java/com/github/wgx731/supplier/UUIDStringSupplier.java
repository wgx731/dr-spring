package com.github.wgx731.supplier;

import java.util.UUID;
import java.util.function.Supplier;

public class UUIDStringSupplier implements Supplier<String> {

	@Override
	public String get() {
		return UUID.randomUUID().toString();
	}

}
