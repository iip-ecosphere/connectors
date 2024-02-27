// SPDX-FileCopyrightText: 2023 Siemens AG
// SPDX-License-Identifier: Apache-2.0
package org.test.streampipes.tutorial.ids.api;

import java.util.Map;

public interface IdsApi {

	/**
	 * POST Add resource
	 * @param params - parameters for response
	 * @return resource ID
	 */
	String addResource(Map<String, Object> params);

	/**
	 * POST Add representation (Local)
	 * @param resId - resource ID
	 * @param params - parameters for response
	 * @return return representationID
	 */
	String addLocalRepresentation(String resId, Map<String, Object> params);

	/**
	 * PUT Add data for local representation
	 * @param resId - resource ID
	 * @param data - data for local representation
	 * @param params - parameters for response
	 */
	void addDataForLocaleRepresentation(String resId, String data, Map<String, Object> params);

}
