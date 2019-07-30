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

import com.kronsoft.internship.ui.rest.dto.AppoimentDto;

public class AppoimentRestService {
	private static final String HOSTNAME = "http://localhost:8080";
	private RestTemplate restTemplate = new RestTemplate();

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
		ResponseEntity<List<AppoimentDto>> responseEntity = restTemplate.exchange(HOSTNAME + "/appoiments", HttpMethod.GET,
				null, new ParameterizedTypeReference<List<AppoimentDto>>() {
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
		ResponseEntity<AppoimentDto> responseEntity = restTemplate.exchange(HOSTNAME + "/appoiment/create", HttpMethod.POST,
				requestEntity, AppoimentDto.class);
		return responseEntity.getBody();
	}

	public void updateAppoiment(AppoimentDto appoiment) {
		HttpEntity<AppoimentDto> requestEntity = new HttpEntity<>(appoiment, createHeader());
		restTemplate.exchange(HOSTNAME + "/appoiment/update", HttpMethod.PUT, requestEntity, AppoimentDto.class);
	}

	private HttpHeaders createHeader() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		return headers;
	}

}
