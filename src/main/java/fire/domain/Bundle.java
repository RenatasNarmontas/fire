package fire.domain;

/**
 * Created by rena17 on 1/22/2017.
 *
 * Class is used to store and retrieve various bundle attributes
 */
public class Bundle {

  private String bundleName;

  /**
   * Constructor
   *
   * @param bundleName
   */
  public Bundle(String bundleName) {
    this.bundleName = bundleName;
  }

  /**
   * Default constructor
   */
  public Bundle() {
  }

  /**
   * @return bundle name
   */
  public String getBundleName() {
    return bundleName;
  }

  /**
   * @param bundleName set bundle name
   */
  public void setBundleName(String bundleName) {
    this.bundleName = bundleName;
  }

  @Override
  public String toString() {
    return "Bundle{" +
        "bundleName='" + bundleName + '\'' +
        '}';
  }

}
