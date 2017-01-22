package fire.constants;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Created by rena17 on 1/22/2017.
 */
public class MessagesEnumTest {

  @Test
  public void getMessage() throws Exception {
    // Validate all messages
    assertThat(MessagesEnum.AGE_CANT_BE_LESS_THAN_0.getMessage(),
        is("Age can't be < 0"));
    assertThat(MessagesEnum.AGE_SHOULD_BE_MORE_THAN_17.getMessage(),
        is("Age should be > 17"));
    assertThat(MessagesEnum.AGE_SHOULD_BE_LESS_THAN_18.getMessage(),
        is("Age should be < 18"));
    assertThat(MessagesEnum.CUSTOMER_IS_NOT_A_STUDENT.getMessage(),
        is("Customer is not a student"));
    assertThat(MessagesEnum.INVALID_BUNDLE_NAME.getMessage(),
        is("Invalid bundle name"));
    assertThat(MessagesEnum.NO_BUNDLE_AVAILABLE.getMessage(),
        is("No Bundle available"));
    assertThat(MessagesEnum.INCOME_SHOULD_BE_MORE_THAN_0.getMessage(),
        is("Income should be > 0"));
    assertThat(MessagesEnum.INCOME_SHOULD_BE_MORE_THAN_12000.getMessage(),
        is("Income should be > 12000"));
    assertThat(MessagesEnum.INCOME_SHOULD_BE_MORE_THAN_40000.getMessage(),
        is("Income should be > 40000"));
  }

}