/**
 * @Author Anindya Bandopadhyay
 *
 * SimpleHttpWebServer\andy.blog.http.server.HttpResponse.java
 *
 * @Created on Sep 14, 2014  6:56:11 PM
 */

package andy.blog.web.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Response {

  private static final int BUFFER_SIZE = 1024;

  String success = "HTTP/1.1 200 OK\r\nConnection: close\r\n"+
          "Server: Anindya's Server v1\r\nContent-Type: text/html\r\n\r\n";
  
  public void processResponse(OutputStream outputStream, File rootDir, String resource) throws IOException {
    byte buffer[] = new byte[BUFFER_SIZE];
    FileInputStream fileInputStream = null;
    try {
      File file = new File(rootDir, resource);
      System.out.println(file.getAbsolutePath());
      if (file.exists()) {
        outputStream.write(success.getBytes());
        fileInputStream = new FileInputStream(file);
        int character = fileInputStream.read(buffer, 0, BUFFER_SIZE);
        while (character != -1) {
          outputStream.write(buffer, 0, character);
          character = fileInputStream.read(buffer, 0, BUFFER_SIZE);
        }
      } else {
        String notFound = "HTTP/1.1 404 File Not Found\r\n" + "Content-Type: text/html\r\n" + "Content-Length: 23\r\n"
                + "\r\n" + "<h1>Page Not Found</h1>";
        outputStream.write(notFound.getBytes());
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (fileInputStream != null) {
        fileInputStream.close();
      }
    }

  }

}
