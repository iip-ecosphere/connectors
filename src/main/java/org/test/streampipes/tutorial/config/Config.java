// SPDX-FileCopyrightText: 2023 Siemens AG
// SPDX-License-Identifier: Apache-2.0
package org.test.streampipes.tutorial.config;

import org.apache.streampipes.config.SpConfig;
import org.apache.streampipes.container.model.PeConfig;

import static org.test.streampipes.tutorial.config.ConfigKeys.*;

public enum Config implements PeConfig {
  INSTANCE;

  private SpConfig config;
  private final static String SERVICE_ID= "pe/org.test.streampipes.tutorial.sink.jvm";

  Config() {
    config = SpConfig.getSpConfig(SERVICE_ID);
    config.register(HOST, "sink-tutorial", "Data sink host");
    config.register(PORT, 8090, "Data sink port");
    config.register(SERVICE_NAME, "sink tutorial", "Data sink service name");
  }

  @Override
  public String getHost() {
    return config.getString(HOST);
  }

  @Override
  public int getPort() {
    return config.getInteger(PORT);
  }

  @Override
  public String getId() {
    return SERVICE_ID;
  }

  @Override
  public String getName() {
    return config.getString(SERVICE_NAME);
  }

}
