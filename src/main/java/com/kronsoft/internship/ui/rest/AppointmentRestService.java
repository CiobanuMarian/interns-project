package com.kronsoft.internship.ui.rest;

import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.kronsoft.internship.ui.rest.dto.AppointmentDto;

public class AppointmentRestService {
	private static final String HOSTNAME = "http://localhost:8080";
	private RestTemplate restTemplate = new RestTemplate();

	private static final Logger LOGGER = LogManager.getLogger(AppointmentRestService.class);
	
	private static AppointmentRestService INSTANCE;

	private AppointmentRestService() {
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
	}

	public static AppointmentRestService getInstance() {
		if (INSTANCE == null)
			INSTANCE = new AppointmentRestService();
		return INSTANCE;
	}

	public List<AppointmentDto> getAllAppointments() {
		ResponseEntity<List<AppointmentDto>> responseEntity = restTemplate.exchange(HOSTNAME + "/appointments",
				HttpMethod.GET, null, new ParameterizedTypeReference<List<AppointmentDto>>() {
				});

		return responseEntity.getBody();
	}

	public void deleteAppointment(Long id) {
		String uriString = UriComponentsBuilder.fromHttpUrl(HOSTNAME + "/appointment/delete").queryParam("id", id).build()
				.toUriString();
		restTemplate.exchange(uriString, HttpMethod.DELETE, null, Void.class);
	}

	public AppointmentDto createAppointment(AppointmentDto appointment) {
		HttpEntity<AppointmentDto> requestEntity = new HttpEntity<>(appointment, createHeader());
		ResponseEntity<AppointmentDto> responseEntity = restTemplate.exchange(HOSTNAME + "/appoiment/create",
				HttpMethod.POST, requestEntity, AppointmentDto.class);
		return responseEntity.getBody();
	}

	public void updateAppointment(AppointmentDto appointment) {
		HttpEntity<AppointmentDto> requestEntity = new HttpEntity<>(appointment, createHeader());
		restTemplate.exchange(HOSTNAME + "/appointment/update", HttpMethod.PUT, requestEntity, AppointmentDto.class);
	}


	private HttpHeaders createHeader() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		return headers;
	}

}
