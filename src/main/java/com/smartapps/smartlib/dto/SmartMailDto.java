package com.smartapps.smartlib.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.smartapps.smartlib.util.SmartLibraryUtil;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "SmartMail")
public class SmartMailDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String from;
	private String to;
	private String cc;
	private String bcc;
	private String subject;
	private String userName;
	private String text;
	private String pathToAttachment;
	private String accountActivationLink;
	private String emailTemplate;
	Map<String, Object> emailProperties = new HashMap<>();
	
	@JsonIgnore
	private String procTs;
	
	@JsonIgnore
	private String procAppId;
	
	@JsonIgnore
	private String procUserId;
	
	@JsonIgnore
	private String procUserIpAddress;
	
	@JsonIgnore
	private String procUserLatitude;

	@JsonIgnore
	private String procUserLongitude;
	
	@JsonIgnore
	private long version;

	@Override
	public String toString() {
		try {
			return SmartLibraryUtil.mapToString(this, true);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
