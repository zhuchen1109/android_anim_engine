package com.zc.common.anim.particle;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Point;
import android.util.Log;

import com.zc.common.anim.texture.CTexture;

public class CStarStyle extends CParticleSystem {

	private CTexture mCTexture;
	
	int STAR_NUM = 5;
	
	public CStarStyle(CTexture texture) {
		this.mCTexture = texture;
	}
	
	public static CStarStyle create(CTexture texture) {
		return new CStarStyle(texture);
	}
	
	public void show() {
		if(mCTexture == null)
			return;
		
		for(int i=0; i< STAR_NUM; i++){
			Point startPoint = new Point(getStartX(), getStartY());
			int duration = getDuration();
			CParticle particle = CParticle.create(getRecycleParticle(), mCTexture, startPoint, schedulePaths(startPoint, 100, duration), duration);
			if(particle != null)
				addParticle(particle);
		}
	}
	
	private int getStartX(){
		return (int) (getWidth() / 2 + 200 * getRandom()*(getRandom() > 0.5 ? 1 : -1));
	}
	
	private int getStartY(){
		return (int) (getHeight() / 2 + + 200 * getRandom()*(getRandom() > 0.5 ? 1 : -1));
	}
	
	private int getDuration(){
		return (int) (2000 + getRandom() * 1000);
	}
	
	int mAngle = 90;
	int mAngleVar = 360;
	
	int mRadiaAccel = 300;
	int mRadiaAccelVar = 500;
	
	
	private List<Point> schedulePaths(Point startPoint, int cnt, int duration){
		List<Point> points = new ArrayList<Point>();
		int angle = mAngle + (int)(mAngleVar*getRandom());
		int radiaAccel = mRadiaAccel + (int)(mRadiaAccelVar*getRandom());
		int unitTime = duration/cnt;
		int tempX = startPoint.x, tempY = startPoint.y;
		int unitS = radiaAccel/cnt;
		int deltay = (int) (unitS*getInterpolation(unitTime/1000));
		for(int i=0; i<cnt/2; i++) {
			tempX += (int)(Math.cos(Math.toDegrees(angle))*unitS)-deltay;
			tempY += (int)(Math.sin(Math.toDegrees(angle))*unitS)-deltay;
			Point temp = new Point((int)tempX, (int)tempY);
			points.add(temp);
		}
		
		int tempx1 = tempX, tempy1 = tempY;
		int deltay1 = (int) (unitS*getInterpolation1(unitTime/1000));
		for(int i=cnt/2; i<cnt;i++) {
			tempx1 -= (int)(Math.cos(Math.toDegrees(angle))*unitS)-deltay1;
			tempy1 -= (int)(Math.sin(Math.toDegrees(angle))*unitS)-deltay1;
			Point temp = new Point((int)tempx1, (int)tempy1);
			points.add(temp);
		}
		
		/*Point endPoint = new Point((int)(Math.cos(Math.toDegrees(angle))*radiaAccel), (int)(Math.cos(Math.toDegrees(angle))*radiaAccel));
		Point ctrlPoint = new Point();
		ctrlPoint.x = (int)(startPoint.x + endPoint.x + dip2px(20) * Math.random())/2;
		ctrlPoint.y = (int)(startPoint.y + endPoint.y + dip2px(20) * Math.random())/2;
		points.addAll(calculateBezierPaths(startPoint, ctrlPoint, endPoint, cnt/2));
		
		Point startPoint1 = endPoint;
		Point endPoint1 = startPoint;
		Point ctrlPoint1 = new Point();
		ctrlPoint1.x = (int)(startPoint1.x + endPoint1.x + dip2px(-20) * Math.random())/2;
		ctrlPoint1.y = (int)(startPoint1.y + endPoint1.y + dip2px(-20) * Math.random())/2;
		points.addAll(calculateBezierPaths(startPoint1, ctrlPoint1, endPoint1, cnt/2));*/

		
		/*StringBuffer sbx = new StringBuffer();
		StringBuffer sby = new StringBuffer();
		for(int i=0; i<points.size();i++) {
			sbx.append(points.get(i).x+",");
			sby.append(points.get(i).y+",");
		}
		Log.d("xxxx", "sbx="+sbx.toString());
		Log.d("xxxx", "sby="+sby.toString());*/
		return points;
	}
	
	
	private float mFactor = 1.0f;
	
	public float getInterpolation(float input) {
        float result;
        if (mFactor == 1.0f) {
            result = (float)(1.0f - (1.0f - input) * (1.0f - input));
        } else {
            result = (float)(1.0f - Math.pow((1.0f - input), 2 * mFactor));
        }
        return result;
    }
	
	public float getInterpolation1(float input) {
		if (mFactor == 1.0f) {
            return input * input;
        } else {
            return (float)Math.pow(input, mFactor*2);
        }
    }
}
