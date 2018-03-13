package com.twinly.enotices.enoticesapp.adapter;

import com.twinly.enotices.enoticesapp.protocol.CHILDREN;

/**
 * Created by huangjinbin on 2018/3/13.
 */

public interface OnPushStateChangeListener {
    public void onPushStateChange(CHILDREN children,boolean isPushed);
}
