package app.marees.mars;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Astro extends AppCompatActivity implements View.OnClickListener{


    ImageView aries,taurus,gemini,cancer,leo,virgo,libra,scorpio,sagittarius,capricorn,aquarius,pisces;
    String zodiac="";
    String zodTam="";
    ConstraintLayout myconstraint;
    AnimationDrawable marsAnim;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.astor);
        aries = findViewById(R.id.imageView7);
        taurus = findViewById(R.id.imageView6);
        gemini = findViewById(R.id.imageView9);
        cancer = findViewById(R.id.imageView8);
        myconstraint = findViewById(R.id.constraintw);
        marsAnim = (AnimationDrawable) myconstraint.getBackground();
        marsAnim.setEnterFadeDuration(2000);
        marsAnim.setExitFadeDuration(4000);
        marsAnim.start();
        leo = findViewById(R.id.imageView10);
        virgo = findViewById(R.id.imageView11);
        libra = findViewById(R.id.imageView12);
        scorpio = findViewById(R.id.imageView13);
        sagittarius = findViewById(R.id.imageView14);
        capricorn = findViewById(R.id.imageView15);
        aquarius = findViewById(R.id.imageView16);
        pisces = findViewById(R.id.imageView17);

        leo.setOnClickListener(this);
        virgo.setOnClickListener(this);
        libra.setOnClickListener(this);
        scorpio.setOnClickListener(this);
        sagittarius.setOnClickListener(this);
        capricorn.setOnClickListener(this);
        aquarius.setOnClickListener(this);
        pisces.setOnClickListener(this);
        aries.setOnClickListener(this);
        taurus.setOnClickListener(this);
        gemini.setOnClickListener(this);
        cancer.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {


        switch (v.getId()){

            case R.id.imageView7:
                zodiac="aries";
                zodTam="மேஷம்";
                getZodiac(zodiac,zodTam);
                break;

            case R.id.imageView6:
                zodiac="taurus";
                zodTam="ரிஷபம்";
                getZodiac(zodiac,zodTam);
                break;
            case R.id.imageView9:
                zodiac="gemini";
                zodTam="மிதுனம்";
                getZodiac(zodiac,zodTam);
                break;
            case R.id.imageView8:
                zodiac="cancer";
                zodTam="கடகம்";
                getZodiac(zodiac,zodTam);
                break;
            case R.id.imageView10:
                zodiac="leo";
                zodTam="சிம்மம்";
                getZodiac(zodiac,zodTam);
                break;
            case R.id.imageView11:
                zodiac="virgo";
                zodTam="கன்னி";
                getZodiac(zodiac,zodTam);
                break;
            case R.id.imageView12:
                zodiac="libra";
                zodTam="துலாம்";
                getZodiac(zodiac,zodTam);
                break;
            case R.id.imageView13:
                zodiac="scorpio";
                zodTam="விருச்சிகம்";
                getZodiac(zodiac,zodTam);
                break;
            case R.id.imageView14:
                zodiac="sagittarius";
                zodTam="தனுசு";
                getZodiac(zodiac,zodTam);
                break;
            case R.id.imageView15:
                zodiac="capricorn";
                zodTam="மகரம்";
                getZodiac(zodiac,zodTam);
                break;
            case R.id.imageView16:
                zodiac="aquarius";
                zodTam="கும்பம்";
                getZodiac(zodiac,zodTam);
                break;
            case R.id.imageView17:
                zodiac="pisces";
                zodTam="மீனம்";
                getZodiac(zodiac,zodTam);
                break;


        }

    }

    private void getZodiac(String zodiac,String zodTam) {


        Intent goIntent = new Intent(Astro.this,viewData.class);
        goIntent.putExtra("zodiac",zodiac);
        goIntent.putExtra("zoditam",zodTam);
        startActivity(goIntent);



    }
}
