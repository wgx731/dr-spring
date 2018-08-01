package com.github.wgx731.pojo;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.avro.AvroFactory;
import com.fasterxml.jackson.dataformat.avro.AvroSchema;
import com.fasterxml.jackson.dataformat.avro.schema.AvroSchemaGenerator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class BermudaTriangle {

  private static AvroSchema avroSchema = null;

  /**
   * Generate static UUID Response avro schema.
   *
   * @return AvroSchema UUIDResponse avro schema
   * @throws JsonMappingException error generate schema
   */
  public static synchronized AvroSchema getAvroSchema() throws JsonMappingException {
    if (avroSchema == null) {
      ObjectMapper mapper = new ObjectMapper(new AvroFactory());
      AvroSchemaGenerator gen = new AvroSchemaGenerator();
      mapper.acceptJsonFormatVisitor(BermudaTriangle.class, gen);
      avroSchema = gen.getGeneratedSchema();
    }
    return avroSchema;
  }

  private static CsvSchema csvSchema = null;

  /**
   * Generate static UUID Response csv schema.
   *
   * @return CsvSchema UUIDResponse csv schema
   * @throws JsonMappingException error generate schema
   */
  public static synchronized CsvSchema getCsvSchema() {
    if (csvSchema == null) {
      CsvMapper mapper = new CsvMapper();
      csvSchema = mapper.schemaFor(BermudaTriangle.class);
    }
    return csvSchema;
  }

  @JsonProperty("uuid")
  private String uuid;

  @JsonProperty("count")
  private int count;

  @JsonProperty("boolean")
  private Boolean booleanValue;

  @JsonProperty("int")
  private Integer intValue;

  @JsonProperty("long")
  private Long longValue;

  @JsonProperty("float")
  private Float floatValue;

  @JsonProperty("double")
  private Double doubleValue;

  @JsonProperty("byte")
  private Byte byteValue;

  @JsonProperty("short")
  private Short shortValue;

  @JsonProperty("char")
  private Character charValue;

  @JsonProperty("decimal")
  private BigDecimal decimalValue;

  @JsonProperty("bytes")
  private byte[] bytesValue;

  @JsonProperty("string")
  private String stringValue;

}

