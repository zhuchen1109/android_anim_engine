package com.zc.common.anim.action;

import android.view.animation.Interpolator;

import com.zc.common.anim.action.base.CIntervalAction;

/**
 * 旋转动作
 * @author zc
 */
public class CRotateToAction extends CIntervalAction {

	private int mFrom = 0;
	private int mDegrees = 0;
	
	protected CRotateToAction(int from, int degrees, float d, Interpolator interpolator) {
		super(d);
		this.mFrom = from;
		this.mDegrees = degrees;
		
		if(interpolator != null)
			setInterpolator(interpolator);
	}

	public static CRotateToAction create(int from, int degrees, float d, Interpolator interpolator){
		return new CRotateToAction(from, degrees, d, interpolator);
	}
	
	public static CRotateToAction create(int from, int degrees, float d){
		return new CRotateToAction(from, degrees, d, null);
	}
	
	@Override
	public void update(float dt) {
		super.update(dt);
		
		if(!mIsStarted || actionNode == null
				|| isDone()){
			return;
		}
		
		float elapsePercent = getElapsePercent();
		int degrees = (int) (mDegrees*elapsePercent);
		
		actionNode.rotate(degrees + mFrom);
	}
}
