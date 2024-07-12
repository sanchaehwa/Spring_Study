package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;


public class JpaMemberRepository implements MemberRepository {

    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    } //jpa는 EntityManger라는 걸로 모든게 동작함.

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
            List<Member> results = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();

        return results.stream().findAny();

    }

    @Override
    public List<Member> findAll() {
        //em : Entity Manager의 인스턴스로 JPA를 사용하여 데이터 베이스 작업을 수행하는 인터페이스
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

        //jpql : jpa 를 사용하여 데이터베이스 쿼리를 작성할 수 있는 객체 지향 쿼리 언어. 테이블 대상으로 하는 sql / jpql은 엔티티 객체를 대상으로 함.
     //   따라서 jpql을 사용하면 데이터 베이스와 객체 간의 매핑을 추상화함으로써 객체 지향적인 방식 조작 가능
    }
