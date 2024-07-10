package hello.hello_spring;

import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;
import hello.hello_spring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {
    //spring이 관리하는 spring boot가 데이터 소스를 만들어주는 형태
    private DataSource dataSource;

//memberservice 랑 memberRepositroy 를 빈에 등록을 하고
    //member repository를 service에 넣어줘야하는데 (service랑 repository는 의존관계)
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }
    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
