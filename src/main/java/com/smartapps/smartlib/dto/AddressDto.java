package com.smartapps.smartlib.dto;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.smartapps.smartlib.util.SmartDateUtil;
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
@Schema(name = "Address")
public class AddressDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String customerId;
	private String addressType;
	private String addressLine1;
	private String addressLine2;
	private String city;
	private String state;
	private String country;
	private String postalCode;
	private String startDate;
	private String endDate;
	
	@JsonIgnore
	private String procTs;
	
	@JsonIgnore
	private String procApprId;
	
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
	public Date getSqlStartDate() {
		if(StringUtils.isNotEmpty(startDate)) {
			return SmartDateUtil.parseDate(startDate);
		}
		return SmartDateUtil.getCurrentSystemDate();
	}

	@JsonIgnore
	public Date getSqlEndDate() {
		if(StringUtils.isNotEmpty(endDate)) {
			return SmartDateUtil.parseDate(endDate);
		}
		return SmartDateUtil.getCurrentSystemDate();
	}
	
	@JsonIgnore
	public Timestamp getSqlProcTs() {
		if(StringUtils.isNotEmpty(procTs)) {
			return SmartDateUtil.parseTimestamp(procTs);
		}
		return SmartDateUtil.getCurrentSystemTimestamp();
	}

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
