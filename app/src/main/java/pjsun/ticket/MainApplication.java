package pjsun.ticket;

import android.support.multidex.MultiDexApplication;

import com.blankj.utilcode.utils.Utils;
import com.facebook.stetho.Stetho;

import org.litepal.LitePal;

/**
 * Created by sunpingji on 2016/12/22.
 */

public class MainApplication extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        LitePal.initialize(this);
        Stetho.initializeWithDefaults(this);
        Utils.init(this);
    }
}
