package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;

import java.util.*;


//@Repository
public class MemoryMemberRepository implements MemberRepository  {

    private static Map<Long, Member> store = new HashMap<>();
    //store:HashMap을 사용하여 회원정보를 저장하는 Map *ID : key , Member: 객체
    private static long sequence = 0L;
    //static : static 변수는 클래스 레벨에서 관리되며, 해당 클래스의 모든 인스턴스가 이 변수를 공유함.

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        System.out.println(member);
        return member;

    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }
    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }
    public void clearStore(){
        store.clear();
    }
    //저장된 모든 회원 조회
}
/*Sequence == 회원 ID
private static long  sequence = 0L;
++sequence : 시퀀스 값 증가
세명의 회원을 저장한다고 가정하며
-> 첫번째 회원 저장
: sequence : 1로 증가
첫번째 회원의 ID: 1
store 에 첫번쨰 회원 저장

**/
