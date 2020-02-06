# Andoird 

##  - 구글 지도 연동하기



### 1. Google API KEY값 받기

1. console.developers.google.com 접속

2. 새 사용자 인증 정보 만들기 → API키

3. 키값 정보 세팅

   ![getapi](https://user-images.githubusercontent.com/36683607/73918509-1d180900-4905-11ea-9556-a0cc2dda0381.png)

4. Android 앱의 사용량 제한 → 항목추가

5. 

   ![getapi2](https://user-images.githubusercontent.com/36683607/73918520-20ab9000-4905-11ea-8d67-02998f7971d9.PNG)

   

> 패키지 이름 - app > manifests > AndroidManifest.xml 에서 패키지명 확인
>
> SHA-1 인증서 
>
> > Windows 디지털 지문 복사
> >
> > ![getapi3](https://user-images.githubusercontent.com/36683607/73918525-230dea00-4905-11ea-8a51-40b8dc52050c.png)
> >
> > cmd → jdk1.8.0_231 > bin 폴더로 이동 후 복사한 값 붙여넣기
> >
> > SHA1값 확인

6. 저장 후 KEY 값 얻기



### 2. Android Studio

1. Create New Project → Google Maps Activity

2. app > res > values > google_maps_api.xml

   YOUR_KEY_HERE → 키 값 복사

   ```java
   <string name="google_maps_key" templateMergeStrategy="preserve" translatable="false">YOUR_KEY_HERE</string>
   ```

3. app > res > layout > activity_maps.xml

   Palette → fragment 추가

4. 실행

   ![map](https://user-images.githubusercontent.com/36683607/73918534-25704400-4905-11ea-81b5-45271da77820.PNG)

 
