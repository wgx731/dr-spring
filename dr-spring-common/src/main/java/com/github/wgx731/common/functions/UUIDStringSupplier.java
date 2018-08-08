package com.github.wgx731.common.functions;

import java.util.UUID;
import java.util.function.Supplier;

public final class UUIDStringSupplier implements Supplier<String> {

  @Override
  public String get() {
    return UUID.randomUUID().toString();
  }

}
