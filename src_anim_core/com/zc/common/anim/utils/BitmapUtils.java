package com.zc.common.anim.utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * 图片通用处理
 * @author zc
 *
 */
public class BitmapUtils {

	/**
	 * 从资源中获取图片
	 * @param res
	 * @param resId
	 * @return
	 */
	public static Bitmap decodeResource(Resources res, int resId){
		return decodeResource(res, resId, -1, -1);
	}
	
	/**
	 * 从资源中获取图片
	 * @param res
	 * @param resId
	 * @param reqWidth
	 * @param reqHeight
	 * @return
	 */
	public static Bitmap decodeResource(Resources res, int resId, int reqWidth, int reqHeight) {
		try {
			if(res == null)
				return null;
			
			final BitmapFactory.Options options = new BitmapFactory.Options();
			options.inJustDecodeBounds = true;
			BitmapFactory.decodeResource(res, resId, options);

			options.inPreferredConfig = Bitmap.Config.ARGB_8888;
			//缩放保证内存开销
			options.inSampleSize = calculateInSampleSize(options, res.getDisplayMetrics().widthPixels, 
					res.getDisplayMetrics().heightPixels, reqWidth, reqHeight);
			//根据分辨率缩放
//			int density = res.getDisplayMetrics().densityDpi;
//			switch(density){
//			case DisplayMetrics.DENSITY_HIGH:
//				options.inSampleSize = 1;
//				break;
//			case DisplayMetrics.DENSITY_LOW:
//				options.inSampleSize = 2;
//				break;
//			case DisplayMetrics.DENSITY_MEDIUM:
//				options.inSampleSize = 2;
//				break;
//			case DisplayMetrics.DENSITY_TV:
//				options.inSampleSize = 1;
//				break;
//			case DisplayMetrics.DENSITY_XHIGH:
//				options.inSampleSize = 1;
//				break;
//			case DisplayMetrics.DENSITY_XXHIGH:
//				options.inSampleSize = 1;
//				break;
//			}
			options.inJustDecodeBounds = false;
			
			return BitmapFactory.decodeResource(res, resId, options);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	/**
	 * 获得缩放比例
	 * @param options
	 * @param winWidth
	 * @param winHeight
	 * @param reqWidth
	 * @param reqHeight
	 * @return
	 */
	private static int calculateInSampleSize(BitmapFactory.Options options, int winWidth, 
			int winHeight, int reqWidth, int reqHeight) {
		if(reqWidth < 0 || reqHeight < 0){
			return 1;
		}
		final int height = options.outHeight;
		final int width = options.outWidth;
		
		int inSampleSize = 1;
		if (height > winHeight || width > winWidth) {
			float raito = height * 1f / width;
			reqWidth = winWidth;
			reqHeight = (int) (reqWidth * raito);
		}
		if (height > reqHeight || width > reqWidth) {
			final int heightRatio = Math.round((float) height / (float) reqHeight);
			final int widthRatio = Math.round((float) width / (float) reqWidth);
			inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
			
			//重新验证，保证内存开销较小
			final float totalPixels = width * height;
			final float totalReqPixelsCap = reqWidth * reqHeight * 2;
			if(totalReqPixelsCap <= 0) return 1;
			while (totalPixels / (inSampleSize * inSampleSize) > totalReqPixelsCap) {
				inSampleSize++;
			}
		}
		return inSampleSize;
	}
}
