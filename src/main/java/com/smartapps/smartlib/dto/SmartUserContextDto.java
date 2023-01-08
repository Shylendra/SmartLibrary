package com.smartapps.smartlib.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Schema(name = "SmartUserContext")
public class SmartUserContextDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String id;
	private String appId;
	private String name;
	private String roles;
	private String firstName;
	private String middleName;
	private String lastName;
	private String about;
	private String company;
	private String job;
	private String gender;
	private String dob;
	private String phone;
	private String email;
	private String profilePhotoPath;
	private Boolean active;
	private String twitterUrl;
	private String facebookUrl;
	private String instagramUrl;
	private String linkedInUrl;
	
}
