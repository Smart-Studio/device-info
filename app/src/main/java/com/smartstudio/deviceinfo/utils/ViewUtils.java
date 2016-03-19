package com.smartstudio.deviceinfo.utils;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.smartstudio.deviceinfo.R;

public final class ViewUtils {
    public static Toast showNoBrowserToast(Context context, @Nullable Toast toast){
        if (toast != null && toast.getView().isShown()) {
            return toast;
        }

        String message = context.getString(R.string.error_no_browser);
        toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
        toast.show();
        return toast;
    }


    private ViewUtils(){

    }
}
