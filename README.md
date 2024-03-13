# 🎮 LegendsOfLeague

![image](https://github.com/deveLOLment/LegendsOfLeague/assets/87007010/ceebf443-48e8-4aae-ba03-0b73e0c3f3a1)

## 프로젝트 링크
API 스웨거 링크: https://15.164.169.174.nip.io/swagger-ui/index.html#/<br>
배포 사이트: https://develolment.site/

## 서비스 설명
Legends of League 프로젝트는 리그 오브 레전드(LoL)과 관련된 종합 커뮤니트 사이트입니다.
이 프로젝트는 LoL 시즌 게임의 요약 결과를 제공하고, 게임에 참가한 프로게이머(플레이어)들에 대한 투표 기능을 포함하고 있습니다.
또한, LoL과 관련된 상품을 구매할 수 있는 쇼핑몰과, 간단한 채팅 기능도 지원합니다.

### 주요 기능

- 📊 **시즌 게임 결과 요약**: 시즌별 게임 결과를 신속하게 요약해서 보여줍니다.
- ✅ **플레이어 투표 시스템**: 게임에 참가한 플레이어들의 성과에 대해 커뮤니티 투표를 진행합니다.
- 🛒 **LoL 관련 상품 쇼핑몰 운영**: LoL 캐릭터 상품, 아이템 등 다양한 상품을 판매합니다.
- 🗨 **간단한 채팅 기능 제공**: 사이트 사용자들이 실시간으로 의견을 교환할 수 있는 채팅방을 제공합니다.

## ⚙️ 기술 스택
- Backend: `Java 17`, `Spring Boot 3.2.1`, `Spring Data JPA`, `Spring Security 6.2.1`, `QueryDsl 5`, `WebSocket`, `STOMP`, `Python`
- Frontend: `React`, `TypeScript`, `BootStrap`
- DB: `MySql`, `Redis`
- Server: `AWS EC2`, `AWS S3`, `AWS RDS`, `AWS CloudFront`, `AWS Route 53`
- Tools: `IntelliJ`, `VSCode`, `PyCharm`
- Collaborations: `Git`, `GitHub Projects`, `Discord`, `Notion`

## 🤝 협업 전략
코드 컨벤션, 깃 커밋 메시지 컨벤션, 브랜치 관리 전략 정리

- [AngularJS Git Commit Message Convention](https://gist.github.com/stephenparish/9941e89d80e2bc58a153)을 준수한다.
- Squash Merge Pull Request만을 사용한다.
- 두 사람 이상의 Approve가 있어야만 PR을 Merge할 수 있다.

#### 브랜치 전략
<image src="https://github.com/deveLOLment/LegendsOfLeague/assets/94341266/13651048-bde3-473c-accf-31f4cb84b35b" width="60%"></image>

- GitHub flow 전략을 사용한다.
   - main (배포서버): 배포시 사용합니다.
   - develop (개발서버): 완전히 개발이 끝난 부분에 대해서만 Merge를 진행합니다.

## 🗓️ 개발 기간
2024-02-05 ~ 2024-03-12

## 🛠 기술적 issue 해결 과정
### 김재현: 쇼핑몰 / 프로젝트 배포
[효율적인 주문 관리: 스케줄링으로 무한 쌓임 탈출하기](https://github.com/deveLOLment/LegendsOfLeague/wiki/%ED%9A%A8%EC%9C%A8%EC%A0%81%EC%9D%B8-%EC%A3%BC%EB%AC%B8-%EA%B4%80%EB%A6%AC:-%EC%8A%A4%EC%BC%80%EC%A4%84%EB%A7%81%EC%9C%BC%EB%A1%9C-%EB%AC%B4%ED%95%9C-%EC%8C%93%EC%9E%84-%ED%83%88%EC%B6%9C%ED%95%98%EA%B8%B0)


[주문 목록 조회 N 플러스 1 문제 해결: QueryDsl, fetch join 활용](https://github.com/deveLOLment/LegendsOfLeague/wiki/%EC%A3%BC%EB%AC%B8-%EB%AA%A9%EB%A1%9D-%EC%A1%B0%ED%9A%8C-N-%ED%94%8C%EB%9F%AC%EC%8A%A4-1-%EB%AC%B8%EC%A0%9C-%ED%95%B4%EA%B2%B0:-QueryDsl,-fetch-join-%ED%99%9C%EC%9A%A9)

### 염금성: 인증 / 인가

### 우성범: 평점
[DB 구축](https://github.com/deveLOLment/LegendsOfLeague/wiki/DB-%EA%B5%AC%EC%B6%95)

### 이비안: 채팅

### 임현우: 주문 / 결제 
[상품구매 시 재고 관리 로직 ‐ MySQL Named Lock으로 동시성 문제 해결](https://github.com/deveLOLment/LegendsOfLeague/wiki/%EC%83%81%ED%92%88%EA%B5%AC%EB%A7%A4-%EC%8B%9C-%EC%9E%AC%EA%B3%A0-%EA%B4%80%EB%A6%AC-%EB%A1%9C%EC%A7%81-%E2%80%90--MySQL-Named-Lock%EC%9C%BC%EB%A1%9C-%EB%8F%99%EC%8B%9C%EC%84%B1-%EB%AC%B8%EC%A0%9C-%ED%95%B4%EA%B2%B0)


[수량제한이 있는 선착순 쿠폰 발급 로직 ‐ Redis로 동시성 문제 해결](https://github.com/deveLOLment/LegendsOfLeague/wiki/%EC%88%98%EB%9F%89%EC%A0%9C%ED%95%9C%EC%9D%B4-%EC%9E%88%EB%8A%94-%EC%84%A0%EC%B0%A9%EC%88%9C-%EC%BF%A0%ED%8F%B0-%EB%B0%9C%EA%B8%89-%EB%A1%9C%EC%A7%81-%E2%80%90-Redis%EB%A1%9C-%EB%8F%99%EC%8B%9C%EC%84%B1-%EB%AC%B8%EC%A0%9C-%ED%95%B4%EA%B2%B0)


[중복 인스턴스 생성 방지: 싱글톤 예외 관리 전략](https://github.com/deveLOLment/LegendsOfLeague/wiki/%EC%A4%91%EB%B3%B5-%EC%9D%B8%EC%8A%A4%ED%84%B4%EC%8A%A4-%EC%83%9D%EC%84%B1-%EB%B0%A9%EC%A7%80:-%EC%8B%B1%EA%B8%80%ED%86%A4-%EC%98%88%EC%99%B8-%EA%B4%80%EB%A6%AC-%EC%A0%84%EB%9E%B5)

## 💾 ERD
![2024-03-12_164810](https://github.com/deveLOLment/LegendsOfLeague/assets/108642772/5308ae80-6be1-440f-8afd-842a1931e135)


## 🖼️ 주요 화면

## 👨‍💻 팀원 소개
<table align=center>
    <thead>
        <tr>
            <th style="text-align:center;" >김재현</th>
            <th style="text-align:center;" >염금성</th>
            <th style="text-align:center;" >우성범</th>
            <th style="text-align:center;" >이비안</th>
            <th style="text-align:center;" >임현우</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td><img width="200" src="https://avatars.githubusercontent.com/u/108642772?s=400&u=762366629894935e0e1809dcb1cefeedcf3e4a4d&v=4" /> </td>
            <td><img width="200" src="https://avatars.githubusercontent.com/u/102720261?v=4" /></td>
            <td><img width="200" src="https://avatars.githubusercontent.com/u/94341266?v=4" /> </td>
            <td><img width="200" src="https://avatars.githubusercontent.com/u/87007010?v=4" /> </td>
            <td><img width="200" src="https://avatars.githubusercontent.com/u/97041290?v=4" /> </td>
        </tr>
        <tr>
            <td style="text-align:center;"><a href="https://github.com/Kim-Jaehyun0328">@JaeniorDeveloper</a></td>
            <td style="text-align:center;"><a href="https://github.com/Venus1234567">@venus01</a></td>
            <td style="text-align:center;"><a href="https://github.com/sbwoo96a">@sbwoo96a</a></td>
            <td style="text-align:center;"><a href="https://github.com/gumgu">@gumgu</a></td>
            <td style="text-align:center;"><a href="https://github.com/hyunwoo0318">@hyunwoo0318</a></td>
        </tr>
        <tr>
            <td width="200">같이 일하고 싶은 개발자가 되자</td>
            <td width="200">도전과 성장을 통해 발전하는 개발자</td>
            <td width="200">어쩌다 보니 백엔드 개발자</td>
            <td width="200"></td>
            <td width="200">항상 꼼꼼하고 행복하게 코딩하자~</td>
        </tr>
    </tbody>
</table>
