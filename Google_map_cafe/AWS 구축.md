# AWS 구축



### 1. AWS 계정생성

https://aws.amazon.com/ko/



### 2. 인스턴스 생성

> 2-1. E2C검색 후 지역선택, 인스턴스 생성
>
> <img width="1208" alt="EC2" src="https://user-images.githubusercontent.com/36683607/74013636-494c8c00-49d0-11ea-812a-757f4e11aa41.png">
>
> 
>
> 2-2. AMI 선택
>
> <img width="1236" alt="EC2-2" src="https://user-images.githubusercontent.com/36683607/74013693-6d0fd200-49d0-11ea-888e-e4732f25f70a.png">
>
> 
>
> 2-3. 유형 선택
>
> <img width="1268" alt="EC2-3" src="https://user-images.githubusercontent.com/36683607/74013699-6ed99580-49d0-11ea-9e30-bd736a0fa96b.png">
>
> 2-4. 검토
>
> <img width="1274" alt="EC2-4" src="https://user-images.githubusercontent.com/36683607/74013700-6f722c00-49d0-11ea-9ded-72fd55f304ec.png">
>
> 2-5. KEY생성
>
> >  키 이름 설정 후 키 페어 다운로드
>
> <img width="524" alt="EC2-5" src="https://user-images.githubusercontent.com/36683607/74013702-6f722c00-49d0-11ea-9eb6-b9f61e2ba97a.png">
>
> 
>
> 2-6. 인스턴스 시작



### 3. IP

로컬 뿐 아니라 외부에서 접근하기 위해 탄력적 IP를 설정해준다.

>  3-1. 탄력적IP 주소 할당
>
> <img width="1251" alt="EC2-6" src="https://user-images.githubusercontent.com/36683607/74013703-700ac280-49d0-11ea-86ff-ff774c6fb144.png">
>
> 
>
> 3-2. 할당
>
> <img width="622" alt="EC2-7" src="https://user-images.githubusercontent.com/36683607/74013704-70a35900-49d0-11ea-8c54-63e321dc74ea.png">
>
> 
>
> 3.3 IP주소 연결
>
> <img width="1271" alt="EC2-8" src="https://user-images.githubusercontent.com/36683607/74013707-70a35900-49d0-11ea-91cf-89d91ba3329c.png">
>
> > 네트워크 인터페이스 선택
>
> <img width="626" alt="EC2-9" src="https://user-images.githubusercontent.com/36683607/74013709-713bef80-49d0-11ea-844d-1d1a4ccd5fe5.png">



### 4. 보안그룹 설정

모든 IP에서 접속 가능하도록 보안 규칙 추가

> 4-1. 인스턴스에서 보안그룹 클릭
>
> <img width="1136" alt="EC2-10" src="https://user-images.githubusercontent.com/36683607/74013710-71d48600-49d0-11ea-9111-22e6e97ee340.png">
>
> 
>
> 4-2. 인바운드 창의 편집 클릭
>
> <img width="947" alt="EC2-11" src="https://user-images.githubusercontent.com/36683607/74013711-726d1c80-49d0-11ea-98b8-47799de1f768.png">
>
> 
>
> 4-3. 규칙 추가
>
> > 인바운드 규칙에 외부 접속이 가능하도록 추가해주어야 인스턴스에 접근이 가능하다.
>
> <img width="844" alt="EC2-12" src="https://user-images.githubusercontent.com/36683607/74013712-7305b300-49d0-11ea-83d6-fc9b05244230.png">

