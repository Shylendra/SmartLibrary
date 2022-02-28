package com.smartapps.smartlib.entities;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import com.smartapps.smartlib.converter.TrimConverter;
import com.smartapps.smartlib.util.SmartDateUtil;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class BaseEntity {

	@Column(name = "PROC_TS")
	private Timestamp procTs = SmartDateUtil.getCurrentSystemTimestamp();

	@Column(name = "PROC_APP_ID")
	@Convert(converter = TrimConverter.class)
	private String procApprId;

	@Column(name = "PROC_USER_ID")
	@Convert(converter = TrimConverter.class)
	private String procUserId;
	
	@Column(name = "PROC_USER_IP_ADDRESS")
	@Convert(converter = TrimConverter.class)
	private String procUserIpAddress;
	
	@Column(name = "PROC_USER_LATITUDE")
	@Convert(converter = TrimConverter.class)
	private String procUserLatitude;
	
	@Column(name = "PROC_USER_LONGITUDE")
	@Convert(converter = TrimConverter.class)
	private String procUserLongitude;

	@Version
	@Column(name = "VERS_ID", columnDefinition = "smallint")
	private long version;
	
}
