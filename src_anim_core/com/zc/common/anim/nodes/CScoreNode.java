package com.zc.common.anim.nodes;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.SparseArray;

import com.zc.common.anim.sprite.CNode;

/**
 * 显示分数
 * @author zc
 *
 */
public class CScoreNode extends CNode {
	
	private Paint mPaint;
	private Matrix mMatrix;
	
	private SparseArray<Bitmap> mScoreSparseArray;
	private int[] mCurrentScores;
	
	private int mScore = 0;
	private int mDuration = 0;
	
	protected CScoreNode(int score, int d, Bitmap ...bitmaps){
		this.mDuration = d;
		showScore(score);
		
		mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mMatrix = new Matrix();
		if(bitmaps != null){
			if(bitmaps.length != 10)
				throw new RuntimeException("");
			else{
				mScoreSparseArray = new SparseArray<Bitmap>();
				for(int i=0; i< bitmaps.length; i++){
					mScoreSparseArray.put(i, bitmaps[i]);
				}
			}
		}else{
			
		}
	}
	
	public static CScoreNode create(int score, int d, Bitmap ...bitmaps){
		return new CScoreNode(score, d, bitmaps);
	}
	
	@Override
	public void render(Canvas canvas) {
		super.render(canvas);
		if(mScoreSparseArray == null || mCurrentScores == null)
			return;
		
		Point position = getPosition();
		int x = 0;
		int y = 0;
		if(position != null){
			x = position.x;
			y = position.y;
		}
		
		for(int i=0; i<mCurrentScores.length; i++){
			if(mCurrentScores[i] < mScoreSparseArray.size()){
				Bitmap bitmap = mScoreSparseArray.get(mCurrentScores[i] );
				
				mMatrix.reset();
				mMatrix.postTranslate(x, y);
				canvas.drawBitmap(bitmap, mMatrix, mPaint);
				x += bitmap.getWidth();
			}
		}
	}
	
	@Override
	public void update(float dt) {
		super.update(dt);
		
		if(mDuration <= 0){
			return;
		}
		
		float elapsed = getElapsed()/mDuration;
		if(elapsed > 1){
			elapsed = 1;
		}
		
		char ch[] = String.valueOf(mScore).toCharArray();
		if(ch.length != mCurrentScores.length){
			return;
		}

		resetScores();
		for(int i=0; i< ch.length; i++){
			mCurrentScores[i] = (int) (elapsed * (ch[i] - '0'));
		}
	}
	
	public void showScore(int score){
		this.mScore = score;
		
		char ch[] = String.valueOf(mScore).toCharArray();
		mCurrentScores = new int[ch.length];
		reset();
	}
	
	@Override
	public int getWidth() {
		try {
			if(mScoreSparseArray != null)
				return mScoreSparseArray.get(0).getWidth() * mCurrentScores.length;
		} catch (Exception e) {
		}
		return super.getWidth();
	}
	
	@Override
	public int getHeight() {
		if(mScoreSparseArray != null)
			return mScoreSparseArray.get(0).getHeight();
		
		return super.getHeight();
	}
	
	private void resetScores(){
		for(int i=0; i<mCurrentScores.length; i++){
			mCurrentScores[i] = 0;
		}
	}
}
