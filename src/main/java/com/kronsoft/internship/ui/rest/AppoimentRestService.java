package com.kronsoft.internship.ui.rest;

import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.appender.rewrite.LoggerNameLevelRewritePolicy;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.kronsoft.internship.ui.controller.AppoimentController;
import com.kronsoft.internship.ui.rest.dto.AppoimentDto;
import com.kronsoft.internship.ui.rest.dto.PatientDto;

public class AppoimentRestService {
	private static final String HOSTNAME = "http://localhost:8080";
	private RestTemplate restTemplate = new RestTemplate();

	private static final Logger LOGGER = LogManager.getLogger(AppoimentRestService.class);
	
	private static AppoimentRestService INSTANCE;

	private AppoimentRestService() {
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
	}

	public static AppoimentRestService getInstance() {
		if (INSTANCE == null)
			INSTANCE = new AppoimentRestService();
		return INSTANCE;
	}

	public List<AppoimentDto> gellAllAppoiments() {
		ResponseEntity<List<AppoimentDto>> responseEntity = restTemplate.exchange(HOSTNAME + "/appoiments",
				HttpMethod.GET, null, new ParameterizedTypeReference<List<AppoimentDto>>() {
				});

		return responseEntity.getBody();
	}

	public void deleteAppoiment(Long id) {
		String uriString = UriComponentsBuilder.fromHttpUrl(HOSTNAME + "/appoiment/delete").queryParam("id", id).build()
				.toUriString();
		restTemplate.exchange(uriString, HttpMethod.DELETE, null, Void.class);
	}

	public AppoimentDto createAppoiment(AppoimentDto appoiment) {
		HttpEntity<AppoimentDto> requestEntity = new HttpEntity<>(appoiment, createHeader());
		ResponseEntity<AppoimentDto> responseEntity = restTemplate.exchange(HOSTNAME + "/appoiment/create",
				HttpMethod.POST, requestEntity, AppoimentDto.class);
		return responseEntity.getBody();
	}

	public void updateAppoiment(AppoimentDto appoiment) {
		HttpEntity<AppoimentDto> requestEntity = new HttpEntity<>(appoiment, createHeader());
		restTemplate.exchange(HOSTNAME + "/appoiment/update", HttpMethod.PUT, requestEntity, AppoimentDto.class);
	}

	
	
	//Function to get the appoiments from a specific patient
//	public List<AppoimentDto> showPatientAppoiments(PatientDto patient) {
//		
//		HttpEntity<PatientDto> requestEntity = new HttpEntity<>(patient, createHeader());
//		
//		LOGGER.info(requestEntity.getBody().getId()+requestEntity.getBody().getFirstName());
//		ResponseEntity<List<AppoimentDto>> responseEntity = restTemplate.exchange(
//				HOSTNAME + "/appoiment/patientAppoiments", HttpMethod.GET,requestEntity,
//				new ParameterizedTypeReference<List<AppoimentDto>>() {
//				});
//
//		return responseEntity.getBody();
//
//	}

	private HttpHeaders createHeader() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		return headers;
	}

}
