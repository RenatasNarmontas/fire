package fire.controller;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by rena17 on 1/22/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class BundleControllerTest {

  @Autowired
  private TestRestTemplate restTemplate;

  @Test
  public void getRecommendedBundle() throws Exception {
    final String uri = "/fire?age=18&student=true&income=0";
    ResponseEntity<String> entity = this.restTemplate.getForEntity(uri, String.class);
    assertThat(entity.getStatusCode(), is(HttpStatus.CREATED));
    assertThat(entity.getBody(), is("{\"bundleName\":\"Student\"}"));
  }

  @Test
  public void validateBundle() throws Exception {
    final String uri = "/bundle?bundle=Gold&age=18&student=false&income=40001";
    ResponseEntity<String> entity = this.restTemplate.getForEntity(uri, String.class);
    assertThat(entity.getStatusCode(), is(HttpStatus.OK));
    assertThat(entity.getBody(), is(nullValue()));
  }

}