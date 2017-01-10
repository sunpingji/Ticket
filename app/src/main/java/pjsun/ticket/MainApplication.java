package pjsun.ticket;

import android.app.Application;
import android.support.multidex.MultiDexApplication;

import com.facebook.stetho.Stetho;

import org.litepal.LitePal;

/**
 * Created by sunpi on 2016/12/22.
 */

public class MainApplication extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        LitePal.initialize(this);
        Stetho.initializeWithDefaults(this);
    }
}
