

# FCM (Firebase Cloud Messaging) Push notification



1. 프로젝트 생성

   <img width="960" alt="p1" src="https://user-images.githubusercontent.com/36683607/76524165-f40f0900-64ac-11ea-8057-4be73fad2c14.PNG">

   <img width="960" alt="p2" src="https://user-images.githubusercontent.com/36683607/76524167-f4a79f80-64ac-11ea-82e3-bba4f9ee84b2.PNG">

   <img width="960" alt="p3" src="https://user-images.githubusercontent.com/36683607/76524168-f5403600-64ac-11ea-8938-7ba5805e7d83.PNG">



2. 안드로이드 앱 추가

   <img width="935" alt="a1" src="https://user-images.githubusercontent.com/36683607/76524116-e78ab080-64ac-11ea-9ebe-04f75f29b744.png">

   <img width="676" alt="a2" src="https://user-images.githubusercontent.com/36683607/76524121-ea85a100-64ac-11ea-845b-dc20894160bd.png">

   <img width="676" alt="a3" src="https://user-images.githubusercontent.com/36683607/76524122-ea85a100-64ac-11ea-8bfd-c1e31df5f076.PNG">

   <img width="959" alt="a4" src="https://user-images.githubusercontent.com/36683607/76524123-eb1e3780-64ac-11ea-931d-b64dbf193ced.png">

   <img width="960" alt="a5" src="https://user-images.githubusercontent.com/36683607/76524125-ebb6ce00-64ac-11ea-92e2-74739ea5f8e0.png">

   <img width="453" alt="a6" src="https://user-images.githubusercontent.com/36683607/76524126-ec4f6480-64ac-11ea-8306-480cf5a45a7a.png">

   <img width="400" alt="a7" src="https://user-images.githubusercontent.com/36683607/76524127-ece7fb00-64ac-11ea-9c9e-fad6a96936e1.png">

   

   ```bash
   classpath 'com.google.gms:google-services:4.3.3'
   ```

   <img width="1071" alt="a8" src="https://user-images.githubusercontent.com/36683607/76524130-ed809180-64ac-11ea-98b1-9f012adb4c4c.png">

   

   ```bash
   apply plugin: 'com.google.gms.google-services'
   
   implementation 'com.google.firebase:firebase-messaging:20.0.0'
   implementation 'com.google.firebase:firebase-core:17.2.0'
   ```

   <img width="1280" alt="a9" src="https://user-images.githubusercontent.com/36683607/76524134-eeb1be80-64ac-11ea-9fed-07cd90cf51d8.png">

   <img width="403" alt="a10" src="https://user-images.githubusercontent.com/36683607/76524135-eeb1be80-64ac-11ea-904d-3828741a44a7.png" style="zoom:74%;" />

   <img width="401" alt="a11" src="https://user-images.githubusercontent.com/36683607/76524139-ef4a5500-64ac-11ea-845c-da5ea58a58c1.PNG" style="zoom:74%;" />

   <img width="253" alt="a12" src="https://user-images.githubusercontent.com/36683607/76524142-efe2eb80-64ac-11ea-8921-4e2c46adf591.PNG">

   <img width="638" alt="a13" src="https://user-images.githubusercontent.com/36683607/76524145-f07b8200-64ac-11ea-8cd7-7ff23d2be099.png" style="zoom:80%;" />

   

   3. FCM 코드 추가

      AndroidManifest.xml

      ```XML
      <?xml version="1.0" encoding="utf-8"?>
      <manifest xmlns:android="http://schemas.android.com/apk/res/android"
          package="com.example.mul_2020">
      <uses-permission android:name="android.permission.INTERNET" />
      
          <application
              android:allowBackup="true"
              android:icon="@mipmap/ic_launcher"
              android:label="@string/app_name"
              android:roundIcon="@mipmap/ic_launcher_round"
              android:supportsRtl="true"
              android:theme="@style/AppTheme"
              android:usesCleartextTraffic="true">
      
              <activity android:name=".MainActivity">
                  <intent-filter>
                      <action android:name="android.intent.action.MAIN" />
      
                      <category android:name="android.intent.category.LAUNCHER" />
                  </intent-filter>
              </activity>
      
              <service android:name=".MyFirebaseMessagingService">
                  <intent-filter>
                      <action android:name="com.google.firebase.MESSAGING_EVENT" />
                  </intent-filter>
              </service>
      
          </application>
      
      </manifest>
      ```

      strings.xml

      ```xml
      <resources>
          <string name="app_name">firebase-fcmtest</string>
          <string name="msg_token_fmt" translatable="false">InstanceID Token : %s</string>
      </resources>
      ```

      MyFirebaseMessagingService.java

      ```java
      package com.example.firebase_fcmtest;
      
      import android.app.NotificationChannel;
      import android.app.NotificationManager;
      import android.os.Build;
      import android.util.Log;
      import androidx.annotation.NonNull;
      import androidx.core.app.NotificationCompat;
      import androidx.core.app.NotificationManagerCompat;
      import com.google.firebase.messaging.FirebaseMessagingService;
      import com.google.firebase.messaging.RemoteMessage;
      
      public class MyFirebaseMessagingService extends FirebaseMessagingService {
      
          String TAG = "===";
          String msg, title;
          NotificationManagerCompat notificationManager;
      
          @Override
          public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
      
              title = remoteMessage.getNotification().getTitle();
              msg = remoteMessage.getNotification().getBody();
      
              Log.d(TAG, "title : " + title + " msg : " + msg);
      
              String channelId = "channel";
              String channelName = "Channel_name";
              int importance = NotificationManager.IMPORTANCE_LOW;
              
              notificationManager = NotificationManagerCompat.from(this);
      
              if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                  NotificationChannel mChannel = new NotificationChannel(channelId, channelName, importance);
                  notificationManager.createNotificationChannel(mChannel);
              }
      
              NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, channelId)
                      .setSmallIcon(R.drawable.common_google_signin_btn_icon_dark)
                      .setContentTitle(title)
                      .setContentText(msg)
                      .setAutoCancel(true)
                      .setVibrate(new long[]{1, 1000});
      
              notificationManager.notify(0, mBuilder.build());
      
          }
      
      }
      
      ```

   4. 테스트

      <img width="535" alt="f1" src="https://user-images.githubusercontent.com/36683607/76524152-f1acaf00-64ac-11ea-831c-fe92d1f7b2d4.png">

      <img width="493" alt="f2" src="https://user-images.githubusercontent.com/36683607/76524154-f2454580-64ac-11ea-890c-bac1161dafa9.PNG">

      <img width="499" alt="f3" src="https://user-images.githubusercontent.com/36683607/76524156-f2454580-64ac-11ea-9527-f6df8ff497ad.PNG">

      <img width="356" alt="f4" src="https://user-images.githubusercontent.com/36683607/76524158-f2dddc00-64ac-11ea-9c57-05094a301b81.png">

   <img width="889" alt="f5" src="https://user-images.githubusercontent.com/36683607/76524163-f3767280-64ac-11ea-83ca-86fe8497829e.PNG">

