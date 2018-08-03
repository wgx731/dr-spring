package com.github.wgx731.common.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.dataformat.avro.AvroSchema;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.github.wgx731.common.pojo.BermudaTriangle;

public interface BermudaConverterService {

  String getPropertiesString(BermudaTriangle bermudaTriangle) throws JsonProcessingException;

  String getCsvString(BermudaTriangle bermudaTriangle) throws JsonProcessingException;

  String getJsonString(BermudaTriangle bermudaTriangle) throws JsonProcessingException;

  String getXmlString(BermudaTriangle bermudaTriangle) throws JsonProcessingException;

  String getYamlString(BermudaTriangle bermudaTriangle) throws JsonProcessingException;

  String getAvroString(BermudaTriangle bermudaTriangle) throws JsonProcessingException;

  String getIonString(BermudaTriangle bermudaTriangle) throws JsonProcessingException;

  String getCborString(BermudaTriangle bermudaTriangle) throws JsonProcessingException;

  AvroSchema getAvroSchema() throws JsonMappingException;

  CsvSchema getCsvSchema();

}
