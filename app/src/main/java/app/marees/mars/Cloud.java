package app.marees.mars;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

public class Cloud extends FragmentActivity {


    private ViewPager mypage;
    private MyPager mAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.network);

        // Initilization
        mypage = (ViewPager) findViewById(R.id.viewpager);

        mAdapter = new MyPager(getSupportFragmentManager());

        mypage.setAdapter(mAdapter);

    }

}
