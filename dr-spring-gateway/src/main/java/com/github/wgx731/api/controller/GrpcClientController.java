package com.github.wgx731.api.controller;

import com.github.wgx731.common.functions.BermudaListProtoToPojo;
import com.github.wgx731.common.pojo.BermudaTriangle;
import com.github.wgx731.proto.BermudaListReply;
import com.github.wgx731.proto.BermudaListRequest;
import com.github.wgx731.proto.BermudaServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class GrpcClientController {

  public static final String BASE_PATH = "/api/bermuda/grpc";
  static final String JSON_CONTENT_TYPE = "application/json";
  static final BermudaListProtoToPojo translator = new BermudaListProtoToPojo();

  ManagedChannel channel;
  BermudaServiceGrpc.BermudaServiceBlockingStub blockingStub;

  @Value("${grpc.host:null}")
  private String grpcHost;

  @Value("${grpc.port:-1}")
  private int grpcPort;

  @Value("${grpc.shutdown.timeout:-1}")
  private int grpcShutdownTimeout;

  /*
   * NOTE: we assume connection between gateway and grpc provider is secure,
   * therefore plain text connection is used here.
   */
  @PostConstruct
  public void start() {
    if (grpcHost == null) {
      throw new IllegalArgumentException(
          "Missing grpc.host in application.properties"
      );
    }
    if (grpcPort == -1) {
      throw new IllegalArgumentException(
          "Missing grpc.port in application.properties"
      );
    }
    if (grpcShutdownTimeout == -1) {
      throw new IllegalArgumentException(
          "Missing grpc.shutdown.timeout in application.properties"
      );
    }
    channel = ManagedChannelBuilder.forAddress(
        grpcHost,
        grpcPort
    ).usePlaintext().build();
    blockingStub = BermudaServiceGrpc.newBlockingStub(channel);
  }

  @PreDestroy
  public void shutdown() throws InterruptedException {
    channel.shutdown().awaitTermination(grpcShutdownTimeout, TimeUnit.SECONDS);
  }

  private List<BermudaTriangle> getBermudaListFromgRpc(long size) {
    BermudaListRequest request = BermudaListRequest.newBuilder().setSize(size).build();
    BermudaListReply reply;
    try {
      reply = blockingStub.getBermudaList(request);
    } catch (StatusRuntimeException e) {
      log.warn("RPC failed: {0}", e.getStatus());
      return new ArrayList<>();
    }
    return translator.apply(reply);
  }

  /**
   * Bermuda List response.
   *
   * @return Bermuda List response
   */
  @GetMapping(
      value = BASE_PATH + "/{size}",
      produces = JSON_CONTENT_TYPE
  )
  public ResponseEntity getBermudaList(@PathVariable("size") long size) {
    return ResponseEntity.ok(getBermudaListFromgRpc(size));
  }


}
