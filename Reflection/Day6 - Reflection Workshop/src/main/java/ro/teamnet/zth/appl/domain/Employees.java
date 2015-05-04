package ro.teamnet.zth.appl.domain;

import ro.teamnet.zth.api.annotations.Column;
import ro.teamnet.zth.api.annotations.Id;
import ro.teamnet.zth.api.annotations.Table;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * Created by Ionutz on 29.04.2015.
 */
@Table(name = "employees")
public class Employees {
    @Id(name = "EMPLOYEE_ID")
    private Integer id;
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Column(name = "LAST_NAME")
    private String lastName;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;
    @Column(name = "HIRE_DATE")
    private Date hireDate;
    @Column(name = "JOB_ID")
    private String jobId;
    @Column(name = "SALARY")
    private BigDecimal salary;
    @Column(name = "COMMISION_PCT")
    private BigDecimal commissionPct;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public BigDecimal getCommissionPct() {
        return commissionPct;
    }

    public void setCommissionPct(BigDecimal commissionPct) {
        this.commissionPct = commissionPct;
    }

    @Override
    public String toString() {
        return "Employees{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", hireDate=" + hireDate +
                ", jobId='" + jobId + '\'' +
                ", salary=" + salary +
                ", commissionPct=" + commissionPct +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employees employees = (Employees) o;

        if (id != null ? !id.equals(employees.id) : employees.id != null) return false;
        if (firstName != null ? !firstName.equals(employees.firstName) : employees.firstName != null) return false;
        if (lastName != null ? !lastName.equals(employees.lastName) : employees.lastName != null) return false;
        if (email != null ? !email.equals(employees.email) : employees.email != null) return false;
        if (phoneNumber != null ? !phoneNumber.equals(employees.phoneNumber) : employees.phoneNumber != null)
            return false;
        if (hireDate != null ? !hireDate.equals(employees.hireDate) : employees.hireDate != null) return false;
        if (jobId != null ? !jobId.equals(employees.jobId) : employees.jobId != null) return false;
        if (salary != null ? !salary.equals(employees.salary) : employees.salary != null) return false;
        return !(commissionPct != null ? !commissionPct.equals(employees.commissionPct) : employees.commissionPct != null);

    }

}
