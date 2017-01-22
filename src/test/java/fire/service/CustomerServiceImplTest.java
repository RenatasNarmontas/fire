package fire.service;

import static org.junit.Assert.*;

import fire.config.AppConfig;
import fire.constants.Bundle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by rena17 on 1/22/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
public class CustomerServiceImplTest {

  @Autowired
  private CustomerService customerService;

  @Test
  public void validateBundleAgainstQuestions() throws Exception {
    // Junior Saver allowed (age < 18 and student = false)
    ResponseEntity<String> responseEntity = customerService.validateBundleAgainstQuestions(Bundle.JUNIOR_SAVER.getBundleName(), 17, false, 0);
    assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    // Junior Saver not allowed (age > 17 and student = false)
    responseEntity = customerService.validateBundleAgainstQuestions(Bundle.JUNIOR_SAVER.getBundleName(), 18, false, 0);
    assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
    assertEquals(responseEntity.getBody(), "Age should be < 18");
    // Junior Saver allowed (age < 18 and student = true)
    responseEntity = customerService.validateBundleAgainstQuestions(Bundle.JUNIOR_SAVER.getBundleName(), 17, true, 0);
    assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    // Junior Saver not allowed (age > 17 and student = true)
    responseEntity = customerService.validateBundleAgainstQuestions(Bundle.JUNIOR_SAVER.getBundleName(), 18, true, 0);
    assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
    assertEquals(responseEntity.getBody(), "Age should be < 18");
    // Junior Saver allowed (age < 18 and income > 0 and student = false)
    responseEntity = customerService.validateBundleAgainstQuestions(Bundle.JUNIOR_SAVER.getBundleName(), 17, false, 1);
    assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    // Junior Saver not allowed (age > 17 and income > 0 and student = false)
    responseEntity = customerService.validateBundleAgainstQuestions(Bundle.JUNIOR_SAVER.getBundleName(), 18, false, 1);
    assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
    assertEquals(responseEntity.getBody(), "Age should be < 18");
    // Junior Saver allowed (age < 18 and income > 0 and student = true)
    responseEntity = customerService.validateBundleAgainstQuestions(Bundle.JUNIOR_SAVER.getBundleName(), 17, true, 1);
    assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    // Junior Saver not allowed (age > 17 and income > 0 and student = true)
    responseEntity = customerService.validateBundleAgainstQuestions(Bundle.JUNIOR_SAVER.getBundleName(), 18, true, 1);
    assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
    assertEquals(responseEntity.getBody(), "Age should be < 18");

    // Student allowed (age > 17 and student = true)
    responseEntity = customerService.validateBundleAgainstQuestions(Bundle.STUDENT.getBundleName(), 18, true, 0);
    assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    // Student not allowed (age < 18 and student  = true)
    responseEntity = customerService.validateBundleAgainstQuestions(Bundle.STUDENT.getBundleName(), 17, true, 0);
    assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
    assertEquals(responseEntity.getBody(), "Age should be > 17");
    // Student not allowed (age > 17 and student = false)
    responseEntity = customerService.validateBundleAgainstQuestions(Bundle.STUDENT.getBundleName(), 18, false, 0);
    assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
    assertEquals(responseEntity.getBody(), "Customer is not a student");

    // Classic allowed (age > 17 and income > 0 and student = false)
    responseEntity = customerService.validateBundleAgainstQuestions(Bundle.CLASSIC.getBundleName(), 18, false, 1);
    assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    // Classic not allowed (age > 17 and income < 1 and student = false)
    responseEntity = customerService.validateBundleAgainstQuestions(Bundle.CLASSIC.getBundleName(), 18, false, 0);
    assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
    assertEquals(responseEntity.getBody(), "Income should be > 0");
    // Classic not allowed (age < 18 and income > 0 and student = false)
    responseEntity = customerService.validateBundleAgainstQuestions(Bundle.CLASSIC.getBundleName(), 17, false, 1);
    assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
    assertEquals(responseEntity.getBody(), "Age should be > 17");
    // Classic not allowed (age < 18 and income < 1 and student = false)
    responseEntity = customerService.validateBundleAgainstQuestions(Bundle.CLASSIC.getBundleName(), 17, false, 0);
    assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
    assertEquals(responseEntity.getBody(), "Age should be > 17");
    // Classic allowed (age > 17 and income > 0 and student = true)
    responseEntity = customerService.validateBundleAgainstQuestions(Bundle.CLASSIC.getBundleName(), 18, true, 1);
    assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    // Classic not allowed (age > 17 and income < 1 and student = true)
    responseEntity = customerService.validateBundleAgainstQuestions(Bundle.CLASSIC.getBundleName(), 18, true, 0);
    assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
    assertEquals(responseEntity.getBody(), "Income should be > 0");
    // Classic not allowed (age < 18 and income > 0 and student = true)
    responseEntity = customerService.validateBundleAgainstQuestions(Bundle.CLASSIC.getBundleName(), 17, true, 1);
    assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
    assertEquals(responseEntity.getBody(), "Age should be > 17");
    // Classic not allowed (age < 18 and income < 1 and student = true)
    responseEntity = customerService.validateBundleAgainstQuestions(Bundle.CLASSIC.getBundleName(), 17, true, 0);
    assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
    assertEquals(responseEntity.getBody(), "Age should be > 17");

    // Classic Plus allowed (age > 17 and income > 12000 and student = false)
    responseEntity = customerService.validateBundleAgainstQuestions(Bundle.CLASSIC_PLUS.getBundleName(), 18, false, 12001);
    assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    // Classic Plus allowed (age < 18 and income > 12000 and student = false)
    responseEntity = customerService.validateBundleAgainstQuestions(Bundle.CLASSIC_PLUS.getBundleName(), 17, false, 12001);
    assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
    assertEquals(responseEntity.getBody(), "Age should be > 17");
    // Classic Plus allowed (age > 17 and income < 12001 and student = false)
    responseEntity = customerService.validateBundleAgainstQuestions(Bundle.CLASSIC_PLUS.getBundleName(), 18, false, 12000);
    assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
    assertEquals(responseEntity.getBody(), "Income should be > 12000");
    // Classic Plus allowed (age < 18 and income < 12001 and student = false)
    responseEntity = customerService.validateBundleAgainstQuestions(Bundle.CLASSIC_PLUS.getBundleName(), 17, false, 12000);
    assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
    assertEquals(responseEntity.getBody(), "Age should be > 17");
    // Classic Plus allowed (age > 17 and income > 12000 and student = true)
    responseEntity = customerService.validateBundleAgainstQuestions(Bundle.CLASSIC_PLUS.getBundleName(), 18, true, 12001);
    assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    // Classic Plus allowed (age < 18 and income > 12000 and student = true)
    responseEntity = customerService.validateBundleAgainstQuestions(Bundle.CLASSIC_PLUS.getBundleName(), 17, true, 12001);
    assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
    assertEquals(responseEntity.getBody(), "Age should be > 17");
    // Classic Plus allowed (age > 17 and income < 12001 and student = true)
    responseEntity = customerService.validateBundleAgainstQuestions(Bundle.CLASSIC_PLUS.getBundleName(), 18, true, 12000);
    assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
    assertEquals(responseEntity.getBody(), "Income should be > 12000");
    // Classic Plus allowed (age < 18 and income < 12001 and student = true)
    responseEntity = customerService.validateBundleAgainstQuestions(Bundle.CLASSIC_PLUS.getBundleName(), 17, true, 12000);
    assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
    assertEquals(responseEntity.getBody(), "Age should be > 17");

    // Gold allowed (age > 17 and income > 40000 and student = false)
    responseEntity = customerService.validateBundleAgainstQuestions(Bundle.GOLD.getBundleName(), 18, false, 40001);
    assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    // Gold allowed (age < 18 and income > 40000 and student = false)
    responseEntity = customerService.validateBundleAgainstQuestions(Bundle.GOLD.getBundleName(), 17, false, 40001);
    assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
    assertEquals(responseEntity.getBody(), "Age should be > 17");
    // Gold allowed (age > 17 and income < 40001 and student = false)
    responseEntity = customerService.validateBundleAgainstQuestions(Bundle.GOLD.getBundleName(), 18, false, 40000);
    assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
    assertEquals(responseEntity.getBody(), "Income should be > 40000");
    // Gold allowed (age < 18 and income < 40001 and student = false)
    responseEntity = customerService.validateBundleAgainstQuestions(Bundle.GOLD.getBundleName(), 17, false, 40000);
    assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
    assertEquals(responseEntity.getBody(), "Age should be > 17");
    // Gold allowed (age > 17 and income > 40000 and student = true)
    responseEntity = customerService.validateBundleAgainstQuestions(Bundle.GOLD.getBundleName(), 18, true, 40001);
    assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    // Gold allowed (age < 18 and income > 40000 and student = true)
    responseEntity = customerService.validateBundleAgainstQuestions(Bundle.GOLD.getBundleName(), 17, true, 40001);
    assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
    assertEquals(responseEntity.getBody(), "Age should be > 17");
    // Gold allowed (age > 17 and income < 40001 and student = true)
    responseEntity = customerService.validateBundleAgainstQuestions(Bundle.GOLD.getBundleName(), 18, true, 40000);
    assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
    assertEquals(responseEntity.getBody(), "Income should be > 40000");
    // Gold allowed (age < 18 and income < 40001 and student = true)
    responseEntity = customerService.validateBundleAgainstQuestions(Bundle.GOLD.getBundleName(), 17, true, 40000);
    assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
    assertEquals(responseEntity.getBody(), "Age should be > 17");

    // Not existing bundle requested
    responseEntity = customerService.validateBundleAgainstQuestions("NOT_EXISTING_BUNDLE", 18, true, 40001);
    assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
    assertEquals(responseEntity.getBody(), "Invalid bundle name");
  }

