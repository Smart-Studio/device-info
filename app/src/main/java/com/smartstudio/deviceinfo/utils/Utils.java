/*
 * Copyright 2016 Smart Studio.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.smartstudio.deviceinfo.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.net.Uri;

import com.smartstudio.deviceinfo.exceptions.BrowserNotFoundException;

import java.util.List;

public final class Utils {
    public static int pxToDp(int pixels) {
        return Math.round(pixels / Resources.getSystem().getDisplayMetrics().density);
    }

    public static void openUrl(Context context, String url) throws BrowserNotFoundException {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        if (!isIntentAvailable(context, intent)) {
            throw new BrowserNotFoundException();
        }

        context.startActivity(intent);
    }

    /**
     * Indicates whether the specified action can be used as an intent. This
     * method queries the package manager for installed packages that can
     * respond to an intent with the specified action. If no suitable package is
     * found, this method returns false.
     *
     * @param context The application's environment.
     * @param intent  The Intent to check for availability.
     * @return True if an Intent with the specified action can be sent and
     * responded to, false otherwise.
     */
    public static boolean isIntentAvailable(Context context, Intent intent) {
        final PackageManager packageManager = context.getPackageManager();
        List<ResolveInfo> list =
                packageManager.queryIntentActivities(intent,
                        PackageManager.MATCH_DEFAULT_ONLY);
        return !list.isEmpty();
    }

    private Utils() {

    }
}
