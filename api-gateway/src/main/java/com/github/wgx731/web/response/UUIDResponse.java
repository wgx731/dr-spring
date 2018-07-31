package com.github.wgx731.web.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.avro.AvroFactory;
import com.fasterxml.jackson.dataformat.avro.AvroSchema;
import com.fasterxml.jackson.dataformat.avro.schema.AvroSchemaGenerator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.dataformat.protobuf.ProtobufFactory;
import com.fasterxml.jackson.dataformat.protobuf.schema.ProtobufSchema;
import com.fasterxml.jackson.dataformat.protobuf.schemagen.ProtobufSchemaGenerator;
import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class UUIDResponse {

  private static AvroSchema avroSchema = null;

  /**
   * Generate static UUID Response avro schema.
   * @return AvroSchema UUIDResponse avro schema
   * @throws JsonMappingException error generate schema
   */
  public static synchronized AvroSchema getAvroSchema() throws JsonMappingException {
    if (avroSchema == null) {
      ObjectMapper mapper = new ObjectMapper(new AvroFactory());
      AvroSchemaGenerator gen = new AvroSchemaGenerator();
      mapper.acceptJsonFormatVisitor(UUIDResponse.class, gen);
      avroSchema = gen.getGeneratedSchema();
    }
    return avroSchema;
  }

  private static ProtobufSchema protobufSchema = null;

  /**
   * Generate static UUID Response protobuf schema.
   * @return ProtobufSchema UUIDResponse protobuf schema
   * @throws JsonMappingException error generate schema
   */
  public static synchronized ProtobufSchema getProtobufSchema() throws JsonMappingException {
    if (protobufSchema == null) {
      ObjectMapper mapper = new ObjectMapper(new ProtobufFactory());
      ProtobufSchemaGenerator gen = new ProtobufSchemaGenerator();
      mapper.acceptJsonFormatVisitor(UUIDResponse.class, gen);
      protobufSchema = gen.getGeneratedSchema();
    }
    return protobufSchema;
  }

  private static CsvSchema csvSchema = null;

  /**
   * Generate static UUID Response csv schema.
   * @return CsvSchema UUIDResponse csv schema
   * @throws JsonMappingException error generate schema
   */
  public static synchronized CsvSchema getCsvSchema() {
    if (csvSchema == null) {
      CsvMapper mapper = new CsvMapper();
      csvSchema = mapper.schemaFor(UUIDResponse.class);
    }
    return csvSchema;
  }

  @JsonProperty("uuid")
  private String uuid;

  @JsonProperty("count")
  private int count;

}
