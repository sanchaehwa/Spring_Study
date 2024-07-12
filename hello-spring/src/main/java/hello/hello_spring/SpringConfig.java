package hello.hello_spring;

import hello.hello_spring.aop.TimeTraceAop;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class  SpringConfig {
    //  private final DataSource dataSource;
    private final MemberRepository memberRepository;

    //private final EntityManager em;
    // public SpringConfig(DataSource dataSource, EntityManager em) {
    //   this.dataSource = dataSource;
    // this.em = em;
    //}
    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }
 //   @Bean
   // public TimeTraceAop timeTraceApp() {
     //   return new TimeTraceAop();
    //} //AOP가 등록되어 쓰임
}
   // @BeanF
   // public MemberRepository memberRepository() {
        //return new MemoryMemberRepository();
        //return new JdbcMemberRepository(dataSource);
        //return new JdbcTemplateMemberRepository(dataSource);
     //   return new JpaMemberRepository( em );
    //}
   // }

