package com.omp4j.omp;

import com.omp4j.commands.*;
import com.proc.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

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
    
    private String ompCommand() {
        return " omp";
    }
    
    private String connectionParameters() {
        return "-u " + username + " -w " + password + " -h " + host + " -p " + port;
    }
    
    public Proc getCommand(String command) {
        try {
            String cmd = ompCommand() + " " + connectionParameters() + " " + command;
            byte[] arr = cmd.getBytes();
            return new Proc(new String(arr, "ISO-8859-1"));
        } catch (UnsupportedEncodingException ex) {
            System.out.println("Unable to convert to ISO-8859-1");
        }
        return null;
    }
    
    public String getConfigs() throws IOException, InterruptedException {
        Proc cmd = getCommand("--xml='" + new GetConfigs() + "' -i");
        
        return cmd.exec();
    }

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
        
                
        System.out.println("===  -g ==============================================================".substring(0, 65));
        cmd = getCommand("-g");
        cmd.addListener(createListener());
        cmd.exec();
        System.out.println();System.out.println();System.out.println();
        
        
        System.out.println("===  new GetConfigs() ================================================".substring(0, 65));
        cmd = getCommand("--xml='" + new GetConfigs() + "' -i");
        cmd.addListener(createListener());
        cmd.exec();
        System.out.println();System.out.println();System.out.println();
        
        
        System.out.println("===  '<get_configs />' ===============================================".substring(0, 65));
        cmd = getCommand("--xml='<get_configs />' -i");
        cmd.addListener(createListener());
        cmd.exec();
        System.out.println();System.out.println();System.out.println();
        
        System.out.println("===  \"<get_configs />\".replaceAll(\"\\\"\", \"\") ===============================================".substring(0, 65));
        cmd = getCommand("--xml=\"<get_configs />\" -i".replaceAll("\"", ""));
        cmd.addListener(createListener());
        cmd.exec();
        System.out.println();System.out.println();System.out.println();
        
        System.out.println("===  \"<get_configs />\" =============================================".substring(0, 65));
        cmd = getCommand("--xml=\"<get_configs />\" -i");
        cmd.addListener(createListener());
        cmd.exec();
        System.out.println();System.out.println();System.out.println();
        
        System.out.println("===  \\\"<get_configs />\\\" =========================================".substring(0, 65));
        cmd = getCommand("--xml=\\\"<get_configs />\\\" -i");
        cmd.addListener(createListener());
        cmd.exec();
        System.out.println();System.out.println();System.out.println();
        
        
//        System.out.println("===   ================================================================".substring(0, 65));
        
        
//        System.out.println("===   ================================================================".substring(0, 65));
        
        
    }
    
    public ProcListener createListener() {
        return new ProcListener() {
            @Override
            public void start(String command) {
                System.out.println("listener: start");
                System.out.println("command: ");
                System.out.println(command);
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
