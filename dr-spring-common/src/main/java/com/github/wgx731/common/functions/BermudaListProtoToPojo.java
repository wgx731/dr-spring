package com.github.wgx731.common.functions;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.github.wgx731.common.pojo.BermudaTriangle;
import com.github.wgx731.proto.BDecimal;
import com.github.wgx731.proto.BermudaListReply;

public final class BermudaListProtoToPojo
    implements Function<BermudaListReply, List<BermudaTriangle>> {

  @Override
  public List<BermudaTriangle> apply(BermudaListReply bermudaListReply) {
    return bermudaListReply
        .getReplyList()
        .parallelStream()
        .map(r -> {
          BermudaTriangle b = new BermudaTriangle();
          b.setUuid(r.getUuid());
          b.setCount(r.getCount());
          b.setBooleanValue(r.getBooleanValue());
          b.setIntValue(r.getIntValue());
          b.setLongValue(r.getLongValue());
          b.setFloatValue(r.getFloatValue());
          b.setDoubleValue(r.getDoubleValue());
          b.setByteValue(Byte.valueOf(String.valueOf(r.getByteValue())));
          b.setShortValue(Short.valueOf(String.valueOf(r.getShortValue())));
          b.setCharValue(Character.valueOf(r.getCharValue().charAt(0)));
          b.setDecimalValue(toBigDecimal(r.getDecimalValue()));
          b.setBytesValue(r.getBytesValue().toByteArray());
          b.setStringValue(r.getStringValue());
          return b;
        }).collect(Collectors.toList());
  }

  /**
   * translate from BDecimal to BigDecimal.
   * @param value BDecimal value
   * @return translated BigDecimal value
   */
  public static BigDecimal toBigDecimal(BDecimal value) {
    return new BigDecimal(
        new BigInteger(value
            .getIntVal()
            .getValue()
            .toByteArray()
        ),
        value.getScale()
    );
  }

}
