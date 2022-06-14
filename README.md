# yu_SoftwareDesign: 영남대학교 소프트웨어설계 Implementation


### 전제조건

- 안드로이드 스튜디오를 사용할 경우 안드로이드 스튜디오가 설치되어 있어야 한다. (작업 버전: 4.0.1)
- 에뮬레이터를 사용할 경우 AVD를 설치하여야 한다. (작업 디바이스: Pixel 2, 작업 시스템이미지: Q)
- 휴대폰을 연결하여 사용할 경우 안드로이드 휴대폰을 사용하여야 한다. (작업 휴대폰: 갤럭시S6, 작업 안드로이드버전: 7.0)
- 실행 방법 3가지 모두 인터넷에 연결된 상태로 진행할 수 있다.

---

### 숙지사항

- 본 프로젝트에서는 비용으로 인하여 서버를 사용하지 않았기 때문에 SQLite 데이터베이스만을 사용하였다.
- 고로, 어플리케이션을 껐다 켰을 경우에는 데이터베이스(사용자 정보)가 초기화되도록 설정하였다.
- 빠른 테스트를 위해 총 10개의 사용자 정보를 추가해놓았다. (아이디: user1 ~ user10, 비밀번호 pw1 ~ pw10)
- 회원 가입, 회원 정보 수정, 회원 탈퇴 기능을 테스트하기 위해서는 어플리케이션을 끄지 않고 뒤로가기 버튼을 활용하면서 테스트를 진행해야 정상적으로 작동하는지 확인할 수 있다.

---

## 실행방법(3가지)

### 1. 휴대폰 연결하여 APK 파일로 설치

1. USB 케이블을 통해 휴대폰을 컴퓨터에 연결
2. 휴대폰 저장소에 들어가서 제출한 news_app.apk를 저장
3. 휴대폰의 파일에서 설치파일을 찾아서 news_app.apk를 설치
4. 설치한 어플리케이션을 실행

### 2. 안드로이드 스튜디오에서 휴대폰 연결하여 실행

1. ### [File] 탭 - 'Open' 클릭
2. 소스코드 위치를 찾고 'OK' 클릭하여 프로젝트 열기
3. USB 케이블을 통해 휴대폰을 컴퓨터에 연결
4. 현재 연결중인 Device가 Running Device가 맞는지 확인하고, Run(Shift+F10)

### 3. 휴대폰을 연결하지 않고 안드로이드 스튜디오의 에뮬레이터로 실행

1. [File] 탭 - 'Open' 클릭
2. 소스코드 위치를 찾고 'OK' 클릭하여 프로젝트 열기
3. USB 케이블을 통해 휴대폰을 컴퓨터에 연결
4. [Tools] 탭 - 'AVD Manager' 클릭
5. 'Create Virtual Device...' 클릭
6. Category = 'Phone' / 'Pixel 2' 선택 후 'Next' 클릭
7. System Image = 'Q' 후 'Next' 클릭
8. Finish

---

## 실행 기능

### 0. 메인

- 어플리케이션을 실행하면 3초 뒤 로그인 화면으로 자동으로 이동한다.

### 1. 로그인

- 기본으로 저장되어 있는 정보(아이디: user1 / 비밀번호: pw1)를 통해 로그인할 수 있다.
- 회원가입 후, 해당 아이디와 비밀번호 입력하여 로그인할 수 있다.
- 관리자(아이디: admin / 비밀번호: admin)로 로그인할 수 있다.

### 2. 회원가입

- 모든 양식을 입력해야 회원가입이 완료된다.
- 반드시 Check 버튼을 통해 아이디 중복 검사를 해야함(아이디를 변경할 경우 다시 입력해야함)
- 반드시 비밀번호와 비밀번호 확인을 동일하게 입력해야함
- 모든 입력 양식을 입력 후 Join 버튼을 클릭하면 회원가입 성공 여부를 출력(성공 시 로그인 화면으로 이동)

### 3. 메뉴

- 각 계정에 따른 메뉴를 출력하고 메뉴를 클릭하여 해당 기능을 실행할 수 있다.
- 회원메뉴 : 뉴스, 날씨, 오늘의 핫키워드, 내 정보
- 관리자메뉴 : 회원 관리

### 4. 뉴스 (회원)

- 초기 실행 시 : 정치 카테고리의 뉴스들이 출력된다.
- 모든 뉴스가 로드되고(로드 성공 메시지 출력) 상단의 카테고리를 클릭하면 선택한 카테고리에 해당하는 뉴스 리스트가 출력된다. (중요: 로드가 다 되기 전에 클릭할 경우 어플리케이션이 꺼질 수 있음)
- 뉴스 리스트에서 뉴스를 클릭할 경우 뉴스 내용 전체를 볼 수 있는 화면으로 이동한다.

### 5. 날씨 (회원)

- 실행 시 네이버 날씨를 크롤링하여 오늘 날씨 정보가 출력되고, 어플에 저장된 날씨에 해당하는 이미지가 출력된다. (중요: 인터넷이 연결되어 있어야 함)

### 6. 오늘의 핫키워드 (회원)

- 실행 시 구글 트렌드 데이터를 크롤링하여 오늘의 핫키워드를 통해, 워드클라우드를 생성하여 이를 출력한다. (중요: 인터넷이 연결되어 있어야 함)

### 7. 내 정보 (회원)

- 현재 로그인되어 있는 회원 정보를 데이터베이스에서 받아와서 출력한다. (비밀번호 제외)
- 수정 버튼 : 모든 양식을 입력하였고, 비밀번호와 비밀번호 확인이 일치할 경우 회원 정보를 변경하고 메뉴로 돌아간다.
- 탈퇴 버튼 : 정말 탈퇴할 것인지 되묻고 회원을 탈퇴시키고(데이터베이스에서 삭제) 로그인 화면으로 돌아간다.

### 8. 회원 관리 (관리자)

- 데이터베이스에 저장된 모든 회원리스트를 출력하고, 이를 클릭할 경우 정말 탈퇴시킬 것인지 되묻고 회원을 탈퇴시키고(데이터베이스에서 삭제) 회원리스트(화면)을 업데이트한다.

---

참고 : 모든 화면에서 ‘뒤로 가기’를 누를 경우 이전 화면으로 돌아갈 수 있다.
