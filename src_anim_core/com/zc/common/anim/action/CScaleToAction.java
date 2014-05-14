package com.zc.common.anim.action;

import android.view.animation.Interpolator;

import com.zc.common.anim.action.base.CIntervalAction;

/**
 * 缩放动画
 * @author zc
 *
 */
public class CScaleToAction extends CIntervalAction {

	private float mScaleX = 1, mScaleY = 1;
	private float mFromScaleX = 1, mFromScaleY = 1;
	
	protected CScaleToAction(float fromScaleX, float fromScaleY, 
			float scaleX, float scaleY, float d, Interpolator interpolator) {
		super(d);
		this.mFromScaleX = fromScaleX;
		this.mFromScaleY = fromScaleY;
		this.mScaleX = scaleX;
		this.mScaleY = scaleY;
		
		if(interpolator != null)
			setInterpolator(interpolator);
	}

	public static CScaleToAction create(float scaleX, float scaleY, float d, Interpolator interpolator){
		return new CScaleToAction(1, 1, scaleX, scaleY, d, interpolator);
	}
	
	public static CScaleToAction create(float fromScale, float toScale, float d){
		return new CScaleToAction(fromScale, fromScale, toScale, toScale, d, null);
	}
	
	@Override
	public void update(float dt) {
		super.update(dt);
		if(!mIsStarted || actionNode == null || isDone()){
			return;
		}

		float elapsePercent = getElapsePercent();
		float sx = mFromScaleX + (mScaleX - mFromScaleX) * elapsePercent;
		float sy = mFromScaleY + (mScaleY - mFromScaleY) * elapsePercent;
		
		actionNode.setScale(sx, sy);
	}
}
