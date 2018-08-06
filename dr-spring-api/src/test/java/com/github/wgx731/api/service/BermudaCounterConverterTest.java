package com.github.wgx731.api.service;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.github.wgx731.common.service.BermudaConverterService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BermudaCounterConverterTest {


  private BermudaConverterService service;

  @Before
  public void setUp() {
    this.service = new BermudaCounterConverter();
  }

  @After
  public void tearDown() {
    this.service = null;
  }

  @Test
  public void getAvroSchema() throws JsonMappingException {
    String schemaString = this.service.getAvroSchema().getAvroSchema().toString();
    assertThat(schemaString).contains(
        "com.github.wgx731.common.pojo",
        "boolean",
        "java.lang.Byte",
        "[B",
        "java.lang.Character",
        "java.lang.Integer",
        "java.math.BigDecimal",
        "java.lang.Double",
        "java.lang.Float",
        "ava.lang.Integer",
        "java.lang.Long",
        "java.lang.Short",
        "string"
    );
    this.service.getAvroSchema();
  }

  @Test
  public void getCsvSchema() {
    String schemaString = this.service.getCsvSchema().toString();
    assertThat(schemaString).contains(
        "\"boolean_value\"/STRING,",
        "\"byte_value\"/STRING,",
        "\"bytes_value\"/STRING,",
        "\"char_value\"/STRING,",
        "\"decimal_value\"/STRING,",
        "\"double_value\"/STRING,",
        "\"float_value\"/STRING,",
        "\"int_value\"/STRING,",
        "\"long_value\"/STRING,",
        "\"short_value\"/STRING,",
        "\"string_value\"/STRING,",
        "\"count\"/STRING,",
        "\"uuid\"/STRING"
    );
    this.service.getCsvSchema();
  }

}
