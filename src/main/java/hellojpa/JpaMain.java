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
            Movie movie = new Movie();
            movie.setDirector("kitae");
            movie.setActor("yj");
            movie.setName("Love");
            movie.setPrice(1000000);

            em.persist(movie);

            em.flush();
            em.clear();

            Movie findMovie = em.find(Movie.class, movie.getId());
            System.out.println("findMovie = " + findMovie);

            tx.commit();
        }catch(Exception e){
            tx.rollback();
        }finally{ // 사용 후에는 항상 닫아주기
            em.close();
            emf.close();
        }
    }
}
