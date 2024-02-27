// SPDX-FileCopyrightText: 2023 Siemens AG
// SPDX-License-Identifier: Apache-2.0
package org.test.streampipes.tutorial.ids.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.TrustAllStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.lang.Nullable;
import org.springframework.web.client.RestTemplate;
import org.test.streampipes.tutorial.entity.gen.RepresentationGenerator;
import org.test.streampipes.tutorial.entity.gen.ResourceGenerator;

import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

@Slf4j
public class IdsApiImpl implements IdsApi {
	private RestTemplate restTemplate;
	private UriBuilder uriBuilder;

	public IdsApiImpl(String host, int port) throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
		HttpClient httpClient = HttpClients
				.custom()
				.setSSLContext(new SSLContextBuilder().loadTrustMaterial(null, TrustAllStrategy.INSTANCE).build()) // turn off ssl
				.setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE)
				.build();

		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
		requestFactory.setHttpClient(httpClient);

		this.restTemplate = new RestTemplate(requestFactory);
		this.uriBuilder = new UriBuilder(host, port);
	}

	@Override
	@Nullable
	public String addResource(Map<String, Object> params) {
		ResponseEntity<String> response = null;

		try {
			response = restTemplate.exchange(uriBuilder.addResourceUri(), HttpMethod.POST, getHttpEntityForAddResource(params), String.class);
		} catch (Exception e) { // TODO
			log.info(ExceptionUtils.getStackTrace(e));
		}

		if (null != response && HttpStatus.CREATED.value() == response.getStatusCodeValue()) {
			String resId = response.getBody();
			log.info("status for Add Resource = {}", response.getStatusCode());
			log.info("res_id = {}", resId);
			return resId;
		}
		log.info("Failed to add resource...");
		return null;
	}

	@Override
	@Nullable
	public String addLocalRepresentation(String resId, Map<String, Object> params) {
		ResponseEntity<String> response = null;
		try {
			response = restTemplate.exchange(uriBuilder.addRepresentationLocalUri(resId), HttpMethod.POST, getHttpEntityForAddLocalRepresentation(params), String.class);
		} catch (Exception e) {  // TODO
			log.info(ExceptionUtils.getStackTrace(e));
		}

		if (null != response && HttpStatus.CREATED.value() == response.getStatusCodeValue()) {
			String representationId = response.getBody();
			log.info("status for Add Locale Representation = {}", response.getStatusCode());
			log.info("representation_id = {}", representationId);
			return representationId;
		}
		log.info("Failed to add local representation...");
		return null;
	}

	@Override
	public void addDataForLocaleRepresentation(String resId, String data, Map<String, Object> params) {
		ResponseEntity<String> response = null;
		try {
			response = restTemplate.exchange(uriBuilder.addDataForLocalepresentationUri(resId), HttpMethod.PUT, getHttpEntityForAddDataForLocaleRepresentation(data), String.class);
		} catch (Exception e) {   // TODO
			log.info(ExceptionUtils.getStackTrace(e));
		}

		if (null != response && HttpStatus.CREATED.value() == response.getStatusCodeValue()) {
			log.info("status for Add Data For Locale Representation = {}", response.getStatusCode());
		} else {
			log.info("Failed to add data for local representation...");
		}
	}

	private HttpEntity<String> getHttpEntityForAddResource(Map<String, Object> params) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString((new ResourceGenerator(params)).generateResource());

		log.info(json);

		return new HttpEntity<>(json, getDefaultHeaders());
	}

	private HttpEntity<String> getHttpEntityForAddLocalRepresentation(Map<String, Object> params) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString((new RepresentationGenerator(params).generateRepresentation()));
		log.info(json);

		return new HttpEntity<>(json, getDefaultHeaders());
	}

	private HttpEntity<String> getHttpEntityForAddDataForLocaleRepresentation(String data) {
		return new HttpEntity<>(data, getDefaultHeaders());
	}

	private HttpHeaders getDefaultHeaders() {
		HttpHeaders headers = new HttpHeaders();

		byte[] token = Base64.encodeBase64(("admin" + ":" + "password").getBytes(StandardCharsets.UTF_8));
		headers.add(HttpHeaders.AUTHORIZATION, "Basic " + new String(token, StandardCharsets.UTF_8));
		headers.add(HttpHeaders.CONTENT_TYPE, "application/json");

		return headers;
	}
}
