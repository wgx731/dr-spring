package com.github.wgx731.web.service;

import java.math.BigDecimal;
import java.util.Base64;
import java.util.concurrent.atomic.AtomicInteger;

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
import com.github.wgx731.pojo.BermudaTriangle;
import com.github.wgx731.service.BermudaService;
import com.github.wgx731.supplier.UUIDStringSupplier;
import org.springframework.stereotype.Service;

@Service
public class BermudaCounterService implements BermudaService {

  public static final String SEPARATOR = "<-===->";

  private static AtomicInteger counter = new AtomicInteger(0);

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

  synchronized BermudaTriangle getBermudaTriangle() {
    BermudaTriangle instance = new BermudaTriangle();
    instance.setUuid(new UUIDStringSupplier().get());
    instance.setCount(counter.incrementAndGet());
    instance.setBooleanValue(Boolean.FALSE);
    instance.setIntValue(Integer.MAX_VALUE);
    instance.setLongValue(Long.MIN_VALUE);
    instance.setFloatValue(Float.MAX_VALUE);
    instance.setDoubleValue(Double.MIN_VALUE);
    instance.setByteValue(Byte.MAX_VALUE);
    instance.setShortValue(Short.MIN_VALUE);
    instance.setCharValue('*');
    instance.setDecimalValue(BigDecimal.ONE);
    instance.setBytesValue("dr-spring".getBytes());
    instance.setStringValue("hello world from dr-spring!");
    return instance;
  }

  @Override
  public String getPropertiesString() throws JsonProcessingException {
    return propsMapper
        .writer(JavaPropsSchema.emptySchema().withKeyValueSeparator(SEPARATOR))
        .writeValueAsString(getBermudaTriangle());
  }

  @Override
  public String getCsvString() throws JsonProcessingException {
    return csvMapper
        .writer(this.getCsvSchema())
        .writeValueAsString(getBermudaTriangle());
  }

  @Override
  public String getJsonString() throws JsonProcessingException {
    return jsonMapper.writeValueAsString(getBermudaTriangle());
  }

  @Override
  public String getXmlString() throws JsonProcessingException {
    return xmlMapper.writeValueAsString(getBermudaTriangle());
  }

  @Override
  public String getYamlString() throws JsonProcessingException {
    return yamlMapper.writeValueAsString(getBermudaTriangle());
  }

  @Override
  public String getAvroString() throws JsonProcessingException {
    return Base64
        .getEncoder()
        .encodeToString(avroMapper
            .writer(this.getAvroSchema())
            .writeValueAsBytes(getBermudaTriangle())
        );
  }

  @Override
  public String getIonString() throws JsonProcessingException {
    return Base64
        .getEncoder()
        .encodeToString(ionMapper
            .writeValueAsBytes(getBermudaTriangle())
        );
  }

  @Override
  public String getCborString() throws JsonProcessingException {
    return Base64
        .getEncoder()
        .encodeToString(cborMapper
            .writeValueAsBytes(getBermudaTriangle())
        );
  }

}
