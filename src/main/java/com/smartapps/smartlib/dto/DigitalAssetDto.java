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
@Schema(name = "DigitalAsset")
public class DigitalAssetDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String url;
	private String name;
	private String type;

}
