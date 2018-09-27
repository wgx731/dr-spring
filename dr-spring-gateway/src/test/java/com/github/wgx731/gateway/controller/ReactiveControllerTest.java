package com.github.wgx731.gateway.controller;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.document;
import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.documentationConfiguration;

@RunWith(SpringRunner.class)
@SuppressWarnings("checkstyle:JavadocMethod")
public class ReactiveControllerTest {

  private static AtomicInteger counter = new AtomicInteger(0);
  private static Random random = new Random();

  @Rule
  public final JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation(
      "target/docs/rest/snippets"
  );

  private ReactiveController controller;

  private WebTestClient webTestClient;

  @Before
  public void setUp() throws Exception {
    this.controller = new ReactiveController();
    this.webTestClient = WebTestClient.bindToController(controller)
        .configureClient()
        .filter(documentationConfiguration(this.restDocumentation))
        .build();
  }

  @After
  public void tearDown() throws Exception {
    this.controller = null;
    this.webTestClient = null;
  }

  @Test
  public void testGetBermudaListSource() {
    controller.getBermudaListSource(10);
    this.webTestClient.get().uri(ReactiveController.BASE_PATH + "/akka/10").exchange()
        .expectStatus().isOk().expectBody()
        .consumeWith(document("bermuda_akka"));
  }

  @Test
  public void testGetBermudaListFlux() {
    controller.getBermudaListFlux(10);
    this.webTestClient.get().uri(ReactiveController.BASE_PATH + "/flux/10").exchange()
        .expectStatus().isOk().expectBody()
        .consumeWith(document("bermuda_flux"));
  }

}
