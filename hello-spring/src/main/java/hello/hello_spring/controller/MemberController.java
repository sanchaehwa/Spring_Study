package hello.hello_spring.controller;

/* 의존관계란 하나의 객체가 다른 객체의 기능이나 데이터를 필요로 하여 그것에 의존하는 것을
의존관계라고 함.
맴버 컨트롤러 (사용자로부터 요청을 처리하는 역할)
맴버 서비스 (비즈니스 로직 / 회원가입, 회원 데이터 조회)
회원가입 작업을 수행해야 함 *회원가입 작업을 수행하기 위해 맴버 서비스가 필요로 하니
맴버 컨트롤러는 맴버 서비스에 의존하는 관계이다.
*/
import hello.hello_spring.domain.Member;
import hello.hello_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {
    private final MemberService memberService;

    @Autowired
    // 생성자 (MemberController 호출)
    // 스프링 컨테이너에 올라오는 것만 AutoWired 작동함
    // DI(Dependency Injection): 의존성 주입 Autowired 어노테이션을 이용한 의존성 주입을 제공함.
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping(value = "/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());
        memberService.join(member);
        System.out.println("member = " + member.getName());
        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}