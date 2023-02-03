# 20230203

## SSL 인증서 적용

---

(React + Nginx)컨테이너와 별개로 EC2 서버 자체에 Nginx 설치를 해주어야한다.

다음은 EC2 Nginx에 대한 SSL 설정 방법과 백엔드 프론트엔드 분기 설정에 관한 내용이다.

1. nginx 설치

```bash
# 설치
sudo apt-get install nginx

# 설치 확인 및 버전 확인
nginx -v
```

1. letsencrypt 설치

```bash
sudo apt-get install letsencrypt

sudo systemctl stop nginx

sudo letsencrypt certonly --standalone -d i8b304.p.ssafy.io
```

1. 인증서 발급 후, /etc/nginx/sites-available로 이동 nginxec2.conf 파일 생성 후 아래 코드 작성

```bash
server {

        location /{
                proxy_pass http://localhost:3000;
        }

        location /api {
                proxy_pass http://localhost:8080/api;
        }

    listen 443 ssl; # managed by Certbot
    ssl_certificate /etc/letsencrypt/live/i8b304.p.ssafy.io/fullchain.pem; # managed by Certbot
    ssl_certificate_key /etc/letsencrypt/live/i8b304.p.ssafy.io/privkey.pem; # managed by Certbot
    # include /etc/letsencrypt/options-ssl-nginx.conf; # managed by Certbot
    # ssl_dhparam /etc/letsencrypt/ssl-dhparams.pem; # managed by Certbot
}

server {
    if ($host = i8b304.p.ssafy.io) {
        return 301 https://$host$request_uri;
    } # managed by Certbot

        listen 80;
        server_name i8b304.p.ssafy.io;
    return 404; # managed by Certbot
}
```

/, /api 주소의 443 포트를 설정해준다.

도메인 주소를 입력했을때 301 리다이렉트로 https가 적용된 주소로 간다.

1. 마무리

```bash
sudo ln -s /etc/nginx/sites-available/nginxec2.conf /etc/nginx/sites-enabled/nginxec2.conf

#success 확인
sudo nginx -t

sudo systemctl restart nginx
```

심볼릭 링크설정 후  nginx 접속 확인후 재시작해준다.