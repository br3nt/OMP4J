OMP4J
=====

A java OMP implementation to communicate with OpenVAS

Dependencies
============

This project uses https://github.com/br3nt/Proc, a simple java.lang.Process wrapper, to send omp commands to the command line.

Example
=======

For an example of how it all fits together and works have a look at the `main` method in [`com.omp4j.opm.Client`](https://github.com/br3nt/OMP4J/blob/master/src/com/omp4j/omp/Client.java).  It shows connecting to `omp`, creating and running a task, and then printing the generated report.  Each step is an example of sending and receiving responses to and from `omp`. 

The [`com.omp4j.opm.Client`](https://github.com/br3nt/OMP4J/blob/master/src/com/omp4j/omp/Client.java) is the entry point into the library.

```java
Client omp = new Client("admin", "password");
```

The `execute` method is the lowest level method which sends commands to `omp` via the command line.  It accepts subclasses of [`com.omp4j.commands.OMPCommand`](https://github.com/br3nt/OMP4J/blob/master/src/com/omp4j/commands/OMPCommand.java).  There are predefined methods that send commands to `omp`.

```java
Client omp = new Client("admin", "password");
GetConfigsResponse cr = omp.getConfigs();
GetTargetsResponse tr = omp.getTargets();
CreateTargetResponse ctr = omp.createTarget("10.0.0.19", "10.0.0.19");
// etc...
```

An [`com.omp4j.commands.OMPCommand`](https://github.com/br3nt/OMP4J/blob/master/src/com/omp4j/commands/OMPCommand.java) is little more than an xml document with helper methods.  Each of the subclasses creates the necessary xml required to interact with `omp`.

When a [`com.omp4j.commands.OMPCommand`](https://github.com/br3nt/OMP4J/blob/master/src/com/omp4j/commands/OMPCommand.java) is executed, `omp` will return an string containing an XML response.  The `com.omp4j.opm.Client#parseResponse` method converts the xml string into an XML object that can be passed into one of the corresponding [`com.omp4j.responses.OMPResponse`](https://github.com/br3nt/OMP4J/blob/master/src/com/omp4j/responses/OMPResponse.java) subclasses to extract some useful information.  Each of the predefined command methods already parse the `omp` xml response into the correct subclass.

```java
Client omp = new Client("admin", "password");

// get config ID for Full and fast scan
GetConfigsResponse cr = omp.getConfigs();
String configID = cr.getConfigIDsByName().get("Full and fast");

// get an existing target
GetTargetsResponse tr = omp.getTargets();
String targetID = tr.getTargetIDsByHost().get("10.0.0.19");

// if no targets found, create it
if (targetID == null) {
    CreateTargetResponse ctr = omp.createTarget("10.0.0.19", "10.0.0.19");
    targetID = ctr.getTargetID();
}

// create a task
CreateTaskResponse ctkr = omp.createTask("Task for " + host, "", configID, targetID);
String taskID = ctkr.getTaskID();

// start the task
StartTaskResponse str = omp.startTask(taskID);
String reportID = str.getReportID();
```

An `omp` task takes time to complete.  Some form of poller is required to request the status of a task.  An example of this is given in [`com.omp4j.opm.Client`](https://github.com/br3nt/OMP4J/blob/master/src/com/omp4j/omp/Client.java)

Questions
=========

**Why does this library exist?**

I originally created this library for my final year project at university.

The project contained three parts, an Android app that communicated via bluetooth to a Raspberry Pi, a bluetooth server on the Raspberry Pi that listened for commands from the Android app, and this library which ran OpenVAS commands on an accessible ip address and returned vulnerability report data back to the Android app.

**Is this library still maintained?**

Not exactly... However, if anyone submits a pull request or posts an issue, I will investigate and respond (eventually).

**Will I make any modifications to the library**

I feel the code as relatively complete as is.  The library already has default methods that communicate with OpenVAS quite readily.  However, will consider any pull requests that add value.

Furthermore, any developer using this library can create their own subclasses of [`com.omp4j.commands.OMPCommand`](https://github.com/br3nt/OMP4J/blob/master/src/com/omp4j/commands/OMPCommand.java) and 
[`com.omp4j.responses.OMPResponse`](https://github.com/br3nt/OMP4J/blob/master/src/com/omp4j/responses/OMPResponse.java) to extend the existing functionality.

Likewise, the existing subclasses of [`com.omp4j.commands.OMPCommand`](https://github.com/br3nt/OMP4J/blob/master/src/com/omp4j/commands/OMPCommand.java) can have additional parameters and options added to the xml quite easily.
