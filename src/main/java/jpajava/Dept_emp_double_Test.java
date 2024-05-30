package jpajava;

import entity.Department;
import entity.EmpType;
import entity.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class Dept_emp_double_Test {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpatest");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        //양방향 연관관계 테스트
        tx.begin();
        try{
            Department department = new Department();
            department.setDeptName("IT");
            department.setEmployees(null);
            em.persist(department); // insert ==> dept_id
            System.out.println("department created with no employees");
            Employee employee = new Employee("202407", "test1", department, EmpType.A, "2024-05-29", 300L,null);
            em.persist(employee);
            Employee employee1 = new Employee("202408", "test2", department, EmpType.A, "2024-05-30", 300L,null);
            em.persist(employee1); // 지연쓰기 ==> commit
            System.out.println("employee, employee1 created");
            department.addNewEmployee(employee);
            department.addNewEmployee(employee1);
            List<Employee> empList = department.getEmployees();
            for (Employee emp : empList) {
                System.out.println(emp.getEmpName());
            }
            System.out.println("department add employee 실행");
            System.out.println("employee : " + employee.getDepartment().getDeptId() + ": " + employee.getDepartment().getDeptName());
            System.out.println("employee1 : " + employee1.getDepartment().getDeptId() + ": " + employee1.getDepartment().getDeptName());
            System.out.println("department no of employees : " + department.getEmployees().size());

            System.out.println(department);

            tx.commit();
            System.out.println("commit");
        } catch (Exception e){
            tx.rollback();
        }
    }

}
