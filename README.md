액티비티 강추! 프로젝트 백엔드 부분입니다.

프론트엔드 소스코드 주소
-> https://github.com/K-srand/Gangwon-do-activity_frontend

# 액티비티 강추!(강원 액티비티 추천) 프로젝트

태그: 팀 프로젝트
사용기술: AWS, IntelliJ, Java, Mysql, Naver Cloud Platform, React, SpringBoot, TourAPI, Visual Studio Code, 날씨API
진행일자: 2024/06/11 → 2024/07/26
프로젝트 참여인원: 6명

# **P**roject GangwonActivity~~!!!

![20240823_174412.png](%E1%84%8B%E1%85%A2%E1%86%A8%E1%84%90%E1%85%B5%E1%84%87%E1%85%B5%E1%84%90%E1%85%B5%20%E1%84%80%E1%85%A1%E1%86%BC%E1%84%8E%E1%85%AE!(%E1%84%80%E1%85%A1%E1%86%BC%E1%84%8B%E1%85%AF%E1%86%AB%20%E1%84%8B%E1%85%A2%E1%86%A8%E1%84%90%E1%85%B5%E1%84%87%E1%85%B5%E1%84%90%E1%85%B5%20%E1%84%8E%E1%85%AE%E1%84%8E%E1%85%A5%E1%86%AB)%20%E1%84%91%E1%85%B3%E1%84%85%E1%85%A9%E1%84%8C%E1%85%A6%E1%86%A8%E1%84%90%E1%85%B3%200d93012ebcea49c5aa55615b9fd9a69d/20240823_174412.png)

![20240823_174721.png](%E1%84%8B%E1%85%A2%E1%86%A8%E1%84%90%E1%85%B5%E1%84%87%E1%85%B5%E1%84%90%E1%85%B5%20%E1%84%80%E1%85%A1%E1%86%BC%E1%84%8E%E1%85%AE!(%E1%84%80%E1%85%A1%E1%86%BC%E1%84%8B%E1%85%AF%E1%86%AB%20%E1%84%8B%E1%85%A2%E1%86%A8%E1%84%90%E1%85%B5%E1%84%87%E1%85%B5%E1%84%90%E1%85%B5%20%E1%84%8E%E1%85%AE%E1%84%8E%E1%85%A5%E1%86%AB)%20%E1%84%91%E1%85%B3%E1%84%85%E1%85%A9%E1%84%8C%E1%85%A6%E1%86%A8%E1%84%90%E1%85%B3%200d93012ebcea49c5aa55615b9fd9a69d/20240823_174721.png)

# 목차

## 📚Project Outline

### 📕목표

실제 사용자들의 리뷰를 기반으로 강원도의 다양한 액티비티와 여행지를 추천하는 여행 플랫폼

### ****📙**프로젝트 기간**

- 2024/06/10 - 2024/07/28(7주, 6인 백엔드 개발자)
    - 이후 배포, 프론트엔드 리팩토링 등을 진행 중에 있음

### 📗시연 영상

