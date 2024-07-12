package hello.hello_spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeTraceAop {
    @Around("execution(* hello.hello_spring..*(..))")
    //하위폴더에 있는 클랴스 모두 적용하는 과정
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        //시간로직
        long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString());
        try {
           // Object result = joinPoint.proceed();
            return joinPoint.proceed();
        }finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish -start;
            System.out.println("END: " + joinPoint.toString() + " " + timeMs + " ms");
        }
     //   Object result = joinPoint.proceed();
    }
}
/*START: execution(String hello.hello_spring.controller.MemberController.list(Model))
START: execution(List hello.hello_spring.service.MemberService.findMembers())
START: execution(List org.springframework.data.repository.ListCrudRepository.findAll())
---->Repository 접근
Hibernate: select m1_0.id,m1_0.name from member m1_0
-------> Query 확인
END: execution(List org.springframework.data.repository.ListCrudRepository.findAll()) 5 ms
END: execution(List hello.hello_spring.service.MemberService.findMembers()) 5 ms
END: execution(String hello.hello_spring.controller.MemberController.list(Model)) 10 ms
--->각각의 호출시간 출력
*/
/*
    @Around("execution(* hello.hello_spring..*(..))")
-----> 이 부분을 통해 원하는 관심영역의 호출시간만 지정해서 호출이 가능함.
 */
/*
AOP 적용후 의존관계
hello Controller => 프록시: MemberService ------(joinPoint.proceed())> 실제 memberService
hellocontroller 가 호출되면 (스프링이 올라올때) 컨테이너에서 빈을 등록시키면서 기능들이 작동하는데
프록시 역할을 하는 가짜 memberservice를 호출해주고 나서 (조인 프로시드를 통해 내부적으로 이동해서) 실제 memberserviced을 호출

 */