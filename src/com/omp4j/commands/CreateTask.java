package com.omp4j.commands;

import nu.xom.*;

/**
 *
 * @author Brent Jacobs
 */
public class CreateTask {
            
    private Document document;
    
    public CreateTask(String name, String comment, String config, String target) {
        Element root = new Element("create_task");
        Element nameEl = new Element("name");
        Element hostsEl = new Element("comment");
        Element configEl = new Element("config");
        Element targetEl = new Element("target");
        
        root.appendChild(nameEl);
        root.appendChild(hostsEl);
        root.appendChild(configEl);
        root.appendChild(targetEl);
        this.document = new Document(root);
        
        nameEl.appendChild(name);
        hostsEl.appendChild(comment);
        configEl.appendChild(config);
        targetEl.appendChild(target);
    }
    
    public String toXML() {
        return this.document.toXML();
    }
    
    @Override
    public String toString() {
        return this.document.toXML();
    }
}
