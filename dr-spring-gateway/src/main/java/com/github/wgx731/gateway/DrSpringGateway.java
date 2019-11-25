package com.github.wgx731.gateway;

import com.github.wgx731.gateway.properties.GrpcProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(GrpcProperties.class)
public class DrSpringGateway {

  public static void main(String[] args) {
    SpringApplication.run(DrSpringGateway.class, args);
  }

}
