package com.example.p501;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {
    ListView listView;
    LinearLayout container;
    ArrayList<Item> list;
    ItemAdapter itemAdapter;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        listView = findViewById(R.id.listView);
        container = findViewById(R.id.container);
        list = new ArrayList<>();
        progressDialog = new ProgressDialog(this);
        getData();

    }

    private void getData() {
        String url = "ip/webview/cafe.jsp";
        ItemAsync itemAsync = new ItemAsync(url);
        itemAsync.execute();
    }


    class ItemAsync extends AsyncTask<Void,Void,String>{
        String url;

        public ItemAsync(String url){
            this.url = url;
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
            String result = null;
            result = HttpHandler.getString(url);
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            Log.d("---",s.trim());

            progressDialog.dismiss();
            JSONArray ja = null;
            try {
                ja = new JSONArray(s);
                for(int i=0;i<ja.length();i++){
                    JSONObject jo = ja.getJSONObject(i);
                    String name = jo.getString("name");
                    double leti = jo.getDouble("leti");
                    double longti = jo.getDouble("longti");
                    int  star = jo.getInt("star");
                    String image = jo.getString("image");
                    String description = jo.getString("description");

                    Item item = new Item(image,name,star,leti,longti,description);
                    list.add(item);

                }



            } catch (JSONException e) {
                e.printStackTrace();
            }

            itemAdapter = new ItemAdapter(list);
            listView.setAdapter(itemAdapter);



        }
    }


    class ItemAdapter extends BaseAdapter{
        ArrayList<Item> alist;

        public ItemAdapter(ArrayList<Item> alist){
            this.alist = alist;
        }

        @Override
        public int getCount() {
            return alist.size();
        }

        @Override
        public Object getItem(int position) {
            return alist.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View itemView = null;
            LayoutInflater inflater = (LayoutInflater)
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            itemView = inflater.inflate(R.layout.item,container,true);

            TextView name = itemView.findViewById(R.id.textView);
            RatingBar star = itemView.findViewById(R.id.ratingBar4);
            final ImageView imageView = itemView.findViewById(R.id.imageView);


            name.setText(alist.get(position).getName());
            star.setRating(alist.get(position).getStar());
            String image = alist.get(position).getImage();

            image = "ip/webview/img/"+image;

            final String finalImg = image;

            name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                    intent.putExtra("name",alist.get(position).name);
                    intent.putExtra("leti",alist.get(position).leti);
                    intent.putExtra("longti",alist.get(position).longti);
                    intent.putExtra("star",alist.get(position).star);
                    intent.putExtra("image",alist.get(position).image);
                    intent.putExtra("description",alist.get(position).description);
                    startActivity(intent);
                }
            });

                                Thread t = new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                URL url = null;
                                //url 요청
                                InputStream is = null;
                                //요청하고 나서 서버 쪽으로 내려오는 통로
                                try{
                                    url = new URL(finalImg);
                                    is = url.openStream();
                                    final Bitmap bm = BitmapFactory.decodeStream(is);
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            imageView.setImageBitmap(bm);

                                        }
                                    });

                    }catch (Exception e){
                        e.printStackTrace();
                    }



                }
            });
            t.start();

            return itemView;
        }
    }
}
