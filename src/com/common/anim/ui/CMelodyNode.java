package com.common.anim.ui;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.View;

import com.zc.common.anim.CLayer;
import com.zc.common.anim.R;
import com.zc.common.anim.particle.CSnowSytle;
import com.zc.common.anim.particle.CStarStyle;
import com.zc.common.anim.texture.CTexture;

/**
 * 旋律组合节点
 * @author zc
 */
public class CMelodyNode extends CLayer {

	private static final int LEFT_WIDTH = 110;
	public static final int TOP_HEIGHT = 110;//144
	public static final int BOTTOM_HEIGHT = 110;
	
	private CMelodyStyle mMelodyStyle;
	private CSnowSytle mSnowSytle;
	private CStarStyle mCStarStyle;
	
	protected CMelodyNode(Context context){
		init(context);
	}
	
	public static CMelodyNode create(Context context){
		return new CMelodyNode(context);
	}
	
	private void init(Context context){
		//旋律的粒子效果
		mMelodyStyle = CMelodyStyle.create(
				CTexture.create(BitmapFactory.decodeResource(context.getResources(), R.drawable.sing_img_note1)),
				CTexture.create(BitmapFactory.decodeResource(context.getResources(), R.drawable.sing_img_note2)),
				CTexture.create(BitmapFactory.decodeResource(context.getResources(), R.drawable.sing_img_note3)),
				CTexture.create(BitmapFactory.decodeResource(context.getResources(), R.drawable.sing_img_note4)),
				CTexture.create(BitmapFactory.decodeResource(context.getResources(), R.drawable.sing_img_note5)),
				CTexture.create(BitmapFactory.decodeResource(context.getResources(), R.drawable.sing_img_note6)),
				CTexture.create(BitmapFactory.decodeResource(context.getResources(), R.drawable.sing_img_note7)),
				CTexture.create(BitmapFactory.decodeResource(context.getResources(), R.drawable.sing_img_note8)),
				CTexture.create(BitmapFactory.decodeResource(context.getResources(), R.drawable.sing_img_note9)),
				CTexture.create(BitmapFactory.decodeResource(context.getResources(), R.drawable.sing_img_note10))
		);
		addNode(mMelodyStyle, 1);
		
		mSnowSytle = CSnowSytle.create(CTexture.create(BitmapFactory.decodeResource(context.getResources(), R.drawable.snow_1)));
//		addNode(mSnowSytle, 2);
		
		mCStarStyle = CStarStyle.create(CTexture.create(BitmapFactory.decodeResource(context.getResources(), R.drawable.stars)));
		addNode(mCStarStyle, 3);
	}
	
	@Override
	public synchronized void update(float dt) {
		if(mMelodyStyle != null) {
			Point point = new Point(dip2px(LEFT_WIDTH - 20), 
					getHeight() - dip2px(BOTTOM_HEIGHT)/2);
			if(curX != 0 || curY !=0) {
				point.x = (int)curX;
				point.y = (int)curY;
			}
			mMelodyStyle.setPosition(point);
		}
		super.update(dt);
	}
	
	private float curX = 0;
	private float curY = 0;
	
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		curX =  event.getRawX();
		curY = event.getRawY();
		
		/*switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			curX =  event.getRawX();
			curY = event.getRawY();
			break;
		case MotionEvent.ACTION_MOVE:
					
			break;
		case MotionEvent.ACTION_UP:
		case MotionEvent.ACTION_CANCEL:
			
			break;
		}*/
		return true;
	}

	/**
	 * 更新星星
	 * @param level
	 */
	public void updateStars(int level){
		if(mMelodyStyle != null){
			mMelodyStyle.updateStars(level);
		}
		if(mSnowSytle != null) {
			mSnowSytle.snow();
		}
		if(mCStarStyle != null) {
			mCStarStyle.show();
		}
	}
}
