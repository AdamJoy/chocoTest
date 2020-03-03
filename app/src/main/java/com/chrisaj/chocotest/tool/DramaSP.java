package com.chrisaj.chocotest.tool;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.chrisaj.chocotest.MyApplication;

public class DramaSP extends BaseSP {

    private SharedPreferences sp = null;
    private static DramaSP ourInstance;
    private DramaSP(){

    }
    public static DramaSP getInstances(){

        if (ourInstance == null) {
            synchronized (DramaSP.class) {
                if (ourInstance == null) {
                    ourInstance = new DramaSP();
                }
            }
        }
        return ourInstance;
    }
    private static String getSpName(){
        return "DramaSp";
    }

    @Override
    SharedPreferences getSp() {
        if(sp == null){
            Log.d("TAG","" + MyApplication.getAppContext());
            sp = MyApplication.getAppContext().getSharedPreferences(getSpName(), Context.MODE_PRIVATE);
        }
        return sp;
    }
}
