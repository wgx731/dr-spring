package com.github.wgx731.common.pojo;

import com.github.wgx731.common.helper.BermudaTriangleGenerator;
import com.pholser.junit.quickcheck.From;
import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JUnitQuickcheck.class)
@SuppressWarnings("checkstyle:JavadocMethod")
public class BermudaTriangleTest {

  @Property
  public void testBermudaTriangle(
      @From(BermudaTriangleGenerator.class)
          BermudaTriangle testCase
  ) {
    assertThat(testCase.toString()).contains(
        "uuid",
        "count",
        "booleanValue",
        "intValue",
        "longValue",
        "floatValue",
        "doubleValue",
        "byteValue",
        "shortValue",
        "charValue",
        "decimalValue",
        "bytesValue",
        "stringValue"
    );
  }


}
