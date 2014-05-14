package com.zc.common.anim.nodes;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;

import com.zc.common.anim.sprite.CNode;

/**
 * 显示文本
 * @author zc
 *
 */
public class CTextNode extends CNode {

	private String mText;
	private Paint mPaint;
	private Drawable mBackground;
	
	protected CTextNode(){
		super();
		init();
	}
	
	public static CTextNode create(){
		return new CTextNode();
	}
	
	private void init(){
		mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mPaint.setColor(Color.RED);
		mPaint.setTextSize(26);
	}
	
	public void setPaint(Paint paint){
		this.mPaint = paint;
	}
	
	public void setBackGround(Drawable drawable){
		this.mBackground = drawable;
	}
	
	@Override
	public synchronized void render(Canvas canvas) {
		super.render(canvas);
		if(TextUtils.isEmpty(mText) || mPaint == null)
			return;
		
		if(mBackground != null){
			int x = getPosition().x;
			int y = getPosition().y;
			mBackground.setBounds(x, y, x + getWidth(), y + getHeight());
			mBackground.draw(canvas);
		}
		
		canvas.drawText(mText, getPosition().x, getPosition().y, mPaint);
	}
	
	@Override
	public synchronized int getHeight(){
		if(mPaint != null)
			return (int) mPaint.measureText("H");
		return 0;
	}
	
	@Override
	public synchronized int getWidth() {
		if(mPaint != null && !TextUtils.isEmpty(mText)){
			return (int) mPaint.measureText(mText);
		}
		return 0;
	}

	public synchronized void setText(String text){
		this.mText = text;
	}
}
