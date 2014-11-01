package com.omp4j.omp;

import com.omp4j.commands.*;
import com.proc.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 *
 * @author Brent Jacobs
 */
public class Client {
    
    private final String username, password, host, port;
    
    public Client(String username, String password, String host, String port) {
        this.username = username;
        this.password = password;
        this.host = host;
        this.port = port;
    }
    
    public Client(String username, String password) {
        this(username, password, "localhost", "9390");
    }
    
//    public Proc getCommand(String command) {
//        try {
//            String cmd = ompCommand() + " " + connectionParameters() + " " + command;
//            byte[] arr = cmd.getBytes();
//            return new Proc(new String(arr, "ISO-8859-1"));
//        } catch (UnsupportedEncodingException ex) {
//            System.out.println("Unable to convert to ISO-8859-1");
//        }
//        return null;
//    }
    
//    public String getConfigs() throws IOException, InterruptedException {
//        Proc cmd = getCommand("--xml='" + new GetConfigs() + "' -i");
//        
//        return cmd.exec();
//    }

    /**
     * @param args the command line arguments
     */
    public static void main(String... args) {
        try {
            Client omp = new Client("admin", "password");
            omp.testCommands();
            
        } catch (IOException ex) {
            System.out.println("Encountered an IOException:");
            System.out.println(ex);
        } catch (InterruptedException ex) {
            System.out.println("Encountered an InterruptedException:");
            System.out.println(ex);
        }
    }
    
    
    public void testCommands() throws IOException, InterruptedException {
        Proc cmd;
        Proc proc;
        
                
        System.out.println("===  -g ==============================================================".substring(0, 65));
        proc = createOMPProc();
        proc.addParameter("-g");
        proc.addListener(createProcListener());
        proc.exec();
        System.out.println();System.out.println();System.out.println();
        
        
        System.out.println("===  new GetConfigs() ================================================".substring(0, 65));
        proc = createOMPProc();
        proc.addParameter("--xml=%s", new GetConfigs());
        proc.addParameter("-i");
        proc.addListener(createProcListener());
        proc.exec();
        System.out.println();System.out.println();System.out.println();
        
        System.out.println("===  'new GetConfigs()' ================================================".substring(0, 65));
        proc = createOMPProc();
        proc.addParameter("--xml='%s'", new GetConfigs());
        proc.addParameter("-i");
        proc.addListener(createProcListener());
        proc.exec();
        System.out.println();System.out.println();System.out.println();
        
        
        System.out.println("===  '<get_configs />' ===============================================".substring(0, 65));
        proc = createOMPProc();
        proc.addParameter("--xml='%s'", "<get_configs />");
        proc.addParameter("-i");
        proc.addListener(createProcListener());
        proc.exec();
        System.out.println();System.out.println();System.out.println();
        
        System.out.println("===  \"<get_configs />\" =============================================".substring(0, 65));
        proc = createOMPProc();
        proc.addParameter("--xml=\"%s\"", "<get_configs />");
        proc.addParameter("-i");
        proc.addListener(createProcListener());
        proc.exec();
        System.out.println();System.out.println();System.out.println();
        
        System.out.println("===  \\\"<get_configs />\\\" =========================================".substring(0, 65));
        proc = createOMPProc();
        proc.addParameter("--xml=\\\"%s\\\"", "<get_configs />");
        proc.addParameter("-i");
        proc.addListener(createProcListener());
        proc.exec();
        System.out.println();System.out.println();System.out.println();
        
        
//        System.out.println("===   ================================================================".substring(0, 65));
        
        
//        System.out.println("===   ================================================================".substring(0, 65));
        
        
    }
    
    private String ompCommand() {
        return "echo omp";
    }
    
    public Proc createOMPProc() {
        Proc proc = new Proc(ompCommand());
        proc.addParameter("-u %s", username);
        proc.addParameter("-w %s", password);
        proc.addParameter("-h %s", host);
        proc.addParameter("-p %s", port);
        return proc;
        
    }
    
    public ProcListener createProcListener() {
        return new ProcListener() {
            @Override
            public void start(String command, List<String> parameters) {
                String cmd = command;
                for (String param : parameters) {
                    command += " " + param;
                }
                
                System.out.println("listener: start");
                System.out.println("command: " );
                System.out.println(cmd);
            }
            
            @Override
            public void line(String line) {
                System.out.println("line: " + line);
            }
            
            @Override
            public void errorLine(String error) {
                System.out.println("error: " + error);
            }
            
            @Override
            public void finished(String output, String error, int exitValue) {
                System.out.println("listener: finished");
                System.out.println("has output? " + !(output == null || output.equals("")));
                System.out.println("has errors? " + !(error == null || error.equals("")));
                System.out.println("exitValue: " + exitValue);
            }
        };
    }
    
}
