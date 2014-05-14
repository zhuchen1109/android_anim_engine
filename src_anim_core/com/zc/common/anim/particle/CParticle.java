package com.zc.common.anim.particle;

import java.util.List;
import java.util.Random;

import com.zc.common.anim.sprite.CNode;
import com.zc.common.anim.texture.CTexture;

import android.graphics.Canvas;
import android.graphics.Point;

/**
 * 单个粒子
 * @author zc
 */
public class CParticle extends CNode {
	
	private CTexture mTexture;
	private List<Point> mPaths;
	
	private int mDuration = 3000;//持续时间
	private float mStart2FadingRate = 1f;//开始衰老比
	private float mStart2GrowingRate = 0.1f, 
			mEndGrowingRate = 0.3f;//开始、结束成长比
	private float mMaxWeight = 1f;//结束体重
	private float mBornWeight = 0.5f;//初始化体重
	private float mGradient = 1f;//年龄增长梯度
	private float mBornDegrees = 0f;//出生角度
	
	private volatile float mAge = 0f;//当前年龄
	private volatile float mFadingRate = 0f;//当前衰老比
	private volatile float mWeight = 0f;//当前体重比
	private volatile float mDegrees = 0f;//当前角度
	private volatile float mSkewX = 0f;//X轴倾斜角度
	private volatile float mSkewY = 0f;//Y轴倾斜角度
	
	private Point mPosition = null;//当前位置
	private volatile boolean mIsOld = false;//是否已经衰老
	private volatile boolean mIsKilled = false;//是否已经被杀死
	
	protected CParticle(int duration){
		this.mDuration = duration;
	}
	
	/**
	 * 释放粒子
	 */
	public void release(){
		if(mPaths != null){
//			mPaths.clear();
			mPaths = null;
		}
		mTexture = null;
		mPosition = null;
		killSelf();
	}
	
	/**
	 * 创建粒子
	 * @return
	 */
	public static CParticle create(CTexture texture, Point position, List<Point> paths, int duration){
		return create(null, texture, position, paths, duration);
	}
	
	/**
	 * 创建粒子
	 * @param particle
	 * @param texture
	 * @param position
	 * @param paths
	 * @param duration
	 * @return
	 */
	public static CParticle create(CParticle particle, CTexture texture, Point position, List<Point> paths, int duration){
		CParticle sprite = particle;
		if(sprite == null)
			sprite = new CParticle(duration);
		
		sprite.reset();
		if(!sprite.initTexture(texture)){
			return null;
		}
		if(!sprite.initPath(position, paths)){
			return null;
		}
		if(!sprite.initOthers()){
			return null;
		}
		return sprite;
	}

	/**
	 * 初始化纹理
	 * @param texture
	 * @return
	 */
	private boolean initTexture(CTexture texture){
		this.mTexture = texture;
		return true;
	}
	
	/**
	 * 规划路径
	 * @param position
	 * @return
	 */
	private boolean initPath(Point position, List<Point> paths){
		this.mPosition = position;
		this.mPaths = paths;
		return true;
	}
	
	/**
	 * 初始化其他精灵环境
	 * @return
	 */
	private boolean initOthers(){
		Random random = getRandomObj();
		//初始化年龄
		mAge = 0;
		//当前衰老比
		mFadingRate = 0f;
		//初始化体重
		mBornWeight = (float) (0.6f + Math.random() * 0.4);
		//当前体重
		mWeight = mBornWeight;
		//结束体重
		mMaxWeight = mBornWeight;
		//开始衰老比
		mStart2FadingRate = 0.4f;
		//开始生长
		mStart2GrowingRate = 0.1f;
		//结束生长
		mEndGrowingRate = 0.3f;
		//是否被killed
		mIsKilled = false;
		//是否开始衰老
		mIsOld = false;
		//初始角度
		mBornDegrees = random.nextInt(360);
		//当前角度
		mDegrees = mBornDegrees;
		//倾斜角度
		mSkewX = (float) (0.2*Math.random());
		mSkewY = (float) (0.2*Math.random());
		
		//初始化年龄梯度
		if(mPaths != null && mPaths.size() > 0){
			mGradient = (mDuration + 0.0f) / mPaths.size();
		}
		return true;
	}
	
