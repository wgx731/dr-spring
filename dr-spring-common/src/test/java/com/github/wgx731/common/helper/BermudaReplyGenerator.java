package com.github.wgx731.common.helper;

import java.math.BigDecimal;

import com.github.wgx731.common.functions.BermudaListPojoToProto;
import com.github.wgx731.common.functions.UUIDStringSupplier;
import com.github.wgx731.proto.BermudaReply;
import com.google.protobuf.ByteString;
import com.pholser.junit.quickcheck.generator.GenerationStatus;
import com.pholser.junit.quickcheck.generator.Generator;
import com.pholser.junit.quickcheck.random.SourceOfRandomness;

@SuppressWarnings("checkstyle:JavadocMethod")
public class BermudaReplyGenerator extends Generator<BermudaReply> {

  public BermudaReplyGenerator() {
    super(BermudaReply.class);
  }

  @Override
  public BermudaReply generate(SourceOfRandomness r, GenerationStatus s) {
    return BermudaReply.newBuilder()
        .setUuid(new UUIDStringSupplier().get())
        .setCount(gen().type(Integer.class).generate(r, s))
        .setBooleanValue(gen().type(Boolean.class).generate(r, s))
        .setIntValue(gen().type(Integer.class).generate(r, s))
        .setLongValue(gen().type(Long.class).generate(r, s))
        .setFloatValue(gen().type(Float.class).generate(r, s))
        .setDoubleValue(gen().type(Double.class).generate(r, s))
        .setByteValue(gen().type(Byte.class).generate(r, s))
        .setShortValue(gen().type(Short.class).generate(r, s))
        .setCharValue(String.valueOf(gen().type(Character.class).generate(r, s)))
        .setDecimalValue(BermudaListPojoToProto.toBDecimal(
            gen().type(BigDecimal.class).generate(r, s)
        ))
        .setBytesValue(ByteString.copyFrom(gen().type(byte[].class).generate(r, s)))
        .setStringValue(gen().type(String.class).generate(r, s))
        .build();
  }

}
