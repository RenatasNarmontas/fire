package fire.service;

import fire.domain.Bundle;
import fire.exception.BundleException;
import fire.exception.CustomerException;

/**
 * Created by rena17 on 1/22/2017.
 *
 * Contract to implement business logic selecting and validating bundle offers according to age,
 * student and income values
 */
public interface CustomerService {

  /**
   * Validates bundle name according to age, student and income values
   *
   * @param bundle bundle name to validate
   * @param age customer age
   * @param student customer student status
   * @param income customer income
   * @return ResponseEntity<String>
   */
  boolean validateBundleAgainstQuestions(String bundle, int age, boolean student,
      int income) throws CustomerException;

  /**
   * Returns recommended bundle name according to age, student and income values
   *
   * @param age customer age
   * @param student customer student status
   * @param income customer income
   * @return ResponseEntity<String>
   * @throws BundleException throw exception if parameters is invalid or bundle not found
   */
  Bundle getRecommendedBundle(int age, boolean student, int income) throws BundleException;

}