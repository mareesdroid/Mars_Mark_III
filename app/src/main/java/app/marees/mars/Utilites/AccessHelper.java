package app.marees.mars.Utilites;

import android.app.Activity;
import android.content.Intent;
import android.media.projection.MediaProjectionManager;
import android.os.Bundle;

import app.marees.mars.ScreenshotAct;
import app.marees.mars.Singletons.Myapp;


public class AccessHelper extends Activity {

    MediaProjectionManager mediaProjectionManager;
    boolean test = true;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(null);
        if (test) {
            mediaProjectionManager = (MediaProjectionManager) Myapp.getAppContext().getSystemService(MEDIA_PROJECTION_SERVICE);
            startActivityForResult(mediaProjectionManager.createScreenCaptureIntent(), 1);

            test = false;
        }


    }

    @Override
    public void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (1 == requestCode) {
            if (Activity.RESULT_OK == resultCode) {
                Myapp.setScreenshotPermission((Intent) data.clone());
                ((Myapp) getApplication()).setMediaProjectionManager(mediaProjectionManager);
                ScreenshotAct t2 = new ScreenshotAct();
                t2.getShot();
            }
        } else if (Activity.RESULT_CANCELED == resultCode) {
            Myapp.setScreenshotPermission(null);


        }
        finish();
    }
}
