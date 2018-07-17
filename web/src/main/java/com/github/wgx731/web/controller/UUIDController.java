package com.github.wgx731.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.javaprop.JavaPropsMapper;
import com.fasterxml.jackson.dataformat.javaprop.JavaPropsSchema;
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

  static final String JSON_CONTENT_TYPE = "application/json";
  static final String XML_CONTENT_TYPE = "application/xml";
  static final String YAML_CONTENT_TYPE = "application/yaml";
  static final String PROPERTIES_CONTENT_TYPE = "text/plain";
  static final String CSV_CONTENT_TYPE = "text/csv";

  private static int counter = 0;

  private ObjectMapper jsonMapper = new ObjectMapper();

  private ObjectMapper xmlMapper = new XmlMapper();

  private ObjectMapper propsMapper = new JavaPropsMapper();

  private ObjectMapper csvMapper = new CsvMapper();

  private ObjectMapper yamlMapper = new ObjectMapper(new YAMLFactory());

  @NonNull
  private UUIDService service;

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
    return ResponseEntity.ok(((CsvMapper) csvMapper)
        .writerWithSchemaFor(UUIDResponse.class)
        .writeValueAsString(getUUIDResponse()));
  }

  private UUIDResponse getUUIDResponse() {
    return UUIDResponse.builder()
        .uuid(service.getUUID())
        .count(counter++)
        .build();
  }

}
