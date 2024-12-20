import java.util.Date;

public class Employee {
    private int empId;
    private String firstName;
    private String lastName;
    private String ssn;
    private String jobTitle;
    private String division;
    private double salary;
    private Date hireDate;

    // Constructor
    public Employee(int empId, String firstName, String lastName, String ssn, String jobTitle, String division, double salary, Date hireDate) {
        this.empId = empId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.ssn = ssn;
        this.jobTitle = jobTitle;
        this.division = division;
        this.salary = salary;
        this.hireDate = hireDate;
    }

    // Getters and Setters
    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    @Override
    public String toString() {
        return "Employee [empId=" + empId + ", firstName=" + firstName + ", lastName=" + lastName +
                ", ssn=" + ssn + ", jobTitle=" + jobTitle + ", division=" + division +
                ", salary=" + salary + ", hireDate=" + hireDate + "]";
    }
}
