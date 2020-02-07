# AWS 연동



### 1. AWS 인스턴스 접속

> 1-1. 연결방법 확인
>
> ![](.\img\연결1.png)
>
> 
>
> 1-2. Git bash창에 명령어 입력
>
> > 아까 받았던 키가 있는 경로로 이동하여 입력
> >
> > ```bash
> > chmod 400 AWS_KEY.pem
> > ssh -i "AWS_KEY.pem" 퍼블릭 DNS(IPv4)
> > ```



### 2. 브라우저로 접속

> 2-1. 인스턴스 창에서 연결 클릭
>
> ![](.\img\연결2.png)
>
> 
>
> 2-2. 연결
>
> > 인스턴스 OS에 root계정으로 접속
>
> ![](.\img\연결3.png)
>
> 
>
> 2-4. 확인 
>
> > 브라우저 기반 연결 확인
>
> ![](.\img\연결4.png)



### 3. AWS 인스턴스 환경설정

> 3.1 자바 설치
>
> ```bash
> java -version // 자바 버전 확인
> sudo yum install -y java1.8.0-openjdk-devel.x86_64 //jdk1.8.0설치 
> 
> //만약 자바가 깔려있다면
> rm java //기존에 깔려있단 자바의 소프트링크 삭제
> whereis java //자바 경로 확인
> rm -rf /usr/bin/java //기존 자바 삭제
> ```
>
> 
>
> 3.2 Tomcat 설치
>
> >  기존에 yum에서 지원하는 버전은 8.0버전, 그래서
> >
> > 외부에서 다운 후 .pem 파일(키)과 같은 경로에 apache-tomcat9.0.tar.gz 파일을 저장
> >
> > 파일을 AWS 인스턴스에 저장(예전 hadoop에서 배웠던 scp commad 사용)
>
> Git bash에서 다음 명령어 실행
>
> ```bash
> scp -i "AWS_KEY.pem" apache-tomcat-9.0.30.tar.gz 퍼블릭 DNS(IPv4)
> ```
>
> > 퍼블릭 DNS(IPv4)는 인스턴스 연결창에서 확인 가능
> >
> > ![](.\img\연결5.png)
>
> 복사된 압축파일을 풀고 소프트링크 걸기
>
> > 인스턴스에 접속된 Git bash에서 다음 명령어 입력
> >
> > ```bash
> > tar xvfz apache-tomcat-9.0.30.tar.gz //압축파일 풀기
> >  ln -s apache-tomcat-9.0.30/bin/start.sh  starttomcat // 톰캣 시작 소프트링크 걸기
> >  ln -s apache-tomcat-9.0.30/bin/shutdown.sh  stoptomcat// 톰캣 종료 소프트링크 걸기
> >  
> >  ./starttomcat //톰캣 시작
> >  ./stoptomcat //톰캣 종료
> > ```
>
> apache-tomcat-9.0.30/webapp 아래에 넣고 톰캣 실행
>
> > Git bash에서 다음 명령어 실행
> >
> > ```bash
> > scp -i "AWS_KEY.pem" webview.WAR 퍼블릭 DNS(IPv4):~/apache-tomcat-9.0.30/webapps/
> > ```
> >
> > 톰캣 실행(인스턴스에 접속된 Git bash)
> >
> > ```bash
> > ./starttomcat
> > ```



### 4. 확인

웹브라우저에서 [IPv4 퍼블릭 IP:포트번호] 입력하여 톰캣이 도는지 확인

![](.\img\확인.png)













