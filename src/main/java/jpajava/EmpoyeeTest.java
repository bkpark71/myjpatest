package jpajava;

import entity.EmpType;
import entity.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class EmpoyeeTest {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpatest");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        try {
            System.out.println("트랜잭션 시작 !!!");

//            Employee employee =
//                    new Employee("202411", "tarzan11", "it", EmpType.A, "2024-05-23", 300L);
//
//            Employee employee1 =
//                    new Employee("202412", "tarzan12", "it", EmpType.A, "2024-05-23", 300L);
//            Employee employee2 =
//                    new Employee("202413", "tarzan13", "it", EmpType.A, "2024-05-23", 300L);
//            System.out.println("비영속 상태");
//            System.out.println("1차 캐시에 추가하기 전");
//            em.persist(employee);
//            em.persist(employee1);
//            em.persist(employee2);
//            System.out.println("영속 상태");
//            System.out.println("1차 캐시에 추가한 후");
//            Employee findEmployee2 = em.find(Employee.class, "202405");
//            System.out.println("emp2 , 1차 캐시에서 가져옴");
//            System.out.println("두 개의 emp가 같은가 ? " + (findEmployee1 == findEmployee2));
            System.out.println("커밋 전");
            tx.commit();
            System.out.println("커밋 후");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            tx.rollback();
            System.out.println("롤백 완료");
        }
        em.close();
        emf.close();
    }
}
