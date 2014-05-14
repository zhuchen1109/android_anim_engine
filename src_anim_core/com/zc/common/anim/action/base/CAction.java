package com.zc.common.anim.action.base;

import com.zc.common.anim.sprite.CActionNode;
import com.zc.common.anim.utils.DebugUtils;

/**
 * 动作基类
 * @author zc
 */
public class CAction {
	
	private static final String LOG_TAG = CAction.class.getSimpleName();
	//精灵
	protected CActionNode actionNode;
	protected volatile boolean mIsStarted = false;
	
    public static CAction action() {
        return new CAction();
    }

    protected CAction() {
    	DebugUtils.debug(LOG_TAG, "init");
    }

    /**
     * 开始播放动画
     */
    public void start(CActionNode actionNode) {
    	DebugUtils.debug(LOG_TAG, "start");
    	mIsStarted = true;
    	this.actionNode = actionNode;
    }

    /**
     * 停止播放动画
     */
    public void stop() {
    	mIsStarted = false;
    	DebugUtils.debug(LOG_TAG, "stop");
    }

    /**
     * 是否完成
     * @return
     */
    public boolean isDone() {
        return !mIsStarted;
    }

    /**
     * 更新动作
     * @param time
     */
    public void update(float dt) {
    	DebugUtils.debug(LOG_TAG, "update");
    }
    
    public void reset(){
    	
    }
}
