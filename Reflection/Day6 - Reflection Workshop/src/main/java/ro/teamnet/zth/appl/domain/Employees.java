package ro.teamnet.zth.appl.domain;

import ro.teamnet.zth.api.annotations.Column;
import ro.teamnet.zth.api.annotations.Id;
import ro.teamnet.zth.api.annotations.Table;

import java.util.Date;

/**
 * Created by Ionutz on 29.04.2015.
 */
@Table(name = "employees")
public class Employees {
    @Id(name = "EMPLOYEE_ID")
    private Long id;
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
    private Long jobId;
    @Column(name = "SALARY")
    private Double salary;
    @Column(name = "COMMISSION_PCT")
    private Double commissionPct;
}
