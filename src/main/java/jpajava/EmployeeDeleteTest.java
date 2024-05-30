package jpajava;

import entity.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class EmployeeDeleteTest {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpatest");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{
            System.out.println("트랜잭션 시작 ");
            Employee findEmp = em.find(Employee.class, "202413");
            em.remove(findEmp);
            System.out.println("커밋 전");
            tx.commit();
            System.out.println("커밋 후");
        } catch(Exception e){
            System.out.println(e.getMessage());
            tx.rollback();
        }
    }
}
