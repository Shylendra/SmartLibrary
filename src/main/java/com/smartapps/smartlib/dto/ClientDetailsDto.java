package com.smartapps.smartlib.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ClientDetailsDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String ipAddress;
	private String latitude;
	private String longitude;

}
