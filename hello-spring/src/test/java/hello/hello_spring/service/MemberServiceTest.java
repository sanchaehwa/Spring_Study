package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }
    @AfterEach
    public void afterEach() {
      memberRepository.clearStore();
    } //메모리를 초기화 시켜줌으로써 첫번째 테스트가 회원저장이 성공적으로 되었나 테스트 성공 / 지우고 두번째 테스트 실행함으로써
    //독립적으로 작용하게 함 그래서 만약에 test1 에서 spring 이름의 회원을 저장했다 (저장 회원가입 성공) -> test2 spring member1,2 저장 해도 무방한거임

    @Test
    // 회원 가입 part
    void join() {
        //given (테스트의 초기 상태를 설정 / 객체 생성 , 동작할때 필요한 초기 설정)
        Member member = new Member();
         member.setName("hello");
         //Member 객체 생성
        //setName (member 객체 -> name 필드 -> "hello" 값 주입)
        //when (테스트 동작 수행하는 단계 / 매서드 호출 , 특정 액션 수행)
        Long saveId = memberService.join(member);
        //"hello" 설정한 member 객체 저장 (join)
        //member 객체 ID : saveID 변수에 저장

        //then (기대 결과 검증 / when 단계에서 수행한 동작의 결과가 예상대로 수행되었는지 확인하는)

        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    //중복 회원 (예외)part
    void findMembers() {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //join
        memberService.join(member1);
        //assertThrows(---) 특정 코드 블록이 예외를 발생하는지 확인
        //memberService.join(member2) : 예외 발생하는 코드
        IllegalStateException e =  assertThrows(IllegalStateException.class, () -> memberService.join(member2) );
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        //assertThrows(NullPointerException.class, () -> memberService.join(member2));
        /*void findMembers()
        memberService.join 매소드가 중복 회원을 감지가 잘 이뤄지는지
        보려고 하는거
        */

 /*       try {
            //member2를 추가했을때
            memberService.join(member2);
            //추가가 정상적으로 되면 테스트 실패
            fail();
        }catch (IllegalAccessError e) {
            //예외 잡으면 이 테스트 코드는 성공

        }
    }
*/
    }
    @Test
    void findOne() {
    }
}