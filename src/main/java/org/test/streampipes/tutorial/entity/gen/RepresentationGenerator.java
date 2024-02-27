// SPDX-FileCopyrightText: 2023 Siemens AG
// SPDX-License-Identifier: Apache-2.0
package org.test.streampipes.tutorial.entity.gen;

import lombok.RequiredArgsConstructor;
import org.test.streampipes.tutorial.entity.rep.Representation;
import org.test.streampipes.tutorial.entity.rep.Source;

import java.util.Map;

@RequiredArgsConstructor
public class RepresentationGenerator {
	private final Map<String, Object> params;

	public Representation generateRepresentation() {
		return new Representation()
				.withUuid("55795317-0aaa-4fe1-b336-b2e26a00597f") // ??? TODO
				.withType("JSON")
				.withByteSize(101)
				.withName("Example Representation (Local)")   // ??? TODO
				.withSource(new Source().withType("local"));
	}
}
