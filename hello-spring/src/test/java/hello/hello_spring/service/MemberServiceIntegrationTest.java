package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
@SpringBootTest
@Transactional
//DB의 상태를 변경시 키는 작업의 단위 (트랜잭션이라함) 트랜잭션은 연산을 모두 처리하지 못한 경우 트랜잭션이 시작되기 전의 상태 (롤백)
    //따라서 데이터베이스에 기존데이터가 존재하면 새로운데이터 추가하거나 수정 삭제 작업을 할때 모두 처리하지 못한 경우 기존데이터가 존재하는 상태로 롤백
    //테스트 실행하고 테스트 끝나면 롤백
class MemberServiceIntegrationTest {

    //MemberService memberService;
    //MemoryMemberRepository memberRepository;

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;
    //필드 기반으로 Autowired 데이터 불러오는
   /* @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    } (직접 주입해주는 방식이 아님) */

   /* @AfterEach : 이게 필요없는게 Transactional 때문임.
    public void afterEach() {
      memberRepository.clearStore();
    } 메모리를 초기화 시켜줌으로써 첫번째 테스트가 회원저장이 성공적으로 되었나 테스트 성공 / 지우고 두번째 테스트 실행함으로써
    독립적으로 작용하게 함 그래서 만약에 test1 에서 spring 이름의 회원을 저장했다 (저장 회원가입 성공) -> test2 spring member1,2 저장 해도 무방한거임
*/
    @Test
    // 회원 가입 part
    void  join() {
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

}