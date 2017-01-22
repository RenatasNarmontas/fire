package fire.service;

import fire.constants.Bundle;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Created by rena17 on 1/22/2017.
 *
 * Main business logic implementation.
 * Validating or proposing best bundle for customer according age, student and income values
 */
public class CustomerServiceImpl implements CustomerService {

  /**
   * Validates bundle name according to age, student and income values.
   *
   * @param bundle bundle name to validate
   * @param age customer age
   * @param student customer student status
   * @param income customer income
   * @return ResponseEntity<String>
   */
  public ResponseEntity<String> validateBundleAgainstQuestions(String bundle, int age,
      boolean student, int income) {

    // Check entered bundle for availability
    if (!validateBundleName(bundle)) {
      return new ResponseEntity<>("Invalid bundle name", HttpStatus.BAD_REQUEST);
    }

    if (age < 0) {
      // Invalid age
      return new ResponseEntity<>("Age can't be < 0",
          HttpStatus.BAD_REQUEST); // TODO: REQUESTED_RANGE_NOT_SATISFIABLE is possible
    } else if (age < 18) {
      // Only Junior Saver should be available for age < 18
      if (bundle.equals(Bundle.JUNIOR_SAVER.getBundleName())) {
        return new ResponseEntity<>(HttpStatus.OK);
      } else {
        return new ResponseEntity<>("Age should be > 17", HttpStatus.BAD_REQUEST);
      }
    } else {
      // Need to evaluate almost all bundles in that age range (except Junior Saver)

      // Student bundle should be available for students only
      if (bundle.equals(Bundle.STUDENT.getBundleName())) {
        if (student) {
          return new ResponseEntity<>(HttpStatus.OK);
        } else {
          return new ResponseEntity<>("Customer is not a student", HttpStatus.BAD_REQUEST);
        }
      }

      // Classic bundle should be available for income > 0
      if (bundle.equals(Bundle.CLASSIC.getBundleName())) {
        if (income > 0) {
          return new ResponseEntity<>(HttpStatus.OK);
        } else {
          return new ResponseEntity<>("Income should be > 0", HttpStatus.BAD_REQUEST);
        }
      }

      // Classic Plus bundle should be available for income > 12000
      if (bundle.equals(Bundle.CLASSIC_PLUS.getBundleName())) {
        if (income > 12000) {
          return new ResponseEntity<>(HttpStatus.OK);
        } else {
          return new ResponseEntity<>("Income should be > 12000", HttpStatus.BAD_REQUEST);
        }
      }

      // Gold bundle should be available for income > 40000
      if (bundle.equals(Bundle.GOLD.getBundleName())) {
        if (income > 40000) {
          return new ResponseEntity<>(HttpStatus.OK);
        } else {
          return new ResponseEntity<>("Income should be > 40000", HttpStatus.BAD_REQUEST);
        }
      }
    }

    // Junior Saver bundle was requested for customer who have age > 17
    return new ResponseEntity<>("Age should be < 18", HttpStatus.BAD_REQUEST);
  }

  /**
   * Proposes best bundle according to age, student and income values
   *
   * @param age customer age
   * @param student customer student status
   * @param income customer income
   * @return ResponseEntity<String>
   */
  public ResponseEntity<String> getRecommendedBundle(int age, boolean student, int income) {
    // Evaluate by age first
    if (age < 0) {
      return new ResponseEntity<>("Age can't be < 0",
          HttpStatus.CREATED); // TODO: REQUESTED_RANGE_NOT_SATISFIABLE or BAD_REQUEST is possible
    } else if (age < 18) {
      return new ResponseEntity<>(Bundle.JUNIOR_SAVER.getBundleName(), HttpStatus.CREATED);
    } else {
      // Evaluate the highest bundles first and return it if condition meets
      if (income > 40000) {
        return new ResponseEntity<>(Bundle.GOLD.getBundleName(), HttpStatus.CREATED);
      }
      if (income > 12000) {
        return new ResponseEntity<>(Bundle.CLASSIC_PLUS.getBundleName(), HttpStatus.CREATED);
      }
      if (income > 0) {
        return new ResponseEntity<>(Bundle.CLASSIC.getBundleName(), HttpStatus.CREATED);
      } else {
        if (student) {
          return new ResponseEntity<>(Bundle.STUDENT.getBundleName(), HttpStatus.CREATED);
        }
      }
    }

    // No matches
    return new ResponseEntity<>("No Bundle available",
        HttpStatus.CREATED); // TODO: HttpStatus.BAD_REQUEST?
  }

  /**
   * Validates bundle name for existence
   *
   * @param bundleName bundle name
   * @return true if name is valid or false if name is invalid
   */
  private boolean validateBundleName(String bundleName) {
    return bundleName.equals(Bundle.JUNIOR_SAVER.getBundleName()) ||
        bundleName.equals(Bundle.STUDENT.getBundleName()) ||
        bundleName.equals(Bundle.CLASSIC.getBundleName()) ||
        bundleName.equals(Bundle.CLASSIC_PLUS.getBundleName()) ||
        bundleName.equals(Bundle.GOLD.getBundleName());
  }

}