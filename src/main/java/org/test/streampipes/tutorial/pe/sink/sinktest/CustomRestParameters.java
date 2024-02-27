// SPDX-FileCopyrightText: 2023 Siemens AG
// SPDX-License-Identifier: Apache-2.0
package org.test.streampipes.tutorial.pe.sink.sinktest;

import lombok.Getter;
import org.apache.streampipes.model.graph.DataSinkInvocation;
import org.apache.streampipes.sdk.extractor.DataSinkParameterExtractor;
import org.apache.streampipes.wrapper.params.binding.EventSinkBindingParams;

import java.util.List;

import static org.test.streampipes.tutorial.params.ParamsKeys.*;

public class CustomRestParameters extends EventSinkBindingParams {

  @Getter
  private String host;
  @Getter
  private int port;
  @Getter
  private String owner;
  @Getter
  private String license;
  @Getter
  private String policy;
  @Getter
  private List<String> fields;
  @Getter
  private int bufferSize;

  public CustomRestParameters(DataSinkInvocation graph, DataSinkParameterExtractor extractor) {
    super(graph);

    this.host = extractor.singleValueParameter(HOST_KEY, String.class);
    this.port = extractor.singleValueParameter(PORT_KEY, Integer.class);
    this.bufferSize = extractor.singleValueParameter(COUNT_KEY, Integer.class);
    this.owner = extractor.singleValueParameter(OWNER_KEY, String.class);
    this.license = extractor.singleValueParameter(LICENSE_KEY, String.class);
    this.policy = extractor.singleValueParameter(POLICY_KEY, String.class)
            .replaceAll("\\<.*?>","")
            .replace("&nbsp;", "")
            .replace("&amp;", "");
    this.fields = extractor.mappingPropertyValues(FIELDS_KEY);
  }
}
