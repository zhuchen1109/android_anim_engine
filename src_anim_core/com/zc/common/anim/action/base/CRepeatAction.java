package com.zc.common.anim.action.base;

import com.zc.common.anim.sprite.CActionNode;

public class CRepeatAction extends CAction {

	private CIntervalAction mIntervalAction;
	
	protected CRepeatAction(CIntervalAction action){
		this.mIntervalAction = action;
	}
	
	public static CRepeatAction create(CIntervalAction action){
		return new CRepeatAction(action);
	}
	
	@Override
	public void update(float dt) {
		super.update(dt);
		if(!mIsStarted || mIntervalAction == null)
			return;
		
		if(mIntervalAction != null){
			mIntervalAction.update(dt);
			
			if(mIntervalAction.isDone()){
				mIntervalAction.reset();
			}
		}
	}
	
	@Override
	public void start(CActionNode actionNode) {
		super.start(actionNode);
		if(mIntervalAction != null)
			mIntervalAction.start(actionNode);
	}
	
	@Override
	public void stop() {
		super.stop();
		if(mIntervalAction != null){
			mIntervalAction.stop();
		}
	}
	
	@Override
	public void reset(){
		if(mIntervalAction != null){
			mIntervalAction.reset();
		}
	}
}
