package com.github.wgx731.web.controller;

import java.io.IOException;
import java.util.Base64;
import java.util.concurrent.atomic.AtomicInteger;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.avro.AvroFactory;
import com.fasterxml.jackson.dataformat.cbor.CBORFactory;
import com.fasterxml.jackson.dataformat.csv.CsvFactory;
import com.fasterxml.jackson.dataformat.ion.IonFactory;
import com.fasterxml.jackson.dataformat.javaprop.JavaPropsFactory;
import com.fasterxml.jackson.dataformat.javaprop.JavaPropsSchema;
import com.fasterxml.jackson.dataformat.protobuf.ProtobufFactory;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.github.wgx731.web.response.UUIDResponse;
import com.github.wgx731.web.service.UUIDService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller to generate random UUID.
 */
@RestController
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UUIDController {

  public static final String BASE_PATH = "/api/uuid";
  public static final String SEPARATOR = "<-===->";

  static final String PROPERTIES_CONTENT_TYPE = "text/plain";
  static final String CSV_CONTENT_TYPE = "text/csv";
  static final String JSON_CONTENT_TYPE = "application/json";
  static final String XML_CONTENT_TYPE = "application/xml";
  static final String YAML_CONTENT_TYPE = "application/yaml";
  static final String AVRO_CONTENT_TYPE = "text/avro";
  static final String ION_CONTENT_TYPE = "text/ion";
  static final String CBOR_CONTENT_TYPE = "text/cbor";
  static final String PROTOBUF_CONTENT_TYPE = "text/protobuf";

  private static AtomicInteger counter = new AtomicInteger(0);

  private ObjectMapper propsMapper = new ObjectMapper(new JavaPropsFactory());

  private ObjectMapper csvMapper = new ObjectMapper(new CsvFactory());

  private ObjectMapper jsonMapper = new ObjectMapper(new JsonFactory());

  private XmlMapper xmlMapper = new XmlMapper();

  private ObjectMapper yamlMapper = new ObjectMapper(new YAMLFactory());

  private ObjectMapper avroMapper = new ObjectMapper(new AvroFactory());

  private ObjectMapper ionMapper = new ObjectMapper(new IonFactory());

  private ObjectMapper cborMapper = new ObjectMapper(new CBORFactory());

  private ObjectMapper protobufMapper = new ObjectMapper(new ProtobufFactory());

  @NonNull
  private UUIDService service;

  /**
   * UUID Property response.
   *
   * @return Property response
   * @throws JsonProcessingException error converting to Property
   */
  @GetMapping(
      value = BASE_PATH + ".properties",
      produces = PROPERTIES_CONTENT_TYPE
  )
  public ResponseEntity getUUIDProperties() throws JsonProcessingException {
    return ResponseEntity.ok(propsMapper
        .writer(JavaPropsSchema.emptySchema().withKeyValueSeparator(SEPARATOR))
        .writeValueAsString(getUUIDResponse()));
  }

  /**
   * UUID CSV response.
   *
   * @return CSV response
   * @throws JsonProcessingException error converting to CSV
   */
  @GetMapping(
      value = BASE_PATH + ".csv",
      produces = CSV_CONTENT_TYPE
  )
  public ResponseEntity getUUIDCsv() throws JsonProcessingException {
    return ResponseEntity.ok(csvMapper
        .writer(UUIDResponse.getCsvSchema())
        .writeValueAsString(getUUIDResponse()));
  }

  /**
   * UUID JSON response.
   *
   * @return JSON response
   * @throws JsonProcessingException error converting to JSON
   */
  @GetMapping(
      value = BASE_PATH + ".json",
      produces = JSON_CONTENT_TYPE
  )
  public ResponseEntity getUUIDJson() throws JsonProcessingException {
    return ResponseEntity.ok(jsonMapper.writeValueAsString(getUUIDResponse()));
  }

  /**
   * UUID XML response.
   *
   * @return XML response
   * @throws JsonProcessingException error converting to XML
   */
  @GetMapping(
      value = BASE_PATH + ".xml",
      produces = XML_CONTENT_TYPE
  )
  public ResponseEntity getUUIDXml() throws JsonProcessingException {
    return ResponseEntity.ok(xmlMapper.writeValueAsString(getUUIDResponse()));
  }

  /**
   * UUID Yaml response.
   *
   * @return Yaml response
   * @throws JsonProcessingException error converting to Yaml
   */
  @GetMapping(
      value = BASE_PATH + ".yaml",
      produces = YAML_CONTENT_TYPE
  )
  public ResponseEntity getUUIDYaml() throws JsonProcessingException {
    return ResponseEntity.ok(yamlMapper.writeValueAsString(getUUIDResponse()));
  }

  /**
   * UUID Avro response.
   *
   * @return Avro response
   * @throws JsonProcessingException error converting to Avro
   */
  @GetMapping(
      value = BASE_PATH + ".avro",
      produces = AVRO_CONTENT_TYPE
  )
  public ResponseEntity getUUIDAvro() throws IOException {
    UUIDResponse response = getUUIDResponse();
    String avroInBase64 = Base64
        .getEncoder()
        .encodeToString(
            avroMapper
                .writer(response.getAvroSchema())
                .writeValueAsBytes(response)
        );
    return ResponseEntity.ok(avroInBase64);
  }

  /**
   * UUID Ion response.
   *
   * @return Ion response
   * @throws JsonProcessingException error converting to Ion
   */
  @GetMapping(
      value = BASE_PATH + ".ion",
      produces = ION_CONTENT_TYPE
  )
  public ResponseEntity getUUIDIon() throws JsonProcessingException {
    UUIDResponse response = getUUIDResponse();
    String ionInBase64 = Base64
        .getEncoder()
        .encodeToString(
            ionMapper.writeValueAsBytes(response)
        );
    return ResponseEntity.ok(ionInBase64);
  }

  /**
   * UUID Cbor response.
   *
   * @return Cbor response
   * @throws JsonProcessingException error converting to Cbor
   */
  @GetMapping(
      value = BASE_PATH + ".cbor",
      produces = CBOR_CONTENT_TYPE
  )
  public ResponseEntity getUUIDCbor() throws JsonProcessingException {
    UUIDResponse response = getUUIDResponse();
    String cborInBase64 = Base64
        .getEncoder()
        .encodeToString(
            cborMapper.writeValueAsBytes(response)
        );
    return ResponseEntity.ok(cborInBase64);
  }

  /**
   * UUID Protobuf response.
   *
   * @return Protobuf response
   * @throws JsonProcessingException error converting to Protobuf
   */
  @GetMapping(
      value = BASE_PATH + ".protobuf",
      produces = PROTOBUF_CONTENT_TYPE
  )
  public ResponseEntity getUUIDProtobuf() throws JsonProcessingException {
    UUIDResponse response = getUUIDResponse();
    String protobufInBase64 = Base64
        .getEncoder()
        .encodeToString(
            protobufMapper
                .writer(response.getProtobufSchema())
                .writeValueAsBytes(response)
        );
    return ResponseEntity.ok(protobufInBase64);
  }

  private synchronized UUIDResponse getUUIDResponse() {
    UUIDResponse response = new UUIDResponse();
    response.setCount(counter.getAndIncrement());
    response.setUuid(service.getUUID());
    return response;
  }

}
