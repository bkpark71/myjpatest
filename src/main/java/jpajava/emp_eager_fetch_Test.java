package jpajava;

import entity.Department;
import entity.EmpType;
import entity.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class emp_eager_fetch_Test {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpatest");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        //양방향 연관관계 테스트
        tx.begin();
        try{
            System.out.println("트랜잭션 시작");
            List<Employee> empList = em.createQuery("select e from Employee e", Employee.class).getResultList();
            System.out.println("DB에서 가져옴");
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
