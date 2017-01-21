package fire.model;

/**
 * Created by rena17 on 1/21/2017.
 *
 * Class is used to store and retrieve various customer attributes
 */
public class Customer {

    private int age;
    private boolean student;
    private int income;
    private String bundle = "";

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
     * @return bundle
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

}
