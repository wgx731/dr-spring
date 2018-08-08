package com.github.wgx731.dubbo.provider.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.wgx731.common.functions.UUIDStringSupplier;
import com.github.wgx731.common.pojo.BermudaTriangle;
import com.github.wgx731.gateway.BermudaListService;

@Service(
    version = "${dr-spring.dubbo.service.version}",
    application = "${dubbo.application.id}",
    protocol = "${dubbo.protocol.id}",
    registry = "${dubbo.registry.id}"
)
public class BermudaListGenerator implements BermudaListService {

  private static AtomicInteger counter = new AtomicInteger(0);
  private static Random random = new Random();

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
      instance.setByteValue(Byte.MIN_VALUE);
      instance.setShortValue(Short.MAX_VALUE);
      instance.setCharValue(Character.forDigit(random.nextInt(9), 10));
      instance.setDecimalValue(BigDecimal.valueOf(random.nextGaussian()));
      byte[] bytes = "dr-spring-dubbo-provider".getBytes();
      random.nextBytes(bytes);
      instance.setBytesValue(bytes);
      instance.setStringValue("hello world from dr-spring-dubbo-provider!");
      return instance;
    }).limit(size);
    return stream.collect(Collectors.toList());
  }

}
