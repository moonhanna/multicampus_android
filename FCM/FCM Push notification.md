

# FCM (Firebase Cloud Messaging) Push notification



1. 프로젝트 생성

   ![](.\img\p1.PNG)

   ![](.\img\p2.PNG)

   ![](.\img\p3.PNG)



2. 안드로이드 앱 추가

   ![](.\img\a1.png)

   ![](.\img\a2.png)

   ![](.\img\a3.PNG)

   ![](.\img\a4.png)

   ![](.\img\a5.png)

   <img src=".\img\a6.png" style="zoom:67%;" />

   <img src=".\img\a7.png" style="zoom:75%;" />

   

   ```bash
   classpath 'com.google.gms:google-services:4.3.3'
   ```

   ![](.\img\a8.png)

   

   ```bash
   apply plugin: 'com.google.gms.google-services'
   
   implementation 'com.google.firebase:firebase-messaging:20.0.0'
   implementation 'com.google.firebase:firebase-core:17.2.0'
   ```

   ![](.\img\a9.png)

   <img src=".\img\a10.png" style="zoom:74%;" />

   <img src=".\img\a11.png" style="zoom:74%;" />

   ![](.\img\a12.PNG)

   <img src=".\img\a13.png" style="zoom:80%;" />

   

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

      ![](.\img\f1.png)

      ![](.\img\f2.PNG)

      ![](.\img\f3.PNG)

      ![](.\img\f4.png)

   ![](.\img\f5.PNG)

