package hello.hello_spring; // 패키지 선언이 파일의 첫 줄에 위치

import org.springframework.boot.SpringApplication; // 필요한 임포트
import org.springframework.boot.autoconfigure.SpringBootApplication; // 필요한 임포트

@SpringBootApplication // 클래스 선언 위에 올바른 애노테이션 위치
public class HelloSpringApplication { // 클래스 선언이 올바르게 되어 있는지 확인

    public static void main(String[] args) { // 메인 메서드가 올바르게 작성되었는지 확인
        SpringApplication.run(HelloSpringApplication.class, args);
    }
}
//HelloSpringApplication Class : Spring Boot Application의 진입점으로 Main 매서드를 통해 Spring Boot 실행
// 내장 Tomcat Web server 실행 -> 애플리케이션 실행시 Tomcat 웹 서버를 띄우면서 Web server 를 Build 하는 역할을 함