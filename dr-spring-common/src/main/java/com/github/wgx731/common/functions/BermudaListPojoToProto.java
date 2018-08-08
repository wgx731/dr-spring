package com.github.wgx731.common.functions;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.github.wgx731.common.pojo.BermudaTriangle;
import com.github.wgx731.proto.BDecimal;
import com.github.wgx731.proto.BInteger;
import com.github.wgx731.proto.BermudaListReply;
import com.github.wgx731.proto.BermudaReply;
import com.google.protobuf.ByteString;

public final class BermudaListPojoToProto
    implements Function<List<BermudaTriangle>, BermudaListReply> {

  @Override
  public BermudaListReply apply(List<BermudaTriangle> bermudaTriangles) {
    List<BermudaReply> replyList = bermudaTriangles
        .parallelStream()
        .map(b -> BermudaReply
            .newBuilder()
            .setUuid(b.getUuid())
            .setCount(b.getCount())
            .setBooleanValue(b.getBooleanValue())
            .setIntValue(b.getIntValue())
            .setLongValue(b.getLongValue())
            .setFloatValue(b.getFloatValue())
            .setDoubleValue(b.getDoubleValue())
            .setByteValue(b.getByteValue())
            .setShortValue(b.getShortValue())
            .setCharValue(String.valueOf(b.getCharValue()))
            .setDecimalValue(toBDecimal(b.getDecimalValue()))
            .setBytesValue(ByteString.copyFrom(b.getBytesValue()))
            .setStringValue(b.getStringValue())
            .build()
        ).collect(Collectors.toList());
    return BermudaListReply
        .newBuilder()
        .addAllReply(replyList)
        .build();
  }

  /**
   * translate from BigDecimal to BDecimal.
   * @param value BigDecimal value
   * @return BDecimal translated BDecimal value
   */
  public static BDecimal toBDecimal(BigDecimal value) {
    return BDecimal.newBuilder()
        .setScale(value.scale())
        .setIntVal(BInteger.newBuilder()
            .setValue(ByteString.copyFrom(
                value.toBigInteger()
                    .toByteArray()
                )
            ).build()
        ).build();
  }

}
