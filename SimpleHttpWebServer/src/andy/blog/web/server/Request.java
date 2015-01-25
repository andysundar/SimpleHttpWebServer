/**
 * @Author Anindya Bandopadhyay
 *
 * SimpleHttpWebServer\andy.blog.http.server.HttpRequest.java
 *
 * @Created on Sep 14, 2014  6:55:52 PM
 */

package andy.blog.web.server;

import java.io.IOException;
import java.io.InputStream;

import andy.blog.constant.ServerConstant;

public class Request {
  
  
  public String getUri(InputStream inputStream) {
    byte buffer[] = new byte[ServerConstant.BUFFER_SIZE];
    StringBuffer inputBuffer = new StringBuffer(ServerConstant.BUFFER_SIZE);
    int byteRequest;
    try {
      byteRequest = inputStream.read(buffer);
    } catch (IOException e) {
      e.printStackTrace();
      byteRequest = -1;
    }
    for (int index = 0; index < byteRequest; index++) {
      inputBuffer.append((char) buffer[index]);
    }
    String request = inputBuffer.toString();
    System.out.println(request);
    return parseUri(request);
  }

 
  private String parseUri(String requestString) {
    // GET /index.html HTTP/1.1
    // strip out '/index.html' from request string
    String[] resource = requestString.split(" ");
    return resource[1];
  }
}
