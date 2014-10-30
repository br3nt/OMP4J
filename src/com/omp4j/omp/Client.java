package com.omp4j.omp;

import com.proc.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        return "omp";
    }
    
    private String connectionParameters() {
        return "-u " + username + " -w " + password + " -h " + host + " -p " + port;
    }
    
    public Proc getCommand(String command) {
        return new Proc(ompCommand() + " " + connectionParameters() + " " + command);
    }
    
    public String getConfigs() throws IOException, InterruptedException {
        Proc cmd = getCommand("--xml=\"<get_configs/>\" -i");
        
//        cmd.addListener(new ProcListener() {
//            @Override
//            public void start() {
//                System.out.println("listener: start");
//            }
//            
//            @Override
//            public void line(String line) {
//                System.out.println("listener: " + line);
//            }
//            
//            
//            @Override
//            public void finished(String output) {
//                System.out.println("listener: final output from subject:");
//                System.out.println(output);
//                System.out.println();
//                System.out.println("listener: collected output:");
//                System.out.println(this.output);
//            }
//        });
        
        String command = ompCommand() + " " + connectionParameters() + "--xml=\"<get_configs/>\" -i";
        
        System.out.println("Running command: " + command);
        Process proc = Runtime.getRuntime().exec(command);
        BufferedReader procReader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
        
        // read output of nmap command
        String line, output = "";
        int nullCount = 0;
        while ((line = procReader.readLine()) != null || nullCount < 10) {
            if (line == null) nullCount++;
            System.out.println("line: " + line);
            output += line;
        }

        System.out.println("final:");
        System.out.println(output);
        proc.waitFor();
        System.out.println("Completed command");
        proc.destroy();
        
        return cmd.exec();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String... args) {
        try {
            Client omp = new Client("admin", "password");
            
            String output = omp.getConfigs();
            System.out.println("===  Get Configs ==================");
            System.out.println(output);
            
        } catch (IOException ex) {
            System.out.println("Encountered and error:");
            System.out.println(ex);
        } catch (InterruptedException ex) {
            System.out.println("Encountered and error:");
            System.out.println(ex);
        }
    }
    
}