	/**
	 * 设置体重信息
	 * @param bornWeight
	 * @param maxWeight
	 */
	public void setWeight(float bornWeight, float maxWeight){
		this.mBornWeight = bornWeight;
		this.mMaxWeight = maxWeight;
	}
	
	/**
	 * 设置成长信息
	 * @param start2GrowRate
	 * @param end2GrowRate
	 */
	public void setGrowRate(float start2GrowRate, float end2GrowRate){
		this.mStart2GrowingRate = start2GrowRate;
		this.mEndGrowingRate = end2GrowRate;
	}
	
	/**
	 * 设置衰老信息
	 * @param rate
	 */
	public void setStart2FadeRate(float rate){
		this.mStart2FadingRate = rate;
	}
	
	/**
	 * 设置初始角度
	 * @param degrees
	 */
	public void setBornDegrees(float degrees){
		this.mBornDegrees = degrees;
	}
	
	/**
	 * 销毁自己
	 */
	private void killSelf(){
		mIsKilled = true;
	}
	
	/**
	 * 检验精灵是否存活
	 * @return
	 */
	public boolean isAlive(){
		return !mIsKilled;
	}
	
	/**
	 * 是否已经开始衰老
	 * @return
	 */
	boolean isOld(){
		return mIsOld;
	}
	
	/**
	 * 刷新界面
	 */
	@Override
	public void update(float dt){
		super.update(dt);
		if(mPaths == null || mPaths.size() == 0 || !isAlive()){
			return;
		}
		//重新计算当前年龄
		mAge = getElapsed();
		
		//验证是否存活
		if(mAge > mDuration || mAge <= 0){
			killSelf();
			return;
		}
		
		//计算精灵是否开始衰老
		if(mAge >= mDuration * mStart2FadingRate){
			mIsOld = true;
			//当前衰老比
			mFadingRate = (mAge - mDuration*mStart2FadingRate)/(mDuration * (1 - mStart2FadingRate));
		}else{
			mIsOld = false;
			mFadingRate = 0f;
		}
		//当前体重比
		if(mAge > mDuration * mStart2GrowingRate && mAge < mDuration * mEndGrowingRate){
			mWeight += (mAge - mDuration * mStart2GrowingRate) * (mMaxWeight - mWeight)/(mDuration * (mEndGrowingRate - mStart2GrowingRate));
		}else if(mAge <= mDuration * mStart2GrowingRate){
			mWeight = mBornWeight;
		}else{
			if(mIsOld){
				mWeight = mMaxWeight * 0.1f * mFadingRate + mMaxWeight*0.9f;
			}else{
				mWeight = mMaxWeight;
			}
		}
		
		//计算精灵位置
		int pathIndex = (int) (mAge / mGradient);
		if(pathIndex >= mPaths.size() || pathIndex < 0){
			//超出边界，标记killed
			killSelf();
			return;
		}
		mPosition = mPaths.get(pathIndex);
		mDegrees += 1;
	}
	
	/**
	 * 绘图
	 * @param canvas
	 */
	@Override
	public void render(Canvas canvas){
		super.render(canvas);
		//有效验证
		if(mTexture == null || mPaths == null || mPaths.size() == 0
				|| mPosition == null){
			return;
		}
		//是否存活
		if(!isAlive()){
			return;
		}
		
		mTexture.setAlpha((int) (255 * (1 - mFadingRate)));
		mTexture.setScale(mWeight, mWeight);
		mTexture.rotate(mDegrees);
		mTexture.setSkew(mSkewX, mSkewY);
		
		canvas.save();
		canvas.translate(mPosition.x, mPosition.y);
		mTexture.render(canvas);
		canvas.restore();
	}
}
