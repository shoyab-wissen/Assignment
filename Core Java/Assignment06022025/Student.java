package Assignment06022025;

import Assignment06022025.Gender;
import Assignment06022025.Fees;

public class Student {
    private String rollNo;
    private String name;
    private int age;
    private int standard;
    private String school;
    private Gender gender;
    private double percentage;
    private Fees fees;

    public Student(String rollNo, String name, int age, int standard, String school, Gender gender, double percentage,
            double totalFees, double feesPaid) {
        this.rollNo = rollNo;
        this.name = name;
        this.age = age;
        this.standard = standard;
        this.school = school;
        this.gender = gender;
        this.percentage = percentage;
        this.setFees(new Fees(totalFees, feesPaid));
    }

    public String getRollNo() {
        return rollNo;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getStandard() {
        return standard;
    }

    public void setStandard(int standard) {
        this.standard = standard;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public Fees getFees() {
        return fees;
    }

    public void setFees(Fees fees) {
        this.fees = fees;
    }

    @Override
    public String toString() {
        return "Student [rollNo=" + rollNo + ", name=" + name + ", age=" + age + ", standard=" + standard + ", school="
                + school + ", gender=" + gender + ", percentage=" + percentage + ", fees=" + fees + "]";
    }

}
