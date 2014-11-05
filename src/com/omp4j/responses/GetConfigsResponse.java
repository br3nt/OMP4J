package com.omp4j.responses;

import java.util.HashMap;
import java.util.Map;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Elements;

/**
 *
 * @author Brent Jacobs
 */
public class GetConfigsResponse extends OMPResponse {
    
    protected Elements configs;
    
    public GetConfigsResponse(Document response) {
        super(response);
        this.configs = root.getChildElements("config");
    }
    
    /**
     * Returns a map of name => id pairs for each config.
     * 
     * @return 
     */
    public Map<String, String> getConfigIDsByName() {
        HashMap<String, String> map = new HashMap<>();
        Element config;
        
        for (int i = 0; i < configs.size(); i++) {
            config = configs.get(i);
            map.put(getName(config), getID(config));
        }
        
        return map;
    }
    
    /**
     * Returns a map of id => name pairs for each config.
     * 
     * @return 
     */
    public Map<String, String> getConfigNames() {
        HashMap<String, String> map = new HashMap<>();
        Element config;
        
        for (int i = 0; i < configs.size(); i++) {
            config = configs.get(i);
            map.put(getID(config), getName(config));
        }
        
        return map;
    }
    
    private String getID(Element config) {
        return config.getAttributeValue("id");
    }
    
    private String getName(Element config) {
        return config.getFirstChildElement("name").getValue();
    }
    
    private String getComment(Element config) {
        return config.getFirstChildElement("comment").getValue();
    }
}
