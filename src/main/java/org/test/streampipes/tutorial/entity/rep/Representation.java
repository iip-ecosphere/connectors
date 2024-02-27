// SPDX-FileCopyrightText: 2023 Siemens AG
// SPDX-License-Identifier: Apache-2.0
package org.test.streampipes.tutorial.entity.rep;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
		"uuid",
		"type",
		"byteSize",
		"name",
		"source"
})
public class Representation implements Serializable {

	@JsonProperty("uuid")
	private String uuid;
	@JsonProperty("type")
	private String type;
	@JsonProperty("byteSize")
	private Integer byteSize;
	@JsonProperty("name")
	private String name;
	@JsonProperty("source")
	private Source source;
	private final static long serialVersionUID = 7957458421672472352L;

	@JsonProperty("uuid")
	public String getUuid() {
		return uuid;
	}

	@JsonProperty("uuid")
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Representation withUuid(String uuid) {
		this.uuid = uuid;
		return this;
	}

	@JsonProperty("type")
	public String getType() {
		return type;
	}

	@JsonProperty("type")
	public void setType(String type) {
		this.type = type;
	}

	public Representation withType(String type) {
		this.type = type;
		return this;
	}

	@JsonProperty("byteSize")
	public Integer getByteSize() {
		return byteSize;
	}

	@JsonProperty("byteSize")
	public void setByteSize(Integer byteSize) {
		this.byteSize = byteSize;
	}

	public Representation withByteSize(Integer byteSize) {
		this.byteSize = byteSize;
		return this;
	}

	@JsonProperty("name")
	public String getName() {
		return name;
	}

	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}

	public Representation withName(String name) {
		this.name = name;
		return this;
	}

	@JsonProperty("source")
	public Source getSource() {
		return source;
	}

	@JsonProperty("source")
	public void setSource(Source source) {
		this.source = source;
	}

	public Representation withSource(Source source) {
		this.source = source;
		return this;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(Representation.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
		sb.append("uuid");
		sb.append('=');
		sb.append(((this.uuid == null) ? "<null>" : this.uuid));
		sb.append(',');
		sb.append("type");
		sb.append('=');
		sb.append(((this.type == null) ? "<null>" : this.type));
		sb.append(',');
		sb.append("byteSize");
		sb.append('=');
		sb.append(((this.byteSize == null) ? "<null>" : this.byteSize));
		sb.append(',');
		sb.append("name");
		sb.append('=');
		sb.append(((this.name == null) ? "<null>" : this.name));
		sb.append(',');
		sb.append("source");
		sb.append('=');
		sb.append(((this.source == null) ? "<null>" : this.source));
		sb.append(',');
		if (sb.charAt((sb.length() - 1)) == ',') {
			sb.setCharAt((sb.length() - 1), ']');
		} else {
			sb.append(']');
		}
		return sb.toString();
	}

}
