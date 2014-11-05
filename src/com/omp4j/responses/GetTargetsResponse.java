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
public class GetTargetsResponse extends OMPResponse {
    
    protected Elements targets;
    
    public GetTargetsResponse(Document response) {
        super(response);
        this.targets = root.getChildElements("target");
    }
    
    
    /**
     * Returns a map of host => id pairs for each target.
     * 
     * @return 
     */
    public Map<String, String> getTargetIDsByHost() {
        HashMap<String, String> map = new HashMap<>();
        Element target;
        
        for (int i = 0; i < targets.size(); i++) {
            target = targets.get(i);
            map.put(getHost(target), getID(target));
        }
        
        return map;
    }
    
    /**
     * Returns a map of name => id pairs for each target.
     * 
     * @return 
     */
    public Map<String, String> getTargetIDsByName() {
        HashMap<String, String> map = new HashMap<>();
        Element target;
        
        for (int i = 0; i < targets.size(); i++) {
            target = targets.get(i);
            map.put(getName(target), getID(target));
        }
        
        return map;
    }
    
    /**
     * Returns a map of id => name pairs for each target.
     * 
     * @return 
     */
    public Map<String, String> getTargetNames() {
        HashMap<String, String> map = new HashMap<>();
        Element target;
        
        for (int i = 0; i < targets.size(); i++) {
            target = targets.get(i);
            map.put(getID(target), getName(target));
        }
        
        return map;
    }
    
    private String getID(Element target) {
        return target.getAttributeValue("id");
    }
    
    private String getName(Element target) {
        return target.getFirstChildElement("name").getValue();
    }
    
    
    private String getHost(Element target) {
        return target.getFirstChildElement("hosts").getValue();
    }
    
    private String getComment(Element target) {
        return target.getFirstChildElement("comment").getValue();
    }
}
