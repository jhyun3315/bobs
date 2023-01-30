# 20230130

## ERD 수정

---

![ERD_연습아닌최종.png](./20230130%20626127c75a004cd989558a5a88111c1a/erd.png)

식별 관계 → 비식별관계로 전체 테이블 수정

알러지 테이블 추가

레시피 테이블 수정

- 난이도 추가
- 소요시간 추가
- 레시피 양 추가

레시피 재료 테이블 수정

- 재료와의 관계 삭제
- 크롤링 시 레시피 재료 테이블과 재료 테이블 이름 일치 시키기

## Springboot 프로젝트 구조 생성

---

![Untitled](20230130%20626127c75a004cd989558a5a88111c1a/Untitled.png)

## EC2 DB 설치 학습

---

## 우분투 기본 세팅하기

우분투를 설치하면 항상 세팅하는 것이 있습니다. 컴퓨터에 설정된 현재 시간을 맞추는 작업, apt 패키지 관리자를 사용하기 위한 apt upate과정, Text Editor인 vim 설치를 하곤 합니다. 여기서 rdate 는 Time Server에 접속해서 시간을 가져오고, 시스템에 세팅해주는 리눅스 유틸리티 입니다.

```bash
sudo apt update
sudo apt install rdate vim -y
```

time.bora.net은 LG U+ 에서 운영하는 Time Server 입니다. 이렇게 시간 값을 가져온 후, Korea (Seoul) 기준으로 날짜 기준 값을 맞춰줍니다. 이렇게 시간 세팅을 하는 이유는 DB에 정확한 시간 값을 기록해주기 위해서 입니다.

```bash
sudo rdate -s time.bora.net
sudo ln -sf /usr/share/zoneinfo/Asia/Seoul /etc/localtime
```

아래와 같이, date 명령어를 입력하면, 현재 시간을 확인할 수 있습니다.

![Untitled](20230130%20626127c75a004cd989558a5a88111c1a/Untitled%201.png)

## MYSQL 설치 및 세팅

먼저 mysql을 설치합니다. 

```bash
sudo apt install mysql-server -y
```

과거 mysql 버전은 mysql_secure_installation 라는 프로그램을 사용해서 root 계정 비밀번호를 세팅했지만, 현재 mysql-server는 관리자 계정 접속 방법이 다음과 같이 바뀌었습니다.

```bash
sudo mysql
```

외부에서 접속 가능한 아이디를 Sample로 만들어봅시다. 아래에서 ‘%’는 특정 IP가 아닌, 어느 IP를 가진 Host에서도 접속 가능함을 나타냅니다. with mysql_native_password 는 예전 방식의 password 인증 방식을 사용하겠다는 의미입니다. (mysql 8.0 이후 부터는 키 인증방식의 로그인을 해야하기 때문에, 예전 방식의 password 인증 방식을 사용합니다.)

```sql
use mysql;
create user '아이디'@'%' identified with mysql_native_password by '비밀번호';
```

여기서 Sample로 mincoding, 1234 로 만들어보았습니다.

![Untitled](20230130%20626127c75a004cd989558a5a88111c1a/Untitled%202.png)

이제 DB 스키마를 만들어봅시다. DB를 쉽게 이해하기 위해서는 엑셀로 비유해서 이해하면 쉽습니다. DB 스키c마는 “엑셀 파일 하나” 로 비유할 수 있고, DB Table은 엑셀 파일 내 Sheet 로 비유하면 쉽습니다. 여기서는 DB 스키마 하나만 제작하고, mincoding 아이디가 해당 스키마에 접속할 수 있는 권한을 부여할 것입니다. 먼저 DB 스키마를 생성합니다.

```sql
create database [DB스키마이름];
```

여기서는 minDB 라는 DB스키마를 만들었습니다.

![Untitled](20230130%20626127c75a004cd989558a5a88111c1a/Untitled%203.png)

지금까지 DB 계정과 DB 스키마를 만들었고, 이 DB 스키마에 DB 계정이 모든 권한을 가지고 있게 하도록 권한설정을 해주도록 합니다.

```sql
grant all privileges on [DB스키마이름].* to '아이디'@'%';
```

![Untitled](20230130%20626127c75a004cd989558a5a88111c1a/Untitled%204.png)

이제 샘플로 만든 계정인 mincoding 이 mysql에 잘 접속 되는지 테스트합니다.

```sql
mysql -u '아이디' -p
```

![Untitled](20230130%20626127c75a004cd989558a5a88111c1a/Untitled%205.png)

