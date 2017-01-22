package fire.constants;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Created by rena17 on 1/22/2017.
 */
public class BundleEnumTest {

  @Test
  public void getBundleName() throws Exception {
    assertThat(BundleEnum.JUNIOR_SAVER.getBundleName(), is("Junior Saver"));
    assertThat(BundleEnum.STUDENT.getBundleName(), is("Student"));
    assertThat(BundleEnum.CLASSIC.getBundleName(), is("Classic"));
    assertThat(BundleEnum.CLASSIC_PLUS.getBundleName(), is("Classic Plus"));
    assertThat(BundleEnum.GOLD.getBundleName(), is("Gold"));
  }

}