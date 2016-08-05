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
