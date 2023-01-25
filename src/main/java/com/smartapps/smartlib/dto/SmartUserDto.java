package com.smartapps.smartlib.dto;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

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
@Schema(name = "SmartUser")
public class SmartUserDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String id;
	private String name;
	private String password;
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
	private Integer primaryAddressId;
	private String primaryAddress;
	private List<AddressDto> addresses;
	
	private String procTs;
	
	private String procAppId;
	
	private String procUserId;
	
	private String procUserIpAddress;
	
	private String procUserLatitude;

	private String procUserLongitude;
	
	@JsonIgnore
	private long version;

	public String getPrimaryAddress() {
		for(AddressDto address: getAddresses()) {
			if(primaryAddressId.intValue() == address.getId()) {
				this.primaryAddress = String.format("%s, %s, %s, %s, %s, %s", 
						address.getAddressLine1(), 
						address.getAddressLine2(), 
						address.getCity(), 
						address.getState(), 
						address.getCountry(), 
						address.getPostalCode());
				return this.primaryAddress;
			}
		}
		return null;
	}
	
	public List<AddressDto> getAddresses() {
		if(this.addresses == null) {
			return new ArrayList<>();
		}
		return this.addresses;
	}

	@JsonIgnore
	public Date getSqlDob() {
		if(StringUtils.isNotEmpty(dob)) {
			return SmartDateUtil.parseDate(dob);
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
