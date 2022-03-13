package com.smartapps.smartlib.util;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.net.InetAddress;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.AddressNotFoundException;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import com.smartapps.smartlib.dto.ErrorMessage;
import com.smartapps.smartlib.dto.GeoLocationDto;
import com.smartapps.smartlib.exception.ResourceNotFoundException;

public class SmartLibraryUtil {

	public static final String APPLICATION_ID_PREFIX = "SMART_";
	
	 public static boolean isValidAppid(String appId) {
		 if(StringUtils.isNotEmpty(appId) && appId.startsWith(APPLICATION_ID_PREFIX)) {
			return true;
		 }
		 return false;
	 }

	public static GeoLocationDto retrievepGeoLocation(final String ipAddress, final String dbLocation)
			throws IOException, GeoIp2Exception {
		CityResponse cityResponse = createCityResponse(ipAddress, dbLocation);
		return GeoLocationDto.builder().ipAddress(ipAddress).countryCode("")
				.countryName(cityResponse.getCountry().getName()).cityCode("")
				.cityName(cityResponse.getCity().getName()).postalCode(cityResponse.getPostal().getCode())
				.state(cityResponse.getLeastSpecificSubdivision().getName())
				.latitude(cityResponse.getLocation().getLatitude().toString())
				.longitude(cityResponse.getLocation().getLongitude().toString())
				.timeZone(cityResponse.getLocation().getTimeZone()).build();
	}

	public static CityResponse createCityResponse(final String ipAddress, final String dbLocation)
			throws IOException, GeoIp2Exception {

		// https://www.baeldung.com/geolocation-by-ip-with-maxmind

		CityResponse cityResponse = null;
		File database = new File(dbLocation);
		try {
			DatabaseReader dbReader = new DatabaseReader.Builder(database).build();
			cityResponse = dbReader.city(InetAddress.getByName(ipAddress));
		} catch (AddressNotFoundException e) {
			throw new ResourceNotFoundException(
					"Address '" + ipAddress + "' not found in the database.");
		}

		return cityResponse;
	}

	public static URI constructUri(String url, Map<String, String> pathParams, Map<String, String> queryParams) {

		MultiValueMap<String, String> queryParamsMultivalueMap = new LinkedMultiValueMap<String, String>();
		if (queryParams != null && !queryParams.isEmpty()) {
			queryParamsMultivalueMap.setAll(queryParams);
		}

		URI uri = UriComponentsBuilder.fromUriString(url).queryParams(queryParamsMultivalueMap)
				.buildAndExpand(pathParams == null ? new HashMap<String, String>() : pathParams).toUri();

		return uri;
	}

	public static ModelMapper createModelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setSkipNullEnabled(true);
		modelMapper.getConfiguration().setMethodAccessLevel(AccessLevel.PUBLIC);
		return modelMapper;
	}

	public static ObjectMapper createObjectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new Jdk8Module());
		objectMapper.setSerializationInclusion(Include.NON_NULL);
		return objectMapper;
	}

	public static XmlMapper createXmlMapper() { // jackson-dataformat-xml
		XmlMapper xmlMapper = new XmlMapper();
		xmlMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
		xmlMapper.configure(ToXmlGenerator.Feature.WRITE_XML_DECLARATION, true);
		xmlMapper.configure(ToXmlGenerator.Feature.WRITE_XML_1_1, true);
		return xmlMapper;
	}

	public static <T> T map(Object source, Class<T> targetClass) {
		return createModelMapper().map(source, targetClass);
	}

	public static <T> T map(Object source, Class<T> targetClass, ModelMapper modelMapper) {
		return modelMapper.map(source, targetClass);
	}

	public static <S, T> List<T> map(List<S> source, Class<T> targetClass) {
		return source.stream().map(element -> createModelMapper().map(element, targetClass))
				.collect(Collectors.toList());
	}

	public static <S, T> List<T> map(List<S> source, Class<T> targetClass, ModelMapper modelMapper) {
		return source.stream().map(element -> modelMapper.map(element, targetClass)).collect(Collectors.toList());
	}

	public static <T> T readJsonFile(String filePath, Class<T> destinationtype)
			throws JsonParseException, JsonMappingException, IOException {
		return createObjectMapper().readValue(new File(filePath), destinationtype);
	}

	public static String mapToString(Object sourceObj, boolean indented) throws JsonProcessingException {
		String jsonString = createObjectMapper().writeValueAsString(sourceObj);
		if (indented) {
			return createObjectMapper().readTree(jsonString).toPrettyString();
		}
		return jsonString;
	}

	public static String mapToXml(Object sourceObj) throws IOException {
		String jsonString = mapToString(sourceObj, false);
		if (StringUtils.isNotEmpty(jsonString)) {
			JsonNode jsonNode = createObjectMapper().readValue(jsonString, JsonNode.class);
			StringWriter w = new StringWriter();
			// createXmlMapper().writer().withRootName(sourceObj.getClass().getSimpleName());
			createXmlMapper().writeValue(w, jsonNode);
			return w.toString();
		}
		return jsonString;
	}

	public static <T> T mapToObject(String sourceJsonString, Class<T> targetClass) throws IOException {
		return createObjectMapper().readValue(sourceJsonString, targetClass);
	}

	public static <T> List<T> mapToObjectList(String sourceJsonString, Class<T> targetClass) throws IOException {
		return createObjectMapper().readValue(sourceJsonString,
				createObjectMapper().getTypeFactory().constructCollectionType(List.class, targetClass));
	}

	public static ResponseEntity<Object> createErrorResponse(HttpStatus status, List<String> errors,
			HttpHeaders headers) {
		return new ResponseEntity<>(ErrorMessage.builder()
				.code(status.value())
				.timestamp(SmartDateUtil.getCurrentSystemDateTimeStr())
				.errors(errors).build(), headers, status);
	}

}
