package com.omp4j.commands;

/**
 *
 * @author Brent Jacobs
 */
public class CreateTarget extends OMPCommand {
    
    public CreateTarget(String name, String hosts) {
        super("create_target");
        this.addElement("name", name);
        this.addElement("hosts", hosts);
    }
}
