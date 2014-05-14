package com.zc.common.anim;

import android.graphics.Point;

import com.zc.common.anim.nodes.CTextNode;


/**
 * 场景
 * @author zc
 */
public class CScene extends CLayer {
	
	private long mLastRefreshTs = -1;
	public static boolean DEBUG_ABLE = true;
	
	private CTextNode mTextNode;
	protected CScene(){
		super();
		if(DEBUG_ABLE){
			mTextNode = CTextNode.create();
			mTextNode.setPosition(new Point(400, 700));
			addNode(mTextNode, Integer.MAX_VALUE);
		}
	}
	
	public static CScene create(){
		return new CScene();
	}
	
	/**
	 * 刷新场景
	 */
	public void refresh(){
		if(!Director.getSharedDirector().isViewVisable())
			return;
		
		if(Director.getSharedDirector().isPaused()){
			mLastRefreshTs = -1;
			return;
		}
		
		long ts = System.currentTimeMillis();
		if(mLastRefreshTs > 0){
			update(ts - mLastRefreshTs);
			
			if(DEBUG_ABLE && mTextNode!= null){
				mTextNode.setText(1000/(ts - mLastRefreshTs) + " FPS");
			}
		}
		mLastRefreshTs = ts;
	}
	
	@Override
	public synchronized void update(float dt) {
		if(!Director.getSharedDirector().isViewVisable())
			return;
		
		if(mTextNode != null)
			mTextNode.setPosition(new Point(0, getHeight() - dip2px(20)));

//		DebugUtils.debug("zc", "scene: update");
//		long start = System.currentTimeMillis();
		super.update(dt);
//		DebugUtils.debug("zc", "cost: " + (System.currentTimeMillis() - start));
	}	

	public void onSceneStart(){}
	public void onSceneStop(){}
	
	public void onSceneResume(){}
	public void onScenePause(){}
	
	@Override
	public boolean isActived() {
		return super.isActived();
	}
}
