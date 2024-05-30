package entity;


import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="dept_id")
    private int deptId;
    @Column(name="dept_name", nullable=false, length=10)
    private String deptName;

    @OneToMany(mappedBy = "department")
    private List<Employee> employees;

    public List<Employee> addNewEmployee(Employee employee) {
       if(employees == null) {
           employees = new ArrayList<>();
       }
       employees.add(employee);
       return employees;
    }

    @Override
    public String toString() {
        return "Department{" +
                "deptId=" + deptId +
                ", deptName='" + deptName + '\'' +
                '}';
    }
}
