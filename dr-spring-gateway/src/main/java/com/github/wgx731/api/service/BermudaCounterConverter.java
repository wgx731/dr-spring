package com.github.wgx731.api.service;

import java.util.Base64;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.avro.AvroFactory;
import com.fasterxml.jackson.dataformat.avro.AvroSchema;
import com.fasterxml.jackson.dataformat.avro.schema.AvroSchemaGenerator;
import com.fasterxml.jackson.dataformat.cbor.CBORFactory;
import com.fasterxml.jackson.dataformat.csv.CsvFactory;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.dataformat.ion.IonFactory;
import com.fasterxml.jackson.dataformat.javaprop.JavaPropsFactory;
import com.fasterxml.jackson.dataformat.javaprop.JavaPropsSchema;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.github.wgx731.common.pojo.BermudaTriangle;
import com.github.wgx731.common.service.BermudaConverterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BermudaCounterConverter implements BermudaConverterService {

  public static final String SEPARATOR = "<-===->";

  private AvroSchema avroSchema = null;

  /**
   * Generate static UUID Response avro schema.
   *
   * @return AvroSchema UUIDResponse avro schema
   * @throws JsonMappingException error generate schema
   */
  public synchronized AvroSchema getAvroSchema() throws JsonMappingException {
    if (avroSchema == null) {
      ObjectMapper mapper = new ObjectMapper(new AvroFactory());
      AvroSchemaGenerator gen = new AvroSchemaGenerator();
      mapper.acceptJsonFormatVisitor(BermudaTriangle.class, gen);
      avroSchema = gen.getGeneratedSchema();
    }
    return avroSchema;
  }

  private CsvSchema csvSchema = null;

  /**
   * Generate static UUID Response csv schema.
   *
   * @return CsvSchema UUIDResponse csv schema
   * @throws JsonMappingException error generate schema
   */
  public synchronized CsvSchema getCsvSchema() {
    if (csvSchema == null) {
      CsvMapper mapper = new CsvMapper();
      csvSchema = mapper.schemaFor(BermudaTriangle.class);
    }
    return csvSchema;
  }

  private ObjectMapper propsMapper = new ObjectMapper(new JavaPropsFactory());

  private ObjectMapper csvMapper = new ObjectMapper(new CsvFactory());

  private ObjectMapper jsonMapper = new ObjectMapper(new JsonFactory());

  private XmlMapper xmlMapper = new XmlMapper();

  private ObjectMapper yamlMapper = new ObjectMapper(new YAMLFactory());

  private ObjectMapper avroMapper = new ObjectMapper(new AvroFactory());

  private ObjectMapper ionMapper = new ObjectMapper(new IonFactory());

  private ObjectMapper cborMapper = new ObjectMapper(new CBORFactory());


  @Override
  public String getPropertiesString(BermudaTriangle bermudaTriangle)
      throws JsonProcessingException {
    return propsMapper
        .writer(JavaPropsSchema.emptySchema().withKeyValueSeparator(SEPARATOR))
        .writeValueAsString(bermudaTriangle);
  }

  @Override
  public String getCsvString(BermudaTriangle bermudaTriangle)
      throws JsonProcessingException {
    return csvMapper
        .writer(this.getCsvSchema())
        .writeValueAsString(bermudaTriangle);
  }

  @Override
  public String getJsonString(BermudaTriangle bermudaTriangle)
      throws JsonProcessingException {
    return jsonMapper.writeValueAsString(bermudaTriangle);
  }

  @Override
  public String getXmlString(BermudaTriangle bermudaTriangle)
      throws JsonProcessingException {
    return xmlMapper.writeValueAsString(bermudaTriangle);
  }

  @Override
  public String getYamlString(BermudaTriangle bermudaTriangle)
      throws JsonProcessingException {
    return yamlMapper.writeValueAsString(bermudaTriangle);
  }

  @Override
  public String getAvroString(BermudaTriangle bermudaTriangle)
      throws JsonProcessingException {
    return Base64
        .getEncoder()
        .encodeToString(avroMapper
            .writer(this.getAvroSchema())
            .writeValueAsBytes(bermudaTriangle)
        );
  }

  @Override
  public String getIonString(BermudaTriangle bermudaTriangle)
      throws JsonProcessingException {
    return Base64
        .getEncoder()
        .encodeToString(ionMapper
            .writeValueAsBytes(bermudaTriangle)
        );
  }

  @Override
  public String getCborString(BermudaTriangle bermudaTriangle)
      throws JsonProcessingException {
    return Base64
        .getEncoder()
        .encodeToString(cborMapper
            .writeValueAsBytes(bermudaTriangle)
        );
  }

}
