package com.github.wgx731.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.javaprop.JavaPropsMapper;
import com.fasterxml.jackson.dataformat.javaprop.JavaPropsSchema;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.github.wgx731.web.response.UUIDResponse;
import com.github.wgx731.web.service.UUIDService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller to generate random UUID
 */
@RestController
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping(UUIDController.BASE_PATH)
public class UUIDController {

	public static final String BASE_PATH = "/api/uuid";
	public static final String SEPARATOR = "<-===->";

	private static int counter = 0;

	private ObjectMapper jsonMapper = new ObjectMapper();

	private ObjectMapper xmlMapper = new XmlMapper();

	private ObjectMapper propsMapper = new JavaPropsMapper();

	private ObjectMapper csvMapper = new CsvMapper();

	@NonNull
	private UUIDService service;

	@GetMapping(
			produces = "application/json"
	)
	public ResponseEntity getUUIDJson() throws JsonProcessingException {
		return ResponseEntity.ok(jsonMapper.writeValueAsString(getUUIDResponse()));
	}

	@GetMapping(
			produces = "application/xml"
	)
	public ResponseEntity getUUIDXml() throws JsonProcessingException {
		return ResponseEntity.ok(xmlMapper.writeValueAsString(getUUIDResponse()));
	}

	@GetMapping(
			produces = "text/plain"
	)
	public ResponseEntity getUUIDProperties() throws JsonProcessingException {
		return ResponseEntity.ok(propsMapper
				.writer(JavaPropsSchema.emptySchema().withKeyValueSeparator(SEPARATOR))
				.writeValueAsString(getUUIDResponse()));
	}

	@GetMapping(
			produces = "text/csv"
	)
	public ResponseEntity getUUIDCsv() throws JsonProcessingException {
		return ResponseEntity.ok(((CsvMapper) csvMapper)
				.writerWithSchemaFor(UUIDResponse.class)
				.writeValueAsString(getUUIDResponse()));
	}


	private UUIDResponse getUUIDResponse() {
		return UUIDResponse.builder()
				.uuid(service.getUUID())
				.count(counter++)
				.build();
	}

}
