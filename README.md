# Spring Boot 프로젝트에 React, Mobx-state-tree,  rest api테스트, 각종 리엑트 컴포넌트등의 개인 데모 용도

spring 2.0을 사용하고 react.js를 프론트단으로 하고 spring boot를 rest API서버로 사용하고 있습니다


개발환경

* IntelliJ
* Spring Boot 2.0
* Gradle
* Lombok


## 목표

- Spring 2.0를 사용하여 Rest API 서버 구현 (ex.고객 리스트 조회)
- JPA를 사용하여 디비 쿼리 (ex.고객 리스트 조회)
- React.js를 사용하여 화면 구성
- typescript 문법 적용
- 글로벌 상태관리 mobx-state-tree 이용 (toast, 팝업등 호출)
- mobx와 mobx-state-tree 이용해서 프론트 -> 백엔드간 API 통신 구현 (ex. 고객 리스트 조회)


### react 코드 번들링 방법
1.intellij IDE 내부에서 springboot-react-mobx-example/package.json 마우스 우클릭(mac 기준) Show npm Scripts선택
2. 'install/update' 선택하여  package에 선언된 node 모듈 download 진행
3. 'build-debug-js' 선택하여 번들링


### intellij 기동
	```
	intellij IDE 내부에서
	# src/main/java/com/zzanggar/Application.java
	main() 함수 왼쪽의 녹색 화살표 클릭
	
	OR
	
	intellij IDE 
	Tomcat local 서버로 구동
	```
	
	
## 참고 서적 및 소스 출처

