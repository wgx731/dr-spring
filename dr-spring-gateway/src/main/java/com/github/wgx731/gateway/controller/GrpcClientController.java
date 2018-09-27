package com.github.wgx731.gateway.controller;

import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import com.github.wgx731.common.functions.BermudaListProtoToPojo;
import com.github.wgx731.common.pojo.BermudaTriangle;
import com.github.wgx731.gateway.properties.GrpcProperties;
import com.github.wgx731.proto.BermudaListReply;
import com.github.wgx731.proto.BermudaListRequest;
import com.github.wgx731.proto.BermudaServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GrpcClientController {

  public static final String INVALID_GRPC_HOST_IN_GRPC_PROPERTIES
      = "Invalid grpc.host in grpc.properties";
  public static final String INVALID_GRPC_PORT_IN_GRPC_PROPERTIES
      = "Invalid grpc.port in grpc.properties";
  public static final String INVALID_GRPC_SHUTDOWN_TIMEOUT_IN_GRPC_PROPERTIES
      = "Invalid grpc.shutdown.timeout in grpc.properties";

  public static final String BASE_PATH = "/gateway/bermuda/grpc";

  static final String JSON_CONTENT_TYPE = "application/json";
  static final BermudaListProtoToPojo translator = new BermudaListProtoToPojo();

  ManagedChannel channel;
  BermudaServiceGrpc.BermudaServiceBlockingStub blockingStub;

  @NonNull
  private GrpcProperties grpcProperties;

  /**
   * Method to start grpc client channel.
   * NOTE: we assume connection between gateway and grpc provider is secure,
   * therefore plain text connection is used here.
   */
  @PostConstruct
  public void start() {
    log.info(String.format("grpc properties: %s", grpcProperties.toString()));
    if (!this.grpcProperties.isHostValid()) {
      throw new IllegalArgumentException(
          String.format(
              "%s host - %s",
              INVALID_GRPC_HOST_IN_GRPC_PROPERTIES,
              this.grpcProperties.getHost()
          )
      );
    }
    if (!this.grpcProperties.isPortValid()) {
      throw new IllegalArgumentException(
          String.format(
              "%s port - %d",
              INVALID_GRPC_PORT_IN_GRPC_PROPERTIES,
              this.grpcProperties.getPort()
          )
      );
    }
    if (!this.grpcProperties.isShutDownTimeoutValid()) {
      throw new IllegalArgumentException(
          String.format(
              "%s shutdown timeout - %d",
              INVALID_GRPC_SHUTDOWN_TIMEOUT_IN_GRPC_PROPERTIES,
              this.grpcProperties.getShutdownTimeout()
          )
      );
    }
    channel = ManagedChannelBuilder.forAddress(
        this.grpcProperties.getHost(),
        this.grpcProperties.getPort()
    ).usePlaintext().build();
    blockingStub = BermudaServiceGrpc.newBlockingStub(channel);
  }

  /**
   * Method to shutdown grpc client channel with timeout.
   *
   * @throws InterruptedException exception when interrupted
   */
  @PreDestroy
  public void shutdown() throws InterruptedException {
    if (channel != null) {
      channel.shutdown().awaitTermination(grpcProperties.getShutdownTimeout(), TimeUnit.SECONDS);
    } else {
      log.warn("chanel is [null] in shutdown.");
    }
  }

  void startTest(ManagedChannel channel) {
    if (channel != null) {
      this.channel = channel;
      blockingStub = BermudaServiceGrpc.newBlockingStub(this.channel);
    } else {
      log.warn("chanel is [null] in startTest.");
    }
  }

  List<BermudaTriangle> getBermudaListFromgRpc(long size) {
    BermudaListRequest request = BermudaListRequest.newBuilder().setSize(size).build();
    BermudaListReply reply;
    try {
      reply = blockingStub.getBermudaList(request);
    } catch (StatusRuntimeException e) {
      log.error(String.format("RPC failed: %s", e.getStatus()));
      throw new InternalError(e.getStatus().toString());
    }
    return translator.apply(reply);
  }

  /**
   * Grpc Bermuda List response.
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
