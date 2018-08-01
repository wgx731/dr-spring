package com.github.wgx731.pojo;

import java.math.BigDecimal;
import java.util.Arrays;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.github.wgx731.supplier.UUIDStringSupplier;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("checkstyle:JavadocMethod")
public class BermudaTriangleTest {

  private BermudaTriangle testCase;
  private String testUUID;
  private int testCountValue = 0;
  private boolean testBooleanValue = false;
  private int testIntValue = Integer.MAX_VALUE;
  private long testLongValue = Long.MIN_VALUE;
  private float testFloatValue = Float.MAX_VALUE;
  private double testDoubleValue = Double.MIN_VALUE;
  private byte testByteValue = Byte.MAX_VALUE;
  private short testShortValue = Short.MIN_VALUE;
  private char testCharValue = '%';
  private BigDecimal testDecimalValue = BigDecimal.TEN;
  private byte[] testBytesValue = "dr-spring".getBytes();
  private String testStringValue = new StringBuilder()
      .append("J2NmDy0xvpKsY59j6fmy\n")
      .append("LYZnHYCGcJW2hqaI3ABZ\n")
      .append("Sti8P5ZO3IEnP92ZTmp1\n")
      .append("PbBxCj3GNV5AWrKFG50G\n")
      .append("N6PwAP0PEq3obeyTeVUz\n")
      .append("bvNvGuGDMKZFACObGooZ\n")
      .append("bBbS19L2AClFxF3XQEb0\n")
      .append("pT1cDfUhC2Z8CNWC7oGA\n")
      .append("YGTZ3gCs56JfdJHcq5AS\n")
      .append("bStknUyA5CiJwlLAcQQ9\n")
      .append("OUhvPEWE9FtvXJEgX34x\n")
      .append("0Ds8cXni6kuCHRFbMzMx\n")
      .append("Lp7SzvUfQ6nSe9fk2SMJ\n")
      .append("iAUnFL3yJN2eD6TNM7Wf\n")
      .append("Jf8nRMYDOt2DNHRN98jM\n")
      .append("yLgF1odSz1nOJNUCvi5m\n")
      .append("M00I9YSgYXiS2rAtbYgO\n")
      .append("4rkX6RAuMxOWD4uLnBCI\n")
      .append("19bz1Wf6lZz1c0Wo5IUG\n")
      .append("wQaQCX4xDOJLZfihHSzb\n")
      .append("jyNkAhLM0UpXAq4EB0lK\n")
      .append("ySNqRJiS6krIVlsVaCqj\n")
      .append("TDAscqWlcCw2s0gSPDJc\n")
      .append("ICvnjoDMlsiOjqojJXAr\n")
      .append("n8WsWkDhjtB6u5wlxfVK\n")
      .append("ovxbgKzOAQYMNnWppsZI\n")
      .append("NPK1UpYu2bYcefU7i1q4\n")
      .append("UsdpCBNUGl0sZihjzHkw\n")
      .append("ONhP4cPMhxtRU4MCRQWF\n")
      .append("Jx7TJZQCatqXfUL9M5pO\n")
      .append("ae4O5Si9Dyewupfyus5z\n")
      .append("sFECNUUA7mreDV7cy1Yp\n")
      .append("htC25BF4XepuljeILNe1\n")
      .append("9fhIbfWQgUhiLBt144tu\n")
      .append("h8qCWmCwCHFUp4enHMKg\n")
      .append("FseXHRWDuMEul5PcYIt0\n")
      .append("vKpjCvx54baJpNzRhvJb\n")
      .append("Xyu8TlGsN4axaOB2aqU0\n")
      .append("0swD5s0JcAaECCe4ExAY\n")
      .append("ceGflaOBw08pmsQx5j0F\n")
      .append("OTT3d5IDX6ghL0YP6qZK\n")
      .append("0PAsw6Skn01q0XHvH1vp\n")
      .append("rDfLsIVkaMNJvJENDqTe\n")
      .append("OB56kTZYID7pw3N6kOAM\n")
      .append("2Nn9jomrO0SMzF7uSeqj\n")
      .append("4kWls4E3gYDdhWwP3Cnh\n")
      .append("UhL6aoiaMhg7Uax4bKhD\n")
      .append("IoTafbtqP8qhJkUadiiM\n")
      .append("v9Tld9PXA1ral8xgu3of\n")
      .append("vAemNKRWquqMO33Ablw3\n")
      .append("wo5zTDDUwi9AI29NO3Xc\n")
      .append("n37cu3LJAUc1WgIPkGIa\n")
      .append("czRrAwGcJkq73I79tmle\n")
      .append("nbLZwZeypMMuTl5eBHde\n")
      .append("woI33lMjv3qAKOjoE3Hz\n")
      .append("JtIQyo2gLZoY0rZ4DF7Y\n")
      .append("irKo4CJjb6e0KPnYy9u5\n")
      .append("uQwkVrodQ6ln6ylMn5eR\n")
      .append("oMN8AE8oX18gSRAvGkNb\n")
      .append("35OI2IP0gPidgZyKdc4l\n")
      .append("Y2540eX8nGNQqlEohFsA\n")
      .append("RqlrWnUnjxTCgUecQcb2\n")
      .append("EdPbZPUwYDLdX2L0b55K\n")
      .append("4URrNkYmXbjipTTVVDwy\n")
      .append("g7dIwapVzNaC37dKT73U\n")
      .append("t886xJo5TDE2L4y7avcI\n")
      .append("TK3iiSR0mktInqbCR03C\n")
      .append("tEeryxGQTTZskgYRF0qy\n")
      .append("2G8HAa0b1weuHRhTnbGO\n")
      .append("asYUcKqJm1jE1RuSLsUD\n")
      .append("hRO0KRpv7ssclKIvhgAp\n")
      .append("ws37BtIw4xEDNOkFztWH\n")
      .append("zfQjOg78L28jXuANB50S\n")
      .append("Eu5Mrr7LZeKRkUv4GNU7\n")
      .append("IJELmZS0TXyKjVXtYfhu\n")
      .append("y4MZwaH1GZT28aMj32Lk\n")
      .append("PULgsAqHFBXRFnWO1oAX\n")
      .append("ZDQJsvGBijWiS1PN1HLM\n")
      .append("lxdrkEbrA3JDXKti5SPf\n")
      .append("6LUEKvoaL6ruMXcowTVO\n")
      .append("N0ogsTPQ8P1mizhwOnLQ\n")
      .append("aVT7MiAzxhC3qkbbimKe\n")
      .append("hk8bG0JETKiaNUdEAMaG\n")
      .append("TBMhL7iRF2K3VMc0udcn\n")
      .append("cG2l3IdXR9pEmtBrVnys\n")
      .append("1g6vdF2eqz2QF1OvxV9X\n")
      .append("AbhsX0pZnKoqrKUk75EC\n")
      .append("DIaIKBhqHjJxvPMP3bGU\n")
      .append("nOmXiveZKHzJIPUUrWHO\n")
      .append("lVXIw1xggseilvwTCssR\n")
      .append("LoE8TgfAanW6iBSTcYd0\n")
      .append("8Mb5ZgIziZZTzzSwA6Lr\n")
      .append("vspOnsFHtlBbZBER9kqS\n")
      .append("Mh8WVipxhloKDsTQIvMF\n")
      .append("VxcGhspm6losI0AJZIv3\n")
      .append("HrYsF6eh7Jfyc5ZGaiwe\n")
      .append("CnznMEAGHPDxTGtHwwS2\n")
      .append("FTlXYkJojXQRWjJqggg7\n")
      .append("0kl4KLg1r7FW7jf4dhGk\n")
      .append("aPaShickD3bvNIoBFDOf\n")
      .append("3GiS1hNWrfRvee5Iglf3\n")
      .append("ip2UyXCuYZgB7oQM41SA\n")
      .append("JwrgL6uEUAiz6Kal99Cb\n")
      .append("GVWpvqbZIgzJin2ls5Je\n")
      .append("ZhDswlM3MW11KEmgaTiZ\n")
      .append("onZeSBhVDCc2QiiGFlMT\n")
      .append("aZ1hB1Ovd8xGgIMNnDYF\n")
      .append("IULdlqfyMUlldsBSQjz2\n")
      .append("EkEAsi8GaaAGnNt4Y5pZ\n")
      .append("NHBUdGFeghcDVwcyVeTO\n")
      .append("Dk1jel6zpo4dgKl213DB\n")
      .append("rhZV26VYx8sV8SCciWpo\n")
      .append("TwirLY5ycaLwVJPSFlS8\n")
      .append("GkdTrxDzT3q5DuCmQNL7\n")
      .append("2IMYrenPnUQd99VhEPRY\n")
      .append("mLjQlJEEj0Dunawh87sn\n")
      .append("nTAJhUCDObI4pNExFBFr\n")
      .append("onXrIbzSMh54qSo0HomJ\n")
      .append("qULx5139FN5PaQH6wFxD\n")
      .append("wqOigLEHunHr1rbaJVpj\n")
      .append("UgKeZJhwWaeIesWfs4RE\n")
      .append("j6yB84fcIS4d16Nez0YB\n")
      .append("oS6FIjWZcIXIrbJFRyvt\n")
      .append("4XaBb75xtWO8TzRHCDWa\n")
      .append("aqA0oZL3LHnPYd7dy0vi\n")
      .append("TQa9RhmfLVeEzl053Q1s\n")
      .append("9agh6QWUXUH19HGEGk81\n")
      .append("mTwhWiYRHTO3WKngYURr\n")
      .append("AxKDM5hZxk2LFxgRTeXn\n")
      .append("1B7lq6v63bHA0xe1i558\n")
      .append("YL4VD3lQecUil7cyyj6k\n")
      .append("x8ICYBR2hgINhYKK6SCd\n")
      .append("GEU4SMHvn9PKmlCDoYex\n")
      .append("Hz7aS4MIeEOFMGfDQkBx\n")
      .append("TuSpv9j3tKCVH8KgZj9d\n")
      .append("T5cIuPPmZSD4ZIMngocG\n")
      .append("XnA6I9oiRBeKRb6ipfeU\n")
      .toString();

