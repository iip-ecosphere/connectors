// SPDX-FileCopyrightText: 2023 Siemens AG
// SPDX-License-Identifier: Apache-2.0


package org.test.streampipes.tutorial.entity.res;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.io.Serializable;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
		"title",
		"description",
		"keywords",
		"owner",
		"license",
		"version",
		"policy",
		"representations"
})
@Generated("jsonschema2pojo")
public class Resource implements Serializable {

	@JsonProperty("title")
	private String title;
	@JsonProperty("description")
	private String description;
	@JsonProperty("keywords")
	private List<String> keywords = null;
	@JsonProperty("owner")
	private String owner;
	@JsonProperty("license")
	private String license;
	@JsonProperty("version")
	private String version;
	@JsonProperty("policy")
	private String policy;
	@JsonProperty("representations")
	private List<Object> representations = null;
	private final static long serialVersionUID = 5576278251012444733L;

	@JsonProperty("title")
	public String getTitle() {
		return title;
	}

	@JsonProperty("title")
	public void setTitle(String title) {
		this.title = title;
	}

	public Resource withTitle(String title) {
		this.title = title;
		return this;
	}

	@JsonProperty("description")
	public String getDescription() {
		return description;
	}

	@JsonProperty("description")
	public void setDescription(String description) {
		this.description = description;
	}

	public Resource withDescription(String description) {
		this.description = description;
		return this;
	}

	@JsonProperty("keywords")
	public List<String> getKeywords() {
		return keywords;
	}

	@JsonProperty("keywords")
	public void setKeywords(List<String> keywords) {
		this.keywords = keywords;
	}

	public Resource withKeywords(List<String> keywords) {
		this.keywords = keywords;
		return this;
	}

	@JsonProperty("owner")
	public String getOwner() {
		return owner;
	}

	@JsonProperty("owner")
	public void setOwner(String owner) {
		this.owner = owner;
	}

	public Resource withOwner(String owner) {
		this.owner = owner;
		return this;
	}

	@JsonProperty("license")
	public String getLicense() {
		return license;
	}

	@JsonProperty("license")
	public void setLicense(String license) {
		this.license = license;
	}

	public Resource withLicense(String license) {
		this.license = license;
		return this;
	}

	@JsonProperty("version")
	public String getVersion() {
		return version;
	}

	@JsonProperty("version")
	public void setVersion(String version) {
		this.version = version;
	}

	public Resource withVersion(String version) {
		this.version = version;
		return this;
	}

	@JsonProperty("policy")
	public String getPolicy() {
		return policy;
	}

	@JsonProperty("policy")
	public void setPolicy(String policy) {
		this.policy = policy;
	}

	public Resource withPolicy(String policy) {
		this.policy = policy;
		return this;
	}

	@JsonProperty("representations")
	public List<Object> getRepresentations() {
		return representations;
	}

	@JsonProperty("representations")
	public void setRepresentations(List<Object> representations) {
		this.representations = representations;
	}

	public Resource withRepresentations(List<Object> representations) {
		this.representations = representations;
		return this;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(Resource.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
		sb.append("title");
		sb.append('=');
		sb.append(((this.title == null)?"<null>":this.title));
		sb.append(',');
		sb.append("description");
		sb.append('=');
		sb.append(((this.description == null)?"<null>":this.description));
		sb.append(',');
		sb.append("keywords");
		sb.append('=');
		sb.append(((this.keywords == null)?"<null>":this.keywords));
		sb.append(',');
		sb.append("owner");
		sb.append('=');
		sb.append(((this.owner == null)?"<null>":this.owner));
		sb.append(',');
		sb.append("license");
		sb.append('=');
		sb.append(((this.license == null)?"<null>":this.license));
		sb.append(',');
		sb.append("version");
		sb.append('=');
		sb.append(((this.version == null)?"<null>":this.version));
		sb.append(',');
		sb.append("policy");
		sb.append('=');
		sb.append(((this.policy == null)?"<null>":this.policy));
		sb.append(',');
		sb.append("representations");
		sb.append('=');
		sb.append(((this.representations == null)?"<null>":this.representations));
		sb.append(',');
		if (sb.charAt((sb.length()- 1)) == ',') {
			sb.setCharAt((sb.length()- 1), ']');
		} else {
			sb.append(']');
		}
		return sb.toString();
	}

}
