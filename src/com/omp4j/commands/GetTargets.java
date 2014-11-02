package com.omp4j.commands;

import nu.xom.*;

/**
 *
 * @author Brent Jacobs
 */
public class GetTargets {
        
    private Document document;
    
    public GetTargets() {
        Element root = new Element("get_targets");
        this.document = new Document(root);
    }
    
    public String toXML() {
        return this.document.toXML();
    }
    
    @Override
    public String toString() {
        return this.document.toXML();
    }
}
