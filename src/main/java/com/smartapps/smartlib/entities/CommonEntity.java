package com.smartapps.smartlib.entities;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.Version;

import org.apache.commons.lang3.StringUtils;

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
public abstract class CommonEntity {

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
	
	@PrePersist
	public void control() {

		if (procTs == null) {
			procTs = SmartDateUtil.getCurrentSystemTimestamp();
		}
		if (StringUtils.isEmpty(procUserIpAddress)) {
			procUserIpAddress = "1.1.1.1";
		}
		if (StringUtils.isEmpty(procUserLatitude)) {
			procUserLatitude = "1.1.1.1";
		}
		if (StringUtils.isEmpty(procUserLongitude)) {
			procUserLongitude = "1.1.1.1";
		}
	}
	
}
