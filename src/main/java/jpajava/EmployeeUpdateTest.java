package jpajava;

import entity.Employee;
import org.hibernate.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class EmployeeUpdateTest {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpatest");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            System.out.println("트랜잭션 시작 !!!");

            Employee findEmp = em.find(Employee.class, "202411");
            System.out.println("DB에서 가져옴");
            findEmp.setSalary(1000L);
            em.persist(findEmp);
            System.out.println("커밋 전");
            tx.commit();
            System.out.println("커밋 후");
        } catch (Exception e){
            System.out.println(e.getMessage());
            tx.rollback();
        }
    }
}