- [https://youtu.be/ELQ3XLpOPKI](https://youtu.be/ELQ3XLpOPKI)

### 📘**링크**

- 배포 링크
    - [http://3.36.27.202/](http://3.36.27.202/)
- Git 링크
    - 백엔드
        
        [https://github.com/KEKEKia/Gangwon-do-activity_backend.git](https://github.com/KEKEKia/Gangwon-do-activity_backend.git)
        
    - 프론트엔드
        
        [https://github.com/KEKEKia/Gangwon-do-activity_frontend.git](https://github.com/KEKEKia/Gangwon-do-activity_frontend.git)
        

### 

 📙핵심 기능

- 회원 관리 : JWT 토큰을 이용한 사용자 인증 및 권한 부여 시스템 구현
- 추천 알고리즘 : Tour 4.0 API를 활용하여 평점, 거리, 경로 시간을 기준으로 최적의 서비스를 추천하는 알고리즘 설계 및 구현
- 이미지 : AWS S3를 사용하여 이미지 업로드, 저장, 관리 시스템 구축

## **📚System Architecture/ERD**

### 📕System Architecture

![Gangwon_Architecture.drawio.png](%E1%84%8B%E1%85%A2%E1%86%A8%E1%84%90%E1%85%B5%E1%84%87%E1%85%B5%E1%84%90%E1%85%B5%20%E1%84%80%E1%85%A1%E1%86%BC%E1%84%8E%E1%85%AE!(%E1%84%80%E1%85%A1%E1%86%BC%E1%84%8B%E1%85%AF%E1%86%AB%20%E1%84%8B%E1%85%A2%E1%86%A8%E1%84%90%E1%85%B5%E1%84%87%E1%85%B5%E1%84%90%E1%85%B5%20%E1%84%8E%E1%85%AE%E1%84%8E%E1%85%A5%E1%86%AB)%20%E1%84%91%E1%85%B3%E1%84%85%E1%85%A9%E1%84%8C%E1%85%A6%E1%86%A8%E1%84%90%E1%85%B3%200d93012ebcea49c5aa55615b9fd9a69d/Gangwon_Architecture.drawio.png)

| **Component** | **툴/프레임워크** |
| --- | --- |
| Backend | Spring Boot 3.3.x/**Java 17** |
| Frontend | React.js 18.0.x/ CSS |
| ORM/Data Mapper | JPA / MyBatis 3.0.3 |
| Database | MySQL Server 8.0 |
| SCM | github |
| CloudStorage | AWS S3 |
| 배포 | Jenkins |
| Test | Postman |
| Api문서 관리 | Swagger |
| TeamCommunication | Zoom, Discord |

### 📙ERD 설계

- **ERD 설계서**

![그림1.png](%E1%84%8B%E1%85%A2%E1%86%A8%E1%84%90%E1%85%B5%E1%84%87%E1%85%B5%E1%84%90%E1%85%B5%20%E1%84%80%E1%85%A1%E1%86%BC%E1%84%8E%E1%85%AE!(%E1%84%80%E1%85%A1%E1%86%BC%E1%84%8B%E1%85%AF%E1%86%AB%20%E1%84%8B%E1%85%A2%E1%86%A8%E1%84%90%E1%85%B5%E1%84%87%E1%85%B5%E1%84%90%E1%85%B5%20%E1%84%8E%E1%85%AE%E1%84%8E%E1%85%A5%E1%86%AB)%20%E1%84%91%E1%85%B3%E1%84%85%E1%85%A9%E1%84%8C%E1%85%A6%E1%86%A8%E1%84%90%E1%85%B3%200d93012ebcea49c5aa55615b9fd9a69d/%25EA%25B7%25B8%25EB%25A6%25BC1.png)

- **ERD 테이블 명세서**

![image.png](%E1%84%8B%E1%85%A2%E1%86%A8%E1%84%90%E1%85%B5%E1%84%87%E1%85%B5%E1%84%90%E1%85%B5%20%E1%84%80%E1%85%A1%E1%86%BC%E1%84%8E%E1%85%AE!(%E1%84%80%E1%85%A1%E1%86%BC%E1%84%8B%E1%85%AF%E1%86%AB%20%E1%84%8B%E1%85%A2%E1%86%A8%E1%84%90%E1%85%B5%E1%84%87%E1%85%B5%E1%84%90%E1%85%B5%20%E1%84%8E%E1%85%AE%E1%84%8E%E1%85%A5%E1%86%AB)%20%E1%84%91%E1%85%B3%E1%84%85%E1%85%A9%E1%84%8C%E1%85%A6%E1%86%A8%E1%84%90%E1%85%B3%200d93012ebcea49c5aa55615b9fd9a69d/image.png)

1. Board : 커뮤니티 데이터 테이블
2. Boardimage : 커뮤니티 이미지 테이블
3. Boardlikesuser : 사용자와 커뮤니티 매핑 테이블
4. Comment : 댓글 데이터 테이블
5. Mycourse : 나의 코스 데이터 테이블
6. Mycourseplace : 나의 코스와 장소 매핑 테이블
7. Myfavoritesuserplace : 사용자와 나의 찜 매핑 테이블
8. Place : TourAPI 데이터 테이블
9. Reportedcontent : 신고된 데이터 테이블
10. User : 사용자 데이터 테이블
11. Weather : 날씨 API 데이터 테이블

## 📚 UX/UI 화면설계서/REST API 명세서

### **📕 UX/UI 화면설계서 및 기능 정의서 링크**

- GoogleDocs(화면설계서, 요구사항정의서, 정책정의서, 기능정의서, TC요구서, WBS포함)
    - [https://drive.google.com/drive/folders/1-wUUd3x-Bkqn-aZ3qXwbIMLeOMdLGVnh?usp=sharing](https://drive.google.com/drive/folders/1-wUUd3x-Bkqn-aZ3qXwbIMLeOMdLGVnh?usp=sharing)
- Figma
    - [https://www.figma.com/design/BFAG60ask6W6TwIgEfPtc6/Figma-basics?node-id=1669-162202&node-type=CANVAS&t=4Bgo0DAaEwq9YjDL-0](https://www.figma.com/design/BFAG60ask6W6TwIgEfPtc6/Figma-basics?node-id=1669-162202&node-type=CANVAS&t=4Bgo0DAaEwq9YjDL-0)

### ****📙 **REST API 명세서 (Swagger)**

   (Swagger 사진 첨부 필)

### 📗**백엔드 패키지 구조**

- **백엔드 패키지 구조**

![image.png](%E1%84%8B%E1%85%A2%E1%86%A8%E1%84%90%E1%85%B5%E1%84%87%E1%85%B5%E1%84%90%E1%85%B5%20%E1%84%80%E1%85%A1%E1%86%BC%E1%84%8E%E1%85%AE!(%E1%84%80%E1%85%A1%E1%86%BC%E1%84%8B%E1%85%AF%E1%86%AB%20%E1%84%8B%E1%85%A2%E1%86%A8%E1%84%90%E1%85%B5%E1%84%87%E1%85%B5%E1%84%90%E1%85%B5%20%E1%84%8E%E1%85%AE%E1%84%8E%E1%85%A5%E1%86%AB)%20%E1%84%91%E1%85%B3%E1%84%85%E1%85%A9%E1%84%8C%E1%85%A6%E1%86%A8%E1%84%90%E1%85%B3%200d93012ebcea49c5aa55615b9fd9a69d/image%201.png)

1. Java : Java package
    
    1-1. Common : ResponseEntity의 CodeMessage(ex : 404, 405..etc)를 포함
    
    1-2. Config : Web, Swagger, API설정 관련 class를 포함
    
    1-3. Controller : controller package
    
    1-4. Dto 
    
    - Request : 클라이언트로부터 요청 받는 패키지
    
    - Response : 클라이언트로 요청을 내보내는 패키지(사실상 ExceptionHandler의 역할 포함)
    
    1-5. Entity : model package
    
    1-6. Exception : Exception package
    
    1-7. Filter : Filter package(SpringSecurity 동작을 포함)
    
    1-8. Mapper : MyBatis와 연동한 mapper를 포함
    
    1-9. Provider : Email, JWT 등 라이브러리 활용/설정 class를 포함
    
    1-10. Repository : 데이터베이스 접근을 담당하는 인터페이스가 위치하는 패키지
    
    1-11. Service : service package
    
2. Resources : resource package
    
    2-1. mapper : mapper XML  package
    
    2-2. static.json : Naver Map API 활용을 위해 강원도 지역 영역 표시를 위한 정적 package
    

- **프론트 패키지 구조**

![image.png](%E1%84%8B%E1%85%A2%E1%86%A8%E1%84%90%E1%85%B5%E1%84%87%E1%85%B5%E1%84%90%E1%85%B5%20%E1%84%80%E1%85%A1%E1%86%BC%E1%84%8E%E1%85%AE!(%E1%84%80%E1%85%A1%E1%86%BC%E1%84%8B%E1%85%AF%E1%86%AB%20%E1%84%8B%E1%85%A2%E1%86%A8%E1%84%90%E1%85%B5%E1%84%87%E1%85%B5%E1%84%90%E1%85%B5%20%E1%84%8E%E1%85%AE%E1%84%8E%E1%85%A5%E1%86%AB)%20%E1%84%91%E1%85%B3%E1%84%85%E1%85%A9%E1%84%8C%E1%85%A6%E1%86%A8%E1%84%90%E1%85%B3%200d93012ebcea49c5aa55615b9fd9a69d/image%202.png)

1. node_modules : npm으로 설치된 패키지들이 저장되는 dir
2. Public : 정적 파일들이 위치하는 dir
3. Src : 소스 코드가 포함된 dir
4. Assets : 애플리케이션에서 사용되는 자원 파일들이 위치하는 dir
5. Images : 이미지 파일들이 위치하는 dir
6. Styles : CSS 파일들이 위치하는 dir
7. Components : React 컴포넌트들이 위치하는 dir
8. Common : 공통으로 사용되는 컴포넌트들이 위치하는 dir
9. Specific : 특정 기능이나 페이지에서만 사용되는 컴포넌트들이 위치하는 dir
10. Pages : 페이지 컴포넌트들이 위치하는 dir

## 📚주요 기능

![20240823_171605.png](%E1%84%8B%E1%85%A2%E1%86%A8%E1%84%90%E1%85%B5%E1%84%87%E1%85%B5%E1%84%90%E1%85%B5%20%E1%84%80%E1%85%A1%E1%86%BC%E1%84%8E%E1%85%AE!(%E1%84%80%E1%85%A1%E1%86%BC%E1%84%8B%E1%85%AF%E1%86%AB%20%E1%84%8B%E1%85%A2%E1%86%A8%E1%84%90%E1%85%B5%E1%84%87%E1%85%B5%E1%84%90%E1%85%B5%20%E1%84%8E%E1%85%AE%E1%84%8E%E1%85%A5%E1%86%AB)%20%E1%84%91%E1%85%B3%E1%84%85%E1%85%A9%E1%84%8C%E1%85%A6%E1%86%A8%E1%84%90%E1%85%B3%200d93012ebcea49c5aa55615b9fd9a69d/20240823_171605.png)

  → 주요 기능 개략도

### **📕커뮤니티**

- 사용자가 작성한 글/댓글 DB에 저장하고 관리하는 기능.
- CRUD, Pagination, 좋아요/싫어요 기능 및 신고 기능 구현하여 사용자 간의 상호작용을 지원.
- 신고/제재 기능 : 사용자가 작성한 글 및 댓글에 대해 신고 기능 구현. 중복 신고 방지 및 신고된 콘텐츠의 제재 여부 관리.
- 커뮤니티 관련 데이터를 효과적으로 조회하고 관리할 수 있도록 시스템 설계 및 최적화.

![20240823_174259.png](%E1%84%8B%E1%85%A2%E1%86%A8%E1%84%90%E1%85%B5%E1%84%87%E1%85%B5%E1%84%90%E1%85%B5%20%E1%84%80%E1%85%A1%E1%86%BC%E1%84%8E%E1%85%AE!(%E1%84%80%E1%85%A1%E1%86%BC%E1%84%8B%E1%85%AF%E1%86%AB%20%E1%84%8B%E1%85%A2%E1%86%A8%E1%84%90%E1%85%B5%E1%84%87%E1%85%B5%E1%84%90%E1%85%B5%20%E1%84%8E%E1%85%AE%E1%84%8E%E1%85%A5%E1%86%AB)%20%E1%84%91%E1%85%B3%E1%84%85%E1%85%A9%E1%84%8C%E1%85%A6%E1%86%A8%E1%84%90%E1%85%B3%200d93012ebcea49c5aa55615b9fd9a69d/20240823_174259.png)

![20240823_174215.png](%E1%84%8B%E1%85%A2%E1%86%A8%E1%84%90%E1%85%B5%E1%84%87%E1%85%B5%E1%84%90%E1%85%B5%20%E1%84%80%E1%85%A1%E1%86%BC%E1%84%8E%E1%85%AE!(%E1%84%80%E1%85%A1%E1%86%BC%E1%84%8B%E1%85%AF%E1%86%AB%20%E1%84%8B%E1%85%A2%E1%86%A8%E1%84%90%E1%85%B5%E1%84%87%E1%85%B5%E1%84%90%E1%85%B5%20%E1%84%8E%E1%85%AE%E1%84%8E%E1%85%A5%E1%86%AB)%20%E1%84%91%E1%85%B3%E1%84%85%E1%85%A9%E1%84%8C%E1%85%A6%E1%86%A8%E1%84%90%E1%85%B3%200d93012ebcea49c5aa55615b9fd9a69d/20240823_174215.png)

  ****

### **📙회원가입/로그인**

- Spring Security 기반으로 OAuth2.0 흐름의 인증 구현. (JWT 형식 사용)
- 삭제된 유저의 경우 재가입 불가능, 삭제/제재 유저를 DB에서 삭제하는 것이 아닌 해당 시점을 TimeStamp로 기록하는 방식
- UserRole에 따라 달리 동작하는 마이페이지/관리자 창을 구현

![image.png](%E1%84%8B%E1%85%A2%E1%86%A8%E1%84%90%E1%85%B5%E1%84%87%E1%85%B5%E1%84%90%E1%85%B5%20%E1%84%80%E1%85%A1%E1%86%BC%E1%84%8E%E1%85%AE!(%E1%84%80%E1%85%A1%E1%86%BC%E1%84%8B%E1%85%AF%E1%86%AB%20%E1%84%8B%E1%85%A2%E1%86%A8%E1%84%90%E1%85%B5%E1%84%87%E1%85%B5%E1%84%90%E1%85%B5%20%E1%84%8E%E1%85%AE%E1%84%8E%E1%85%A5%E1%86%AB)%20%E1%84%91%E1%85%B3%E1%84%85%E1%85%A9%E1%84%8C%E1%85%A6%E1%86%A8%E1%84%90%E1%85%B3%200d93012ebcea49c5aa55615b9fd9a69d/image%203.png)

![image.png](%E1%84%8B%E1%85%A2%E1%86%A8%E1%84%90%E1%85%B5%E1%84%87%E1%85%B5%E1%84%90%E1%85%B5%20%E1%84%80%E1%85%A1%E1%86%BC%E1%84%8E%E1%85%AE!(%E1%84%80%E1%85%A1%E1%86%BC%E1%84%8B%E1%85%AF%E1%86%AB%20%E1%84%8B%E1%85%A2%E1%86%A8%E1%84%90%E1%85%B5%E1%84%87%E1%85%B5%E1%84%90%E1%85%B5%20%E1%84%8E%E1%85%AE%E1%84%8E%E1%85%A5%E1%86%AB)%20%E1%84%91%E1%85%B3%E1%84%85%E1%85%A9%E1%84%8C%E1%85%A6%E1%86%A8%E1%84%90%E1%85%B3%200d93012ebcea49c5aa55615b9fd9a69d/image%204.png)

→ FE 분기처리를 통해 아이디/이메일 인증/닉네임 등 조건에 부합 할 시에만 회원 가입 기능 구현

### **📗관리자 페이지**

![20240823_174846.png](%E1%84%8B%E1%85%A2%E1%86%A8%E1%84%90%E1%85%B5%E1%84%87%E1%85%B5%E1%84%90%E1%85%B5%20%E1%84%80%E1%85%A1%E1%86%BC%E1%84%8E%E1%85%AE!(%E1%84%80%E1%85%A1%E1%86%BC%E1%84%8B%E1%85%AF%E1%86%AB%20%E1%84%8B%E1%85%A2%E1%86%A8%E1%84%90%E1%85%B5%E1%84%87%E1%85%B5%E1%84%90%E1%85%B5%20%E1%84%8E%E1%85%AE%E1%84%8E%E1%85%A5%E1%86%AB)%20%E1%84%91%E1%85%B3%E1%84%85%E1%85%A9%E1%84%8C%E1%85%A6%E1%86%A8%E1%84%90%E1%85%B3%200d93012ebcea49c5aa55615b9fd9a69d/20240823_174846.png)

![image.png](%E1%84%8B%E1%85%A2%E1%86%A8%E1%84%90%E1%85%B5%E1%84%87%E1%85%B5%E1%84%90%E1%85%B5%20%E1%84%80%E1%85%A1%E1%86%BC%E1%84%8E%E1%85%AE!(%E1%84%80%E1%85%A1%E1%86%BC%E1%84%8B%E1%85%AF%E1%86%AB%20%E1%84%8B%E1%85%A2%E1%86%A8%E1%84%90%E1%85%B5%E1%84%87%E1%85%B5%E1%84%90%E1%85%B5%20%E1%84%8E%E1%85%AE%E1%84%8E%E1%85%A5%E1%86%AB)%20%E1%84%91%E1%85%B3%E1%84%85%E1%85%A9%E1%84%8C%E1%85%A6%E1%86%A8%E1%84%90%E1%85%B3%200d93012ebcea49c5aa55615b9fd9a69d/image%205.png)

![20240823_174908.png](%E1%84%8B%E1%85%A2%E1%86%A8%E1%84%90%E1%85%B5%E1%84%87%E1%85%B5%E1%84%90%E1%85%B5%20%E1%84%80%E1%85%A1%E1%86%BC%E1%84%8E%E1%85%AE!(%E1%84%80%E1%85%A1%E1%86%BC%E1%84%8B%E1%85%AF%E1%86%AB%20%E1%84%8B%E1%85%A2%E1%86%A8%E1%84%90%E1%85%B5%E1%84%87%E1%85%B5%E1%84%90%E1%85%B5%20%E1%84%8E%E1%85%AE%E1%84%8E%E1%85%A5%E1%86%AB)%20%E1%84%91%E1%85%B3%E1%84%85%E1%85%A9%E1%84%8C%E1%85%A6%E1%86%A8%E1%84%90%E1%85%B3%200d93012ebcea49c5aa55615b9fd9a69d/20240823_174908.png)

- 회원 제재 및 신고 받은 컨텐츠 검수 기능
- 특정 신고가 자주 누적되면 우선순위가 높아져 목록 상단에 노출되게 구현
- UserRole이 ‘ADMIN’인 경우에만 접근 가능하게 WebConfig에서 설정.
- 관리자가 올리는 글은 무조건 ‘공지사항’으로 커뮤니티글 목록 중 최상단에 노출.

### 📘**마이페이지**

![image.png](%E1%84%8B%E1%85%A2%E1%86%A8%E1%84%90%E1%85%B5%E1%84%87%E1%85%B5%E1%84%90%E1%85%B5%20%E1%84%80%E1%85%A1%E1%86%BC%E1%84%8E%E1%85%AE!(%E1%84%80%E1%85%A1%E1%86%BC%E1%84%8B%E1%85%AF%E1%86%AB%20%E1%84%8B%E1%85%A2%E1%86%A8%E1%84%90%E1%85%B5%E1%84%87%E1%85%B5%E1%84%90%E1%85%B5%20%E1%84%8E%E1%85%AE%E1%84%8E%E1%85%A5%E1%86%AB)%20%E1%84%91%E1%85%B3%E1%84%85%E1%85%A9%E1%84%8C%E1%85%A6%E1%86%A8%E1%84%90%E1%85%B3%200d93012ebcea49c5aa55615b9fd9a69d/4ca6dde7-1ad8-4001-9a86-c9b25a8ea8c0.png)

![image.png](%E1%84%8B%E1%85%A2%E1%86%A8%E1%84%90%E1%85%B5%E1%84%87%E1%85%B5%E1%84%90%E1%85%B5%20%E1%84%80%E1%85%A1%E1%86%BC%E1%84%8E%E1%85%AE!(%E1%84%80%E1%85%A1%E1%86%BC%E1%84%8B%E1%85%AF%E1%86%AB%20%E1%84%8B%E1%85%A2%E1%86%A8%E1%84%90%E1%85%B5%E1%84%87%E1%85%B5%E1%84%90%E1%85%B5%20%E1%84%8E%E1%85%AE%E1%84%8E%E1%85%A5%E1%86%AB)%20%E1%84%91%E1%85%B3%E1%84%85%E1%85%A9%E1%84%8C%E1%85%A6%E1%86%A8%E1%84%90%E1%85%B3%200d93012ebcea49c5aa55615b9fd9a69d/image%206.png)

- 사용자 정보 수정/탈퇴 기능 구현
    - 정보 수정 시 E-mail 인증 서비스 구현
    - 회원 탈퇴시 TimeStamp형식으로 회원탈퇴시점 기록(삭제 x)
- 내가 찜한 곳 및 마이 코스 목록 조회/삭제

### 📔**코스 만들기**

- 유저가 담아둔 place들의 최단 경로를 구해주는 기능
    - NaverMap API를 활용
- 유저가 찜한 place들은 카테고리 별로 ‘찜 리스트’에 담김
    - 4개 이상의 place들을 조합하면 ‘경로 총 정리’ 창이 완성

![image.png](%E1%84%8B%E1%85%A2%E1%86%A8%E1%84%90%E1%85%B5%E1%84%87%E1%85%B5%E1%84%90%E1%85%B5%20%E1%84%80%E1%85%A1%E1%86%BC%E1%84%8E%E1%85%AE!(%E1%84%80%E1%85%A1%E1%86%BC%E1%84%8B%E1%85%AF%E1%86%AB%20%E1%84%8B%E1%85%A2%E1%86%A8%E1%84%90%E1%85%B5%E1%84%87%E1%85%B5%E1%84%90%E1%85%B5%20%E1%84%8E%E1%85%AE%E1%84%8E%E1%85%A5%E1%86%AB)%20%E1%84%91%E1%85%B3%E1%84%85%E1%85%A9%E1%84%8C%E1%85%A6%E1%86%A8%E1%84%90%E1%85%B3%200d93012ebcea49c5aa55615b9fd9a69d/image%207.png)

→ 찜 하기 기능

![image.png](%E1%84%8B%E1%85%A2%E1%86%A8%E1%84%90%E1%85%B5%E1%84%87%E1%85%B5%E1%84%90%E1%85%B5%20%E1%84%80%E1%85%A1%E1%86%BC%E1%84%8E%E1%85%AE!(%E1%84%80%E1%85%A1%E1%86%BC%E1%84%8B%E1%85%AF%E1%86%AB%20%E1%84%8B%E1%85%A2%E1%86%A8%E1%84%90%E1%85%B5%E1%84%87%E1%85%B5%E1%84%90%E1%85%B5%20%E1%84%8E%E1%85%AE%E1%84%8E%E1%85%A5%E1%86%AB)%20%E1%84%91%E1%85%B3%E1%84%85%E1%85%A9%E1%84%8C%E1%85%A6%E1%86%A8%E1%84%90%E1%85%B3%200d93012ebcea49c5aa55615b9fd9a69d/image%208.png)

→ 경로 총 정리 기능

- **구현 코드**
    
    ```java
    package com.multicampus.gangwonActivity.service;
    
    import com.multicampus.gangwonActivity.dto.request.api.GetNaverMapDto;
    import lombok.RequiredArgsConstructor;
    import org.springframework.beans.factory.annotation.Value;
    import org.springframework.http.HttpEntity;
    import org.springframework.http.HttpHeaders;
    import org.springframework.http.HttpMethod;
    import org.springframework.http.ResponseEntity;
    import org.springframework.stereotype.Service;
    import org.springframework.web.client.RestTemplate;
    
    @Service
    @RequiredArgsConstructor
    public class NaverMapService {
    
        private final String apiUrl = "https://oapi.map.naver.com/openapi/v3/maps.js";
        private final String directionsApiUrl = "https://naveropenapi.apigw.ntruss.com/map-direction/v1/driving";
    
        @Value("${naver.client.id}")
        private String clientId;
    
        @Value("${naver.client.secret}")
        private String clientSecret;
    
        //네이버 맵 API 호출
        public String getNaverMapScript() {
            String fullUrl = apiUrl + "?ncpClientId=" + clientId + "&submodules=geocoder";
            return fullUrl;
        }
    
        //소요시간 호출
        public String getDrivingDuration(GetNaverMapDto getNaverMapDto) {
            RestTemplate restTemplate = new RestTemplate();
            String url = directionsApiUrl + "?start=" + getNaverMapDto.getStartLng() + "," + getNaverMapDto.getStartLat() + "&goal="
                    + getNaverMapDto.getEndLng() + "," + getNaverMapDto.getEndLat();
    
            HttpHeaders headers = new HttpHeaders();
            headers.set("X-NCP-APIGW-API-KEY-ID", clientId);
            headers.set("X-NCP-APIGW-API-KEY", clientSecret);
    
            HttpEntity<String> entity = new HttpEntity<>(headers);
    
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
            System.out.println(response.getBody());
            return response.getBody();  // You'll need to parse the response to get the time
        }
    }
    
    ```
    

## 📚이슈와 개선 사항

### **📕CORS 정책 관련 오류**

- API 요청 시 발생한 "Access-Control-Allow-Origin" 오류로 인해 CORS(Cross-Origin Resource Sharing) 정책에 위배되어 요청이 차단되었음.

![image.png](%E1%84%8B%E1%85%A2%E1%86%A8%E1%84%90%E1%85%B5%E1%84%87%E1%85%B5%E1%84%90%E1%85%B5%20%E1%84%80%E1%85%A1%E1%86%BC%E1%84%8E%E1%85%AE!(%E1%84%80%E1%85%A1%E1%86%BC%E1%84%8B%E1%85%AF%E1%86%AB%20%E1%84%8B%E1%85%A2%E1%86%A8%E1%84%90%E1%85%B5%E1%84%87%E1%85%B5%E1%84%90%E1%85%B5%20%E1%84%8E%E1%85%AE%E1%84%8E%E1%85%A5%E1%86%AB)%20%E1%84%91%E1%85%B3%E1%84%85%E1%85%A9%E1%84%8C%E1%85%A6%E1%86%A8%E1%84%90%E1%85%B3%200d93012ebcea49c5aa55615b9fd9a69d/image%209.png)

 →  백엔드포트(4040)와 프론트엔드포트(3030)간 명시되지 않은 데이터 연결로 인해 보안 상 발생하는 오류

![image.png](%E1%84%8B%E1%85%A2%E1%86%A8%E1%84%90%E1%85%B5%E1%84%87%E1%85%B5%E1%84%90%E1%85%B5%20%E1%84%80%E1%85%A1%E1%86%BC%E1%84%8E%E1%85%AE!(%E1%84%80%E1%85%A1%E1%86%BC%E1%84%8B%E1%85%AF%E1%86%AB%20%E1%84%8B%E1%85%A2%E1%86%A8%E1%84%90%E1%85%B5%E1%84%87%E1%85%B5%E1%84%90%E1%85%B5%20%E1%84%8E%E1%85%AE%E1%84%8E%E1%85%A5%E1%86%AB)%20%E1%84%91%E1%85%B3%E1%84%85%E1%85%A9%E1%84%8C%E1%85%A6%E1%86%A8%E1%84%90%E1%85%B3%200d93012ebcea49c5aa55615b9fd9a69d/image%2010.png)

이를 해결하기 위해 백엔드 패키지의 WebSecurityConfig 클래스 내에 corsConfig 메소드 구현

![image.png](%E1%84%8B%E1%85%A2%E1%86%A8%E1%84%90%E1%85%B5%E1%84%87%E1%85%B5%E1%84%90%E1%85%B5%20%E1%84%80%E1%85%A1%E1%86%BC%E1%84%8E%E1%85%AE!(%E1%84%80%E1%85%A1%E1%86%BC%E1%84%8B%E1%85%AF%E1%86%AB%20%E1%84%8B%E1%85%A2%E1%86%A8%E1%84%90%E1%85%B5%E1%84%87%E1%85%B5%E1%84%90%E1%85%B5%20%E1%84%8E%E1%85%AE%E1%84%8E%E1%85%A5%E1%86%AB)%20%E1%84%91%E1%85%B3%E1%84%85%E1%85%A9%E1%84%8C%E1%85%A6%E1%86%A8%E1%84%90%E1%85%B3%200d93012ebcea49c5aa55615b9fd9a69d/image%2011.png)

이후 프론트/백엔드 간 데이터를 주고받는 Controller에 @CrossOrigin을 명시하여 문제 해결

![image.png](%E1%84%8B%E1%85%A2%E1%86%A8%E1%84%90%E1%85%B5%E1%84%87%E1%85%B5%E1%84%90%E1%85%B5%20%E1%84%80%E1%85%A1%E1%86%BC%E1%84%8E%E1%85%AE!(%E1%84%80%E1%85%A1%E1%86%BC%E1%84%8B%E1%85%AF%E1%86%AB%20%E1%84%8B%E1%85%A2%E1%86%A8%E1%84%90%E1%85%B5%E1%84%87%E1%85%B5%E1%84%90%E1%85%B5%20%E1%84%8E%E1%85%AE%E1%84%8E%E1%85%A5%E1%86%AB)%20%E1%84%91%E1%85%B3%E1%84%85%E1%85%A9%E1%84%8C%E1%85%A6%E1%86%A8%E1%84%90%E1%85%B3%200d93012ebcea49c5aa55615b9fd9a69d/image%2012.png)

👍 **성과** 

**백/프론트 간 실제로 연결해보는 작업을 통해 RESTful한 통신 규약의 필요성과 서버 동작의 기본 원리를 습득할 수 있었음.**

### **📙N:M 관계 Mapping 시 MappingTable 활용**

- ERD 설계 중 N:M관계와 관련된 데이터를 ‘List’ 형태로 활용하는 방법을 처음 고려하였으나, Service단에서 받아온 List를 String화 하여 parsing하는데 비효율적임을 느꼈음.

- 또한 ‘place’ table의 경우 업체가 망하거나 상호를 변경하는 경우 해당 placeNo를 가진 모든 요소들을 전체 탐색하면서 일일히 변경해주어야 하는 어려움이 있음.

(직접 구현한 StringParser 사진 첨부 필요)

→ DB단에서 받아온 데이터들을 직접 parsing하기 위해 추가한 메소드 ‘’

복잡하게 생긴 만큼, 데이터 하나하나를 일일히 받아오는데 개선의 필요성을 느낌

**이를 보완하기 위해,**

- Mapping Table을 활용하여 table간 N:M matching을 구현.

![image.png](%E1%84%8B%E1%85%A2%E1%86%A8%E1%84%90%E1%85%B5%E1%84%87%E1%85%B5%E1%84%90%E1%85%B5%20%E1%84%80%E1%85%A1%E1%86%BC%E1%84%8E%E1%85%AE!(%E1%84%80%E1%85%A1%E1%86%BC%E1%84%8B%E1%85%AF%E1%86%AB%20%E1%84%8B%E1%85%A2%E1%86%A8%E1%84%90%E1%85%B5%E1%84%87%E1%85%B5%E1%84%90%E1%85%B5%20%E1%84%8E%E1%85%AE%E1%84%8E%E1%85%A5%E1%86%AB)%20%E1%84%91%E1%85%B3%E1%84%85%E1%85%A9%E1%84%8C%E1%85%A6%E1%86%A8%E1%84%90%E1%85%B3%200d93012ebcea49c5aa55615b9fd9a69d/image%2013.png)

→ MappingTable을 따로 두어 복잡한 parsing이 필요하지 않을 뿐더러, 삭제/변경 된 place에 대한 분기 처리 또한 편리하게 개선하였음.

👍 **성과** 

**직관적이고 효율적 DB설계를 어떻게 하는지 배울 수 있었음.** 

### **📗**색인(Indexing)을 활용해 조회 효율화

![20240823_171809.png](%E1%84%8B%E1%85%A2%E1%86%A8%E1%84%90%E1%85%B5%E1%84%87%E1%85%B5%E1%84%90%E1%85%B5%20%E1%84%80%E1%85%A1%E1%86%BC%E1%84%8E%E1%85%AE!(%E1%84%80%E1%85%A1%E1%86%BC%E1%84%8B%E1%85%AF%E1%86%AB%20%E1%84%8B%E1%85%A2%E1%86%A8%E1%84%90%E1%85%B5%E1%84%87%E1%85%B5%E1%84%90%E1%85%B5%20%E1%84%8E%E1%85%AE%E1%84%8E%E1%85%A5%E1%86%AB)%20%E1%84%91%E1%85%B3%E1%84%85%E1%85%A9%E1%84%8C%E1%85%A6%E1%86%A8%E1%84%90%E1%85%B3%200d93012ebcea49c5aa55615b9fd9a69d/20240823_171809.png)

- join을 통해 두 table에서 각각 userNo/boardNo를 활용하였음.
- 이외에도 orderBy 절에 있는 칼럼 또한 신고된 시점의 timeStamp에 색인을 생성하였음

- 색인 칼럼 산정 기준
    - **분포도가 낮은 column**
    
    →  다양한 user가 올린 글/댓글은 분포도가 낮다. 즉 100명의 유저가 있다면 이들이 작성한 글/댓글들은 대체적으로 100명이 고루 작성한 글일 확률이 높다.
    
     
    
    - **Join의 연결고리로써 활용되는 column**
    
    - **조건절에서 자주 사용되는 column**

👍 **성과** 

**인덱싱 적용 이후 단순 조회 시 2배(0.4초 → 0.2초)의 속도 향상 결과를 얻을 수 있었음.**

### 📘**외부 API Parsing시, 비동기(Async)를 사용한 시간 단축**

- 외부 API를 끌어오는 날씨, placeData 중, placeData는 SpringScheduler를 사용, 매주 월요일마다 동작하게 설정하였음
- 하지만 매주 월요일마다 6000여개의 placeData를 받아오고, placeData중 rating column의 경우 google Api를 활용해 ‘상호명’을 통해 일일히 조회해야함.
    
    ![image.png](%E1%84%8B%E1%85%A2%E1%86%A8%E1%84%90%E1%85%B5%E1%84%87%E1%85%B5%E1%84%90%E1%85%B5%20%E1%84%80%E1%85%A1%E1%86%BC%E1%84%8E%E1%85%AE!(%E1%84%80%E1%85%A1%E1%86%BC%E1%84%8B%E1%85%AF%E1%86%AB%20%E1%84%8B%E1%85%A2%E1%86%A8%E1%84%90%E1%85%B5%E1%84%87%E1%85%B5%E1%84%90%E1%85%B5%20%E1%84%8E%E1%85%AE%E1%84%8E%E1%85%A5%E1%86%AB)%20%E1%84%91%E1%85%B3%E1%84%85%E1%85%A9%E1%84%8C%E1%85%A6%E1%86%A8%E1%84%90%E1%85%B3%200d93012ebcea49c5aa55615b9fd9a69d/image%2014.png)
    

  → 간단한 개략도

- 일단 아무런 조치 없이 1. 외부 API호출, 2.상호명을 통한 평점 조회를 진행한 결과 1시간 가량의 시간이 소요

→ 이를 개선하기 위해 Async를 활용하기로 함

👍 **성과** 

**Async를 활용하니, Url에 요청하는 동시에 비동기적으로 rating Column에 데이터를 Insert하는 작업이 수행되었고, 이는 50분→7분으로 시간 효율을 증가시켰음.** 

### 📔인프라 구축과 CI/CD 적용

- AWS EC2를 이용하여 Amazon Linux 2 환경의 서버 구축
- DB 서버는 AWS RDS를 사용하였으며, 보안을 고려하여 운영 서버를 통해 ssh 터널링으로 접속가능하도록 구축

![운영 서버](%E1%84%8B%E1%85%A2%E1%86%A8%E1%84%90%E1%85%B5%E1%84%87%E1%85%B5%E1%84%90%E1%85%B5%20%E1%84%80%E1%85%A1%E1%86%BC%E1%84%8E%E1%85%AE!(%E1%84%80%E1%85%A1%E1%86%BC%E1%84%8B%E1%85%AF%E1%86%AB%20%E1%84%8B%E1%85%A2%E1%86%A8%E1%84%90%E1%85%B5%E1%84%87%E1%85%B5%E1%84%90%E1%85%B5%20%E1%84%8E%E1%85%AE%E1%84%8E%E1%85%A5%E1%86%AB)%20%E1%84%91%E1%85%B3%E1%84%85%E1%85%A9%E1%84%8C%E1%85%A6%E1%86%A8%E1%84%90%E1%85%B3%200d93012ebcea49c5aa55615b9fd9a69d/image%2015.png)

운영 서버

![DB 서버](%E1%84%8B%E1%85%A2%E1%86%A8%E1%84%90%E1%85%B5%E1%84%87%E1%85%B5%E1%84%90%E1%85%B5%20%E1%84%80%E1%85%A1%E1%86%BC%E1%84%8E%E1%85%AE!(%E1%84%80%E1%85%A1%E1%86%BC%E1%84%8B%E1%85%AF%E1%86%AB%20%E1%84%8B%E1%85%A2%E1%86%A8%E1%84%90%E1%85%B5%E1%84%87%E1%85%B5%E1%84%90%E1%85%B5%20%E1%84%8E%E1%85%AE%E1%84%8E%E1%85%A5%E1%86%AB)%20%E1%84%91%E1%85%B3%E1%84%85%E1%85%A9%E1%84%8C%E1%85%A6%E1%86%A8%E1%84%90%E1%85%B3%200d93012ebcea49c5aa55615b9fd9a69d/image%2016.png)

DB 서버

![putty 접속](%E1%84%8B%E1%85%A2%E1%86%A8%E1%84%90%E1%85%B5%E1%84%87%E1%85%B5%E1%84%90%E1%85%B5%20%E1%84%80%E1%85%A1%E1%86%BC%E1%84%8E%E1%85%AE!(%E1%84%80%E1%85%A1%E1%86%BC%E1%84%8B%E1%85%AF%E1%86%AB%20%E1%84%8B%E1%85%A2%E1%86%A8%E1%84%90%E1%85%B5%E1%84%87%E1%85%B5%E1%84%90%E1%85%B5%20%E1%84%8E%E1%85%AE%E1%84%8E%E1%85%A5%E1%86%AB)%20%E1%84%91%E1%85%B3%E1%84%85%E1%85%A9%E1%84%8C%E1%85%A6%E1%86%A8%E1%84%90%E1%85%B3%200d93012ebcea49c5aa55615b9fd9a69d/image%2017.png)

putty 접속

![서버에서 DB 접속](%E1%84%8B%E1%85%A2%E1%86%A8%E1%84%90%E1%85%B5%E1%84%87%E1%85%B5%E1%84%90%E1%85%B5%20%E1%84%80%E1%85%A1%E1%86%BC%E1%84%8E%E1%85%AE!(%E1%84%80%E1%85%A1%E1%86%BC%E1%84%8B%E1%85%AF%E1%86%AB%20%E1%84%8B%E1%85%A2%E1%86%A8%E1%84%90%E1%85%B5%E1%84%87%E1%85%B5%E1%84%90%E1%85%B5%20%E1%84%8E%E1%85%AE%E1%84%8E%E1%85%A5%E1%86%AB)%20%E1%84%91%E1%85%B3%E1%84%85%E1%85%A9%E1%84%8C%E1%85%A6%E1%86%A8%E1%84%90%E1%85%B3%200d93012ebcea49c5aa55615b9fd9a69d/image%2018.png)

서버에서 DB 접속

- Github Webhook을 이용하여 해당 branch에 push하면 젠킨스 파이프라인이 트리거될 수 있도록 설정
- 젠킨스 파이프라인은 각각 백엔드, 프론트엔드로 나누어 관리
- 각각 프로젝트 안에 젠킨스 파일, 도커파일과 프론트에는 추가적으로 nginx 설정 파일을 작성

![image.png](%E1%84%8B%E1%85%A2%E1%86%A8%E1%84%90%E1%85%B5%E1%84%87%E1%85%B5%E1%84%90%E1%85%B5%20%E1%84%80%E1%85%A1%E1%86%BC%E1%84%8E%E1%85%AE!(%E1%84%80%E1%85%A1%E1%86%BC%E1%84%8B%E1%85%AF%E1%86%AB%20%E1%84%8B%E1%85%A2%E1%86%A8%E1%84%90%E1%85%B5%E1%84%87%E1%85%B5%E1%84%90%E1%85%B5%20%E1%84%8E%E1%85%AE%E1%84%8E%E1%85%A5%E1%86%AB)%20%E1%84%91%E1%85%B3%E1%84%85%E1%85%A9%E1%84%8C%E1%85%A6%E1%86%A8%E1%84%90%E1%85%B3%200d93012ebcea49c5aa55615b9fd9a69d/image%2019.png)

![image.png](%E1%84%8B%E1%85%A2%E1%86%A8%E1%84%90%E1%85%B5%E1%84%87%E1%85%B5%E1%84%90%E1%85%B5%20%E1%84%80%E1%85%A1%E1%86%BC%E1%84%8E%E1%85%AE!(%E1%84%80%E1%85%A1%E1%86%BC%E1%84%8B%E1%85%AF%E1%86%AB%20%E1%84%8B%E1%85%A2%E1%86%A8%E1%84%90%E1%85%B5%E1%84%87%E1%85%B5%E1%84%90%E1%85%B5%20%E1%84%8E%E1%85%AE%E1%84%8E%E1%85%A5%E1%86%AB)%20%E1%84%91%E1%85%B3%E1%84%85%E1%85%A9%E1%84%8C%E1%85%A6%E1%86%A8%E1%84%90%E1%85%B3%200d93012ebcea49c5aa55615b9fd9a69d/image%2020.png)

![image.png](%E1%84%8B%E1%85%A2%E1%86%A8%E1%84%90%E1%85%B5%E1%84%87%E1%85%B5%E1%84%90%E1%85%B5%20%E1%84%80%E1%85%A1%E1%86%BC%E1%84%8E%E1%85%AE!(%E1%84%80%E1%85%A1%E1%86%BC%E1%84%8B%E1%85%AF%E1%86%AB%20%E1%84%8B%E1%85%A2%E1%86%A8%E1%84%90%E1%85%B5%E1%84%87%E1%85%B5%E1%84%90%E1%85%B5%20%E1%84%8E%E1%85%AE%E1%84%8E%E1%85%A5%E1%86%AB)%20%E1%84%91%E1%85%B3%E1%84%85%E1%85%A9%E1%84%8C%E1%85%A6%E1%86%A8%E1%84%90%E1%85%B3%200d93012ebcea49c5aa55615b9fd9a69d/image%2021.png)

👍 **성과**

**테스트 진행 시 Github에서 클론하지 않아도 팀원 모두 외부에서 접속하여 결과물을 확인할 수 있게 됨.**
