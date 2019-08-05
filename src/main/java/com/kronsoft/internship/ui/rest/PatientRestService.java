package com.kronsoft.internship.ui.rest;

import java.util.Collections;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.kronsoft.internship.ui.rest.dto.PatientDto;

public class PatientRestService {
	private static final String HOSTNAME = "http://localhost:8080";
	private RestTemplate restTemplate = new RestTemplate();

	private static PatientRestService INSTANCE;

	private PatientRestService() {
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
	}

	public static PatientRestService getInstance() {
		if (INSTANCE == null)
			INSTANCE = new PatientRestService();
		return INSTANCE;
	}

	public List<PatientDto> getAllPatients() {
		ResponseEntity<List<PatientDto>> responseEntity = restTemplate.exchange(HOSTNAME + "/patients", HttpMethod.GET,
				null, new ParameterizedTypeReference<List<PatientDto>>() {
				});

		return responseEntity.getBody();
	}

	public void deletePatient(Long id) {
		String uriString = UriComponentsBuilder.fromHttpUrl(HOSTNAME + "/patient/delete").queryParam("id", id).build()
				.toUriString();
		restTemplate.exchange(uriString, HttpMethod.DELETE, null, Void.class);
	}

	public PatientDto createPatient(PatientDto patient) {
		HttpEntity<PatientDto> requestEntity = new HttpEntity<>(patient, createHeader());
		ResponseEntity<PatientDto> responseEntity = restTemplate.exchange(HOSTNAME + "/patient/create", HttpMethod.POST,
				requestEntity, PatientDto.class);
		return responseEntity.getBody();
	}

	public void updatePatient(PatientDto patient) {
		HttpEntity<PatientDto> requestEntity = new HttpEntity<>(patient, createHeader());
		restTemplate.exchange(HOSTNAME + "/patient/update", HttpMethod.PUT, requestEntity, PatientDto.class);
	}

	private HttpHeaders createHeader() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		return headers;
	}

}
