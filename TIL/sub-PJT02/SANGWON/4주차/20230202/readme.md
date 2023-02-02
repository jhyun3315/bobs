# 20230202

## Jenkins

---

1. 도커 허브에서 jenkins/jenkins:lts 이미지 pull

```bash
sudo docker pull jenkins/jenkins:lts
```

1. 젠킨스 컨테이너 실행

```bash
sudo docker run -d --name jenkins -p 8888:8080 -v /jenkins:/var/jenkins_home -v /usr/bin/docker:/usr/bin/docker -v /var/run/docker.sock:/var/run/docker.sock -u root jenkins/jenkins:lts
```

- **v /jenkins:/var/jenkins_home**

젠킨스 컨테이너의 설정을 호스트 서버와 공유함으로써, 컨테이너가 삭제되는 경우에도 설정을 유지할수 있게 해줌

- **v /usr/bin/docker:/usr/bin/docker -v /var/run/docker.sock:/var/run/docker.sock**

젠킨스 컨테이너에서도 호스트 서버의 도커를 사용하기 위한 바인딩입니다.이렇게 컨테이너 내부에서 설치없이, 외부의 도커를 사용하는 방식을 **DooD(Dock out of Docker)** 라고 합니다.

- **p 8080:8080**젠킨스의 defaul port 인 8080 을 호스트 서버와 매핑해줍니다.

### 젠킨스 Configuratrion

![Untitled](20230202%2069f90def0ecf44299370901e634f7075/Untitled.png)

- 플러그인 관리에서 Nodejs 설치 해준다. **Gloval Tool Configuration**에 들어가서 리액트 버전에 맞게 선택 후 ADD 해준다.
- 플러그인 관리에서 Gitlab 관련 파일을 설치한다.
- 깃랩 프로젝트 access tokens을 생성한다.(생성 직후만 확인할 수 있으므로 복사 해둔다.)
- 젠킨스 Credentials 등록한다. username with password(kind)로 등록하고, password에 복사한 토큰을 붙여넣는다.

![Untitled](20230202%2069f90def0ecf44299370901e634f7075/Untitled%201.png)

## Jenkins Pipeline

---

```bash
pipeline {
    agent any
    
    environment {
        GIT_URL = "https://lab.ssafy.com/s08-webmobile1-sub2/S08P12B304"
    }

    tools {
        nodejs "nodejs-blog"
    }

    stages {
        stage('Pull') {
            steps {
                git url: "${GIT_URL}", branch: "main", credentialsId: "jenkins_gitlab"
            }
        }
        
        stage('React Build') {
            steps {
                dir('frontend/pjt01') {
                    sh 'pwd'
                    sh 'npm install'
                    sh 'CI=false npm run build'
                }
                
            }
        }
        
        stage('Build') {
            steps {
                dir('frontend/pjt01') {
                    sh 'docker build -t nginx-react:0.1 .'
                }
                
            }
        }
        
        stage('Deploy') {
            steps{
                script {
                    try {
                        sh 'docker ps -q -f name=nginx-react | grep . && docker stop nginx-react && docker rm nginx-react'
                    } catch (e) {
                        sh 'exit 0'
                        sh 'echo docker container stop and remove Fail!!'
                    }
                }
                sh 'docker run --name nginx-react -d -p 3000:80 nginx-react:0.1'
            }
        }

       stage('Finish') {
             steps{
                 sh 'docker images -qf dangling=true | xargs -I{} docker rmi {}'
             }
         }
    }
}
```

fronted 자동 배포를 위한 스크립트이다.

- **Pull** - Github로 부터 프로젝트 소스를 Pull 함
- **React Build** - NodeJS 플러그인을 사용하여, 프로젝트 소스를 사전 Build 함
- **Build** - 도커파일을 실행시켜서 이미지를 생성
- **Deploy** - 이미 실행중인 nginx 컨테이너가 있다면 찾아서 중단하고, 삭제한다. 이후 방금 빌드한 이미지를 컨테이너로 실행
- **Finish** - 일련의 과정에서 불필요한 Danlgling 이미지가 생성되는데, 이를 삭제해주기 위한 스크립트