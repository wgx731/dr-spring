package com.github.wgx731.web.service;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.github.wgx731.service.BermudaConverterService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BermudaCounterServiceTest {


  private BermudaConverterService service;

  @Before
  public void setUp() {
    this.service = new BermudaCounterConverterService();
  }

  @After
  public void tearDown() {
    this.service = null;
  }

  @Test
  public void getAvroSchema() throws JsonMappingException {
    String schemaString = this.service.getAvroSchema().getAvroSchema().toString();
    assertThat(schemaString).contains(
        "com.github.wgx731.pojo",
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
        "\"boolean\"/STRING,",
        "\"byte\"/STRING,",
        "\"bytes\"/STRING,",
        "\"char\"/STRING,",
        "\"count\"/STRING,",
        "\"decimal\"/STRING,",
        "\"double\"/STRING,",
        "\"float\"/STRING,",
        "\"int\"/STRING,",
        "\"long\"/STRING,",
        "\"short\"/STRING,",
        "\"string\"/STRING,",
        "\"uuid\"/STRING"
    );
    this.service.getCsvSchema();
  }

}
