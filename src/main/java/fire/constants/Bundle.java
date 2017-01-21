package fire.constants;

/**
 * Created by rena17 on 1/22/2017.
 *
 * All available bundles
 */
public enum Bundle {

    JUNIOR_SAVER("Junior Saver"),
    STUDENT("Student"),
    CLASSIC("Classic"),
    CLASSIC_PLUS("Classic Plus"),
    GOLD("Gold");

    private String bundleName;

    /**
     * Constructor
     *
     * @param bundleName set bundle name
     */
    Bundle (final String bundleName) {
        this.bundleName = bundleName;
    }

    /**
     * Get bundle name
     *
     * @return bundle name
     */
    public String getBundleName() {
        return bundleName;
    }
}
