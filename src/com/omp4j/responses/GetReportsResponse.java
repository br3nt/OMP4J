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
public class GetReportsResponse extends OMPResponse {
    
    private Elements reports;
    
    public GetReportsResponse(Document response) {
        super(response);
        this.reports = root.getChildElements("report");
    }
    
    /**
     * Returns a map of id => name pairs for each report.
     * 
     * @return 
     */
    public Map<String, String> getReportNames() {
        HashMap<String, String> map = new HashMap<>();
        Element report;
        
        for (int i = 0; i < reports.size(); i++) {
            report = reports.get(i);
            map.put(getID(report), getName(report));
        }
        
        return map;
    }
    
    private String getID(Element report) {
        return report.getAttributeValue("id");
    }
    
    private String getName(Element report) {
        return report.getFirstChildElement("name").getValue();
    }
}
