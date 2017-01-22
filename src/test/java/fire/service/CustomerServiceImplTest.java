package fire.service;

import static org.junit.Assert.*;

import fire.constants.BundleEnum;
import fire.constants.MessagesEnum;
import fire.exception.BundleException;
import fire.exception.CustomerException;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by rena17 on 1/22/2017.
 */
public class CustomerServiceImplTest {

  private CustomerService customerService;

  @Before
  public void setUp() throws Exception {
    customerService = new CustomerServiceImpl();
  }

  @Test
  public void validateBundleAgainstQuestions() throws Exception {

    // ---- Junior Saver bundle validation ----
    // Junior Saver allowed (age < 18 and student = false)
    assertTrue(customerService.validateBundleAgainstQuestions(
        BundleEnum.JUNIOR_SAVER.getBundleName(), 17, false, 0));
    // Junior Saver not allowed (age > 17 and student = false)
    try {
      customerService
          .validateBundleAgainstQuestions(BundleEnum.JUNIOR_SAVER.getBundleName(), 18, false, 0);
      fail();
    } catch (CustomerException e) {
      assertEquals(MessagesEnum.AGE_SHOULD_BE_LESS_THAN_18.getMessage(), e.getMessage());
    }
    // Junior Saver allowed (age < 18 and student = true)
    assertTrue(customerService.validateBundleAgainstQuestions(
        BundleEnum.JUNIOR_SAVER.getBundleName(), 17, true, 0));
    // Junior Saver not allowed (age > 17 and student = true)
    try {
      customerService
          .validateBundleAgainstQuestions(BundleEnum.JUNIOR_SAVER.getBundleName(), 18, true, 0);
      fail();
    } catch (CustomerException e) {
      assertEquals(MessagesEnum.AGE_SHOULD_BE_LESS_THAN_18.getMessage(), e.getMessage());
    }
    // Junior Saver allowed (age < 18 and income > 0 and student = false)
    assertTrue(customerService.validateBundleAgainstQuestions(
        BundleEnum.JUNIOR_SAVER.getBundleName(), 17, false, 1));
    // Junior Saver not allowed (age > 17 and income > 0 and student = false)
    try {
      customerService.validateBundleAgainstQuestions(
          BundleEnum.JUNIOR_SAVER.getBundleName(), 18, false, 1);
      fail();
    } catch (CustomerException e) {
      assertEquals(MessagesEnum.AGE_SHOULD_BE_LESS_THAN_18.getMessage(), e.getMessage());
    }
    // Junior Saver allowed (age < 18 and income > 0 and student = true)
    assertTrue(customerService.validateBundleAgainstQuestions(
        BundleEnum.JUNIOR_SAVER.getBundleName(), 17, true, 1));
    // Junior Saver not allowed (age > 17 and income > 0 and student = true)
    try {
      customerService.validateBundleAgainstQuestions(
          BundleEnum.JUNIOR_SAVER.getBundleName(), 18, true, 1);
      fail();
    } catch (CustomerException e) {
      assertEquals(MessagesEnum.AGE_SHOULD_BE_LESS_THAN_18.getMessage(), e.getMessage());
    }

    // ---- Student bundle validation ----
    // Student allowed (age > 17 and student = true)
    assertTrue(customerService.validateBundleAgainstQuestions(
        BundleEnum.STUDENT.getBundleName(), 18, true, 0));
    // Student not allowed (age < 18 and student  = true)
    try {
      customerService.validateBundleAgainstQuestions(
          BundleEnum.STUDENT.getBundleName(), 17, true, 0);
      fail();
    } catch (CustomerException e) {
      assertEquals(MessagesEnum.AGE_SHOULD_BE_MORE_THAN_17.getMessage(), e.getMessage());
    }
    // Student not allowed (age > 17 and student = false)
    try {
      customerService.validateBundleAgainstQuestions(
          BundleEnum.STUDENT.getBundleName(), 18, false, 0);
      fail();
    } catch (CustomerException e) {
      assertEquals(MessagesEnum.CUSTOMER_IS_NOT_A_STUDENT.getMessage(), e.getMessage());
    }

    // Classic allowed (age > 17 and income > 0 and student = false)
    assertTrue(customerService.validateBundleAgainstQuestions(
        BundleEnum.CLASSIC.getBundleName(), 18, false, 1));
    // Classic not allowed (age > 17 and income < 1 and student = false)
    try {
      customerService.validateBundleAgainstQuestions(
          BundleEnum.CLASSIC.getBundleName(), 18, false, 0);
      fail();
    } catch (CustomerException e) {
      assertEquals(MessagesEnum.INCOME_SHOULD_BE_MORE_THAN_0.getMessage(), e.getMessage());
    }
    // Classic not allowed (age < 18 and income > 0 and student = false)
    try {
      customerService.validateBundleAgainstQuestions(
          BundleEnum.CLASSIC.getBundleName(), 17, false, 1);
      fail();
    } catch (CustomerException e) {
      assertEquals(MessagesEnum.AGE_SHOULD_BE_MORE_THAN_17.getMessage(), e.getMessage());
    }
    // Classic not allowed (age < 18 and income < 1 and student = false)
    try {
      customerService.validateBundleAgainstQuestions(
          BundleEnum.CLASSIC.getBundleName(), 17, false, 0);
      fail();
    } catch (CustomerException e) {
      assertEquals(MessagesEnum.AGE_SHOULD_BE_MORE_THAN_17.getMessage(), e.getMessage());
    }
    // Classic allowed (age > 17 and income > 0 and student = true)
    assertTrue(customerService.validateBundleAgainstQuestions(
        BundleEnum.CLASSIC.getBundleName(), 18, true, 1));
    // Classic not allowed (age > 17 and income < 1 and student = true)
    try {
      customerService.validateBundleAgainstQuestions(
          BundleEnum.CLASSIC.getBundleName(), 18, true, 0);
      fail();
    } catch (CustomerException e) {
      assertEquals(MessagesEnum.INCOME_SHOULD_BE_MORE_THAN_0.getMessage(), e.getMessage());
    }
    // Classic not allowed (age < 18 and income > 0 and student = true)
    try {
      customerService.validateBundleAgainstQuestions(
          BundleEnum.CLASSIC.getBundleName(), 17, true, 1);
      fail();
    } catch (CustomerException e) {
      assertEquals(MessagesEnum.AGE_SHOULD_BE_MORE_THAN_17.getMessage(), e.getMessage());
    }
    // Classic not allowed (age < 18 and income < 1 and student = true)
    try {
      customerService.validateBundleAgainstQuestions(
          BundleEnum.CLASSIC.getBundleName(), 17, true, 0);
      fail();
    } catch (CustomerException e) {
      assertEquals(MessagesEnum.AGE_SHOULD_BE_MORE_THAN_17.getMessage(), e.getMessage());
    }

    // ---- Classic Plus bundle validation ----
    // Classic Plus allowed (age > 17 and income > 12000 and student = false)
    assertTrue(customerService.validateBundleAgainstQuestions(
        BundleEnum.CLASSIC_PLUS.getBundleName(), 18, false, 12001));
    // Classic Plus allowed (age < 18 and income > 12000 and student = false)
    try {
      customerService.validateBundleAgainstQuestions(
          BundleEnum.CLASSIC_PLUS.getBundleName(), 17, false, 12001);
      fail();
    } catch (CustomerException e) {
      assertEquals(MessagesEnum.AGE_SHOULD_BE_MORE_THAN_17.getMessage(), e.getMessage());
    }
    // Classic Plus allowed (age > 17 and income < 12001 and student = false)
    try {
      customerService.validateBundleAgainstQuestions(
          BundleEnum.CLASSIC_PLUS.getBundleName(), 18, false, 12000);
      fail();
    } catch (CustomerException e) {
      assertEquals(MessagesEnum.INCOME_SHOULD_BE_MORE_THAN_12000.getMessage(), e.getMessage());
    }
    // Classic Plus allowed (age < 18 and income < 12001 and student = false)
    try {
      customerService.validateBundleAgainstQuestions(
          BundleEnum.CLASSIC_PLUS.getBundleName(), 17, false, 12000);
      fail();
    } catch (CustomerException e) {
      assertEquals(MessagesEnum.AGE_SHOULD_BE_MORE_THAN_17.getMessage(), e.getMessage());
    }
    // Classic Plus allowed (age > 17 and income > 12000 and student = true)
    assertTrue(customerService.validateBundleAgainstQuestions(
        BundleEnum.CLASSIC_PLUS.getBundleName(), 18, true, 12001));
    // Classic Plus allowed (age < 18 and income > 12000 and student = true)
    try {
      customerService.validateBundleAgainstQuestions(
          BundleEnum.CLASSIC_PLUS.getBundleName(), 17, true, 12001);
      fail();
    } catch (CustomerException e) {
      assertEquals(MessagesEnum.AGE_SHOULD_BE_MORE_THAN_17.getMessage(), e.getMessage());
    }
    // Classic Plus allowed (age > 17 and income < 12001 and student = true)
    try {
      customerService.validateBundleAgainstQuestions(
          BundleEnum.CLASSIC_PLUS.getBundleName(), 18, true, 12000);
      fail();
    } catch (CustomerException e) {
      assertEquals(MessagesEnum.INCOME_SHOULD_BE_MORE_THAN_12000.getMessage(), e.getMessage());
    }
    // Classic Plus allowed (age < 18 and income < 12001 and student = true)
    try {
      customerService.validateBundleAgainstQuestions(
          BundleEnum.CLASSIC_PLUS.getBundleName(), 17, true, 12000);
      fail();
    } catch (CustomerException e) {
      assertEquals(MessagesEnum.AGE_SHOULD_BE_MORE_THAN_17.getMessage(), e.getMessage());
    }

    // ---- Gold bundle validation ----
    // Gold allowed (age > 17 and income > 40000 and student = false)
    assertTrue(customerService.validateBundleAgainstQuestions(
        BundleEnum.GOLD.getBundleName(), 18, false, 40001));
    // Gold allowed (age < 18 and income > 40000 and student = false)
    try {
      customerService.validateBundleAgainstQuestions(
          BundleEnum.GOLD.getBundleName(), 17, false, 40001);
      fail();
    } catch (CustomerException e) {
      assertEquals(MessagesEnum.AGE_SHOULD_BE_MORE_THAN_17.getMessage(), e.getMessage());
    }
    // Gold allowed (age > 17 and income < 40001 and student = false)
    try {
      customerService.validateBundleAgainstQuestions(
          BundleEnum.GOLD.getBundleName(), 18, false, 40000);
      fail();
    } catch (CustomerException e) {
      assertEquals(MessagesEnum.INCOME_SHOULD_BE_MORE_THAN_40000.getMessage(), e.getMessage());
    }
    // Gold allowed (age < 18 and income < 40001 and student = false)
    try {
      customerService.validateBundleAgainstQuestions(
          BundleEnum.GOLD.getBundleName(), 17, false, 40000);
      fail();
    } catch (CustomerException e) {
      assertEquals(MessagesEnum.AGE_SHOULD_BE_MORE_THAN_17.getMessage(), e.getMessage());
    }
    // Gold allowed (age > 17 and income > 40000 and student = true)
    assertTrue(customerService.validateBundleAgainstQuestions(
        BundleEnum.GOLD.getBundleName(), 18, true, 40001));
    // Gold allowed (age < 18 and income > 40000 and student = true)
    try {
      customerService.validateBundleAgainstQuestions(
          BundleEnum.GOLD.getBundleName(), 17, true, 40001);
      fail();
    } catch (CustomerException e) {
      assertEquals(MessagesEnum.AGE_SHOULD_BE_MORE_THAN_17.getMessage(), e.getMessage());
    }
    // Gold allowed (age > 17 and income < 40001 and student = true)
    try {
      customerService.validateBundleAgainstQuestions(
          BundleEnum.GOLD.getBundleName(), 18, true, 40000);
      fail();
    } catch (CustomerException e) {
      assertEquals(MessagesEnum.INCOME_SHOULD_BE_MORE_THAN_40000.getMessage(), e.getMessage());
    }
    // Gold allowed (age < 18 and income < 40001 and student = true)
    try {
      customerService.validateBundleAgainstQuestions(
          BundleEnum.GOLD.getBundleName(), 17, true, 40000);
      fail();
    } catch (CustomerException e) {
      assertEquals(MessagesEnum.AGE_SHOULD_BE_MORE_THAN_17.getMessage(), e.getMessage());
    }

    // Not existing bundle requested
    try {
      customerService.validateBundleAgainstQuestions("NOT_EXISTING_BUNDLE", 18, true, 40001);
      fail();
    } catch (CustomerException e) {
      assertEquals(MessagesEnum.INVALID_BUNDLE_NAME.getMessage(), e.getMessage());
    }
  }

