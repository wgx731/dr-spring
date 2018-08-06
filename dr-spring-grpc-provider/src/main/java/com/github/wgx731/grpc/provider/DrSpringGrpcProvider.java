package com.github.wgx731.grpc.provider;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
@SuppressWarnings("checkstyle:JavadocMethod")
public class DrSpringGrpcProvider {

  public static void main(String[] args) {
    new SpringApplicationBuilder(DrSpringGrpcProvider.class)
        .web(WebApplicationType.NONE)
        .run(args);
  }

}
