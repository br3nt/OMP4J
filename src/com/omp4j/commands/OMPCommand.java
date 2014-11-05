package com.omp4j.commands;

import nu.xom.*;

/**
 *
 * @author Brent Jacobs
 */
public abstract class OMPCommand {
    
    protected final Document document;
    protected final Element root;
    
    public OMPCommand(String command) {
        this.root = new Element(command);
        this.document = new Document(root);
    }
    
    public final void addElement(String name, String value) {
        Element element = new Element(name);
        element.appendChild(value);
        root.appendChild(element);
    }
    
    public final void addElement(String name, Element child) {
        Element element = new Element(name);
        element.appendChild(child);
        root.appendChild(element);
    }
    
    public final void addElementWithAttribute(String elementName, String attributeName, String value) {
        Element element = new Element(elementName);
        Attribute attribute = new Attribute(attributeName, value);
        element.addAttribute(attribute);
        root.appendChild(element);
    }
    
    public final void addAttribute(String name, String value) {
        Attribute attribute = new Attribute(name, value);
        root.addAttribute(attribute);
    }
    
    public final String toXML() {
        return this.document.toXML();
    }
    
    @Override
    public final String toString() {
        return this.document.toXML();
    }
}
