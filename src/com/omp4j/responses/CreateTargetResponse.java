package com.omp4j.responses;

import nu.xom.Document;

/**
 *
 * @author Brent Jacobs
 */
public class CreateTargetResponse extends OMPResponse {
    
    public CreateTargetResponse(Document response) {
        super(response);
    }
    
    public String getTargetID() {
        return root.getAttributeValue("id");
    }
}
