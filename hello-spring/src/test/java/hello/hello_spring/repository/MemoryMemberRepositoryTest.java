/*Repository : 데이터 베이스에 접근하고 도메인 객체를 DB에 저장하고 관리 하는역할
 * 여기서 도메인이란, 회원정보 (데이터)가 해당됨
 * 태스트를 위해 Junit이라는 프레임워크로 테스트를 실행하는 목적을 둔 테스트 코드
 */
package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MemoryMemberRepositoryTest {


    MemoryMemberRepository repository = new MemoryMemberRepository();
    //MemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach(){

        repository.clearStore();
    }
    //Test 매서드가 실행되고 나서 호출되는 매서드 (AfterEach) clearStore (저장소 초기화) 다음 테스트 독립적으로 실행

    @Test
    public void save() {
        //given
        Member member = new Member();
        member.setName("spring");
        //when
        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        // System.out.println("result = " + (result == member));
        assertThat(result).isEqualTo(member); // Optional: 검증 추가
    }
   @Test
    public void findByName(){
        //given
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);
        //when
       Member result = repository.findByName("spring1").get();
      //then
       assertThat(result).isEqualTo(member1);
       //spring1, spring2 이름을 조회했을때 회원 (spring 1, spring2) 올바르게 반환
   }
   @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        //when
        List<Member> result = repository.findAll();

        //then
        assertThat(result.size()).isEqualTo(2);
    }
/*3으로 설정되면 값은 2개 (Spring 1, Spring2) 3을 기대하고 있으니 실제값과 달라 에러가 생기는거임
 */

}