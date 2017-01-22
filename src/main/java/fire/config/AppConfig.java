package fire.config;

import fire.service.CustomerService;
import fire.service.CustomerServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by rena17 on 1/20/2017.
 */
@Configuration
public class AppConfig {

  @Bean
  public CustomerService customerService() {
    return new CustomerServiceImpl();
  }

}