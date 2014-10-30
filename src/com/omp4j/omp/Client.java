package com.omp4j.omp;

import com.proc.*;
import java.io.IOException;
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
        System.out.println(cmd.getCommand());
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
