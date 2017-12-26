package com.github.wgx731.web.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class UUIDResponse {

	@JsonProperty("uuid")
	private String uuid;

	@JsonProperty("count")
	private int count;

}
