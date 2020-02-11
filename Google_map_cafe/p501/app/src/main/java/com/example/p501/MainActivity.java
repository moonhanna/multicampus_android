package com.example.p501;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.PermissionChecker;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {
    EditText editText,editText2;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editText);
        editText2 = findViewById(R.id.editText2);
        progressDialog = new ProgressDialog(this);
        String[] permissions = {
                Manifest.permission.ACCESS_NETWORK_STATE
        };
        ActivityCompat.requestPermissions(
                this,permissions,101);

        int check =
                PermissionChecker.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_NETWORK_STATE
                );


    }
    public void login(View v){
        String id = editText.getText().toString();
        String pwd = editText2.getText().toString();
        HttpTask task = new HttpTask(id,pwd);
        task.execute();
    }

    class HttpTask extends AsyncTask<Void,Void,String>{

        String url;


        public HttpTask(String id, String pwd){
            url = "ip/webview/login.jsp?";
            url += "id="+id+"&pwd="+pwd;
        }

        @Override
        protected void onPreExecute() {
            progressDialog.setTitle("HTTP Connect ..");
            progressDialog.setMessage("Please Wait..");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected String doInBackground(Void... voids) {
            return HttpHandler.getString(url);
        }

        @Override
        protected void onPostExecute(String s) {
            Log.d("---",s.trim());
            if(s.trim().equals("1")){
                Intent intent = new Intent(
                        getApplicationContext(),SecondActivity.class
                );
                startActivity(intent);
            }
            progressDialog.dismiss();
        }
    }

}
