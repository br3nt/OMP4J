package com.omp4j.responses;

import nu.xom.*;

/**
 *
 * @author Brent Jacobs
 */
public abstract class OMPResponse {
    protected Document document;
    protected Element root;
    
    public OMPResponse(Document response) {
        this.document = response;
        this.root = document.getRootElement();
    }
    
    public String getStatus() {
        return root.getAttributeValue("status");
    }
    
    public String getStatusText() {
        return root.getAttributeValue("status_text");
    }
    
    public String toXML() {
        return document.toXML();
    }
    
    public String toString() {
        return document.toString();
    }
}
