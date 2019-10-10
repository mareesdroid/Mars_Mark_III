package app.marees;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import app.marees.mars.NewsFrag.Latest;
import app.marees.mars.NewsFrag.Others;
import app.marees.mars.NewsFrag.Stories;
import app.marees.mars.NewsFrag.Today;
import app.marees.mars.NotificationFrag;

public class NewsPager extends FragmentPagerAdapter {
    public NewsPager(FragmentManager supportFragmentManager) {
        super(supportFragmentManager);
    }

    @Override
    public Fragment getItem(int i) {

        switch (i){

            case 0:
                return new Latest();

            case 1:
                return new Others();
            case 2:
                // Games fragment activity
                return new Stories();

            case 3:
                // Games fragment activity
                return new Today();


        }

        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }
}
