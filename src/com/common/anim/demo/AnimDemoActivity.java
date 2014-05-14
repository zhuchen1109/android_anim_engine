package com.common.anim.demo;
import com.common.anim.ui.CRecordingScene;
import com.zc.common.anim.CGLView;
import com.zc.common.anim.CScene;
import com.zc.common.anim.Director;
import com.zc.common.anim.R;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

public class AnimDemoActivity extends Activity {

	private CGLView mCglView;
	private CRecordingScene mRecordingScene;
	private Handler handler = new Handler();
	
	private int num = 1;
	private int numVar = 2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity);
		mCglView = (CGLView)findViewById(R.id.glview);
		showAnimScene();
		initEngine();
		runAnim();
	}
	
	private void runAnim() {
		//模拟数据
		handler.postDelayed(new Runnable() {
			
			@Override
			public void run() {
				Log.i("xxxx", "run...");
				mRecordingScene.updateStars(num + (int)(Math.random()*numVar));
				runAnim();
			}
		}, 150);
	}
	
	private void showAnimScene(){
		Director.getSharedDirector().setAttachView(mCglView);
		CScene topScene = Director.getSharedDirector().getActiveScene();
		if(topScene == null || !(topScene instanceof CRecordingScene)){
			mRecordingScene = CRecordingScene.create(this);
			Director.getSharedDirector().showScene(mRecordingScene);
		}else{
			Director.getSharedDirector().resumeScene();
		}
		mCglView.startRefresh();
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		stopEngine();
	}
	
	private Director mDirector;
	private void initEngine(){
		mDirector = Director.getSharedDirector();
		mDirector.start();
	}
	
	private void stopEngine(){
		if(mDirector != null)
			mDirector.stop();
	}
}
