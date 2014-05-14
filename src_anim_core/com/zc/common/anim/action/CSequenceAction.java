package com.zc.common.anim.action;

import com.zc.common.anim.action.base.CAction;
import com.zc.common.anim.action.base.CIntervalAction;
import com.zc.common.anim.sprite.CActionNode;

/**
 * 顺序动作
 * @author zc
 */
public class CSequenceAction extends CIntervalAction {

	private CAction[] mActions;
	
	protected CSequenceAction(CAction ...actions) {
		super(Integer.MAX_VALUE);
		this.mActions = actions;
	}

	public static CSequenceAction create(CAction ...actions){
		return new CSequenceAction(actions);
	}
	
	@Override
	public synchronized void update(float dt) {
		super.update(dt);
		
		if(mActions == null || !mIsStarted){
			return;
		}
		
		CAction curAction = null;
		for(int i=0; i< mActions.length; i++){
			CAction action = mActions[i];
			if(!action.isDone()){
				curAction = action;
				break;
			}
		}
		
		if(curAction != null){
			curAction.update(dt);
		}else{
			mIsStarted = false;
		}
	}
	
	@Override
	public synchronized void start(CActionNode sprite) {
		super.start(sprite);
		if(mActions != null){
			for(int i=0; i< mActions.length; i++){
				mActions[i].start(sprite);
			}
		}
	}
	
	@Override
	public synchronized void reset() {
		super.reset();
		if(mActions != null){
			for(int i=0; i< mActions.length; i++){
				mActions[i].reset();
			}
		}
		mIsStarted = true;
	}
	
	@Override
	public synchronized void stop() {
		super.stop();
		if(mActions != null){
			for(int i=0; i< mActions.length; i++){
				mActions[i].stop();
			}
		}
	}
	
	@Override
	public synchronized boolean isDone() {
		return super.isDone();
	}
}
