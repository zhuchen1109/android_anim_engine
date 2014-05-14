package com.zc.common.anim.sprite;

import java.util.Random;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

import com.zc.common.anim.Director;
import com.zc.common.anim.utils.DebugUtils;

/**
 * 单个对象节点
 * @author zc
 *
 */
public abstract class CNode implements OnTouchListener {

	private static final String LOG_TAG = CNode.class.getSimpleName();

	public static final int FILL_PARENT = -1;
	
	private CNode mParent;
	//对齐方式
	protected CAlign mAlign = null;
	//花费的时间
	private float elapsed = 0;
	//z轴索引
	private int mZindex;

	private int mX = 0, mY = 0;
	private int mWidth = FILL_PARENT;
	private int mHeight = FILL_PARENT;
	
	private Paint mPaint;
	
	private static Random mRandom = new Random();
	
	public CNode(){}
	
	public void setParent(CNode parent){
		this.mParent = parent;
	}

	/**
	 * 渲染
	 * @param canvas
	 */
	public void render(Canvas canvas){
		if(mPaint != null){
			canvas.drawRect(new Rect(getPosition().x, getPosition().y, 
					getPosition().x + getWidth(), getPosition().y + getHeight()), mPaint);
		}
	}
	
	/**
	 * 刷新帧
	 * @param dt
	 */
	public void update(float dt){
		DebugUtils.debug(LOG_TAG, "update");
		elapsed += dt;
	}
	
	/**
	 * 获得已经使用时间
	 * @return
	 */
	protected float getElapsed(){
		return elapsed;
	}
	
	/**
	 * 重置状态
	 */
	public void reset(){
		elapsed = 0f;
	}
	
	/**
	 * 设置节点位置
	 * @param position
	 */
	public void setPosition(Point position){
		this.mX = position.x;
		this.mY = position.y;
	}
	
	/**
	 * 设置view大小
	 * @param width
	 * @param height
	 */
	public void setViewSize(int width, int height){
		this.mWidth = width;
		this.mHeight = height;
	}
	
	/**
	 * 内容宽度
	 * @return
	 */
	public int getWidth() {
		if(mParent != null){
			if(mWidth == FILL_PARENT){
				return mParent.getWidth();
			}
			return mWidth;
		}
		if(Director.getSharedDirector().getViewSize() == null)
			return 0;
		
		return Director.getSharedDirector().getViewSize().width();
	}
	
	/**
	 * 内容高度
	 * @return
	 */
	public int getHeight() {
		if(mParent != null){
			if(mHeight == FILL_PARENT){
				return mParent.getHeight();
			}
			return mHeight;
		}
		if(Director.getSharedDirector().getViewSize() == null){
			return 0;
		}
		return Director.getSharedDirector().getViewSize().height();
	}
	
	/**
	 * 设置对齐方式
	 * @param align
	 */
	public void setAlign(CAlign align){
		if(align == null)
			return;
		this.mAlign = align;
	}
	
	public enum CAlign{
		TOP_LEFT(0)
		,TOP_CENTER(1)
		,TOP_RIGHT(2)
		
		,CENTER_LEFT(3)
		,CENTER_CENTER(4)
		,CENTER_RIGHT(5)
		
		,BOTTOM_LEFT(6)
		,BOTTOM_CENTER(7)
		,BOTTOM_RIGHT(8);
		
		CAlign(int type){
			this.type = type;
		}
		
		private int type;
		
		public int getValue(){
			return type;
		}
	}
	
	/**
	 * 设置z轴索引
	 * @param zindex
	 */
	public void setZindex(int zindex){
		this.mZindex = zindex;
	}
	
	/**
	 * 获得z轴索引
	 * @return
	 */
	public int getZindex(){
		return mZindex;
	}
	
    @Override
    public boolean onTouch(View v, MotionEvent event) {
    	return false;
    }
    
	/**
	 * 获得随机数对象
	 * @return
	 */
	public static Random getRandomObj(){
		if(mRandom == null)
			mRandom = new Random();
		return mRandom;
	}
	
	/** 
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素) 
     */  
	public int dip2px(float dpValue) {  
        return Director.getSharedDirector().dp2px(dpValue);
    }
    
	/**
	 * 获得节点位置
	 * @return
	 */
	public Point getPosition(){
		if(Director.getSharedDirector().getViewSize() == null)
			return new Point(mX, mY);
		
		int pleft = 0, ptop = 0;
		int pwidth = Director.getSharedDirector().getViewSize().width();
		int pheight = Director.getSharedDirector().getViewSize().height();
		if(mParent != null){
			pleft = mParent.getPosition().x;
			ptop = mParent.getPosition().y;
			pwidth = mParent.getWidth();
			pheight = mParent.getHeight();
		}
		
		if(mAlign != null){
			switch (mAlign) {
			case TOP_LEFT:
				return new Point(pleft, ptop);
			case TOP_CENTER:
				return new Point(pleft + (pwidth - getWidth())/2, ptop + 0);
			case TOP_RIGHT:
				return new Point(pleft + pwidth - getWidth(), ptop + 0);
				
			case CENTER_LEFT:
				return new Point(pleft, ptop + (pheight - getHeight())/2);
			case CENTER_CENTER:
				return new Point(pleft + (pwidth - getWidth())/2, ptop + (pheight - getHeight())/2);
			case CENTER_RIGHT:
				return new Point(pleft + pwidth - getWidth(), ptop + (pheight - getHeight())/2);
				
			case BOTTOM_LEFT:
				return new Point(pleft + 0, ptop + pheight - getHeight());
			case BOTTOM_CENTER:
				return new Point(pleft + (pwidth - getWidth())/2, ptop + pheight - getHeight());
			case BOTTOM_RIGHT:
				return new Point(pleft + pwidth - getWidth(), ptop + pheight - getHeight());
			default:
				break;
			}
		}
		return new Point(pleft + mX, ptop + mY);
	}
	
	/**
	 * 设置背景颜色
	 * @param color
	 */
	public void setColor(int color){
		if(mPaint == null)
			mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mPaint.setColor(color);
	}
	
	/**
	 * 检查所有节点是否有变化
	 * @return
	 */
	public boolean isActived(){
		return true;
	}
}
