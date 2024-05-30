package jpajava;

import entity.Department;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class DepartmentTest {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpatest");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        try{
            System.out.println("트랜잭션 시작");
            Department department = new Department(0,"IT",null);
            System.out.println("비영속 상태");
            // AI 키가 있는 테이블은 persist 와 동시에 SQL이 실행된다.
            em.persist(department);
            System.out.println("영속 상태");
            em.find(Department.class, department.getDeptId());
            System.out.println("1차캐시 ? 데이터베이스 ? " + department.getDeptId());
            System.out.println("커밋 전");
            // AI 키가 없는 테이블은 commit시에 SQL이 실행된다.
            tx.commit();
            System.out.println("커밋 후");
        } catch(Exception e){
            tx.rollback();
        }
    }
}
