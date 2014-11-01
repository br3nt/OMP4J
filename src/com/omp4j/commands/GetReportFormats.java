package com.omp4j.commands;

import nu.xom.*;

/**
 *
 * @author Brent Jacobs
 */
public class GetReportFormats {
    
    private Document document;
    
    public GetReportFormats() {
        Element root = new Element("get_report_formats");
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
