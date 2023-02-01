# 2주차


### 01/16 (월)

1. GIRA 2주차 계획 구성

![image.png](./image.png)

2. 사용자 기능 명세서 작성

![image-1.png](./image-1.png)


### 01/17 (화)

1. Intellij 공부

2. ERD 설계 - 작성중
![erd.png](./erd.png)


### 01/18 (수)

1. 레시피 크롤링 공부 
(참고 : 만개의 레시피)

```

for (int i = 0; i < list.size(); i++) { //0부터 list의 사이즈까지 반복
  WebElement tag = list.get(i).findElement(By.tagName("span"));
  // list의 i번째에서 tagName이 span인 요소 tag에 담기 
  
  String spanText = tag.getText();
  // tag의 text를 가져와 spanText에 담기
}

```

2. ERD 설계 (구체화)
![erd2.PNG](./erd2.PNG)


### 01/19 (목)

1. ERD 설계 (거의 확정)
![erd3.PNG](./erd3.PNG)


2. API 문서 - 작성중
![api.PNG](./api.PNG)


### 01/20 (금)

1. API 문서 수정
![api2.PNG](./api2.PNG)

2. 인텔리제이 개발 환경 세팅
![project.PNG](./project.PNG)




# 3주차


# 01/25 (수)

1. API 문서 (거의 확정)
![api3.PNG](./api3.PNG)

2. 중간 발표 PPT 제작
![ppt_ing.PNG](./ppt_ing.PNG)


# 01/26 (목)
- 중간 발표 PPT 완성
![ppt1.PNG](./ppt1.PNG)
![ppt2.PNG](./ppt2.PNG)


# 01/27 (금)
- webRTC 개발 환경 셋팅 및 샘플 구현

```
<script>
import axios from 'axios';
import { OpenVidu } from 'openvidu-browser';
import UserVideo from './components/UserVideo';

axios.defaults.headers.post['Content-Type'] = 'application/json';

const OPENVIDU_SERVER_URL = "https://" + location.hostname + ":4443";
const OPENVIDU_SERVER_SECRET = "MY_SECRET";

export default {
	name: 'App',

	components: {
		UserVideo,
	},

	data () {
		return {
			OV: undefined,
			session: undefined,
			mainStreamManager: undefined,
			publisher: undefined,
			subscribers: [],

			mySessionId: 'SessionA',
			myUserName: 'Participant' + Math.floor(Math.random() * 100),
		}
	},

	methods: {
		joinSession () {
			// --- Get an OpenVidu object ---
			this.OV = new OpenVidu();

			// --- Init a session ---
			this.session = this.OV.initSession();

			// --- Specify the actions when events take place in the session ---

			// On every new Stream received...
			this.session.on('streamCreated', ({ stream }) => {
				const subscriber = this.session.subscribe(stream);
				this.subscribers.push(subscriber);
			});

			// On every Stream destroyed...
			this.session.on('streamDestroyed', ({ stream }) => {
				const index = this.subscribers.indexOf(stream.streamManager, 0);
				if (index >= 0) {
					this.subscribers.splice(index, 1);
				}
			});

			// On every asynchronous exception...
			this.session.on('exception', ({ exception }) => {
				console.warn(exception);
			});

			// --- Connect to the session with a valid user token ---

			// 'getToken' method is simulating what your server-side should do.
			// 'token' parameter should be retrieved and returned by your own backend
			this.getToken(this.mySessionId).then(token => {
				this.session.connect(token, { clientData: this.myUserName })
					.then(() => {

						// --- Get your own camera stream with the desired properties ---

						let publisher = this.OV.initPublisher(undefined, {
							audioSource: undefined, // The source of audio. If undefined default microphone
							videoSource: undefined, // The source of video. If undefined default webcam
							publishAudio: true,  	// Whether you want to start publishing with your audio unmuted or not
							publishVideo: true,  	// Whether you want to start publishing with your video enabled or not
							resolution: '640x480',  // The resolution of your video
							frameRate: 30,			// The frame rate of your video
							insertMode: 'APPEND',	// How the video is inserted in the target element 'video-container'
							mirror: false       	// Whether to mirror your local video or not
						});

						this.mainStreamManager = publisher;
						this.publisher = publisher;

						// --- Publish your stream ---

						this.session.publish(this.publisher);
					})
					.catch(error => {
						console.log('There was an error connecting to the session:', error.code, error.message);
					});
			});

			window.addEventListener('beforeunload', this.leaveSession)
		},

		leaveSession () {
			// --- Leave the session by calling 'disconnect' method over the Session object ---
			if (this.session) this.session.disconnect();

			this.session = undefined;
			this.mainStreamManager = undefined;
			this.publisher = undefined;
			this.subscribers = [];
			this.OV = undefined;

			window.removeEventListener('beforeunload', this.leaveSession);
		},

		updateMainVideoStreamManager (stream) {
			if (this.mainStreamManager === stream) return;
			this.mainStreamManager = stream;
		},

		/**
		 * --------------------------
		 * SERVER-SIDE RESPONSIBILITY
		 * --------------------------
		 * These methods retrieve the mandatory user token from OpenVidu Server.
		 * This behavior MUST BE IN YOUR SERVER-SIDE IN PRODUCTION (by using
		 * the API REST, openvidu-java-client or openvidu-node-client):
		 *   1) Initialize a Session in OpenVidu Server	(POST /openvidu/api/sessions)
		 *   2) Create a Connection in OpenVidu Server (POST /openvidu/api/sessions/<SESSION_ID>/connection)
		 *   3) The Connection.token must be consumed in Session.connect() method
		 */

		getToken (mySessionId) {
			return this.createSession(mySessionId).then(sessionId => this.createToken(sessionId));
		},

		// See https://docs.openvidu.io/en/stable/reference-docs/REST-API/#post-openviduapisessions
		createSession (sessionId) {
			return new Promise((resolve, reject) => {
				axios
					.post(`${OPENVIDU_SERVER_URL}/openvidu/api/sessions`, JSON.stringify({
						customSessionId: sessionId,
					}), {
						auth: {
							username: 'OPENVIDUAPP',
							password: OPENVIDU_SERVER_SECRET,
						},
					})
					.then(response => response.data)
					.then(data => resolve(data.id))
					.catch(error => {
						if (error.response.status === 409) {
							resolve(sessionId);
						} else {
							console.warn(`No connection to OpenVidu Server. This may be a certificate error at ${OPENVIDU_SERVER_URL}`);
							if (window.confirm(`No connection to OpenVidu Server. This may be a certificate error at ${OPENVIDU_SERVER_URL}\n\nClick OK to navigate and accept it. If no certificate warning is shown, then check that your OpenVidu Server is up and running at "${OPENVIDU_SERVER_URL}"`)) {
								location.assign(`${OPENVIDU_SERVER_URL}/accept-certificate`);
							}
							reject(error.response);
						}
					});
			});
		},

		// See https://docs.openvidu.io/en/stable/reference-docs/REST-API/#post-openviduapisessionsltsession_idgtconnection
		createToken (sessionId) {
			return new Promise((resolve, reject) => {
				axios
					.post(`${OPENVIDU_SERVER_URL}/openvidu/api/sessions/${sessionId}/connection`, {}, {
						auth: {
							username: 'OPENVIDUAPP',
							password: OPENVIDU_SERVER_SECRET,
						},
					})
					.then(response => response.data)
					.then(data => resolve(data.token))
					.catch(error => reject(error.response));
			});
		},
	}
}
</script>
```

