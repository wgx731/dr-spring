package com.github.wgx731.gateway.properties;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:grpc.properties")
@ConfigurationProperties(prefix = "grpc")
@Getter
@AllArgsConstructor
public class GrpcProperties {

  @NotBlank
  private String host;

  @Min(1025)
  @Max(65536)
  private int port;

  @Min(0)
  private int shutdownTimeout;

}
