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
//            Member findMember = em.find(Member.class, 1L); // 단건 조회시 사용
//            findMember.setName("kitae");

            List<Member> result =
                    em.createQuery("select m from Member as m", Member.class).getResultList();
            // ㅇㅣ게 무슨 메리트가 있을까?
            // 페이징을 할 때 페이지네이션

            for(Member member : result){
                System.out.println("Member.name = " + member.getName());
            }

            // JAVA객체에서 값만 바꿨는데 어떻게 DB에 접근해서 값을 바꿨지?
            // JPA를 통해서 Entity를 가져오면 관리를 함.
            // 트랜잭션 시점에 확인해서 쿼리 수행
            tx.commit();
        }catch(Exception e){
            tx.rollback();
        }finally{ // 사용 후에는 항상 닫아주기
            em.close();
            emf.close();
        }
    }
}
