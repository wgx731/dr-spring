package com.github.wgx731.gateway.controller;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import akka.NotUsed;
import akka.stream.javadsl.Source;
import com.github.wgx731.common.functions.UUIDStringSupplier;
import com.github.wgx731.common.pojo.BermudaTriangle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@Slf4j
public class ReactiveController {

  public static final String BASE_PATH = "/gateway/bermuda";

  static final String JSON_CONTENT_TYPE = "application/json";

  private static AtomicInteger counter = new AtomicInteger(0);
  private static Random random = new Random();

  Stream<BermudaTriangle> getStream() {
    return Stream.generate(() -> {
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
      byte[] bytes = "dr-spring-reactive-provider".getBytes(StandardCharsets.UTF_8);
      random.nextBytes(bytes);
      instance.setBytesValue(bytes);
      instance.setStringValue("hello world from dr-spring-reactive-provider!");
      return instance;
    });
  }

  /**
   * Akka Bermuda List response.
   *
   * @return Bermuda List response
   */
  @GetMapping(
      value = BASE_PATH + "/akka/{size}",
      produces = JSON_CONTENT_TYPE
  )
  public Source<BermudaTriangle, NotUsed> getBermudaListSource(@PathVariable("size") long size) {
    return Source.from(getStream().limit(size).collect(Collectors.toList()));
  }

  /**
   * Flux Bermuda List response.
   *
   * @return Bermuda List response
   */
  @GetMapping(
      value = BASE_PATH + "/flux/{size}",
      produces = JSON_CONTENT_TYPE
  )
  public Flux<BermudaTriangle> getBermudaListFlux(@PathVariable("size") long size) {
    return Flux.fromIterable(getStream().limit(size).collect(Collectors.toList()));
  }

}
