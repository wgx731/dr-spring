package com.github.wgx731.dubbo.provider;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
@SuppressWarnings("checkstyle:JavadocMethod")
public class DrSpringDubboProvider {

  public static void main(String[] args) {
    new SpringApplicationBuilder(DrSpringDubboProvider.class)
        .web(WebApplicationType.NONE)
        .run(args);
  }

}
