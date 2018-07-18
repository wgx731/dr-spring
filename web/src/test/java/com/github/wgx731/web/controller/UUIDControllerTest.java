package com.github.wgx731.web.controller;

import java.util.Base64;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.avro.AvroFactory;
import com.fasterxml.jackson.dataformat.cbor.CBORFactory;
import com.fasterxml.jackson.dataformat.ion.IonFactory;
import com.fasterxml.jackson.dataformat.protobuf.ProtobufFactory;
import com.github.wgx731.web.response.UUIDResponse;
import com.github.wgx731.web.service.UUIDService;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
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
public class UUIDControllerTest {

  static final String TEST_UUID = "TEST-UUID";
  static final String SAMPLE_UUID = "8e4721a4-7940-4d57-9f59-85762475f478";

  @Rule
  public final JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation(
      "target/snippets"
  );

  @MockBean
  private UUIDService service;

  private UUIDController controller;

  private WebTestClient webTestClient;

  @Before
  public void setUp() {
    this.service = Mockito.mock(UUIDService.class);
    this.controller = new UUIDController(service);
    this.webTestClient = WebTestClient.bindToController(controller)
        .configureClient()
        .filter(documentationConfiguration(this.restDocumentation))
        .build();
    Mockito.when(service.getUUID()).thenReturn(TEST_UUID);
  }

  @After
  public void tearDown() {
    service = null;
    controller = null;
  }

  @Test
  public void getUUIDProperties() throws Exception {
    ResponseEntity responseEntity = controller.getUUIDProperties();
    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(responseEntity.getBody().toString()).contains(TEST_UUID);
    Mockito.when(service.getUUID()).thenReturn(SAMPLE_UUID);
    this.webTestClient.get().uri(UUIDController.BASE_PATH + ".properties").exchange()
        .expectStatus().isOk().expectBody()
        .consumeWith(document("uuid_properties"));
  }

  @Test
  public void getUUIDCsv() throws Exception {
    ResponseEntity responseEntity = controller.getUUIDCsv();
    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(responseEntity.getBody().toString()).contains(TEST_UUID);
    Mockito.when(service.getUUID()).thenReturn(SAMPLE_UUID);
    this.webTestClient.get().uri(UUIDController.BASE_PATH + ".csv").exchange()
        .expectStatus().isOk().expectBody()
        .consumeWith(document("uuid_csv"));
  }

  @Test
  public void getUUIDJson() throws Exception {
    ResponseEntity responseEntity = controller.getUUIDJson();
    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(responseEntity.getBody().toString()).contains(TEST_UUID);
    Mockito.when(service.getUUID()).thenReturn(SAMPLE_UUID);
    this.webTestClient.get().uri(UUIDController.BASE_PATH + ".json").exchange()
        .expectStatus().isOk().expectBody()
        .consumeWith(document("uuid_json"));
  }

  @Test
  public void getUUIDXml() throws Exception {
    ResponseEntity responseEntity = controller.getUUIDXml();
    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(responseEntity.getBody().toString()).contains(TEST_UUID);
    Mockito.when(service.getUUID()).thenReturn(SAMPLE_UUID);
    this.webTestClient.get().uri(UUIDController.BASE_PATH + ".xml").exchange()
        .expectStatus().isOk().expectBody()
        .consumeWith(document("uuid_xml"));
  }

  @Test
  public void getUUIDYaml() throws Exception {
    ResponseEntity responseEntity = controller.getUUIDYaml();
    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(responseEntity.getBody().toString()).contains(TEST_UUID);
    Mockito.when(service.getUUID()).thenReturn(SAMPLE_UUID);
    this.webTestClient.get().uri(UUIDController.BASE_PATH + ".yaml").exchange()
        .expectStatus().isOk().expectBody()
        .consumeWith(document("uuid_yaml"));
  }

  @Test
  public void getUUIDAvro() throws Exception {
    ResponseEntity responseEntity = controller.getUUIDAvro();
    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    ObjectMapper mapper = new ObjectMapper(new AvroFactory());
    UUIDResponse response = mapper.readerFor(UUIDResponse.class)
        .with(UUIDResponse.getAvroSchema())
        .readValue(
            Base64.getDecoder().decode((String) responseEntity.getBody())
        );
    assertThat(response.getUuid()).isEqualTo(TEST_UUID);
    Mockito.when(service.getUUID()).thenReturn(SAMPLE_UUID);
    this.webTestClient.get().uri(UUIDController.BASE_PATH + ".avro").exchange()
        .expectStatus().isOk().expectBody()
        .consumeWith(document("uuid_avro"));
  }

  @Test
  public void getUUIDIon() throws Exception {
    ResponseEntity responseEntity = controller.getUUIDIon();
    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    ObjectMapper mapper = new ObjectMapper(new IonFactory());
    UUIDResponse response = mapper.readValue(
        Base64.getDecoder().decode((String) responseEntity.getBody()),
        UUIDResponse.class
    );
    assertThat(response.getUuid()).isEqualTo(TEST_UUID);
    Mockito.when(service.getUUID()).thenReturn(SAMPLE_UUID);
    this.webTestClient.get().uri(UUIDController.BASE_PATH + ".ion").exchange()
        .expectStatus().isOk().expectBody()
        .consumeWith(document("uuid_ion"));
  }

  @Test
  public void getUUIDCbor() throws Exception {
    ResponseEntity responseEntity = controller.getUUIDCbor();
    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    ObjectMapper mapper = new ObjectMapper(new CBORFactory());
    UUIDResponse response = mapper.readValue(
        Base64.getDecoder().decode((String) responseEntity.getBody()),
        UUIDResponse.class
    );
    assertThat(response.getUuid()).isEqualTo(TEST_UUID);
    Mockito.when(service.getUUID()).thenReturn(SAMPLE_UUID);
    this.webTestClient.get().uri(UUIDController.BASE_PATH + ".cbor").exchange()
        .expectStatus().isOk().expectBody()
        .consumeWith(document("uuid_cbor"));
  }

  @Test
  public void getUUIDProtobuf() throws Exception {
    ResponseEntity responseEntity = controller.getUUIDProtobuf();
    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    ObjectMapper mapper = new ObjectMapper(new ProtobufFactory());
    UUIDResponse response = mapper.readerFor(UUIDResponse.class)
        .with(UUIDResponse.getProtobufSchema())
        .readValue(
            Base64.getDecoder().decode((String) responseEntity.getBody())
        );
    assertThat(response.getUuid()).isEqualTo(TEST_UUID);
    Mockito.when(service.getUUID()).thenReturn(SAMPLE_UUID);
    this.webTestClient.get().uri(UUIDController.BASE_PATH + ".cbor").exchange()
        .expectStatus().isOk().expectBody()
        .consumeWith(document("uuid_cbor"));
  }

}


