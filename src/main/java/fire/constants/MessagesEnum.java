package fire.constants;

/**
 * Created by rena17 on 1/22/2017.
 *
 * Available messages
 */
public enum MessagesEnum {

  AGE_CANT_BE_LESS_THAN_0("Age can't be < 0"),
  AGE_SHOULD_BE_MORE_THAN_17("Age should be > 17"),
  AGE_SHOULD_BE_LESS_THAN_18("Age should be < 18"),
  CUSTOMER_IS_NOT_A_STUDENT("Customer is not a student"),
  INVALID_BUNDLE_NAME("Invalid bundle name"),
  NO_BUNDLE_AVAILABLE("No Bundle available"),
  INCOME_SHOULD_BE_MORE_THAN_0("Income should be > 0"),
  INCOME_SHOULD_BE_MORE_THAN_12000("Income should be > 12000"),
  INCOME_SHOULD_BE_MORE_THAN_40000("Income should be > 40000");

  private String message;

  /**
   * Constructor
   *
   * @param message set message
   */
  MessagesEnum(String message) {
    this.message = message;
  }

  /**
   * Get message
   *
   * @return message
   */
  public String getMessage() {
    return message;
  }

}
