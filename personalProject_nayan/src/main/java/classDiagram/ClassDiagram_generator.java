package classDiagram;

import java.net.*;
import java.io.*;
// import java.io.FileOutputStream;
// import java.io.IOException;
// import java.io.OutputStream;
// import java.net.MalformedURLException;
// import java.net.URL;
public class ClassDiagram_generator {
        @SuppressWarnings("finally")
		public static Boolean imageGenerator_fn(String output_file, String code_gram) {

                try {
                    String img_link_website = "https://yuml.me/diagram/plain/class/" + code_gram
                            + ".png";
                    URL uniformResourceLocator = new URL(img_link_website);
                    HttpURLConnection yuml_connectionObject = (HttpURLConnection) uniformResourceLocator.openConnection();
                    yuml_connectionObject.setRequestProperty("Accept", "application/json");
                    yuml_connectionObject.setRequestMethod("GET");

                    if (yuml_connectionObject.getResponseCode() != 200) {
						throw new RuntimeException("There is an Error with Yuml website. Please try again.");
					}

                    byte[] imgByteCode = new byte[1024];
                    int noOfBytesRead = 0;
                    OutputStream output_fileStream = new FileOutputStream(new File(output_file));
                    noOfBytesRead = yuml_connectionObject.getInputStream().read(imgByteCode);
                    while (noOfBytesRead!= -1) {
                        output_fileStream.write(imgByteCode, 0, noOfBytesRead);
                        noOfBytesRead = yuml_connectionObject.getInputStream().read(imgByteCode);
                    }
                    yuml_connectionObject.disconnect();
                    output_fileStream.close();
                } 
                
                
                catch (MalformedURLException malformedURLException) {
                    malformedURLException.printStackTrace();
                } 
                
                
                catch (IOException ioException) {
                    ioException.printStackTrace();
                } 
                
                finally {
                	return null;
				}
                
            }
        }
