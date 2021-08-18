/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redns.libs;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
/**
 *
 * @author alice
 */
public class DownloadFile { // from https://www.techiedelight.com/download-file-from-url-java/
        public static void downloadFile(URL url, String outputFileName) throws IOException
        {
            try (InputStream in = url.openStream();
                ReadableByteChannel rbc = Channels.newChannel(in);
                FileOutputStream fos = new FileOutputStream(outputFileName)) {
                fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            }
    }
}
