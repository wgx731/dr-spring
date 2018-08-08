package com.github.wgx731.gateway.controller;

import com.github.wgx731.common.service.BermudaConverterService;
import com.github.wgx731.gateway.service.BermudaCounterConverter;
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
public class BermudaControllerTest {

  @Rule
  public final JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation(
      "target/docs/rest/snippets"
  );

  private BermudaConverterService service;

  private BermudaController controller;

  private WebTestClient webTestClient;

  @Before
  public void setUp() {
    this.service = new BermudaCounterConverter();
    this.controller = new BermudaController(service);
    this.webTestClient = WebTestClient.bindToController(controller)
        .configureClient()
        .filter(documentationConfiguration(this.restDocumentation))
        .build();
  }

  @After
  public void tearDown() {
    this.service = null;
    this.controller = null;
    this.webTestClient = null;
  }

  @Test
  public void testGetProperties() throws Exception {
    ResponseEntity responseEntity = controller.getProperties();
    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    this.webTestClient.get().uri(BermudaController.BASE_PATH + ".properties").exchange()
        .expectStatus().isOk().expectBody()
        .consumeWith(document("bermuda_properties"));
  }

  @Test
  public void testGetCsv() throws Exception {
    ResponseEntity responseEntity = controller.getCsv();
    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    this.webTestClient.get().uri(BermudaController.BASE_PATH + ".csv").exchange()
        .expectStatus().isOk().expectBody()
        .consumeWith(document("bermuda_csv"));
  }

  @Test
  public void testGetJson() throws Exception {
    ResponseEntity responseEntity = controller.getJson();
    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    this.webTestClient.get().uri(BermudaController.BASE_PATH + ".json").exchange()
        .expectStatus().isOk().expectBody()
        .consumeWith(document("bermuda_json"));
  }

  @Test
  public void testGetXml() throws Exception {
    ResponseEntity responseEntity = controller.getXml();
    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    this.webTestClient.get().uri(BermudaController.BASE_PATH + ".xml").exchange()
        .expectStatus().isOk().expectBody()
        .consumeWith(document("bermuda_xml"));
  }

  @Test
  public void testGetYaml() throws Exception {
    ResponseEntity responseEntity = controller.getYaml();
    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    this.webTestClient.get().uri(BermudaController.BASE_PATH + ".yaml").exchange()
        .expectStatus().isOk().expectBody()
        .consumeWith(document("bermuda_yaml"));
  }

  @Test
  public void testGetAvro() throws Exception {
    ResponseEntity responseEntity = controller.getAvro();
    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    this.webTestClient.get().uri(BermudaController.BASE_PATH + ".avro").exchange()
        .expectStatus().isOk().expectBody()
        .consumeWith(document("bermuda_avro"));
  }

  @Test
  public void testGetIon() throws Exception {
    ResponseEntity responseEntity = controller.getIon();
    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    this.webTestClient.get().uri(BermudaController.BASE_PATH + ".ion").exchange()
        .expectStatus().isOk().expectBody()
        .consumeWith(document("bermuda_ion"));
  }

  @Test
  public void testGetCbor() throws Exception {
    ResponseEntity responseEntity = controller.getCbor();
    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    this.webTestClient.get().uri(BermudaController.BASE_PATH + ".cbor").exchange()
        .expectStatus().isOk().expectBody()
        .consumeWith(document("bermuda_cbor"));
  }

}


