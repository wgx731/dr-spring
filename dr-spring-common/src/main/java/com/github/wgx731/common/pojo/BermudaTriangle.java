package com.github.wgx731.common.pojo;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@Data
@EqualsAndHashCode
public class BermudaTriangle implements Serializable {

  @JsonProperty("uuid")
  private String uuid;

  @JsonProperty("count")
  private int count;

  @JsonProperty("boolean_value")
  private Boolean booleanValue;

  @JsonProperty("int_value")
  private Integer intValue;

  @JsonProperty("long_value")
  private Long longValue;

  @JsonProperty("float_value")
  private Float floatValue;

  @JsonProperty("double_value")
  private Double doubleValue;

  @JsonProperty("byte_value")
  private Byte byteValue;

  @JsonProperty("short_value")
  private Short shortValue;

  @JsonProperty("char_value")
  private Character charValue;

  @JsonProperty("decimal_value")
  private BigDecimal decimalValue;

  @JsonProperty("bytes_value")
  private byte[] bytesValue;

  @JsonProperty("string_value")
  private String stringValue;

}

