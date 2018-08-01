package com.github.wgx731.service;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface BermudaService {

  String getPropertiesString() throws JsonProcessingException;

  String getCsvString() throws JsonProcessingException;

  String getJsonString() throws JsonProcessingException;

  String getXmlString() throws JsonProcessingException;

  String getYamlString() throws JsonProcessingException;

  String getAvroString() throws JsonProcessingException;

  String getIonString() throws JsonProcessingException;

  String getCborString() throws JsonProcessingException;

}
