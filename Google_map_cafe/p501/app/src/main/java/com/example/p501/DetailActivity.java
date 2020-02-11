package com.example.p501;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

public class DetailActivity extends AppCompatActivity {


    ImageView imageView;
    TextView shop_name;
    RatingBar ratingBar;
    TextView description;
    GoogleMap gMap;
    SupportMapFragment supportMapFragment;

    String name;
    double leti;
    double longti;
    int star;
    String image;
    String desc;

    Bitmap bmImg;
    back task;

    ArrayList<Marker> array_marker = new ArrayList<Marker>();
    int i = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        imageView = findViewById(R.id.imageView2);
        shop_name = findViewById(R.id.shop_name);
        ratingBar = findViewById(R.id.ratingBar2);
        description = findViewById(R.id.description);

        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        leti = intent.getDoubleExtra("leti",0.0);
        longti = intent.getDoubleExtra("longti",0.0);
        star = intent.getIntExtra("star",1);
        image = intent.getStringExtra("image");
        desc = intent.getStringExtra("description");

        supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                gMap = googleMap;
                LatLng latLng = new LatLng(leti, longti); //최초 시작했을 때 지도의 위치
                gMap.addMarker(new MarkerOptions().position(latLng).title(name));

                gMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(final Marker marker) { //클릭했을 때 동작
                        Thread t = new Thread(new Runnable() {
                            public void run() {
                                while (i > 0) {
                                    final Random random = new Random();
                                    final float ran = ((float) (random.nextInt()*0.000000000003));
                                    final float rsn = ((float) (random.nextInt()*0.000000000002));
                                    runOnUiThread(new Runnable() {
                                        public void run() {
                                            LatLng latLng = new LatLng(leti+rsn, longti+ran);
                                            array_marker.add(gMap.addMarker(new MarkerOptions().position(latLng).title("coupon")));
                                            for(int i = 0; i < array_marker.size()-1; i++)
                                            {
                                                Marker tmp =  array_marker.get(i);
                                                tmp.setVisible(false);
                                            }
                                            gMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                                                @Override
                                                public boolean onMarkerClick(Marker marker) {
                                                    Toast.makeText(getApplicationContext(), "쿠폰 획득!!!",Toast.LENGTH_SHORT).show();
                                                    for(int i = 0; i < array_marker.size(); i++)
                                                    {
                                                        Marker tmp =  array_marker.get(i);
                                                        tmp.remove();
                                                    }
                                                    array_marker.clear();
                                                    i = 0;
                                                    return false;
                                                }
                                            });
                                        }
                                    });
                                    try {
                                        Thread.sleep(500);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    i--;
                                }
                            }
                        });
                        t.start();
                        return false;
                    }
                });
                gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15)); //숫자 클수록 줌이 가까워짐
            }
        });

        shop_name.setText(name);
        ratingBar.setRating(star);
        description.setText(desc);
        String img = "http://70.12.113.207:90/webview/img/"+image;
        task = new back();
        task.execute(img);
    }


    private class back extends AsyncTask<String, Integer, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... urls) {

            try{
                URL myFileUrl = new URL(urls[0]);
                HttpURLConnection conn = (HttpURLConnection)myFileUrl.openConnection();
                conn.setDoInput(true);
                conn.connect();
                InputStream is = conn.getInputStream();
                bmImg = BitmapFactory.decodeStream(is);
            }catch(IOException e){
                e.printStackTrace();
            }
            return bmImg;
        }

        protected void onPostExecute(Bitmap img){
            imageView.setImageBitmap(bmImg);
        }
    }

}
