// SPDX-FileCopyrightText: 2023 Siemens AG
// SPDX-License-Identifier: Apache-2.0

package org.test.streampipes.tutorial.entity.gen;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.test.streampipes.tutorial.entity.res.Resource;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ResourceGenerator {
	private final Map<String, Object> params;

	public Resource generateResource() {
		return new Resource()
				.withTitle("Sample Resource")  // ??? TODO
				.withDescription("desk")  // ??? TODO
				.withKeywords(null != params.get("keywords") ? ((List<String>) params.get("keywords"))
						.stream()
						.map(Objects::toString)
						.collect(Collectors.toList()) : new ArrayList<>())
				.withOwner(Objects.toString(params.get("owner")))
				.withLicense(Objects.toString(params.get("license")))
				.withVersion("1.0")
				.withPolicy(Objects.toString(params.get("policy")))
				.withRepresentations(new ArrayList<>());
	}
}
