/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redns.installer;
 
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import static redns.libs.DownloadFile.downloadFile;
import redns.libs.Unzip;

/**
 *
 * @author alice
 */
public class ReDNSInstaller {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Loaded from ReDNSInstaller version 0.1.0");
        InstallerGUI.main(args);
    }
    public static void install() {
        try {
            downloadFile(new URL("https://api.github.com/repos/skrebel/redns/releases/latest"), "./github.api");
            JSONParser parser = new JSONParser();
            try (Reader reader = new FileReader("./github.api")) {
                JSONObject jsonObject = (JSONObject) parser.parse(reader);
                JSONArray assetsArray = (JSONArray) jsonObject.get("assets");
                JSONObject assets = (JSONObject) assetsArray.get(0);
                String downloadURL = (String) assets.get("browser_download_url");
                System.out.println(downloadURL);
                downloadFile(new URL(downloadURL), "./github.zip");
                Unzip.unzip("./github.zip", new File("./zip-Xtract/"));
                
            } catch (ParseException ex) {
                Logger.getLogger(ReDNSInstaller.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (MalformedURLException ex) {
            Logger.getLogger(ReDNSInstaller.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ReDNSInstaller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void remove() {
        
    }
    
}
