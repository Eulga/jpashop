package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    /*
    생성자 제작후 PersistenceContext -> Autowired = 스프링 데이터 JPA가 지원해주기 때문에 가능
    Autowired를 떼고 final을 달고 @RequiredArgsConstructor 적용
    */
    private final EntityManager em;

    /*
    직접주입 하는 방법 = 뉴비는 쓸일이없다 위에 애가 다 해주니까~
    @PersistenceUnit
    private EntityManagerFactory emf;
    */

    public void save(Member member) {
        em.persist(member);
    }

    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }

    public List<Member> findByname(String name){
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name) //:name 에 바인딩
                .getResultList();
    }
}
