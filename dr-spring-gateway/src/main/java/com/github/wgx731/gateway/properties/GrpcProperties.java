package com.github.wgx731.gateway.properties;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:grpc.properties")
@ConfigurationProperties(prefix = "grpc")
@ToString
@Data
public class GrpcProperties {

  public static final String IP_REGEX = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
      + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
      + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
      + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";

  static final java.util.regex.Pattern IP_REGEX_PATTERN
      = java.util.regex.Pattern.compile(IP_REGEX);

  public static final int MIN_PORT = 1025;
  public static final int MAX_PORT = 65536;
  public static final int MIN_TIMEOUT = 1;

  @Pattern(regexp = IP_REGEX)
  private String host;

  @Min(MIN_PORT)
  @Max(MAX_PORT)
  private int port;

  @Min(MIN_TIMEOUT)
  private int shutdownTimeout;

  /**
   * check if grpc host is valid.
   *
   * @return true if valid host otherwise false
   */
  public boolean isHostValid() {
    return host != null && IP_REGEX_PATTERN.matcher(host).matches();
  }

  /**
   * check if grpc port is valid.
   *
   * @return true if valid host otherwise false
   */
  public boolean isPortValid() {
    return this.port >= MIN_PORT && this.port <= MAX_PORT;
  }

  /**
   * check if grpc shutdown timeout is valid.
   *
   * @return true if valid host otherwise false
   */
  public boolean isShutDownTimeoutValid() {
    return this.shutdownTimeout >= MIN_TIMEOUT;
  }

}
