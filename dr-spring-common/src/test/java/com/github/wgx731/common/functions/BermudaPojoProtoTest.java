package com.github.wgx731.common.functions;

import java.util.List;

import com.github.wgx731.common.helper.BermudaReplyGenerator;
import com.github.wgx731.common.helper.BermudaTriangleGenerator;
import com.github.wgx731.common.pojo.BermudaTriangle;
import com.github.wgx731.proto.BermudaListReply;
import com.github.wgx731.proto.BermudaReply;
import com.pholser.junit.quickcheck.From;
import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JUnitQuickcheck.class)
@SuppressWarnings("checkstyle:JavadocMethod")
public class BermudaPojoProtoTest {

  private BermudaListPojoToProto pojoToProto;
  private BermudaListProtoToPojo protoToPojo;

  @Before
  public void setUp() {
    pojoToProto = new BermudaListPojoToProto();
    protoToPojo = new BermudaListProtoToPojo();
  }

  @After
  public void tearDown() {
    pojoToProto = null;
    protoToPojo = null;
  }

  @Property(trials = 300)
  public void testFromPojoToProto(
      List<@From(BermudaTriangleGenerator.class) BermudaTriangle> testList
  ) {
    assertThat(testList).isEqualTo(
        protoToPojo.apply(pojoToProto.apply(testList))
    );
  }

  @Property(trials = 300)
  public void testFromProtoToPojo(
      List<@From(BermudaReplyGenerator.class) BermudaReply> testList
  ) {
    BermudaListReply reply = BermudaListReply.newBuilder()
        .addAllReply(testList)
        .build();
    assertThat(reply).isEqualTo(
        pojoToProto.apply(protoToPojo.apply(reply))
    );
  }

}