  @Test
  public void getRecommendedBundle() throws Exception {
    // Junior Saver (age < 18 and income = 0 and student = false)
    ResponseEntity<String> responseEntity = customerService.getRecommendedBundle(17, false, 0);
    assertEquals(responseEntity.getStatusCode(), HttpStatus.CREATED);
    assertEquals(responseEntity.getBody(), Bundle.JUNIOR_SAVER.getBundleName());

    // Student
    responseEntity = customerService.getRecommendedBundle(18, true, 0);
    assertEquals(responseEntity.getStatusCode(), HttpStatus.CREATED);
    assertEquals(responseEntity.getBody(), Bundle.STUDENT.getBundleName());

    // Classic (student = true)
    responseEntity = customerService.getRecommendedBundle(18, true, 1);
    assertEquals(responseEntity.getStatusCode(), HttpStatus.CREATED);
    assertEquals(responseEntity.getBody(), Bundle.CLASSIC.getBundleName());
    // Classic (student = false)
    responseEntity = customerService.getRecommendedBundle(18, false, 1);
    assertEquals(responseEntity.getStatusCode(), HttpStatus.CREATED);
    assertEquals(responseEntity.getBody(), Bundle.CLASSIC.getBundleName());

    // Classic Plus (student = true)
    responseEntity = customerService.getRecommendedBundle(18, true, 12001);
    assertEquals(responseEntity.getStatusCode(), HttpStatus.CREATED);
    assertEquals(responseEntity.getBody(), Bundle.CLASSIC_PLUS.getBundleName());
    // Classic Plus (student = false)
    responseEntity = customerService.getRecommendedBundle(18, false, 12001);
    assertEquals(responseEntity.getStatusCode(), HttpStatus.CREATED);
    assertEquals(responseEntity.getBody(), Bundle.CLASSIC_PLUS.getBundleName());

    // Gold (student = true)
    responseEntity = customerService.getRecommendedBundle(18, true, 40001);
    assertEquals(responseEntity.getStatusCode(), HttpStatus.CREATED);
    assertEquals(responseEntity.getBody(), Bundle.GOLD.getBundleName());
    // Gold (student = false)
    responseEntity = customerService.getRecommendedBundle(18, false, 40001);
    assertEquals(responseEntity.getStatusCode(), HttpStatus.CREATED);
    assertEquals(responseEntity.getBody(), Bundle.GOLD.getBundleName());

    // "No Bundle available" (age > 17, not student, income = 0)
    responseEntity = customerService.getRecommendedBundle(18, false, 0);
    assertEquals(responseEntity.getStatusCode(), HttpStatus.CREATED);
    assertEquals(responseEntity.getBody(), "No Bundle available");

    // "Age can't be < 0" (age < 0)
    responseEntity = customerService.getRecommendedBundle(-1, false, 0);
    assertEquals(responseEntity.getStatusCode(), HttpStatus.CREATED);
    assertEquals(responseEntity.getBody(), "Age can't be < 0");
  }

}