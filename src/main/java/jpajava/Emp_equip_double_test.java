package jpajava;

import entity.Department;
import entity.EmpType;
import entity.Employee;
import entity.Equipment;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Emp_equip_double_test {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpatest");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        //양방향 연관관계 테스트
        tx.begin();
        try {
            Department dept = em.find(Department.class, 3);
            Employee emp = new Employee("202410", "test10", dept, EmpType.B, "2024-05-30", 300L, null);
            System.out.println("=========");
            em.persist(emp);
            System.out.println("=========");
            Equipment eq = new Equipment(0,"0000000001", 1_000_000, emp);
            System.out.println("=========");
            em.persist(eq);
            System.out.println("=========");
            tx.commit();
            System.out.println("commit");
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
            emf.close();
        }

    }
}