  @Before
  public void setUp() {
    testUUID = new UUIDStringSupplier().get();
    testCase = new BermudaTriangle();
    testCase.setUuid(testUUID);
    testCase.setCount(testCountValue);
    testCase.setBooleanValue(testBooleanValue);
    testCase.setIntValue(testIntValue);
    testCase.setLongValue(testLongValue);
    testCase.setFloatValue(testFloatValue);
    testCase.setDoubleValue(testDoubleValue);
    testCase.setByteValue(testByteValue);
    testCase.setShortValue(testShortValue);
    testCase.setCharValue(testCharValue);
    testCase.setDecimalValue(testDecimalValue);
    testCase.setBytesValue(testBytesValue);
    testCase.setStringValue(testStringValue);
  }

  @After
  public void tearDown() {
    testCase = null;
  }

  @Test
  public void getAvroSchema() throws JsonMappingException {
    String schemaString = BermudaTriangle.getAvroSchema().getAvroSchema().toString();
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
    BermudaTriangle.getAvroSchema();
  }

  @Test
  public void getCsvSchema() {
    String schemaString = BermudaTriangle.getCsvSchema().toString();
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
    BermudaTriangle.getCsvSchema();
  }

  @Test
  public void testToString() {
    assertThat(testCase.toString()).contains(
        testStringValue,
        testUUID,
        Arrays.toString(testBytesValue),
        String.valueOf(testBooleanValue),
        String.valueOf(testByteValue),
        String.valueOf(testCharValue),
        String.valueOf(testCountValue),
        String.valueOf(testDecimalValue),
        String.valueOf(testDoubleValue),
        String.valueOf(testFloatValue),
        String.valueOf(testIntValue),
        String.valueOf(testLongValue),
        String.valueOf(testShortValue)
    );
  }

}
