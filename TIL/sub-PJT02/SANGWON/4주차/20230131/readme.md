# 20230131

# EC2 개발환경

# EC2 Info

---

### Ubuntu

version : 20.04 LTS

310 GB

172.26.1.227

ubuntu@i8b304.p.ssafy.io

### Docker

Docker version 20.10.23

### GITLAB

https://lab.ssafy.com/s08-webmobile1-sub2/S08P12B304.git

# **Docker Containers**

---

### MySQL

현재 active 상태

- Version : 8.0.32 MySQL Community Server - GPL
- image name : mysql:latest
- volume name : mysql-volume
- 0.0.0.0 : 3306→3306

### Nginx & React

### SpringBoot

### OpenVidu

### Redis

### Jenkins

- docker 명령을 사용해야 하므로 jenkins container 안에 docker가 명령이 작동하도록 해야함.
docker in docker 와 docker out docker 둘 중 하나를 적용해야함.

# 도커 설치

1. Update the `apt` package index and install packages to allow `apt` to use a repository over HTTPS:

```bash
sudo apt-get update
sudo apt-get install ca-certificates
sudo apt-get install curl
sudo apt-get install gnupg
sudo apt-get install lsb-release
```

1. Add Docker’s official GPG key:

```bash
sudo mkdir -p /etc/apt/keyrings
curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo gpg --dearmor -o /etc/apt/keyrings/docker.gpg
```

1. Use the following command to set up the repository:

```bash
echo \
  "deb [arch=$(dpkg --print-architecture) signed-by=/etc/apt/keyrings/docker.gpg] https://download.docker.com/linux/ubuntu \
  $(lsb_release -cs) stable" | sudo tee /etc/apt/sources.list.d/docker.list > /dev/null
```

****Install Docker Engine****

1. Update the `apt` package index:

```bash
sudo apt-get update
```

*if, Receiving a GPG error when running `apt-get update`? 위에 명령에서 에러 안나면 스킵!!*

```bash
sudo chmod a+r /etc/apt/keyrings/docker.gpg
sudo apt-get update
```

1. Install Docker Engine, containerd, and Docker Compose.

```bash
sudo apt-get install docker-ce docker-ce-cli containerd.io docker-compose-plugin
```

1. Verify that the Docker Engine installation is successful by running the `hello-world`
 image: (잘 되는지 테스트)

```bash
sudo docker run hello-world
```

# MySQL 설치

### 설치 과정

---

1. mysql 최신 버전의 이미지를 가져온다.

```bash
$ docker pull mysql:latest
```

1. Docker 컨테이너 볼륨 설정

```bash
# volume 생성
$ docker volume create mysql-volume
```

```bash
# volume 확인
$ docker volume ls
```

1. 생성한 volume을 컨테이너에 마운팅하여 실행시킨다. 루트 비번 넣어줘야함

```bash
$ docker run -d --name mysql-container -p 3306:3306 -v mysql-volume:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=1234 mysql:latest
```

- -name : **mysql-container**라는 이름의 컨테이너 생성
- p : **호스트의 3306** 포트를 **컨테이너의 3306** 포트와 포트포워딩
- v : **호스트의 mysql-volume**의 volume에 **컨테이너의 /var/lib/mysql** volume을 **마운팅**
- e : MYSQL_ROOT_PASSWORD 환경변수 값을 '1234'로 지정
- d : daemon으로 실행

1. 컨테이너에 접속하기

```bash
$ docker exec -it mysql-container bash
```

1. MySQL 서버에 접속하기

```bash
$ mysql -u root -p
# 컨테이너 생성 시 입력했던 패스워드 입력
Enter password:
...
mysql>
```

1. 이제 새로운 user를 만든다.

```bash
# USER 생성, '%'는 모든 IP에서 접속 가능
mysql> CREATE USER test01@'%' identified by '1234';
# 생성한 USER에 모든 권한 부여
mysql> GRANT ALL PRIVILEGES ON *.* to test01@'%';
# 변경 사항 적용
mysql> FLUSH PRIVILEGES;
mysql> exit;
```

1. 생성한 user로 MySQL 서버에 접속한다.

```bash
$ mysql -u test01 -p
Enter password:
...
mysql>
```

1. 데이터베이스를 하나 생성해본다.

```bash
mysql> CREATE DATABASE test;
mysql> SHOW DATABASES;
+--------------------+
| Database           |
+--------------------+
| information_schema |
| mysql              |
| performance_schema |
| sys                |
| test               |
+--------------------+
5 rows in set (0.00 sec)
```

1. 외부에서 접속하기
- MySQL workbench에서 외부 접속

: Hostname에 서버의 IP or 도메인을 입력하고, 외부접속을 위해 생성했던 사용자명과 비밀번호를 입력해준다.