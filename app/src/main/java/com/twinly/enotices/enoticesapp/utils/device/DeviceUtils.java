package com.twinly.enotices.enoticesapp.utils.device;

import android.content.Context;

/**
 * Created by jbhuang on 2/8/2018.
 */

public class DeviceUtils {
    /**
     * 根据手机分辨率从DP转成PX
     * @param context
     * @param dpValue
     * @return
     */
    public static int dip2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
