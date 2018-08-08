package com.github.wgx731.grpc.provider;

import java.io.IOException;

import com.github.wgx731.grpc.provider.service.BermudaListGenerator;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
@SuppressWarnings("checkstyle:JavadocMethod")
@Slf4j
public class DrSpringGrpcProvider implements ApplicationRunner {

  public static final String GRPC_SERVER_PORT_OPTION_NAME = "grpc.port";

  private Server server;

  @Autowired
  private BermudaListGenerator service;

  private void start(int port) throws IOException {
    server = ServerBuilder.forPort(port)
        .addService(service)
        .build()
        .start();
    log.info(String.format("*** gRPC server started, listening on %d ...", port));
    Runtime.getRuntime().addShutdownHook(new Thread(() -> {
      System.err.println("*** shutting down gRPC server since JVM is shutting down ...");
      DrSpringGrpcProvider.this.stop();
      System.err.println("*** gRPC server shut down ...");
    }));
  }

  private void stop() {
    if (server != null) {
      server.shutdown();
    }
  }

  /**
   * Await termination on the main thread since the grpc library uses daemon threads.
   */
  private void blockUntilShutdown() throws InterruptedException {
    if (server != null) {
      server.awaitTermination();
    }
  }


  @Override
  public void run(ApplicationArguments args) throws Exception {
    if (!args.containsOption(GRPC_SERVER_PORT_OPTION_NAME)) {
      throw new IllegalArgumentException(
          String.format("Missing [%s] option.", GRPC_SERVER_PORT_OPTION_NAME)
      );
    }
    int port = -1;
    try {
      port = Integer.parseInt(String.join(
          " ",
          args.getOptionValues(GRPC_SERVER_PORT_OPTION_NAME)
      ));
    } catch (NumberFormatException e) {
      log.error("parse int failed: {0}", e);
      throw new IllegalArgumentException(
          String.format(
              "Wrong [%s] option, not a number - %s",
              GRPC_SERVER_PORT_OPTION_NAME,
              args.getOptionValues(GRPC_SERVER_PORT_OPTION_NAME)
          )
      );
    }
    if (port > 65535 || port < 0) {
      log.error("invalid port: {0}", port);
      throw new IllegalArgumentException(
          String.format(
              "Wrong [%s] option, out of range of valid port - %s",
              GRPC_SERVER_PORT_OPTION_NAME,
              args.getOptionValues(GRPC_SERVER_PORT_OPTION_NAME)
          )
      );
    }
    this.start(port);
    this.blockUntilShutdown();
  }

  public static void main(String[] args) {
    new SpringApplicationBuilder(DrSpringGrpcProvider.class)
        .web(WebApplicationType.NONE)
        .run(args);
  }

}
