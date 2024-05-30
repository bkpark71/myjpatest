package jpajava;

import entity.Department;
import entity.EmpType;
import entity.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class emp_JPQL_Test {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpatest");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        try{
            System.out.println("트랜잭션 시작");
            List<Employee> empList = em.createQuery("select e from Employee e", Employee.class).getResultList();
            System.out.println("before ==> 사원 수 : " + empList.size());
            Department dept = em.find(Department.class, 6);
            //Employee emp1 = new Employee("20241", "test11", dept, EmpType.A, "2024-05-30", 300L);
            Employee emp1 = em.find(Employee.class, "202401");
            emp1.setDepartment(dept);
            System.out.println("======");
            em.persist(emp1);
            System.out.println("======");
            Employee emp2 = em.find(Employee.class, "202408");
            System.out.println("======");
            em.remove(emp2);
            System.out.println("======");

            System.out.println("jpql 실행전");
            empList = em.createQuery("select e from Employee e", Employee.class).getResultList();
            System.out.println("jpql 실행후");
            System.out.println("after ==> 사원 수 : " + empList.size());
            for (Employee emp : empList) {
                System.out.println(emp.getEmpId() + ":" + emp.getEmpName());
                System.out.println(emp.getDepartment().getDeptName());
            }
            tx.commit();
            System.out.println("commit");
        } catch (Exception e){
            tx.rollback();
        }
    }

}
