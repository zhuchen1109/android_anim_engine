package com.zc.common.anim.action;

import android.view.animation.Interpolator;

import com.zc.common.anim.action.base.CIntervalAction;
import com.zc.common.anim.sprite.CActionNode;

public class CAlphaToAction extends CIntervalAction {

	private int mStartAlpha = 255;
	private int mEndAlpha = 255;
	
	protected CAlphaToAction(int fromAlpha, int alpha, float d, Interpolator interpolator) {
		super(d);
		this.mStartAlpha = fromAlpha;
		this.mEndAlpha = alpha;
		
		if(interpolator != null)
			setInterpolator(interpolator);
	}
	
	/**
	 * 创建渐变动作
	 * @param alpha
	 * @param d
	 * @param interpolator 插入器
	 * @return
	 */
	public static CAlphaToAction create(int fromAlpha, int alpha, float d, Interpolator interpolator){
		return new CAlphaToAction(fromAlpha, alpha, d, interpolator);
	}
	
	/**
	 * 创建渐变动作
	 * @param alpha
	 * @param d
	 * @return
	 */
	public static CAlphaToAction create(int fromAlpha, int alpha, float d){
		return new CAlphaToAction(fromAlpha, alpha, d, null);
	}
	
	public static CAlphaToAction create(int alpha, float d){
		return new CAlphaToAction(255, alpha, d, null);
	}
	
	@Override
	public void update(float dt) {
		super.update(dt);
		if(!mIsStarted || actionNode == null || isDone()){
			return;
		}

		float elapsePercent = getElapsePercent();
		int alpha = (int) ((mEndAlpha - mStartAlpha)*elapsePercent + mStartAlpha);
		
		actionNode.setAlpha(alpha);
	}
	
	@Override
	public void start(CActionNode actionNode) {
		super.start(actionNode);
	}
}
