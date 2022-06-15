package com.smartapps.smartlib.enums;

import java.util.HashMap;
import java.util.Map;

public enum AssetTypeEnum {

	IMAGES("IMAGES", "images"),
	DOCUMENTS("DOCUMENTS", "documents"),
	VIDEOS("VIDEOS", "videos"),
	AUDIOS("AUDIOS", "audios");

    private final String key;
    private final String value;

    /**
     * A mapping between the integer code and its corresponding text to facilitate lookup by code.
     */
    private static Map<String, AssetTypeEnum> valueToTextMapping;

    private AssetTypeEnum(String key, String value){
        this.key = key;
        this.value = value;
    }

    public static AssetTypeEnum getAssetTypeEnum(String key){
        if(valueToTextMapping == null){
            initMapping();
        }
        return valueToTextMapping.get(key);
    }

    private static void initMapping(){
        valueToTextMapping = new HashMap<>();
        for(AssetTypeEnum s : values()){
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
        sb.append("AssetTypeEnum");
        sb.append("{key=").append(key);
        sb.append(", value='").append(value).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
