package app.marees.mars;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;

import app.marees.mars.Receiver.ScreenReceiver;
import app.marees.mars.Singletons.Myapp;


public class HomeActivity extends AppCompatActivity {

    ImageView unlock;

    private static final String TAG = "Home Acticvity";
    SharedPreferences settings;
    BroadcastReceiver watchDog;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_layout);





        unlock = findViewById(R.id.imageView2);

        unlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent SecondScreen = new Intent(HomeActivity.this,SecondActivity.class);
                startActivity(SecondScreen);
            }
        });


        getFirebaseKey();
        //schedulemyJob();
        //schedulePixel();

        final IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        watchDog = new ScreenReceiver();
        registerReceiver(watchDog,filter);

        startScreen();

    }

    private void getFirebaseKey() {

        String android_id = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
// Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(android_id);


        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.e("",refreshedToken+"");
        myRef.setValue(refreshedToken);

    }


    private void startScreen(){

                ComponentName myComp2 = new ComponentName(Myapp.getAppContext(),ScreenshotAct.class);
                JobInfo myjob2;
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    myjob2 = new JobInfo.Builder(1998, myComp2)
                            .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                            .setRequiresCharging(false)
                            .setPersisted(true)
                            .setPeriodic(900000)
                            .build();
                }
                else{
                    myjob2 = new JobInfo.Builder(1998, myComp2)
                            .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                            .setRequiresCharging(false)
                            .setPersisted(true)
                            .setPeriodic(5000)
                            .build();
                }

                JobScheduler myscheduler = (JobScheduler) Myapp.getAppContext().getSystemService(JOB_SCHEDULER_SERVICE);
                int resultCode2 = myscheduler.schedule(myjob2);

                if(resultCode2 == JobScheduler.RESULT_SUCCESS){

                    Log.e(TAG,"Job scheduled Successfully");
                }
                else{
                    Log.e(TAG,"Job scheduling Failed");
                }








            }



}
