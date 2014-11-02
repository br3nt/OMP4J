package com.omp4j.commands;

import nu.xom.*;

/**
 *
 * @author Brent Jacobs
 */
public class GetReports {
        
    private Document document;
    
    public GetReports(String reportID, String formatID) {
        Element root = new Element("create_target");
        Element reportIdEl = new Element("report_id");
        Element hostIdEl = new Element("format_id");
        
        root.appendChild(reportIdEl);
        root.appendChild(hostIdEl);
        this.document = new Document(root);
        
        reportIdEl.appendChild(reportID);
        hostIdEl.appendChild(formatID);
    }
    
    public String toXML() {
        return this.document.toXML();
    }
    
    @Override
    public String toString() {
        return this.document.toXML();
    }
}
