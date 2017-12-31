package com.github.wgx731.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DrSpringWebApplicationTest {

  @Test
  public void testMain() {
    String[] args = {""};
    DrSpringWebApplication.main(args);
  }

}
