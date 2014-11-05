package com.omp4j.commands;

/**
 *
 * @author Brent Jacobs
 */
public class PauseTask extends OMPCommand {
    
    /**
     * @param taskID A Task UUID.
     */
    public PauseTask(String taskID) {
        super("pause_task");
        addAttribute("task_id", taskID);
    }
}
