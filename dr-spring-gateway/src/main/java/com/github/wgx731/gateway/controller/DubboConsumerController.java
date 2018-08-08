package com.github.wgx731.gateway.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.wgx731.gateway.BermudaListService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class DubboConsumerController {

  public static final String BASE_PATH = "/gateway/bermuda/dubbo";

  static final String JSON_CONTENT_TYPE = "application/json";

  @Reference(version = "${dr-spring.dubbo.service.version}",
      application = "${dubbo.application.id}",
      url = "${dubbo.url}")
  BermudaListService service;

  /**
   * Bermuda List response.
   *
   * @return Bermuda List response
   */
  @GetMapping(
      value = BASE_PATH + "/{size}",
      produces = JSON_CONTENT_TYPE
  )
  public ResponseEntity getBermudaList(@PathVariable("size") int size) {
    return ResponseEntity.ok(service.getBermudaList(size));
  }

}
