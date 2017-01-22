package fire.config;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.instanceOf;

import fire.service.CustomerService;
import org.junit.Test;

/**
 * Created by rena17 on 1/22/2017.
 */
public class AppConfigTest {

  @Test
  public void customerService() throws Exception {
    AppConfig appConfig = new AppConfig();
    Object object = appConfig.customerService();
    assertThat(object, instanceOf(CustomerService.class));
  }

}