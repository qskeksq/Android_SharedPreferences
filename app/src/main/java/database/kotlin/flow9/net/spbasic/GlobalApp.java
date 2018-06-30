package database.kotlin.flow9.net.spbasic;

import android.app.Application;
import android.content.Context;

public class GlobalApp extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    public static Context getContext() {
        return mContext;
    }
}
