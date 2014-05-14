package com.zc.common.anim.texture;


import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * 默认纹理
 * 处理图片展现
 * @author zc
 */
public class CTexture extends CBaseTexture {

	private Bitmap mBitmap;
	
	private CTexture(Bitmap bitmap){
		super();
		this.mBitmap = bitmap;
	}
	
	/**
	 * 创建Texture
	 * @param bitmap 纹理
	 * @return
	 */
	public static CTexture create(Bitmap bitmap){
		CTexture texture = new CTexture(bitmap);
		return texture;
	}
	
	@Override
	public void render(Canvas canvas) {
		super.render(canvas);
		if(mBitmap == null || mMatrix == null 
				|| mPaint == null || mBitmap.isRecycled())
			return;
		
		canvas.drawBitmap(mBitmap, mMatrix, mPaint);
	}

	/**
	 * 设置纹理
	 * @param bitmap
	 */
	public void setTexture(Bitmap bitmap){
		this.mBitmap = bitmap;
	}
	
	@Override
	public int getWidth(){
		if(mBitmap != null){
			return mBitmap.getWidth();
		}
		return 0;
	}

	@Override
	public int getHeight(){
		if(mBitmap != null){
			return mBitmap.getHeight();
		}
		return 0;
	}
	
	/**
	 * 获得纹理图片
	 * @return
	 */
	public Bitmap getTexture(){
		return mBitmap;
	}
}
