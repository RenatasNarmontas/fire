package fire.domain;

/**
 * Created by rena17 on 1/22/2017.
 *
 * Class is used to store and retrieve various customer attributes
 */
public class Customer {

  private int age;
  private boolean student;
  private int income;
  private String bundle = "";

  /**
   * Constructor
   *
   * @param age customer age
   * @param student student status
   * @param income customer income
   * @param bundle customer bundle
   */
  public Customer(int age, boolean student, int income, String bundle) {
    this.age = age;
    this.student = student;
    this.income = income;
    this.bundle = bundle;
  }

  /**
   * Empty constructor
   */
  public Customer() {

  }

  /**
   * @return customer age
   */
  public int getAge() {
    return age;
  }

  /**
   * @param age age to set
   */
  public void setAge(int age) {
    this.age = age;
  }

  /**
   * @return true if customer is a student or false otherwise
   */
  public boolean isStudent() {
    return student;
  }

  /**
   * @param student set true if customer is a student or false if customer is not a student
   */
  public void setStudent(boolean student) {
    this.student = student;
  }

  /**
   * @return customer income
   */
  public int getIncome() {
    return income;
  }

  /**
   * @param income set customer income
   */
  public void setIncome(int income) {
    this.income = income;
  }

  /**
   * @return bundle name
   */
  public String getBundle() {
    return bundle;
  }

  /**
   * @param bundle set bundle
   */
  public void setBundle(String bundle) {
    this.bundle = bundle;
  }

  @Override
  public String toString() {
    return "Customer{" +
        "age=" + age +
        ", student=" + student +
        ", income=" + income +
        ", bundle='" + bundle + '\'' +
        '}';
  }

}