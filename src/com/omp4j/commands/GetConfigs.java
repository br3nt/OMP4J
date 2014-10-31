package com.omp4j.commands;

import nu.xom.*;

/**
 *
 * @author Brent Jacobs
 */
public class GetConfigs {
    
    private Document document;
    
    public GetConfigs() {
        Element root = new Element("root");
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
