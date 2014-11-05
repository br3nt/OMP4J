package com.omp4j.responses;

import nu.xom.Document;

/**
 *
 * @author Brent Jacobs
 */
public class StartTaskResponse extends OMPResponse {
    
    public StartTaskResponse(Document response) {
        super(response);
    }
    
    public String getReportID() {
        return root.getFirstChildElement("report_id").getValue();
    }
}
