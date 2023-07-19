package com.smartapps.smartlib.dto.trade.binance;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
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
@Schema(name = "CryptoCurrency")
public class CryptoCurrencyDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String symbol;
	private String priceChange;
	private String priceChangePercent;
	private String weightedAvgPrice;
	private String prevClosePrice;
	private String lastPrice;
	private String lastQty;
	private String bidPrice;
	private String bidQty;
	private String askPrice;
	private String askQty;
	private String openPrice;
	private String highPrice;
	private String lowPrice;
	private String volume;
	private String quoteVolume;
	private String openTime;
	private String closeTime;
	private String firstId;
	private String lastId;
	private String count;
	
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
