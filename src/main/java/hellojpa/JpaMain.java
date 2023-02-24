package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
       EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

       EntityManager em = emf.createEntityManager();

        // JPA에서는 모든 작업을 트랜잭션 안에서 해줘야 한다.
        EntityTransaction tx = em.getTransaction();
        tx.begin(); // DB transaction start
        try{
            Member m1 = new Member(150L, "A");
            Member m2 = new Member(160L, "B");

            em.persist(m1);
            em.persist(m2);
            // 여기까지 Insert SQL을 보내지 않음
            // 쓰기 지연 SQL 저장소에 보관하다가
            // 트랜잭션이 커밋되는 순간
            // flush 되면서 insert SQL문장들 나감

            tx.commit();
        }catch(Exception e){
            tx.rollback();
        }finally{ // 사용 후에는 항상 닫아주기
            em.close();
            emf.close();
        }
    }
}
