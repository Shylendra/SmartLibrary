package com.smartapps.smartlib.enums;

import java.util.HashMap;
import java.util.Map;

public enum UserStatusEnum {

	AVAILABLE("AVAILABLE", "Available"),
	BUSY("BUSY", "Busy"),
	DONT_DISTURB("DONT_DISTURB", "Do not disturb"),
	BRB("BRB", "Be right back"),
	AWAY("AWAY", "Appear away"),
	OFFLINE("OFFLINE", "Appear offline");

    private final String key;
    private final String value;

    /**
     * A mapping between the integer code and its corresponding text to facilitate lookup by code.
     */
    private static Map<String, UserStatusEnum> valueToTextMapping;

    private UserStatusEnum(String key, String value){
        this.key = key;
        this.value = value;
    }

    public static UserStatusEnum getUserStatusEnum(String key){
        if(valueToTextMapping == null){
            initMapping();
        }
        return valueToTextMapping.get(key);
    }

    private static void initMapping(){
        valueToTextMapping = new HashMap<>();
        for(UserStatusEnum s : values()){
            valueToTextMapping.put(s.key, s);
        }
    }

    public String getKey(){
        return key;
    }

    public String getValue(){
        return value;
    }

    @Override
    public String toString(){
        final StringBuilder sb = new StringBuilder();
        sb.append("UserStatusEnum");
        sb.append("{key=").append(key);
        sb.append(", value='").append(value).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
