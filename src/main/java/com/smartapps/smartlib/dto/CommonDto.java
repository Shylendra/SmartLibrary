package com.smartapps.smartlib.dto;

import java.sql.Timestamp;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.smartapps.smartlib.util.SmartDateUtil;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "CommonDto")
public class CommonDto {
	
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

	@JsonIgnore
	public Timestamp getSqlProcTs() {
		if(StringUtils.isNotEmpty(procTs)) {
			return SmartDateUtil.parseTimestamp(procTs);
		}
		return SmartDateUtil.getCurrentSystemTimestamp();
	}

}
