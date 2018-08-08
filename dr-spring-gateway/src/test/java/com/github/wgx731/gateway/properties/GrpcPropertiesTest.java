package com.github.wgx731.gateway.properties;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("checkstyle:JavadocMethod")
public class GrpcPropertiesTest {

  static final String TEST_HOST = "127.0.0.3";
  static int TEST_PORT = 2231;
  static int TEST_TIMEOUT = 50;

  private GrpcProperties grpcProperties;

  @Before
  public void setUp() throws Exception {
    this.grpcProperties = new GrpcProperties(
        TEST_HOST,
        TEST_PORT,
        TEST_TIMEOUT
    );
  }

  @After
  public void tearDown() throws Exception {
    this.grpcProperties = null;
  }

  @Test
  public void getHost() {
    assertThat(this.grpcProperties.getHost()).isEqualTo(TEST_HOST);
  }

  @Test
  public void getPort() {
    assertThat(this.grpcProperties.getPort()).isEqualTo(TEST_PORT);
  }

  @Test
  public void getShutdownTimeout() {
    assertThat(this.grpcProperties.getShutdownTimeout()).isEqualTo(TEST_TIMEOUT);
  }

  @Test
  public void testIsValidHost() {
    assertThat(this.grpcProperties.isHostValid()).isTrue();
    GrpcProperties nullHost = new GrpcProperties(
        null,
        1,
        1
    );
    assertThat(nullHost.isHostValid()).isFalse();
    GrpcProperties invalidHost = new GrpcProperties(
        "ddd",
        1,
        1
    );
    assertThat(nullHost.isHostValid()).isFalse();
  }

  @Test
  public void testIsPortValid() {
    assertThat(this.grpcProperties.isPortValid()).isTrue();
    GrpcProperties smallPort = new GrpcProperties(
        "127.0.0.1",
        1024,
        1
    );
    assertThat(smallPort.isPortValid()).isFalse();
    GrpcProperties biggerPort = new GrpcProperties(
        "127.0.0.1",
        66666666,
        1
    );
    assertThat(biggerPort.isPortValid()).isFalse();
  }

  @Test
  public void testIsTimeoutValid() {
    assertThat(this.grpcProperties.isShutDownTimeoutValid()).isTrue();
    GrpcProperties invalidTimeout = new GrpcProperties(
        "127.0.0.1",
        1026,
        -1
    );
    assertThat(invalidTimeout.isShutDownTimeoutValid()).isFalse();
  }
}
