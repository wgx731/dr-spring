package com.github.wgx731.common.helper;

import java.math.BigDecimal;

import com.github.wgx731.common.functions.UUIDStringSupplier;
import com.github.wgx731.common.pojo.BermudaTriangle;
import com.pholser.junit.quickcheck.generator.GenerationStatus;
import com.pholser.junit.quickcheck.generator.Generator;
import com.pholser.junit.quickcheck.random.SourceOfRandomness;

@SuppressWarnings("checkstyle:JavadocMethod")
public class BermudaTriangleGenerator extends Generator<BermudaTriangle> {

  public BermudaTriangleGenerator() {
    super(BermudaTriangle.class);
  }

  @Override
  public BermudaTriangle generate(SourceOfRandomness r, GenerationStatus s) {
    BermudaTriangle testCase = new BermudaTriangle();
    testCase.setUuid(new UUIDStringSupplier().get());
    testCase.setCount(gen().type(Integer.class).generate(r, s));
    testCase.setBooleanValue(gen().type(Boolean.class).generate(r, s));
    testCase.setIntValue(gen().type(Integer.class).generate(r, s));
    testCase.setLongValue(gen().type(Long.class).generate(r, s));
    testCase.setFloatValue(gen().type(Float.class).generate(r, s));
    testCase.setDoubleValue(gen().type(Double.class).generate(r, s));
    testCase.setByteValue(gen().type(Byte.class).generate(r, s));
    testCase.setShortValue(gen().type(Short.class).generate(r, s));
    testCase.setCharValue(gen().type(Character.class).generate(r, s));
    testCase.setDecimalValue(gen().type(BigDecimal.class).generate(r, s));
    testCase.setBytesValue(gen().type(byte[].class).generate(r, s));
    testCase.setStringValue(gen().type(String.class).generate(r, s));
    return testCase;
  }

}