  @Test
  public void getRecommendedBundle() throws Exception {
    // Junior Saver (age < 18 and income = 0 and student = false)
    assertEquals(customerService.getRecommendedBundle(17, false, 0).getBundleName(),
        BundleEnum.JUNIOR_SAVER.getBundleName());

    // Student
    assertEquals(customerService.getRecommendedBundle(18, true, 0).getBundleName(),
        BundleEnum.STUDENT.getBundleName());

    // Classic (student = true)
    assertEquals(customerService.getRecommendedBundle(18, true, 1).getBundleName(),
        BundleEnum.CLASSIC.getBundleName());
    // Classic (student = false)
    assertEquals(customerService.getRecommendedBundle(18, false, 1).getBundleName(),
        BundleEnum.CLASSIC.getBundleName());

    // Classic Plus (student = true)
    assertEquals(customerService.getRecommendedBundle(18, true, 12001).getBundleName(),
        BundleEnum.CLASSIC_PLUS.getBundleName());
    // Classic Plus (student = false)
    assertEquals(customerService.getRecommendedBundle(18, false, 12001).getBundleName(),
        BundleEnum.CLASSIC_PLUS.getBundleName());

    // Gold (student = true)
    assertEquals(customerService.getRecommendedBundle(18, true, 40001).getBundleName(),
        BundleEnum.GOLD.getBundleName());
    // Gold (student = false)
    assertEquals(customerService.getRecommendedBundle(18, false, 40001).getBundleName(),
        BundleEnum.GOLD.getBundleName());

    // "No BundleEnum available" (age > 17, not student, income = 0)
    try {
      customerService.getRecommendedBundle(18, false, 0);
      fail();
    } catch (BundleException e) {
      assertEquals(MessagesEnum.NO_BUNDLE_AVAILABLE.getMessage(), e.getMessage());
    }

    // "Age can't be < 0" (age < 0)
    try {
      customerService.getRecommendedBundle(-1, false, 0);
      fail();
    } catch (BundleException e) {
      assertEquals(MessagesEnum.AGE_CANT_BE_LESS_THAN_0.getMessage(), e.getMessage());
    }
  }

}