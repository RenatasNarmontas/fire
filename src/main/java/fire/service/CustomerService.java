package fire.service;

import org.springframework.http.ResponseEntity;

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
  ResponseEntity<String> validateBundleAgainstQuestions(String bundle, int age, boolean student,
      int income);

  /**
   * Returns recommended bundle name according to age, student and income values
   *
   * @param age customer age
   * @param student customer student status
   * @param income customer income
   * @return ResponseEntity<String>
   */
  ResponseEntity<String> getRecommendedBundle(int age, boolean student, int income);

}