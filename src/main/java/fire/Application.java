package fire;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by rena17 on 1/21/2017.
 *
 * Main application class
 */
@SpringBootApplication
public class Application {

  /**
   * Entry point
   *
   * @param args command line arguments
   * @throws Exception if some Exception occurs
   */
  public static void main(String[] args) throws Exception {
    SpringApplication.run(Application.class, args);
  }

}
