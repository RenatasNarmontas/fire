package fire.constants;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Created by rena17 on 1/22/2017.
 */
public class BundleTest {

  @Test
  public void getBundleName() throws Exception {
    assertThat(Bundle.JUNIOR_SAVER.getBundleName(), is("Junior Saver"));
    assertThat(Bundle.STUDENT.getBundleName(), is("Student"));
    assertThat(Bundle.CLASSIC.getBundleName(), is("Classic"));
    assertThat(Bundle.CLASSIC_PLUS.getBundleName(), is("Classic Plus"));
    assertThat(Bundle.GOLD.getBundleName(), is("Gold"));
  }

}