package com.nopossible.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class IntentUtil {

    public IntentUtil() {
    }

    public static void startActivity(Context context,Class c){
        Intent intent = new Intent(context,c);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public static void startActivity(Activity activity, Class c){
        Intent intent = new Intent(activity,c);
        activity.startActivity(intent);
    }

    public static void startActivity(Context context,Class c,Bundle bundle){
        Intent intent = new Intent(context,c);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
    public static void startActivity(Activity activity, Class c, Bundle bundle){
        Intent intent = new Intent(activity,c);
        intent.putExtras(bundle);
        activity.startActivity(intent);
    }


}