마지막으로 mysql 을 SSH 접속 뿐만 아니라, 외부 접속이 되게 끔 설정해야합니다.

```bash
sudo vi /etc/mysql/mysql.conf.d/mysqld.cnf
```

![Untitled](20230130%20626127c75a004cd989558a5a88111c1a/Untitled%206.png)

모든 IP가 접속 가능하도록 mysql 데몬 설정값을 세팅하였습니다. 이제 mysql 데몬을 재시작하여, 설정 내용을 적용합니다.

## AWS S3 학습

---

## AWS S3(Simple Storage Service)란?

> Simple Storage Service의 약자로 파일 서버의 역할을 하는 서비스다. 일반적인 파일서버는 트래픽이 증가함에 따라서 장비를 증설하는 작업을 해야 하는데 S3는 이와 같은 것을 대행한다. 트래픽에 따른 시스템적인 문제는 걱정할 필요가 없어진다. 또 파일에 대한 접근 권한을 지정 할 수 있어서 서비스를 호스팅 용도로 사용하는 것을 방지 할 수 있다. 아래는 S3의 주요한 기능적인 특성들이다.
> 

### AWS S3(Simple Storage Service의 특징

- 많은 사용자가 접속을 해도 이를 감당하기 위해서 시스템적인 작업을 하지 않아도 된다.
- 저장할 수 있는 파일 수의 제한이 없다.
- 최소 1바이트에서 최대 5TB의 데이터를 저장하고 서비스 할 수 있다.
- 파일에 인증을 붙여서 무단으로 엑세스 하지 못하도록 할 수 있다.
- HTTP와 BitTorrent 프로토콜을 지원한다.
- REST, SOAP 인터페이스를 제공한다.
- 데이터를 여러 시설에서 중복으로 저장해 데이터의 손실이 발생할 경우 자동으로 복원한다.
- 버전관리 기능을 통해서 사용자에 의한 실수도 복원이 가능하다.
- 정보의 중요도에 따라서 보호 수준을 차등 할 수 있고, 이에 따라서 비용을 절감 할 수 있다. (RSS)

### AWS S3(Simple Storage Service에서 사용되는 용어

- 객체 - object, AWS는 S3에 저장된 데이터 하나 하나를 객체라고 명명하는데, 하나 하나의 파일이라고 생각하면 된다.
- 버킷 - bucket, 객체가 파일이라면 버킷은 연관된 객체들을 그룹핑한 최상위 디렉토리라고 할 수 있다. 버킷 단위로 지역(region)을 지정 할 수 있고, 또 버킷에 포함된 모든 객체에 대해서 일괄적으로 인증과 접속 제한을 걸 수 있다.
- 버전관리 - S3에 저장된 객체들의 변화를 저장. 예를들어 A라는 객체를 사용자가 삭제하거나 변경해도 각각의 변화를 모두 기록하기 때문에 실수를 만회할 수 있다.
- RSS - Reduced Redundancy Storage의 약자로 일반 S3 객체에 비해서 데이터가 손실될 확률이 높은 형태의 저장 방식. 대신에 가력이 저렴하기 때문에 복원이 가능한 데이터, 이를테면 섬네일 이미지와 같은 것을 저장하는데 적합하다. 그럼에도 불구하고 물리적인 하드 디스크 대비 400배 가량 안전하다는 것이 아마존의 주장
- Glacier - 영어로는 빙하라는 뜻으로 매우 저렴한 가격으로 데이터를 저장 할 수 있는 아마존의 스토리지 서비스

## AWS S3(Simple Storage Service 버킷 생성

