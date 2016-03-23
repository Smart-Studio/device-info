package com.smartstudio.deviceinfo.utils;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.smartstudio.deviceinfo.R;

public final class ViewUtils {

    /**
     * Displays a toast to notify the user that the web browser is not available on the device
     *
     * @param context Context The application's environment.
     * @param toast   Reference to the toast, if it's null the toast will be created otherwise it will
     *                be checked if is still displayed.
     **/
    public static Toast showNoBrowserToast(Context context, @Nullable Toast toast) {
        if (toast != null && toast.getView().isShown()) {
            //TODO Try to call toast.show again instead of creating a new one
            return toast;
        }

        String message = context.getString(R.string.error_no_browser);
        toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
        toast.show();
        return toast;
    }


    private ViewUtils() {

    }
}
