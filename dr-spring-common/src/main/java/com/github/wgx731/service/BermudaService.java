package com.github.wgx731.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.dataformat.avro.AvroSchema;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

public interface BermudaService {

  String getPropertiesString() throws JsonProcessingException;

  String getCsvString() throws JsonProcessingException;

  String getJsonString() throws JsonProcessingException;

  String getXmlString() throws JsonProcessingException;

  String getYamlString() throws JsonProcessingException;

  String getAvroString() throws JsonProcessingException;

  String getIonString() throws JsonProcessingException;

  String getCborString() throws JsonProcessingException;

  AvroSchema getAvroSchema() throws JsonMappingException;

  CsvSchema getCsvSchema();

}
