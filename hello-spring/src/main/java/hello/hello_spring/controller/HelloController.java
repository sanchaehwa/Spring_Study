//HelloController.java 코드는 SpringMVC를 사용하여 여러 종류의 HTTP GET 요청을 처리하는 컨틀롤러 클래스
//대략적으로 보면, 각 매소드는 특정 경로로 들어오는 요청을 처리하고, 해당 요청에 대해 다양한 방식으로 응답함.
package hello.hello_spring.controller;

import hello.hello_spring.HelloSpringApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
//@annotation 은 Java에서 Metadata를 제공하는 방식, 코드에 대한 추가 정보를 제공하며 컴파일러나 런타임에서 해당 정보를 참조할 수 있도록함.
//@Controller : SpringMVC 에서 해당 클래스를 SpringMVC controller로 인식하게 함.
public class HelloController {
//web application에서 / hello 라고 들어오면 아래 매소드를 호출함
    @GetMapping("hello")
    //@GetMapping("hello") 어노테이션은 메소드가 /hello URL로 들어오는 GET 요청
    public String hello(Model model) {
        //mvc: Model view controller
        model.addAttribute("data", "hello!!");
        return "hello";
    }
    //model data를 전달하여, 템플릿 엔진이 HTML을 생성함. *Page Source 하면 Html 속성이 있음을 확인
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value = "name", required = true) String name, Model model) {
        model.addAttribute("name", name);

        return "hello-template";
    }
    //@ResponseBody : 반환값을 뷰가 아닌 HTTP 응답 본문으로 직접 전송 / 단순 텍스트 응답을 제공
    //view 이런거 없이 그냥 바로 그대로 HTTP Response name 값을 넣어서 반환
    //http://localhost:8080/hello-string?name=Max 하면 오로지 hello Max만 보여지는거
    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;
    }
    //객체를 반환하여 JSON 형식으로 직렬화된 데이터를 HTTP 응답 본문으로 전송
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApI(@RequestParam("name")String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }
    static class Hello {
        private String name;
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }
}
