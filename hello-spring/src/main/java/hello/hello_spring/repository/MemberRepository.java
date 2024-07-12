/*MemberRepository : 회원(Member)객체를 저장하고 검색하는 방법을 제공함.
* 인터페이스로 작동 / 인터페이스는 구체적인 동작 방식을 알려주는게 아니라 이를 구현하는 클래스가 구체적인 로직을 제공함
* 그렇기 때문에 MemberRepository 는 회원 객체를 저장 , 조회, 목록을 반환하는 기능을 제공함. 이 기능으로 구체적인 way 는 클래스가 해야겠지
* */

package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;

import java.util.List;
import java.util.Optional;


public interface MemberRepository {

    Member save(Member member); //반환 타입 : member / member 객체 저장
    Optional<Member> findById(Long id);// 주어진 회원 ID로 회원 검색
    Optional<Member> findByName(String name); //주어진 이름으로 회원 검색
    List<Member> findAll();
    /*Optional 이 회원 ID로 회원 검색을 해봤을때 또는 이름으로 검색해봤을때 이름이 없다면 null 반환할 수 있지만
    * 여기선 Optional.empty() 로 반환함 이에 Null을 다루지 않도록 함 */
    //저장된 모든 회원 목록을 반환 변환 타입은 List<Member>
}
