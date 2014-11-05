/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.omp4j.responses;

import java.util.HashMap;
import java.util.Map;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Elements;

/**
 *
 * @author brent
 */
public class GetReportFormatsResponse extends OMPResponse {
    
    private Elements formats;
    
    public GetReportFormatsResponse(Document response) {
        super(response);
        this.formats = root.getChildElements("report_format");
    }
    
    /**
     * Returns a map of name => id pairs for each report format.
     * 
     * @return 
     */
    public Map<String, String> getReportFormatIDsByName() {
        HashMap<String, String> map = new HashMap<>();
        Element report_format;
        
        for (int i = 0; i < formats.size(); i++) {
            report_format = formats.get(i);
            map.put(getName(report_format), getID(report_format));
        }
        
        return map;
    }
    
    /**
     * Returns a map of id => name pairs for each report format.
     * 
     * @return 
     */
    public Map<String, String> getReportNames() {
        HashMap<String, String> map = new HashMap<>();
        Element report_format;
        
        for (int i = 0; i < formats.size(); i++) {
            report_format = formats.get(i);
            map.put(getID(report_format), getName(report_format));
        }
        
        return map;
    }
    
    private String getID(Element report_format) {
        return report_format.getAttributeValue("id");
    }
    
    private String getName(Element report_format) {
        return report_format.getFirstChildElement("name").getValue();
    }
    
    private String getComment(Element report_format) {
        return report_format.getFirstChildElement("comment").getValue();
    }
}
