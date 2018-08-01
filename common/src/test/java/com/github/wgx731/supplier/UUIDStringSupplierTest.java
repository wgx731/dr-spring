package com.github.wgx731.supplier;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("checkstyle:JavadocMethod")
public class UUIDStringSupplierTest {

  private UUIDStringSupplier supplier;

  @Before
  public void setUp() {
    supplier = new UUIDStringSupplier();
  }

  @After
  public void tearDown() {
    supplier = null;
  }

  @Test
  public void testSupplier() {
    String target = supplier.get();
    assertThat(target).hasSize(36);
    assertThat(target).matches("[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[34][0-9a-fA-F]{3}"
        + "-[89ab][0-9a-fA-F]{3}-[0-9a-fA-F]{12}");
  }
}
