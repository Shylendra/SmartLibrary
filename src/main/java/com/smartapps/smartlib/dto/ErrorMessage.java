package com.smartapps.smartlib.dto;

import java.io.Serializable;
import java.util.List;

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
@Schema(name = "ErrorMessage")
public class ErrorMessage implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int code;
	private String timestamp;
	private String message;
	private String description;
	private List<String> errors;

}
