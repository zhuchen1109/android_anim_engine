package com.zc.common.anim.action;

import android.graphics.Point;
import android.view.animation.Interpolator;

import com.zc.common.anim.action.base.CIntervalAction;

/**
 * 移动动作
 * @author zc
 *
 */
public class CMoveToAction extends CIntervalAction {

	private Point mStart;
	private Point mTarget;
	
	protected CMoveToAction(int x, int y, float d, Interpolator interpolator) {
		super(d);
		
		mTarget = new Point(x, y);
		
		if(interpolator != null)
			setInterpolator(interpolator);
	}
	
	/**
	 * 创建动作
	 * @param x 目标位置X
	 * @param y 目标位置Y
	 * @param d 持续时间
	 * @param interpolator 插入器
	 * @return
	 */
	public static CMoveToAction create(int x, int y, float d, Interpolator interpolator){
		return new CMoveToAction(x, y, d, interpolator);
	}
	
	/**
	 * 创建动作
	 * @param x 目标位置X
	 * @param y 目标位置Y
	 * @param d 持续时间
	 * @return
	 */
	public static CMoveToAction create(int x, int y, float d){
		return new CMoveToAction(x, y, d, null);
	}

	@Override
	public void update(float dt) {
		if(mStart == null){
			mStart = actionNode.getPosition();
			return;
		}
		
		super.update(dt);
		
		if(actionNode == null || mStart == null 
				|| !mIsStarted || isDone()){
			return;
		}
		
		float elapsePercent = getElapsePercent();
		int x = 0, y =0;
		if(mTarget.x < mStart.x){
			x = (int) (mStart.x - (mStart.x - mTarget.x) * elapsePercent);
			if(mTarget.y < mStart.y){
				y = (int) (mStart.y - (mStart.y - mTarget.y) * elapsePercent);
			}else{
				y = (int) ((mTarget.y - mStart.y) * elapsePercent + mStart.y);
			}
		}else{
			x = (int) ((mTarget.x - mStart.x) * elapsePercent + mStart.x);
			if(mTarget.y < mStart.y){
				y = (int) (mStart.y - (mStart.y - mTarget.y) * elapsePercent);
			}else{
				y = (int) ((mTarget.y - mStart.y) * elapsePercent + mStart.y);
			}
		}
		
		if(actionNode != null){
			actionNode.setPosition(new Point(x, y));
		}
	}
}
