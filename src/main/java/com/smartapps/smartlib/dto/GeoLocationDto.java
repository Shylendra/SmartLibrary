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
@Schema(name = "GeoLocation")
public class GeoLocationDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String ipAddress;
    private String countryCode;
    private String countryName;
    private String cityCode;
    private String cityName;
    private String postalCode;
    private String state;
    private String latitude;
    private String longitude;
    private String timeZone;

}
