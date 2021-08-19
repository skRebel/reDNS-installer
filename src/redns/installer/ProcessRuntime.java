/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redns.installer;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alice
 */


public class ProcessRuntime {
    

    public static Map<String, String> paths = new HashMap<>(); // List of nodeJS runtimes
    public static void initializer() {
        paths.put("AdobeCCX", "C:\\Program Files\\Adobe\\Adobe Creative Cloud Experience\\libs\\node.exe"); // Register AdobeCCX
    }   
    public static void callNode(String... path) {
        if (path.length > 1) try {
            runCommandLine("./zip-Xtract/", path[1], ".");
        } catch (IOException ex) {
            Logger.getLogger(ProcessRuntime.class.getName()).log(Level.SEVERE, null, ex);
        } else try {
            runCommandLine("./zip-Xtract/","C:\\Program Files\\Adobe\\Adobe Creative Cloud Experience\\libs\\node.exe", ".");
        } catch (IOException ex) {
            Logger.getLogger(ProcessRuntime.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void callBrowser() {
        try {
            // Runtime.getRuntime().exec("taskkill /F /IM chrome.exe"); //Windows only
            runCommandLine(".","C:\\Program Files\\Google\\Application\\chrome.exe", "--proxy-server=127.0.0.1:8124");
        } catch (IOException ex) {
            Logger.getLogger(ProcessRuntime.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public static void runCommandLine(String directory, String command, String args) throws IOException { //https://stackoverflow.com/questions/30706704/java-run-async-processes
        ProcessBuilder processBuilder = new ProcessBuilder(
                        command, args);
        processBuilder.directory(new File(directory));
        Process process = processBuilder.start();
        Thread commandLineThread = new Thread(() -> {
            try {
                System.out.println(process.getErrorStream());
                System.out.println(process.getInputStream());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        commandLineThread.setDaemon(true);
        commandLineThread.start();
        System.out.println("Task Dispatched");
    }
    
}
