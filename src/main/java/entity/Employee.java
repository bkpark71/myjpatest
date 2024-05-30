package entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name="employee")
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    @Id
    @Column(name="emp_id", nullable=false, length=6)
    private String empId;
    @Column(name="emp_name", nullable=false, length=20)
    private String empName;
    // 단방향 다대일 연관관계 설정
    @ManyToOne(fetch = FetchType.LAZY) // default = EAGER(즉시로딩), LAZY(지연로딩)
    @JoinColumn(name="dept_id")
    private Department department;
    @Enumerated(EnumType.STRING)
    @Column(name="emp_type", length = 1)
    private EmpType empType;
    @Column(name="join_date", length = 10)
    private String joinDate;
    private Long salary;
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "employee")
    private Equipment equipment;

    @Override
    public String toString() {
        return "Employee{" +
                "empId='" + empId + '\'' +
                ", empName='" + empName + '\'' +
                ", department=" + department +
                ", empType=" + empType +
                ", joinDate='" + joinDate + '\'' +
                ", salary=" + salary +
                '}';
    }
}
