// SPDX-FileCopyrightText: 2023 Siemens AG
// SPDX-License-Identifier: Apache-2.0

package org.test.streampipes.tutorial.pe.sink.sinktest;

import org.apache.streampipes.model.DataSinkType;
import org.apache.streampipes.model.graph.DataSinkDescription;
import org.apache.streampipes.model.graph.DataSinkInvocation;
import org.apache.streampipes.model.schema.PropertyScope;
import org.apache.streampipes.sdk.builder.DataSinkBuilder;
import org.apache.streampipes.sdk.builder.StreamRequirementsBuilder;
import org.apache.streampipes.sdk.extractor.DataSinkParameterExtractor;
import org.apache.streampipes.sdk.helpers.EpRequirements;
import org.apache.streampipes.sdk.helpers.Labels;
import org.apache.streampipes.wrapper.standalone.ConfiguredEventSink;
import org.apache.streampipes.wrapper.standalone.declarer.StandaloneEventSinkDeclarer;
import org.apache.streampipes.sdk.helpers.*;
import org.apache.streampipes.sdk.utils.Assets;

import static org.test.streampipes.tutorial.params.ParamsKeys.*;

public class CustomRestController extends StandaloneEventSinkDeclarer<CustomRestParameters> {

	@Override
	public DataSinkDescription declareModel() {
		return DataSinkBuilder.create("org.test.streampipes.tutorial.pe.sink.sinktest")
						.category(DataSinkType.NOTIFICATION)
						.withAssets(Assets.DOCUMENTATION, Assets.ICON)
						.withLocales(Locales.EN)
						.requiredStream(StreamRequirementsBuilder
								.create()
								.requiredPropertyWithNaryMapping(EpRequirements.anyProperty(), Labels.withId(FIELDS_KEY), PropertyScope.NONE)
								.build())
						.requiredTextParameter(Labels.withId(HOST_KEY))
						.requiredIntegerParameter(Labels.withId(PORT_KEY), 8080)
						.requiredIntegerParameter(Labels.withId(COUNT_KEY), 1, 1000000, 1)
						.requiredTextParameter(Labels.withId(OWNER_KEY))
						.requiredTextParameter(Labels.withId(LICENSE_KEY))
						.requiredTextParameter(Labels.withId(POLICY_KEY), true, false, true)
						.build();
	}

	@Override
	public ConfiguredEventSink<CustomRestParameters> onInvocation(DataSinkInvocation graph, DataSinkParameterExtractor extractor) {

		CustomRestParameters params = new CustomRestParameters(graph,  extractor);

		return new ConfiguredEventSink<>(params, CustomRest::new);
	}
}
