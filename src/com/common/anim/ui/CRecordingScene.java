package com.common.anim.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;

import com.zc.common.anim.CScene;
import com.zc.common.anim.Director;
import com.zc.common.anim.sprite.CNode;
import com.zc.common.anim.utils.UIUtils;

public class CRecordingScene extends CScene {

	public static final int TOP_HEIGHT = 110;//144
	public static final int BOTTOM_HEIGHT = 110;
	
	protected CMelodyNode mMelodyNode;
	private Context mContext;
	
	protected CRecordingScene(Context context){
		super();
		this.mContext = context;
		initScene(context);
	}
	
	public static CRecordingScene create(Context context){
		return new CRecordingScene(context);
	}
	
	/**
	 * 初始化节点
	 * @param context
	 */
	private void initScene(Context context){
		//添加旋律
		mMelodyNode = CMelodyNode.create(context);
		addNode(mMelodyNode, 0);
	}
	
	@Override
	public synchronized void render(Canvas canvas) {
		if(mRender){
//			canvas.drawColor(0xffeefcff);
			super.render(canvas);
		}
	}
	
	private volatile boolean mRender = false;
	
	@Override
	public void update(float dt) {
		super.update(dt);
		mRender = true;
	}
	
	/**
	 * 获得旋律节点
	 * @return
	 */
	public CMelodyNode getMelodyNode(){
		return mMelodyNode;
	}
	
	/**
	 * 更新星星
	 * @param level
	 */
	public void updateStars(int level){
		if(mMelodyNode != null){
			mMelodyNode.updateStars(level);
		}
	}
}
