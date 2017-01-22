package fire.domain;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by rena17 on 1/22/2017.
 */
public class CustomerTest {

  private Customer customer;

  @Before
  public void setUp() throws Exception {
    customer = new Customer();
  }

  @Test
  public void getAge() throws Exception {
    int age = 10;
    customer.setAge(age);
    assertEquals(age, customer.getAge());
  }

  @Test
  public void isStudent() throws Exception {
    // test all available values
    boolean studentStatus = true;
    customer.setStudent(studentStatus);
    assertEquals(studentStatus, customer.isStudent());

    studentStatus = false;
    customer.setStudent(studentStatus);
    assertEquals(studentStatus, customer.isStudent());
  }

  @Test
  public void getIncome() throws Exception {
    int income = 400001;
    customer.setIncome(income);
    assertEquals(income, customer.getIncome());
  }

  @Test
  public void getBundle() throws Exception {
    String bundle = "test bundle";
    customer.setBundle(bundle);
    assertEquals(bundle, customer.getBundle());
  }

}