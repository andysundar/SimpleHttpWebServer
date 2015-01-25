/**
 * @Author Anindya Bandopadhyay
 *
 * SimpleHttpWebServer\andy.blog.http.server.HttpServer.java
 *
 * @Created on Sep 14, 2014  6:55:31 PM
 */

package andy.blog.web.server;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import andy.blog.constant.ServerConstant;

public class Server {
  private boolean serverStart = true;
  private int port = ServerConstant.PORT;
  private String webRootPath = ServerConstant.DEFAULT_WEB_ROOT;
  private File webRootDir = new File(ServerConstant.WORKING_DIR, webRootPath);

  public static void main(String[] args) {
    Server server = new Server();
    server.initialSetUp(args);
    server.waitForClient();
  }

  private void initialSetUp(String[] args) {
    for (int index = 0; index < args.length; index++) {
      switch (index) {
        case 0:
          break;

        case 1:
          try {
            port = Integer.valueOf(args[1]);
          } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Port number must be numeric.");
          }
          break;
        case 2:
          webRootPath = args[2];
          webRootDir = new File(ServerConstant.WORKING_DIR, webRootPath);
          if (!(webRootDir.exists() && webRootDir.isDirectory())) {
            throw new IllegalArgumentException("Web root '" + webRootPath + "' folder doesn't exist");
          }
          break;
        default:
          break;
      }
    }
  }

  private void waitForClient() {
    ServerSocket serverSocket = null;
    try {
      serverSocket = new ServerSocket(port, 1, InetAddress.getByName(ServerConstant.HOSTNAME));
    } catch (Exception e) {
      e.printStackTrace();
      System.exit(1);
    }

    while (serverStart) {
      Socket socket = null;
      InputStream inputStream = null;
      OutputStream outputStream = null;
      try {
        socket = serverSocket.accept();
        inputStream = socket.getInputStream();
        outputStream = socket.getOutputStream();

        Request httpRequest = new Request();
        String resource = httpRequest.getUri(inputStream);

        System.out.println("\nRequested resource = " + resource);

        Response httpResponse = new Response();
        httpResponse.processResponse(outputStream, webRootDir, resource);

        inputStream.close();
        outputStream.close();
        socket.close();
      } catch (Exception e) {
        e.printStackTrace();
        continue; // We want server to keep running...
      }
    }

  }
}
