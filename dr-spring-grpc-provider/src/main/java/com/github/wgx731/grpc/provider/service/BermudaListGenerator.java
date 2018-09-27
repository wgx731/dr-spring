package com.github.wgx731.grpc.provider.service;

import com.github.wgx731.common.functions.BermudaListPojoToProto;
import com.github.wgx731.common.functions.UUIDStringSupplier;
import com.github.wgx731.common.pojo.BermudaTriangle;
import com.github.wgx731.gateway.BermudaListService;
import com.github.wgx731.proto.BermudaListReply;
import com.github.wgx731.proto.BermudaListRequest;
import com.github.wgx731.proto.BermudaServiceGrpc;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Slf4j
public class BermudaListGenerator
    extends BermudaServiceGrpc.BermudaServiceImplBase
    implements BermudaListService {

  private static AtomicInteger counter = new AtomicInteger(0);
  private static Random random = new Random();
  private static BermudaListPojoToProto translator = new BermudaListPojoToProto();

  @Override
  public List<BermudaTriangle> getBermudaList(long size) {
    Stream<BermudaTriangle> stream = Stream.generate(() -> {
      BermudaTriangle instance = new BermudaTriangle();
      instance.setUuid(new UUIDStringSupplier().get());
      instance.setCount(counter.incrementAndGet());
      instance.setBooleanValue(random.nextBoolean());
      instance.setIntValue(random.nextInt());
      instance.setLongValue(random.nextLong());
      instance.setFloatValue(random.nextFloat());
      instance.setDoubleValue(random.nextDouble());
      instance.setByteValue(Byte.MAX_VALUE);
      instance.setShortValue(Short.MIN_VALUE);
      instance.setCharValue(Character.forDigit(random.nextInt(9), 10));
      instance.setDecimalValue(BigDecimal.valueOf(random.nextGaussian()));
      byte[] bytes = "dr-spring-grpc-provider".getBytes(StandardCharsets.UTF_8);
      random.nextBytes(bytes);
      instance.setBytesValue(bytes);
      instance.setStringValue("hello world from dr-spring-grpc-provider!");
      return instance;
    }).limit(size);
    return stream.collect(Collectors.toList());
  }

  @Override
  public void getBermudaList(
      BermudaListRequest request,
      StreamObserver<BermudaListReply> responseObserver
  ) {
    BermudaListReply reply = translator.apply(getBermudaList(request.getSize()));
    responseObserver.onNext(reply);
    responseObserver.onCompleted();
  }

}
