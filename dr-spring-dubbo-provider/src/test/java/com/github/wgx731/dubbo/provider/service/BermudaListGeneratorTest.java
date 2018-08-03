package com.github.wgx731.dubbo.provider.service;

import java.util.List;

import com.github.wgx731.common.pojo.BermudaTriangle;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BermudaListGeneratorTest {

  private BermudaListGenerator service;

  @Before
  public void setUp() throws Exception {
    this.service = new BermudaListGenerator();
  }

  @After
  public void tearDown() throws Exception {
    this.service = null;
  }

  @Test
  public void getBermudaList() {
    List<BermudaTriangle> list = this.service.getBermudaList(100);
    assertThat(list).hasSize(100);
    assertThat(list.get(0).getCount()).isEqualTo(1);
    assertThat(list.get(99).getCount()).isEqualTo(100);
  }

}
