package com.omp4j.commands;

import nu.xom.*;

/**
 *
 * @author Brent Jacobs
 */
public class CreateTarget {
        
    private Document document;
    
    public CreateTarget(String name, String hosts) {
        Element root = new Element("create_target");
        Element nameEl = new Element("name");
        Element hostsEl = new Element("hosts");
        
        root.appendChild(nameEl);
        root.appendChild(hostsEl);
        this.document = new Document(root);
        
        nameEl.appendChild(name);
        hostsEl.appendChild(hosts);
    }
    
    public String toXML() {
        return this.document.toXML();
    }
    
    @Override
    public String toString() {
        return this.document.toXML();
    }
}
