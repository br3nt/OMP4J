package com.omp4j.commands;

/**
 *
 * @author Brent Jacobs
 */
public class GetReports extends OMPCommand {
    
    public GetReports(String reportID, String formatID) {
        super("get_reports");
        addAttribute("report_id", reportID);
        addAttribute("format_id", formatID);
    }
}
