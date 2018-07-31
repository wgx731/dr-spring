package com.github.wgx731.web.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SupplierUUIDServiceTest {

  private SupplierUUIDService service;

  @Before
  public void setUp() throws Exception {
    service = new SupplierUUIDService();
  }

  @After
  public void tearDown() throws Exception {
    service = null;
  }

  @Test
  public void getUUID() {
    String target = service.getUUID();
    assertThat(target).hasSize(36);
    assertThat(target).matches("[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[34][0-9a-fA-F]{3}"
        + "-[89ab][0-9a-fA-F]{3}-[0-9a-fA-F]{12}");
  }

}
