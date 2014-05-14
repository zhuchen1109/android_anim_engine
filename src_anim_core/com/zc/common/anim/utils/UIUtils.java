package com.zc.common.anim.utils;

import android.content.Context;

public class UIUtils {

	public static int dp2px(Context context, float dpValue) {
		if(context == null){
			return 0;
		}
        float scale = context.getResources().getDisplayMetrics().density;  
        return (int) (dpValue * scale + 0.5f);  
    }
	
}
