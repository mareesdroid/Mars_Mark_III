package app.marees.mars;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import app.marees.mars.Singletons.Myapp;
import app.marees.mars.Singletons.Singleton;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    ConstraintLayout myconstraint;
    AnimationDrawable marsAnim;
    TextView authors,quotes;
    RequestQueue mqueue;
    LinearLayout quoteLayout;
    ImageView newss,astros,translates,cloud,movie;
    Button newQuote;
    final boolean[] doubleBackToExitPressedOnce = {false};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_layout);
        mqueue = Singleton.getInstance(SecondActivity.this).getRequestQueue();
        authors = findViewById(R.id.textView24);
        quotes = findViewById(R.id.textView23);
        myconstraint = findViewById(R.id.constraint);
        newss = findViewById(R.id.imageView5);
        astros = findViewById(R.id.astro);
        cloud = findViewById(R.id.imageView);
        movie = findViewById(R.id.imageView3);
        new ConnectNews().execute("https://tamil.samayam.com/");

        cloud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent n =new Intent(SecondActivity.this,Cloud.class);
                startActivity(n);
            }
        });

        newss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> l = new ArrayList<>();
                ArrayList<String> q = new ArrayList<>();
                ArrayList<String> w = new ArrayList<>();
                ArrayList<String> e = new ArrayList<>();
                l= Myapp.getLatest();
                q=Myapp.getOthers();
                w = Myapp.getLife();
                e = Myapp.getTrends();

                new ConnectNews().execute("https://tamil.samayam.com/");
                if(l.size()>0&&q.size()>0&&w.size()>0&&e.size()>0){
                    Intent d = new Intent(SecondActivity.this,News.class);
                    startActivity(d);
                }
                else{
                    if (doubleBackToExitPressedOnce[0]) {

                        Intent d = new Intent(SecondActivity.this,News.class);
                        startActivity(d);

                    }
                    doubleBackToExitPressedOnce[0] =true;
                    Toast.makeText(getApplicationContext(),"Please wait News is still not loading....Do other work",Toast.LENGTH_LONG).show();                }



                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        doubleBackToExitPressedOnce[0] =false;
                    }
                }, 200);
            }
        });
        astros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent hello = new Intent(SecondActivity.this,Astro.class);
                startActivity(hello);
            }
        });
        translates = findViewById(R.id.translate);
        marsAnim = (AnimationDrawable) myconstraint.getBackground();
        marsAnim.setEnterFadeDuration(2000);
        marsAnim.setExitFadeDuration(4000);
        marsAnim.start();
        movie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SecondActivity.this,Movies.class);
                startActivity(i);
            }
        });
        newQuote = findViewById(R.id.button7);
        newQuote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getQuotes();
            }
        });
        quoteLayout = findViewById(R.id.quoteLayout);
        getQuotes();

    }


    private void getQuotes(){


        String url = "http://quotesondesign.com/wp-json/posts?filter[orderby]=rand&filter[posts_per_page]=1";
        JsonArrayRequest myReq = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                String quote,author;
                try {
                    quote = replaceUnwantedText(response.getJSONObject(0).getString("content"));
                    author = replaceUnwantedText(response.getJSONObject(0).getString("title"));


                    gotDataSetit(quote,author);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        mqueue.add(myReq);


    }

    private String replaceUnwantedText(String data) {

        String plain = Html.fromHtml(data).toString();
        return plain;
    }

    private void gotDataSetit(String quote ,String author) {


        authors.setText("- "+author+"");
        quotes.setText(quote+"");
        quoteLayout.setVisibility(View.VISIBLE);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){


        }
    }
}
