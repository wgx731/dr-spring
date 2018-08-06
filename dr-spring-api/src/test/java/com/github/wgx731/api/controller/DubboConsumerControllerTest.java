package com.github.wgx731.api.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.github.wgx731.common.functions.UUIDStringSupplier;
import com.github.wgx731.common.pojo.BermudaTriangle;
import com.github.wgx731.dubbo.api.BermudaListService;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.document;
import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.documentationConfiguration;


@RunWith(SpringRunner.class)
@SuppressWarnings("checkstyle:JavadocMethod")
public class DubboConsumerControllerTest {

  private static AtomicInteger counter = new AtomicInteger(0);
  private static Random random = new Random();

  @Rule
  public final JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation(
      "target/docs/rest/snippets"
  );

  @MockBean
  private BermudaListService service;

  private DubboConsumerController controller;

  private WebTestClient webTestClient;

  private List<BermudaTriangle> getBermudaList(int size) {
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
      byte[] bytes = "dr-spring".getBytes();
      random.nextBytes(bytes);
      instance.setBytesValue(bytes);
      instance.setStringValue("hello world from dr-spring!");
      return instance;
    }).limit(size);
    return stream.collect(Collectors.toList());
  }

  private List<BermudaTriangle> testCaseReturnValue = this.getBermudaList(10);

  @Before
  public void setUp() throws Exception {
    given(this.service.getBermudaList(10)).willReturn(testCaseReturnValue);
    this.controller = new DubboConsumerController(service);
    this.webTestClient = WebTestClient.bindToController(controller)
        .configureClient()
        .filter(documentationConfiguration(this.restDocumentation))
        .build();
  }

  @After
  public void tearDown() throws Exception {
    this.testCaseReturnValue = null;
    this.service = null;
    this.controller = null;
    this.webTestClient = null;
  }

  @Test
  public void testGetBermudaList() {
    ResponseEntity responseEntity = controller.getBermudaList(10);
    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    this.webTestClient.get().uri(DubboConsumerController.BASE_PATH + "/10").exchange()
        .expectStatus().isOk().expectBody()
        .consumeWith(document("bermuda_dubbo"));
  }

}
