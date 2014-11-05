package com.omp4j.responses;

import nu.xom.Document;

/**
 *
 * @author Brent Jacobs
 */
public class CreateTaskResponse extends OMPResponse {
    
    public CreateTaskResponse(Document response) {
        super(response);
    }
    
    public String getTaskID() {
        return root.getAttributeValue("id");
    }
}
