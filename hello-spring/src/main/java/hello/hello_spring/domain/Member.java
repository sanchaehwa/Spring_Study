/*비즈니스 요구사항 정리
* 데이터 : 회원 ID(식별자 역할(시스템이 저장하느 ID)) , 이름
* 기능 : 회원 등록 , 조회
* 아직 데이터 저장소가 선정되지 않음 (우선 인터페이스로 구현 클래스를 변경할 수 있도록 설계)
*  어떤 동작(매소드)를 제공하는지는 인터페이스 / 어떻게 수행해 이건 구현클래스
* 이렇게 함으로써 클라이언트코드가 MemberRepository 인터페이스를 사용하여 회원 저장소를 상호작용할 수 있으며,
* 나중에 저장 방식이 변경(아직 정해져있지 않는 경우를 고려한 케이스)되더라도 클라이언트 코드를 수정하지 않고 구현 클래스를 교체할 수 있
* */

package hello.hello_spring.domain;

public class Member {

    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }


}
