// SPDX-FileCopyrightText: 2023 Siemens AG
// SPDX-License-Identifier: Apache-2.0
package org.test.streampipes.tutorial.ids.api;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UriBuilder {
	private final String host;
	private final int port;

	private final String ADMIN = "/admin";
	private final String API = "/api";
	private final String RESOURCES = "/resources";
	private final String RESOURCE = "/resource";
	private final String REPRESENTATION = "/representation";
	private final String DATA = "/data";

	public String addResourceUri() {
		return host + ":" + port + ADMIN + API + RESOURCES + RESOURCE;
	}

	public String addRepresentationLocalUri(String resId) {
		return host + ":" + port + ADMIN + API + RESOURCES + "/" + resId + REPRESENTATION;
	}

	public String addDataForLocalepresentationUri(String resId) {
		return host + ":" + port + ADMIN + API + RESOURCES + "/" + resId + DATA;
	}
}
