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

            //저장


            Team team = new Team();
            team.setName("NameA");

            // 역방향(주인이 아닌 방향)만 연관관계 설정
//            team.getMembers().add(member);
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
            member.setTeam(team); // 연관관계의 주인에게 setting하는게 중요 // ****
//            member.setTeam(team);
            em.persist(member);


            team.getMembers().add(member); // **** 위 라인과 한 셋(양방향 연관관계시 주의해야 할 점)

//            em.flush();
//            em.clear();

            Team findTeam = em.find(Team.class, team.getId()); // 1차 캐시에 존재
            List<Member> members = findTeam.getMembers();
            
            for(Member m : members){
                System.out.println("m.getUsername() = " + m.getUsername());
            }

            tx.commit();
        }catch(Exception e){
            tx.rollback();
        }finally{ // 사용 후에는 항상 닫아주기
            em.close();
            emf.close();
        }
    }
}
