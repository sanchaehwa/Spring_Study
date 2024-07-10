## 
#### 스프링 부트가 제공하는 Welcom page를 만들기 위한 과정
-> static 폴더에 index.html 파일을 생성함. <br>
-> thymeleaf 탬플릿 엔진을 쓰기전의 index.html은 정적파일이다. <br>
> 정적파일이란, Javascript CSS Image 등 웹 서비스에서 사용하기 위해 미리 서버에 저장해놓은 파일이다. 
> 외부 환경에 관계 없이 일정한 결과값을 제공해주는것, 즉 웹서버에 미리 저장된 파일이 그대로 전달되는 웹페이지를 정적파일이라함.(웹상)

따라서, thymeleaf 탬플릿 엔진을 쓰기전의 index.html 에 있는 resource를 그대로 웹서버에 넘겨주는 형식인거임. 
```tsx
<!DOCTYPE HTML>
<html>
<head>
<title>Hello</title>
<meta http-equiv="Content-Type" content="text/html"; charset="UTF-8" />
    </head>
<body>
Hello
<a href="/hello">hello</a>
</body>
</html>


```
