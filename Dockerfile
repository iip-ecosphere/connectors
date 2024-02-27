# SPDX-FileCopyrightText: 2023 Siemens AG
# SPDX-License-Identifier: Apache-2.0
# openjdk docker info https://hub.docker.com/_/openjdk
# openjdk license https://openjdk.org/legal/gplv2+ce.html
FROM openjdk:8-jdk-alpine

EXPOSE 8090
ENV CONSUL_LOCATION consul

COPY ./target/sink-tutorial.jar  /streampipes-processing-element-container.jar

ENTRYPOINT ["java", "-jar", "/streampipes-processing-element-container.jar"]
