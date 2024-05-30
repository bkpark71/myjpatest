package jpajava;

import entity.Department;
import entity.EmpType;
import entity.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Dept_Emp_test {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpatest");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        try{
            Department department = new Department();
            department.setDeptName("HR");
            em.persist(department); // insert ==> dept_id
            System.out.println("department created");
            Employee employee = new Employee("202401", "test1", department, EmpType.A, "2024-05-30", 300L);
            em.persist(employee); // 지연쓰기 ==> commit
            System.out.println("employee created");
            System.out.println(employee.getDepartment().getDeptId() + ": " + employee.getDepartment().getDeptName());
            tx.commit();
            System.out.println("commit");
        } catch (Exception e){
            tx.rollback();
        }
    }
}
