package com.github.wgx731.gateway.controller;

import com.github.wgx731.gateway.properties.GrpcProperties;
import com.github.wgx731.grpc.provider.service.BermudaListGenerator;
import com.github.wgx731.proto.BermudaServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.inprocess.InProcessChannelBuilder;
import io.grpc.inprocess.InProcessServerBuilder;
import io.grpc.testing.GrpcCleanupRule;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import static com.github.wgx731.gateway.controller.GrpcClientController.INVALID_GRPC_HOST_IN_GRPC_PROPERTIES;
import static com.github.wgx731.gateway.controller.GrpcClientController.INVALID_GRPC_PORT_IN_GRPC_PROPERTIES;
import static com.github.wgx731.gateway.controller.GrpcClientController.INVALID_GRPC_SHUTDOWN_TIMEOUT_IN_GRPC_PROPERTIES;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.document;
import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.documentationConfiguration;


@RunWith(SpringRunner.class)
@SuppressWarnings("checkstyle:JavadocMethod")
public class GrpcClientControllerTest {

  @Rule
  public final GrpcCleanupRule grpcCleanup = new GrpcCleanupRule();

  @Rule
  public final JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation(
      "target/docs/rest/snippets"
  );

  @MockBean
  private GrpcProperties grpcProperties;

  private BermudaServiceGrpc.BermudaServiceImplBase serviceImpl;

  private GrpcClientController controller;

  private WebTestClient webTestClient;

  @Before
  public void setUp() throws Exception {
    // setup test grpc server
    this.serviceImpl = new BermudaListGenerator();
    // Generate a unique in-process server name.
    String serverName = InProcessServerBuilder.generateName();

    // Create a server, add service, start, and register for automatic graceful shutdown.
    grpcCleanup.register(InProcessServerBuilder
        .forName(serverName).directExecutor().addService(serviceImpl).build().start());

    // setup controller and test grpc client
    this.grpcProperties = new GrpcProperties();
    this.grpcProperties.setHost("127.0.0.1");
    this.grpcProperties.setPort(12347);
    this.grpcProperties.setShutdownTimeout(5);
    this.controller = new GrpcClientController(this.grpcProperties);
    this.webTestClient = WebTestClient.bindToController(controller)
        .configureClient()
        .filter(documentationConfiguration(this.restDocumentation))
        .build();
    // Create a client channel and register for automatic graceful shutdown.
    ManagedChannel channel = grpcCleanup.register(
        InProcessChannelBuilder.forName(serverName).directExecutor().build());
    this.controller.startTest(channel);
  }

  @After
  public void tearDown() throws InterruptedException {
    this.controller.shutdown();
    this.grpcProperties = null;
    this.serviceImpl = null;
    this.controller = null;
    this.webTestClient = null;
  }

  @Test
  public void testStart() throws InterruptedException {
    // test invalid host
    GrpcProperties grpcProperties = new GrpcProperties();
    grpcProperties.setHost(null);
    grpcProperties.setPort(1066);
    grpcProperties.setShutdownTimeout(10);
    GrpcClientController clientController = new GrpcClientController(
        grpcProperties
    );
    try {
      clientController.start();
      Assert.fail();
    } catch (IllegalArgumentException e) {
      assertThat(e.getMessage()).contains(
          INVALID_GRPC_HOST_IN_GRPC_PROPERTIES
      );
    }
    // test invalid port
    grpcProperties.setHost("127.0.0.2");
    grpcProperties.setPort(65537);
    grpcProperties.setShutdownTimeout(10);
    clientController = new GrpcClientController(grpcProperties);
    try {
      clientController.start();
      Assert.fail();
    } catch (IllegalArgumentException e) {
      assertThat(e.getMessage()).contains(
          INVALID_GRPC_PORT_IN_GRPC_PROPERTIES
      );
    }
    // test invalid timeout
    grpcProperties.setHost("127.0.0.2");
    grpcProperties.setPort(1026);
    grpcProperties.setShutdownTimeout(-1);
    clientController = new GrpcClientController(grpcProperties);
    try {
      clientController.start();
      Assert.fail();
    } catch (IllegalArgumentException e) {
      assertThat(e.getMessage()).contains(
          INVALID_GRPC_SHUTDOWN_TIMEOUT_IN_GRPC_PROPERTIES
      );
    }
    // test valid grpc properties
    grpcProperties.setHost("127.0.0.1");
    grpcProperties.setPort(1029);
    grpcProperties.setShutdownTimeout(10);
    clientController = new GrpcClientController(grpcProperties);
    // channel null start
    clientController.start();
    clientController.shutdown();
    // null in startTest
    clientController.startTest(null);
    clientController.shutdown();
    // wrong channel start
    clientController.startTest(grpcCleanup.register(
        InProcessChannelBuilder.forName(
            InProcessServerBuilder.generateName()
        ).directExecutor().build())
    );
    try {
      clientController.getBermudaList(10);
      Assert.fail();
    } catch (InternalError e) {
      assertThat(e.getMessage()).contains(
          "UNAVAILABLE"
      );
    }
    clientController.shutdown();
  }

  @Test
  public void testGetBermudaList() {
    ResponseEntity responseEntity = controller.getBermudaList(10);
    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    this.webTestClient.get().uri(GrpcClientController.BASE_PATH + "/10").exchange()
        .expectStatus().isOk().expectBody()
        .consumeWith(document("bermuda_grpc"));
  }

}
