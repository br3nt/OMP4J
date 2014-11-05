package com.omp4j.commands;

/**
 *
 * @author Brent Jacobs
 */
public class GetTasks extends OMPCommand {
    
    public GetTasks(String taskID) {
        super("get_tasks");
        addAttribute("task_id", taskID);
        addAttribute("details", "1");
    }
}
