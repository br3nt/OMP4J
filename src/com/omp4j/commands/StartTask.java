package com.omp4j.commands;

import nu.xom.*;

/**
 *
 * @author Brent Jacobs
 */
public class StartTask {
            
    private Document document;
    
    public StartTask(String taskID) {
        Element root = new Element("start_task");
        Element taskIdEl = new Element("task_id");
        
        root.appendChild(taskIdEl);
        this.document = new Document(root);
        
        taskIdEl.appendChild(taskID);
    }
    
    public String toXML() {
        return this.document.toXML();
    }
    
    @Override
    public String toString() {
        return this.document.toXML();
    }
}