![rtc.PNG](./rtc.PNG)




# 4주차


# 01/30 (월)
- erd 완성 및 적용
![erd_finish.png](./erd_finish.png)


# 01/31 (화)
- entity 작성 
```
package com.b304.bobs.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="user")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @Column(name="user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String user_id;

    @Column(name="user_name")
    private String user_name;
    @Column(name="user_profile")
    private String user_profile;
    @Column(name="user_status")
    private Boolean user_status;
}

```


# 02/01 (수)
- kakao 로그인 백엔드 구현 진행중

```
	@GetMapping("/login/{code}")
    @ApiOperation(value = "카카오 로그인 토큰", response = String.class)
    public ResponseEntity<String> kakaoLogin(@PathVariable String code)
            throws ParseException, SQLException, NoSuchAlgorithmException {
        // REST API 동기 방식
        RestTemplate restTemplate = new RestTemplate();

        // HttpHeader 오브젝트 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // HttpBody 오브젝트 생성
        // 카카오 로그인 토큰 발급 과정
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", "발급받은 클라이언트 REST API 키 입력");
        params.add("redirect_uri", "http://localhost:8080/{URL}");
        params.add("code", code);
        params.add("client_secret", "...");

        HttpEntity<MultiValueMap<String, String>> kakaoRequest = new HttpEntity<MultiValueMap<String, String>>(params,
                headers);

        ResponseEntity<String> response = restTemplate.exchange("https://kauth.kakao.com/oauth/token", HttpMethod.POST,
                kakaoRequest, String.class);
        // JSON 파싱
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(response.getBody().toString());

        // 발급받은 토큰을 통해 사용자 조회
        HttpHeaders token_access = new HttpHeaders();
        String token = (String) jsonObject.get("access_token");
        token_access.add("Authorization", "Bearer " + token);
        token_access.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        HttpEntity<HttpHeaders> kakaoProfileRequest = new HttpEntity<>(token_access);

        ResponseEntity<String> profileResponse = restTemplate.exchange("https://kapi.kakao.com/v2/user/me",
                HttpMethod.POST, kakaoProfileRequest, String.class);

        // 사용자 정보 확인
        jsonObject = (JSONObject) parser.parse(profileResponse.getBody().toString());
        System.out.println("jsonObjct : " + jsonObject);
        HttpHeaders kakaoLoginHeaders = new HttpHeaders();
        String userid = String.valueOf(jsonObject.get("id"));
        kakaoLoginHeaders.add("userid", userid);
        jsonObject = (JSONObject) parser.parse(jsonObject.get("kakao_account").toString());
        jsonObject = (JSONObject) parser.parse(jsonObject.get("profile").toString());
        String name = (String) jsonObject.get("nickname");
        kakaoLoginHeaders.add("token", token);

        // 가입된 이력 여부
        if (kakaoService.checkMember(userid) > 0) {
            System.out.println("가입된 이력이 있습니다.");
        } else {
            System.out.println("가입된 이력이 없습니다.");
            if (kakaoService.registMember(userid, name) > 0) {
                System.out.println("가입 완료 되었습니다.");
            }

        }
        System.out.println(kakaoLoginHeaders.toString());
        return new ResponseEntity<String>("success", kakaoLoginHeaders, HttpStatus.OK);

    }
```
