package com.omp4j.commands;

/**
 *
 * @author Brent Jacobs
 */
public class CreateTask extends OMPCommand {
    
    public CreateTask(String name, String comment, String configID, String targetID) {
        super("create_task");
        
        addElement("name", name);
        addElement("comment", comment);
        addElementWithAttribute("config", "id", configID);
        addElementWithAttribute("target", "id", targetID);
    }
}
