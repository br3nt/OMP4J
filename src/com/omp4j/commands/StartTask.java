package com.omp4j.commands;

/**
 *
 * @author Brent Jacobs
 */
public class StartTask extends OMPCommand {
    
    /**
     * @param taskID A Task UUID.
     */
    public StartTask(String taskID) {
        super("start_task");
        addAttribute("task_id", taskID);
    }
}
