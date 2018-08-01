package com.github.wgx731.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.wgx731.service.BermudaService;
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
public class BermudaController {

  public static final String BASE_PATH = "/api/bermuda";

  static final String PROPERTIES_CONTENT_TYPE = "text/plain";
  static final String CSV_CONTENT_TYPE = "text/csv";
  static final String JSON_CONTENT_TYPE = "application/json";
  static final String XML_CONTENT_TYPE = "application/xml";
  static final String YAML_CONTENT_TYPE = "application/yaml";
  static final String AVRO_CONTENT_TYPE = "text/avro";
  static final String ION_CONTENT_TYPE = "text/ion";
  static final String CBOR_CONTENT_TYPE = "text/cbor";

  @NonNull
  private BermudaService service;

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
  public ResponseEntity getProperties() throws JsonProcessingException {
    return ResponseEntity.ok(service.getPropertiesString());
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
  public ResponseEntity getCsv() throws JsonProcessingException {
    return ResponseEntity.ok(service.getCsvString());
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
  public ResponseEntity getJson() throws JsonProcessingException {
    return ResponseEntity.ok(service.getJsonString());
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
  public ResponseEntity getXml() throws JsonProcessingException {
    return ResponseEntity.ok(service.getXmlString());
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
  public ResponseEntity getYaml() throws JsonProcessingException {
    return ResponseEntity.ok(service.getYamlString());
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
  public ResponseEntity getAvro() throws JsonProcessingException {
    return ResponseEntity.ok(service.getAvroString());
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
  public ResponseEntity getIon() throws JsonProcessingException {
    return ResponseEntity.ok(service.getIonString());
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
  public ResponseEntity getCbor() throws JsonProcessingException {
    return ResponseEntity.ok(service.getCborString());
  }

}
