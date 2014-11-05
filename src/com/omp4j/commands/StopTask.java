package com.omp4j.commands;

/**
 * In short: Stop a running task.
 *
 * The client uses the stop_task command to manually stop a running task.
 * 
 * See: http://www.openvas.org/omp-5-0.html#command_stop_task
 * 
 * @author Brent Jacobs
 */
public class StopTask extends OMPCommand {
    
    /**
     * @param taskID A Task UUID.
     */
    public StopTask(String taskID) {
        super("stop_task");
        addAttribute("task_id", taskID);
    }
}
