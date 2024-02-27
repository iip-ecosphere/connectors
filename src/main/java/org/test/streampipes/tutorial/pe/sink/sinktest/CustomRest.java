// SPDX-FileCopyrightText: 2023 Siemens AG
// SPDX-License-Identifier: Apache-2.0
package org.test.streampipes.tutorial.pe.sink.sinktest;

import lombok.extern.slf4j.Slf4j;
import org.apache.streampipes.commons.exceptions.SpRuntimeException;
import org.apache.streampipes.dataformat.SpDataFormatDefinition;
import org.apache.streampipes.dataformat.json.JsonDataFormatDefinition;
import org.apache.streampipes.model.runtime.Event;
import org.apache.streampipes.wrapper.context.EventSinkRuntimeContext;
import org.apache.streampipes.wrapper.runtime.EventSink;
import org.test.streampipes.tutorial.ids.api.IdsApi;
import org.test.streampipes.tutorial.ids.api.IdsApiImpl;
import org.test.streampipes.tutorial.pe.sink.sinktest.buffer.BufferListener;
import org.test.streampipes.tutorial.pe.sink.sinktest.buffer.MessageBuffer;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class CustomRest implements EventSink<CustomRestParameters>, BufferListener {

	private String host;
	private int port;
	private String owner;
	private String license;
	private String policy;
	private List<String> fields;

	private MessageBuffer buffer;

	private SpDataFormatDefinition dataFormatDefinition;

	private IdsApi api;

	public CustomRest() {
		this.dataFormatDefinition = new JsonDataFormatDefinition();
	}

	@Override
	public void onInvocation(CustomRestParameters parameters, EventSinkRuntimeContext runtimeContext) {
		this.host = parameters.getHost();
		this.port = parameters.getPort();
		this.owner = parameters.getOwner();
		this.license = parameters.getLicense();
		this.policy = parameters.getPolicy();
		this.fields = parameters.getFields();

		this.buffer = new MessageBuffer(parameters.getBufferSize());
		this.buffer.addListener(this);
		try {
			this.api = new IdsApiImpl(host, port);
		} catch (NoSuchAlgorithmException | KeyStoreException | KeyManagementException e) {
			log.info("Failed to create IDS API...");
			e.printStackTrace();
		}
	}

	@Override
	public void onEvent(Event event) {
		Map<String, Object> outEventMap = event.getSubset(fields).getRaw();
		try {
			String json = new String(dataFormatDefinition.fromMap(outEventMap));
			this.buffer.addMessage(json);
		} catch (SpRuntimeException e) {
			log.info("Could not parse incoming event");
		}
	}

	@Override
	public void onDetach() {
		buffer.removeListener(this);
	}

	@Override
	public void bufferFull(String messagesJsonArray) { // TODO не вызывается, если буфер не полный (не будут отправлены последние данные)

		if (null == api) {
			log.info("Unable to send data to IDS. IDS API not init...");
			return;
		}

		log.info("Sending data to the IDS connector: {}.", messagesJsonArray);

		Map<String, Object> params = new HashMap<>();
		params.put("keywords", fields);
		params.put("owner", owner);
		params.put("license", license);
		params.put("policy", policy);

		String resId = api.addResource(params);
		if (null != resId) {
			String representId = api.addLocalRepresentation(resId, null);
			if (null != representId) {
				api.addDataForLocaleRepresentation(resId, messagesJsonArray, null);
			}
		}

		log.info("Sending data to the IDS connector completed.");
	}
}
