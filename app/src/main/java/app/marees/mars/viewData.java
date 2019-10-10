package app.marees.mars;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import app.marees.mars.Singletons.Singleton;

public class viewData extends AppCompatActivity {
    String zodiac;
    RequestQueue mqueue;
    String time,zodTam;
    TextView status,date,tamilzodi,zodi;
    String week,month,today;
    LinearLayout myconstraint;
    AnimationDrawable marsAnim;
    Button wk,day,mnth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.final_layout);
        time = "today";
        status = findViewById(R.id.textView38);
        tamilzodi= findViewById(R.id.textView40);
        myconstraint = findViewById(R.id.constraintx);
        marsAnim = (AnimationDrawable) myconstraint.getBackground();
        marsAnim.setEnterFadeDuration(2000);
        marsAnim.setExitFadeDuration(4000);
        marsAnim.start();
        zodi = findViewById(R.id.textView41);
        date = findViewById(R.id.textView39);
        wk=findViewById(R.id.button8);
        mnth=findViewById(R.id.button9);
        day=findViewById(R.id.button10);
        wk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time="week";
                findRasi(zodiac,time,zodTam);

            }
        });

        mnth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time="date";
                findRasi(zodiac,time,zodTam);
            }
        });
        day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time="month";
                findRasi(zodiac,time,zodTam);
            }
        });






        status.setText(today);
        week = "வார ராசி பலன் ";
        month = "மாத ராசி பலன் ";
        today = "இன்றைய ராசி பலன் ";

        mqueue = Singleton.getInstance(viewData.this).getRequestQueue();
        Intent goIntent = getIntent();
        zodiac = goIntent.getStringExtra("zodiac");
        zodTam = goIntent.getStringExtra("zoditam");
        findRasi(zodiac,time,zodTam);
    }

    private void findRasi(String zodiac,String time,String zodTam) {
        String url = "http://horoscope-api.herokuapp.com/horoscope/"+time+"/"+zodiac;
        JSONObject js = new JSONObject();


        JsonObjectRequest myRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String duration;
                    if(time.equals("today")){
                        status.setText(today);
                        duration = response.getString("date");
                    }
                    else if(time.equals("week")){
                        status.setText(week);
                      duration = response.getString("week");
                    }
                    else{
                        status.setText(month);
                        duration = response.getString("month");
                    }


                    String horoScope = response.getString("horoscope");

                    putData(duration,horoScope,zodTam);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


        mqueue.add(myRequest);
    }

    private void putData(String duration, String horoScope, String zodiacTam) {

        date.setText(duration);
        tamilzodi.setText(zodiacTam);

        zodi.setText(horoScope);

    }
}
