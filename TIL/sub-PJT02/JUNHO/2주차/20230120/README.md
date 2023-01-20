# 2주차 과제

---

### 2023.01.20(금)

##### JIRA의 장점

- 애자일 팀에서 추천하는 최고의 도구
- 프로젝트의 체계적 관리
- 편리한 이슈 트래킹
- 프로젝트 전반에 대한 레퍼런스 확보 및 관리

### JIRA의 기본적 활용

- Workflow
- BULK
- Components and Labels
- Releases

### Work flow

- 템플릿 개념 : 공용템플릿을 수정하면 관련한 템플릿은 전부 변경되어, 맘대로 쓰기 쉽지 않다.

- Ticket이 생정되고 완료될 때까지의 Status 변화

- 프로젝트 진행상황을 한눈에 파악가능

- 불필요한 커뮤니케이션 감소

- Progress
  
  - open
  - resolve
  - QA : 품질보증
  - reopen
  - 개발자의 인지 및 assign
  - 완료 후 close

### BULK

- 이슈들을 SUB1에서 SUB2로 옮길 때 건바이건으로 옮기고 싶은 니즈가 있을 때, 일괄적으로 옮길 수 있는 기능(일괄변경)
- 일괄작업선택 → 이슈 편집, 이동, 전환, 삭제, 지켜보기

### Component and Labels

- 컴포넌트 : 라벨링작업 ⇒ 체계적인 관리를 위해선 컴포넌트를 써라(복수개로 넣어도 상관없다.)
  
  - 컴포넌트 생성 → 새 구성 요소 만들기 → 작업 세부 정보

- 라벨 : 프로젝트에 종속되지 않는 값 / 몇 백개의 라벨이 다 들어가있음 ⇒ 통계로 안잡혀서 쿼리로 짤 수 없음

### Release

- CI/CD를 구축하고 있는데, 이를 진정으로 사용하도록 돕는 JIRA의 툴
- 즉, 배포를 편하게 하기 전 버전컨트롤을 쉽게 할 수 있게 하며, 그 버전에 어떤 티켓이 있고, 안정성확보를 어떻게 하면 좋을지? 도와주는 툴이라고 생각하면 된다.
- 백로그에서 핸들링한다. 



## 스크럼 회의

### 프론트

- 유저 스토리보드 수정
- 컴포넌트 구성 및 역할 분담
- UX/UI는 변경이 일어날 수 있음
- assets의 구매는 좀더 고민해봐야 할 듯
- 컬리나 토스처럼 깔끔하게 구성하려고 함

## 프론트 회의

### 오전

- ~~재료 탭에서 재료의 위치 순서를 변경~~
- 냄비 아이콘을 만들어서 선택된 재료는 냄비에 넣기
- 냄비 애니메이션을 만들어서 그 동안 레시피 목록 불러오기

## 오후

### 컴포넌트 구성

- 로그인 전 페이지
  
  - 기본 소개-page_info

- 로그인 페이지
  
  - 로그인-login

- 메인
  
  - 최초 로그인 first_main
  
  - 알러지 allergy
  
  - 최초 로그인 아닐시 main 

- 냉장고
  
  - 재고
  
  - 수정 edit_item
  
  - 기본 get_item

- 냄비
  
  - 기본 selected_item
  
  - 검색
    
    - 검색결과 search_item
    
    - 추가할 애들 add-item

- 밥터디
  
  - 가입한 스터디 목록 list_mystudy
  
  - 스터디 목록 list_bobtudy
  
  - 등록 create_bobtudy
  
  - 상세 detail_bobtudy
  
  - 댓글 comment_bobtudy

- 레시피
  
  - 목록 list_recipe
  
  - 상세
    
    - 재료 item-recipe
    
    - 요리단계 steps_recipe
    
    - 댓글 comment_recipe

- 커뮤니티
  
  - 게시물
    
    - 게시물 목록 list_community
    
    - 게시물 상세 detail_community
    
    - 게시물 댓글 list_comment
  
  - 등록
    
    - 게시글 등록 create_community
    
    - 사진등록 create_photo
    
    - 글 create_write

# 