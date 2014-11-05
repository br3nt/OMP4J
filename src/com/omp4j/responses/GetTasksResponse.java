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
public class GetTasksResponse extends OMPResponse {
    
    private Elements tasks;
    
    public GetTasksResponse(Document response) {
        super(response);
        this.tasks = root.getChildElements("task");
    }
    
    /**
     * Returns a map of name => id pairs for each task.
     * 
     * @return 
     */
    public Map<String, String> getTaskIDsByName() {
        HashMap<String, String> map = new HashMap<>();
        Element task;
        
        for (int i = 0; i < tasks.size(); i++) {
            task = tasks.get(i);
            map.put(getName(task), getID(task));
        }
        
        return map;
    }
    
    /**
     * Returns a map of id => name pairs for each task.
     * 
     * @return 
     */
    public Map<String, String> getTaskNames() {
        HashMap<String, String> map = new HashMap<>();
        Element task;
        
        for (int i = 0; i < tasks.size(); i++) {
            task = tasks.get(i);
            map.put(getID(task), getName(task));
        }
        
        return map;
    }
    
    /**
     * Returns a map of id => status pairs for each task.
     * 
     * @return 
     */
    public Map<String, String> getTaskStatuses() {
        HashMap<String, String> map = new HashMap<>();
        Element task;
        
        for (int i = 0; i < tasks.size(); i++) {
            task = tasks.get(i);
            map.put(getID(task), getStatus(task));
        }
        
        return map;
    }
    
    private String getID(Element task) {
        return task.getAttributeValue("id");
    }
    
    private String getName(Element task) {
        return task.getFirstChildElement("name").getValue();
    }
    
    private String getComment(Element task) {
        return task.getFirstChildElement("comment").getValue();
    }
    
    private String getStatus(Element task) {
        return task.getFirstChildElement("status").getValue();
    }
}
