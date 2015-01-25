/**
 * @Author Anindya Bandopadhyay
 *
 * SimpleHttpWebServer\andy.blog.constant.Contant.java
 *
 * @Created on Sep 28, 2014  9:06:15 PM
 */

package andy.blog.constant;

public final class ServerConstant {

  public static final String START_SERVER = "start";
  public static final String STOP_SERVER = "stop";
  public static final String WORKING_DIR = System.getProperty("user.dir");
  public static final String DEFAULT_WEB_ROOT = "webResource";
  public static final String HOSTNAME = "localhost";
  
  public static final int BUFFER_SIZE = 2048; // 2048 size of GET request
  public static final int PORT = 7070;
}
