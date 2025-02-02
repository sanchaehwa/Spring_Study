### AOP
1. **AOP 란,** <br>
    AOP는 관점을 기준으로 묶어 개발하는 방식을 의미함.<br>
    관점이란 어떤 기능을 구현할 때,<br>
    ```tsx
    ├── 핵심기능 (비즈니스 로직을 구현하는 과정에서 비즈니스 로직이 처리하는 목적 기능)
    ├
    ├── 부가기능 ( 애플케이션의 핵심 비즈니스 로직과 직접적인 관련은 없는 기능 (캐싱 , 로깅과 같이))
        
    ```
   이런식으로 핵심기능 외, 부가기능을 AOP로 처리함으로써, 핵심 비즈니스 로직과 부가 기능을 명확히 분리할수 있음.<br>
    이렇게 함으로써 코드가 더 모듈화되고 유지보수가 쉬워지며 각 기능을 독립적으로 관리할 수 있음. <br>
2. **AOP 적용후 의존관계** <br>
`  hello Controller => 프록시: MemberService ------(joinPoint.proceed())> 실제 memberService`  <br>
  -  Hello Controller 는 MemberService 와 의존성을 가지고 있음. <br>
  - 그리고 스프링 컨테이너가 시작되면, 스프링은 모든 빈을 생성하고 초기화 시킴 <br>
  -  MemberService에 AOP가 적용된다, 이 때 주입되는 것은 실제 MemberService 객체가 아닌 프록시 객체이다.<br>
  -  HelloController가 MemberService의 메서드를 호출하면, 프록시 객체가 호출을 가로채고, 부가 기능(예: 로깅, 트랜잭션 관리)을 적용한 후 (===joinpoint) 실제 MemberService 객체의 메서드를 호출함.
