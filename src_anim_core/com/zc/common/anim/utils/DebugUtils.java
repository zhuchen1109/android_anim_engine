package com.zc.common.anim.utils;

import android.util.Log;

public class DebugUtils {

	public static void debug(String tag, String msg){
		if(!"zc".equals(tag)){
			return;
		}
		Log.v(tag, msg);
	}
	
}