![https://d1tlzifd8jdoy4.cloudfront.net/wp-content/uploads/2021/07/1-3-640x195.png](https://d1tlzifd8jdoy4.cloudfront.net/wp-content/uploads/2021/07/1-3-640x195.png)

그럼 바로 bucket을 만들어봅시다!

s3를 검색하고 들어간 다음, 버킷 만들기를 클릭합니다.

![https://d1tlzifd8jdoy4.cloudfront.net/wp-content/uploads/2021/07/9e696ada0c2812e6f2608349d04cbf12-640x345.png](https://d1tlzifd8jdoy4.cloudfront.net/wp-content/uploads/2021/07/9e696ada0c2812e6f2608349d04cbf12-640x345.png)

그리고 일반 구성은 다음과 같이 설정했습니다.

bucket 이름은 임의로 입력해주고, 지역은 아시아 서울로 선택했습니다.

![https://d1tlzifd8jdoy4.cloudfront.net/wp-content/uploads/2021/07/3-4.png](https://d1tlzifd8jdoy4.cloudfront.net/wp-content/uploads/2021/07/3-4.png)

버전 관리의 경우, 같은 파일을 올리면서 계속 업데이트 과정을 거치더라도, 업데이트 이전의 내용을 복원할 수 있게 해줍니다.

![https://d1tlzifd8jdoy4.cloudfront.net/wp-content/uploads/2021/07/4-4-640x243.png](https://d1tlzifd8jdoy4.cloudfront.net/wp-content/uploads/2021/07/4-4-640x243.png)

그 다음, 퍼블릭 액세스입니다.

가장 첫 번째 부분을 체크해두면, 공개 설정으로 해둔 파일을 업로드 할 때, 업로드가 거절이 됩니다. 체크를 해제하면 공개 파일을 업로드 할 때 업로드가 됩니다.두 번째의 경우, 첫 번째 체크를 해제 하더라도, 비공개로 인식해서 공개가 되지 않습니다.

즉 bucket를 비공개로 사용하겠다고 하면 체크를 하는것이 좋고, 업로드하는 파일중 어떠한것들은 공개 하고 싶다 라고 하면 체크를 해제하는것이 좋습니다.

그 다음 버킷 생성을 눌러줍니다.

![https://d1tlzifd8jdoy4.cloudfront.net/wp-content/uploads/2021/07/1fabbbb573565c106b5b4cf45f2de1da-640x272.png](https://d1tlzifd8jdoy4.cloudfront.net/wp-content/uploads/2021/07/1fabbbb573565c106b5b4cf45f2de1da-640x272.png)

그럼 버킷이 생성된 것을 볼 수 있습니다.

### 버킷 읽기, 수정, 삭제

![https://d1tlzifd8jdoy4.cloudfront.net/wp-content/uploads/2021/07/6-3-640x68.png](https://d1tlzifd8jdoy4.cloudfront.net/wp-content/uploads/2021/07/6-3-640x68.png)

먼저 생성한 bucket을 누르면 여러 태그들이 뜨게됩니다. 객체의 경우 업로드한 파일 리스트들을 나타냅니다.

![https://d1tlzifd8jdoy4.cloudfront.net/wp-content/uploads/2021/07/7-2-640x458.png](https://d1tlzifd8jdoy4.cloudfront.net/wp-content/uploads/2021/07/7-2-640x458.png)

권한을 바꾸고 싶을 때는 권한 탭을 클릭하면 되고, 속성을 바꾸고 싶으면 속성을 클릭하면 됩니다.

![https://d1tlzifd8jdoy4.cloudfront.net/wp-content/uploads/2021/07/7fe1955c26500fcf78666c9d8217eb0e-640x259.png](https://d1tlzifd8jdoy4.cloudfront.net/wp-content/uploads/2021/07/7fe1955c26500fcf78666c9d8217eb0e-640x259.png)

삭제하고 싶은 경우, 삭제 탭을 누르면 그림과 같은 페이지가 나옵니다. 그리고 bucket 이름을 입력해주고 삭제버튼을 누르면 삭제가 됩니다.

![https://d1tlzifd8jdoy4.cloudfront.net/wp-content/uploads/2021/07/9-2.png](https://d1tlzifd8jdoy4.cloudfront.net/wp-content/uploads/2021/07/9-2.png)

이어서, 폴더를 한번 만들어보도록 하겠습니다. bucket을 클릭하고 객체 탭에서 폴더 만들기를 클릭합니다.

![https://d1tlzifd8jdoy4.cloudfront.net/wp-content/uploads/2021/07/10-640x470.png](https://d1tlzifd8jdoy4.cloudfront.net/wp-content/uploads/2021/07/10-640x470.png)

폴더 이름을 적어주고 폴더 만들기를 클릭합니다.

![https://d1tlzifd8jdoy4.cloudfront.net/wp-content/uploads/2021/07/11-3-640x112.png](https://d1tlzifd8jdoy4.cloudfront.net/wp-content/uploads/2021/07/11-3-640x112.png)

그럼 이렇게 폴더가 생성됩니다.

폴더의 이름 같은 경우에는 바꾸는 방법이 안보이는데, "새로운 폴더" 라는 폴더를 하나 만들고 중요한 폴더 안에 있는 내용을 새로운 폴더로 옮긴 다음, 중요한 폴더를 삭제하는 방법을 사용하면 될 것 같습니다.

### 객체(파일)

![https://d1tlzifd8jdoy4.cloudfront.net/wp-content/uploads/2021/07/12-1.png](https://d1tlzifd8jdoy4.cloudfront.net/wp-content/uploads/2021/07/12-1.png)

파일을 업로드 해봅시다. 먼저 중요한 폴더 라는 이름의 폴더를 하나 만들고 이 폴더안에 중요한 파일 이라는 텍스트 파일을 하나 만들어 넣어줍니다.

![https://d1tlzifd8jdoy4.cloudfront.net/wp-content/uploads/2021/07/14.png](https://d1tlzifd8jdoy4.cloudfront.net/wp-content/uploads/2021/07/14.png)

그리고 객체 탭에서 중요한 폴더를 드래그 해서 올려줍니다.

![https://d1tlzifd8jdoy4.cloudfront.net/wp-content/uploads/2021/07/e6fb6cd0032dbcf0686902e8844935ea-640x267.png](https://d1tlzifd8jdoy4.cloudfront.net/wp-content/uploads/2021/07/e6fb6cd0032dbcf0686902e8844935ea-640x267.png)

그러면 여러 옵션 사항들을 줄 수 있는데, 먼저 ACL 액세스 제어 목록에는 현재 객체(파일)을 읽고 쓰는 권한을 누구에게 줄 것인지 설정할 수 있습니다. AWS 계정 소유자만 읽고 쓰기가 가능하도록 선택하고 넘겨줍니다.

![https://d1tlzifd8jdoy4.cloudfront.net/wp-content/uploads/2021/07/16-1-640x431.png](https://d1tlzifd8jdoy4.cloudfront.net/wp-content/uploads/2021/07/16-1-640x431.png)

그리고 스토리지 클래스가 나옵니다. 스토리지 클래스는 가격이나 속도, 안전성에 따라서 비용이 달라지게 됩니다. 현재는 그냥 기본값으로 선택하고 업로드 버튼을 눌러줍니다.

![https://d1tlzifd8jdoy4.cloudfront.net/wp-content/uploads/2021/07/17-640x170.png](https://d1tlzifd8jdoy4.cloudfront.net/wp-content/uploads/2021/07/17-640x170.png)

그럼 중요한 폴더 안에 중요한 파일.txt가 업로드 된 것을 볼 수 있습니다.

### 공유와 권한

![https://d1tlzifd8jdoy4.cloudfront.net/wp-content/uploads/2021/07/18.png](https://d1tlzifd8jdoy4.cloudfront.net/wp-content/uploads/2021/07/18.png)

S3에 올려놓은 폴더나 파일을 다른 사람이 볼 수 있도록 할 수 있습니다. 먼저 중요한 파일.txt를 클릭하고 속성탭에 들어갑니다. 그리고 오른쪽 아래에 객체URL이라고 되어있는데 복사합니다.

![https://d1tlzifd8jdoy4.cloudfront.net/wp-content/uploads/2021/07/19-640x49.png](https://d1tlzifd8jdoy4.cloudfront.net/wp-content/uploads/2021/07/19-640x49.png)

웹에서 실행시켜보면 접근 거부라고 뜹니다.

![https://d1tlzifd8jdoy4.cloudfront.net/wp-content/uploads/2021/07/dac7bbc06951324f54e7f30a72f493ad-640x364.png](https://d1tlzifd8jdoy4.cloudfront.net/wp-content/uploads/2021/07/dac7bbc06951324f54e7f30a72f493ad-640x364.png)

파일의 권한 탭으로 들어가서 아래쪽을 보면 액세스 제어 목록이 뜹니다.

현재 객체 소유자는 나 자신을 의미합니다. 현재 객체 소유자는 읽기, 쓰기 전부다 가능하기 때문에 접근이 가능하겠지만, 모든 사람, 인증된 사용자 그룹은 허용되지 않은 상태입니다.

![https://d1tlzifd8jdoy4.cloudfront.net/wp-content/uploads/2021/07/21-640x51.png](https://d1tlzifd8jdoy4.cloudfront.net/wp-content/uploads/2021/07/21-640x51.png)

그럼 편집으로 들어가서 읽기를 체크해줍니다.

왼쪽은 객체 읽기, 오른쪽은 객체 ACL 읽기인데, 객체만 읽고 싶으면 객체 읽기만 체크해주면 됩니다.

그리고 다시 해당 링크로 접속해보면 파일 내용이 출력되는 것을 볼 수 있습니다.