package com.zc.common.anim.texture;

import android.graphics.Matrix;
import android.graphics.Paint;

import com.zc.common.anim.sprite.CNode;

/**
 * 基础纹理
 * @author zc
 */
public class CBaseTexture extends CNode {

	Matrix mMatrix;
	Paint mPaint;
	
	int mAlpha = 255;
	float mScaleX = 1;
	float mScaleY = 1;
	float mAnchorX = 0;
	float mAnchorY = 0;
	float mDegrees = 0;
	float mSkewX = 0f;
	float mSkewY = 0f;
//	int mX = 0;
//	int mY = 0;
	
	public CBaseTexture(){
		initTexture();
	}
	
	/**
	 * 初始化Texture
	 */
	private void initTexture(){
		mMatrix = new Matrix();
		mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
	}
	
	/**
	 * 设置alpha
	 * @param alpha
	 */
	public void setAlpha(int alpha){
		this.mAlpha = alpha;
		invalidate();
	}
	
	/**
	 * 获得透明度
	 * @return
	 */
	public int getAlpha(){
		return mAlpha;
	}
	
	/**
	 * 缩放图片
	 * @param sx
	 * @param sy
	 */
	public void setScale(float sx, float sy){
		this.mScaleX = sx;
		this.mScaleY = sy;
		invalidate();
	}
	
	/**
	 * 设置锚点
	 * @param x
	 * @param y
	 */
	public void setAnchor(int x, int y){
		this.mAnchorX = x;
		this.mAnchorY = y;
		invalidate();
	}
	
	/**
	 * 设置倾斜角度
	 * @param skewX
	 * @param skewY
	 */
	public void setSkew(float skewX, float skewY){
		this.mSkewX = skewX;
		this.mSkewY = skewY;
	}
	
	/**
	 * 旋转
	 * @param degrees
	 */
	public void rotate(float degrees){
		this.mDegrees = degrees;
		invalidate();
	}
	
	/**
	 * 设置位置
	 * @param x
	 * @param y
	 */
//	public void setPosition(int x, int y){
//		this.mX = x;
//		this.mY = y;
//		invalidate();
//	}
	
	/**
	 * 更新数据
	 */
	private void invalidate(){
		if(mPaint == null)
			mPaint = new Paint();
		mPaint.reset();
		mPaint.setAlpha(mAlpha);
		
		if(mMatrix == null){
			mMatrix = new Matrix();
		}
		mMatrix.reset();
//		mMatrix.postTranslate(mX, mY);//设置位置
		mMatrix.postScale(mScaleX, mScaleY, mAnchorX, mAnchorY);//缩放
		mMatrix.postRotate(mDegrees, mAnchorX, mAnchorY);//旋转
		mMatrix.postSkew(mSkewX, mSkewY, mAnchorX, mAnchorY);//视野
	}
	
	/**
	 * 初始化变量
	 */
	public void reset(){
		this.mAlpha = 255;
		this.mScaleX = 1;
		this.mScaleY = 1;
		this.mAnchorX = 0;
		this.mAnchorY = 0;
		this.mDegrees = 0f;
//		this.mX = 0;
//		this.mY = 0;
		invalidate();
	}
}
