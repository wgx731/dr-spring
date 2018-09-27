package com.github.wgx731.gateway;

import akka.stream.alpakka.spring.web.SpringWebAkkaStreamsConfiguration;
import com.github.wgx731.gateway.properties.GrpcProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

// TODO: follow up on spring web akka
@SpringBootApplication(exclude = SpringWebAkkaStreamsConfiguration.class)
@EnableConfigurationProperties(GrpcProperties.class)
public class DrSpringGateway {

  public static void main(String[] args) {
    SpringApplication.run(DrSpringGateway.class, args);
  }

}
