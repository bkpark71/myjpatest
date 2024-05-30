package jpajava;

import entity.Department;
import entity.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class dept_lazy_fetch_Test {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpatest");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        //양방향 연관관계 테스트
        tx.begin();
        try{
            System.out.println("트랜잭션 시작");
            List<Department> deptList = em.createQuery("select d from Department d", Department.class).getResultList();

            System.out.println("DB에서 가져옴");
            System.out.println(deptList.size());
            for(Department dept : deptList){
                System.out.println(dept.getDeptId() + ":" + dept.getDeptName());
                //System.out.println(dept.getEmployees().size() + "명이 소속되어 있습니다.");
                for(Employee emp : dept.getEmployees()){
                    System.out.println(emp.getEmpId());
                }
                //System.out.println(emp.getDepartment().getDeptName());
            }
            tx.commit();
            System.out.println("commit");
        } catch (Exception e){
            tx.rollback();
        } finally {
            em.close();
            emf.close();
        }
    }
}
