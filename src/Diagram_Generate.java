package umlparser.umlparser;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Diagram_Generate {

    public static Boolean PNG_generator(String gram, String o_path) {

        try {
            String yuml = "http://yuml.me/diagram/plain/class/" + gram
                    + ".png";
            URL url = new URL(yuml);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");

            if (connection.getResponseCode() != 200) {
                throw new RuntimeException(
                        "Failed : HTTP error code : " + connection.getResponseCode());
            }
            OutputStream o_stream = new FileOutputStream(new File(o_path));
            int ready = 0;
            byte[] byt = new byte[1024];

            
        } catch (MalformedURLException error) {
            error.printStackTrace();
        } catch (IOException error) {
            error.printStackTrace();
        }
        return null;
    }
}
