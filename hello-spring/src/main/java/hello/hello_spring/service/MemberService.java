/*  회원 서비스는 회원 레포지토리, 도메인을 활용해서 실제 비즈니스 로직을 작성하는 쪽이고
*   회원 서비스
* */

package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
//@Service
public class MemberService {

    private final MemberRepository memberRepository;

    //@Autowired//(MemberRepository 주입)
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /*
     * 회원 가입
     * 비즈니스 로직중 같은 이름이 있는 회원 정보는 없어야 함
     */
    public Long join(Member member) {
         /*Optional<M ember> result = memberRepository.findByName(member.getName());
        Optional : Optional 안에 맴버 객체가 있음
        Optional를 바로 반환하는게 안좋음.
        반환 한다는게, 내부 값을 직접 꺼내서 사용하거나, 빈 값 (null)을 처리하는 로직을 호출하는 코드 쪽에서 작성해야 하니
        매소드 내부에서 처리하는 방식
        * */
        validateDuplicateMember(member); //중복 회원 검사
        memberRepository.save(member); // 회원 저장
        return member.getId(); //return (반환) : ID 값으로 조회 수정 식별 테스트 검증
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /*
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
