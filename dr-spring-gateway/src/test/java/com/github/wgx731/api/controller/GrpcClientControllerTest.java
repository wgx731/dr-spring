package com.github.wgx731.api.controller;

import com.github.wgx731.grpc.provider.service.BermudaListGenerator;
import com.github.wgx731.proto.BermudaServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.inprocess.InProcessChannelBuilder;
import io.grpc.inprocess.InProcessServerBuilder;
import io.grpc.testing.GrpcCleanupRule;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

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

  private BermudaServiceGrpc.BermudaServiceImplBase serviceImpl;

  private GrpcClientController controller;

  private WebTestClient webTestClient;

  @Before
  public void setUp() throws Exception {
    this.serviceImpl = new BermudaListGenerator();
    // Generate a unique in-process server name.
    String serverName = InProcessServerBuilder.generateName();

    // Create a server, add service, start, and register for automatic graceful shutdown.
    grpcCleanup.register(InProcessServerBuilder
        .forName(serverName).directExecutor().addService(serviceImpl).build().start());

    // Create a client channel and register for automatic graceful shutdown.
    ManagedChannel channel = grpcCleanup.register(
        InProcessChannelBuilder.forName(serverName).directExecutor().build());

    this.controller = new GrpcClientController(channel);
    this.webTestClient = WebTestClient.bindToController(controller)
        .configureClient()
        .filter(documentationConfiguration(this.restDocumentation))
        .build();
  }

  @After
  public void tearDown() throws Exception {
    this.serviceImpl = null;
    this.controller = null;
    this.webTestClient = null;
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
