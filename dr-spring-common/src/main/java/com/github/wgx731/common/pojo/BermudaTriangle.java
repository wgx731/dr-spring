package com.github.wgx731.common.pojo;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class BermudaTriangle implements Serializable {

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

