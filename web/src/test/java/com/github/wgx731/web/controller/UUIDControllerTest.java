package com.github.wgx731.web.controller;

import com.github.wgx731.web.service.UUIDService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;

@RunWith(SpringRunner.class)
@WebMvcTest(UUIDController.class)
@AutoConfigureRestDocs(outputDir = "target/snippets")
public class UUIDControllerTest {

	static final String TEST_UUID = "TEST-UUID";

	private UUIDController controller;

	@MockBean
	private UUIDService service;

	@Before
	public void setUp() {
		service = Mockito.mock(UUIDService.class);
		Mockito.when(service.getUUID()).thenReturn(TEST_UUID);
		controller = new UUIDController(service);
	}

	@After
	public void tearDown() {
		service = null;
		controller = null;
	}

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void getUUIDJson() throws Exception {
		ResponseEntity responseEntity = controller.getUUIDJson();
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(responseEntity.getBody().toString()).contains(TEST_UUID);
		this.mockMvc.perform(
				get(UUIDController.BASE_PATH + ".json")
		).andDo(
				document("uuid_json")
		);
	}

	@Test
	public void getUUIDXml() throws Exception {
		ResponseEntity responseEntity = controller.getUUIDXml();
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(responseEntity.getBody().toString()).contains(TEST_UUID);
		this.mockMvc.perform(
				get(UUIDController.BASE_PATH + ".xml")
		).andDo(
				document("uuid_xml")
		);
	}

	@Test
	public void getUUIDProperties() throws Exception {
		ResponseEntity responseEntity = controller.getUUIDProperties();
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(responseEntity.getBody().toString()).contains(TEST_UUID);
		this.mockMvc.perform(
				get(UUIDController.BASE_PATH + ".properties")
		).andDo(
				document("uuid_properties")
		);
	}

	@Test
	public void getUUIDCsv() throws Exception {
		ResponseEntity responseEntity = controller.getUUIDCsv();
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(responseEntity.getBody().toString()).contains(TEST_UUID);
		this.mockMvc.perform(
				get(UUIDController.BASE_PATH + ".csv")
		).andDo(
				document("uuid_csv")
		);
	}

}