package com.smartapps.smartlib.util;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.net.URI;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;
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

public class SmartLibraryUtil {
	
	 public static final String SYS_DATE_FORMAT = "yyyy-MM-dd";
	 
	 public static final String SYS_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS zzz";
	 
	 public static Optional<Date> currentSysDate = Optional.of(new Date(System.currentTimeMillis()));
	 
	 public static boolean isValidEmployeeNumber(String empNumber) {
		 if(StringUtils.isNotEmpty(empNumber)) {
			 if(empNumber.length() <= 8) {
				 return true;
			 }
		 }
		 return false;
	 }
	 
	 public static URI constructUri(String url, Map<String, String> pathParams, Map<String, String> queryParams) {
		 
		 MultiValueMap<String, String> queryParamsMultivalueMap = new LinkedMultiValueMap<String, String>();
		 if(queryParams != null && !queryParams.isEmpty()) {
			 queryParamsMultivalueMap.setAll(queryParams);
		 }
		 
		 
		 URI uri = UriComponentsBuilder.fromUriString(url)
				 	.queryParams(queryParamsMultivalueMap)
			        .buildAndExpand(pathParams == null ? new HashMap<String, String>(): pathParams)
			        .toUri();
		 
		 return uri;
	 }
	 
	 public static String getCurrentSystemDate() {
		 DateFormat df = new SimpleDateFormat(SYS_DATE_FORMAT);
		 return df.format(currentSysDate.get());
	 }
	 
	 public static String getCurrentSystemDateTime() {
		 DateFormat df = new SimpleDateFormat(SYS_DATE_TIME_FORMAT);
		 return df.format(currentSysDate.get());
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

	 public static XmlMapper createXmlMapper() { //jackson-dataformat-xml 
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
		 return source
				 .stream()
				 .map(element -> createModelMapper().map(element,  targetClass))
				 .collect(Collectors.toList());
	 }
	 
	 public static <S, T> List<T> map(List<S> source, Class<T> targetClass, ModelMapper modelMapper) {
		 return source
				 .stream()
				 .map(element -> modelMapper.map(element,  targetClass))
				 .collect(Collectors.toList());
	 }
	 
	 public static <T> T readJsonFile(String filePath, Class<T> destinationtype) throws JsonParseException, JsonMappingException, IOException {
		 return createObjectMapper().readValue(new File(filePath), destinationtype);
	 }
	 
	 public static String mapToString(Object sourceObj, boolean indented) throws JsonProcessingException {
		 String jsonString = createObjectMapper().writeValueAsString(sourceObj);
		 if(indented) {
			 return createObjectMapper().readTree(jsonString).toPrettyString();
		 }
		 return jsonString;
	 }
	 
	 public static String mapToXml(Object sourceObj) throws IOException {
		 String jsonString = mapToString(sourceObj, false);
		 if(StringUtils.isNotEmpty(jsonString)) {
			 JsonNode jsonNode = createObjectMapper().readValue(jsonString, JsonNode.class);
			 StringWriter w = new StringWriter();
			 //createXmlMapper().writer().withRootName(sourceObj.getClass().getSimpleName());
			 createXmlMapper().writeValue(w, jsonNode);
			 return w.toString();
		 }
		 return jsonString;
	 }
	 
	 public static <T> T mapToObject(String sourceJsonString, Class<T> targetClass) throws IOException {
		 return createObjectMapper().readValue(sourceJsonString,  targetClass);
	 }

	 public static <T> List<T> mapToObjectList(String sourceJsonString, Class<T> targetClass) throws IOException {
		 return createObjectMapper().readValue(sourceJsonString, createObjectMapper().getTypeFactory().constructCollectionType(List.class, targetClass));
	 }
	 
}
