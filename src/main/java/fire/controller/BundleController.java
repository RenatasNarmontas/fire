package fire.controller;

import fire.domain.Bundle;
import fire.domain.Customer;
import fire.exception.BundleException;
import fire.exception.CustomerException;
import fire.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest controller
 * /fire - to get best available bundle
 * /bundle - to validate bundle availability
 *
 * Created by rena17 on 1/22/2017.
 */
@RestController
public class BundleController {

  private final CustomerService customerService;

  @Autowired
  public BundleController(CustomerService customerService) {
    this.customerService = customerService;
  }

  /**
   * Returns best available bundle according to age, student and income values
   *
   * @param age customer age
   * @param student student status
   * @param income customer income
   * @return HttpStatus and Bundle JSON
   */
  @GetMapping(value = "/fire")
  public ResponseEntity<Bundle> getRecommendedBundle(
      @RequestParam(value = "age", required = true) int age,
      @RequestParam(value = "student", required = false) boolean student,
      @RequestParam(value = "income", required = false, defaultValue = "0") int income
  ) {
    Bundle bundle = null;
    try {
      bundle = customerService.getRecommendedBundle(age, student, income);
    } catch (BundleException e) {
      bundle = new Bundle(e.getMessage());
      // TODO: Do we need to return different HttpStatus status?
      // return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bundle);
    }
    return ResponseEntity.status(HttpStatus.CREATED).body(bundle);
  }

  /**
   * Validates if bundle can be assigned to customer according to age, student and income values
   *
   * @param bundle bundle to validate
   * @param age custiner age
   * @param student student status
   * @param income customer income
   * @return HttpStatus and optionally Customer JSON
   */
  @GetMapping(value = "/bundle")
  public ResponseEntity<Customer> validateBundle(
      @RequestParam(value = "bundle", required = true) String bundle,
      @RequestParam(value = "age", required = true) int age,
      @RequestParam(value = "student", required = false) boolean student,
      @RequestParam(value = "income", required = false, defaultValue = "0") int income
  ) {
    try {
      customerService.validateBundleAgainstQuestions(bundle, age, student, income);
    } catch (CustomerException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Customer(age, student, income,
          e.getMessage()));
    }

    // TODO: do we need to return Customer as JSON?
    return ResponseEntity.status(HttpStatus.OK).body(null);
  }

}
