package fire.service;

import fire.constants.BundleEnum;
import fire.domain.Bundle;
import fire.exception.BundleException;
import fire.exception.CustomerException;

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
  public boolean validateBundleAgainstQuestions(String bundle, int age,
      boolean student, int income) throws CustomerException {

    // Check entered bundle for availability
    if (!validateBundleName(bundle)) {
      throw new CustomerException("Invalid bundle name");
    }

    if (age < 0) {
      // Invalid age
      throw new CustomerException("Age can't be < 0");
    } else if (age < 18) {
      // Only Junior Saver should be available for age < 18
      if (bundle.equals(BundleEnum.JUNIOR_SAVER.getBundleName())) {
        return true;
      } else {
        throw new CustomerException("Age should be > 17");
      }
    } else {
      // Need to evaluate almost all bundles in that age range (except Junior Saver)

      // Student bundle should be available for students only
      if (bundle.equals(BundleEnum.STUDENT.getBundleName())) {
        if (student) {
          return true;
        } else {
          throw new CustomerException("Customer is not a student");
        }
      }

      // Classic bundle should be available for income > 0
      if (bundle.equals(BundleEnum.CLASSIC.getBundleName())) {
        if (income > 0) {
          return true;
        } else {
          throw new CustomerException("Income should be > 0");
        }
      }

      // Classic Plus bundle should be available for income > 12000
      if (bundle.equals(BundleEnum.CLASSIC_PLUS.getBundleName())) {
        if (income > 12000) {
          return true;
        } else {
          throw new CustomerException("Income should be > 12000");
        }
      }

      // Gold bundle should be available for income > 40000
      if (bundle.equals(BundleEnum.GOLD.getBundleName())) {
        if (income > 40000) {
          return true;
        } else {
          throw new CustomerException("Income should be > 40000");
        }
      }
    }

    // Junior Saver bundle was requested for customer who have age > 17
    throw new CustomerException("Age should be < 18");
  }

  /**
   * Proposes best bundle according to age, student and income values
   *
   * @param age customer age
   * @param student customer student status
   * @param income customer income
   * @return Bundle
   */
  public Bundle getRecommendedBundle(int age, boolean student, int income) throws BundleException {
    Bundle bundle = new Bundle();
    // Evaluate by age first
    if (age < 0) {
      throw new BundleException("Age can't be < 0");
    } else if (age < 18) {
      bundle.setBundleName(BundleEnum.JUNIOR_SAVER.getBundleName());
      return bundle;
    } else {
      // Evaluate the highest bundles first and return it if condition meets
      if (income > 40000) {
        bundle.setBundleName(BundleEnum.GOLD.getBundleName());
        return bundle;
      }
      if (income > 12000) {
        bundle.setBundleName(BundleEnum.CLASSIC_PLUS.getBundleName());
        return bundle;
      }
      if (income > 0) {
        bundle.setBundleName(BundleEnum.CLASSIC.getBundleName());
        return bundle;
      } else {
        if (student) {
          bundle.setBundleName(BundleEnum.STUDENT.getBundleName());
          return bundle;
        }
      }
    }

    // No matches
    throw new BundleException("No Bundle available");
  }

  /**
   * Validates bundle name for existence
   *
   * @param bundleName bundle name
   * @return true if name is valid or false if name is invalid
   */
  private boolean validateBundleName(String bundleName) {
    return bundleName.equals(BundleEnum.JUNIOR_SAVER.getBundleName()) ||
        bundleName.equals(BundleEnum.STUDENT.getBundleName()) ||
        bundleName.equals(BundleEnum.CLASSIC.getBundleName()) ||
        bundleName.equals(BundleEnum.CLASSIC_PLUS.getBundleName()) ||
        bundleName.equals(BundleEnum.GOLD.getBundleName());
  }

}