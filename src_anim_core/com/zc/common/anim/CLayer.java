package com.zc.common.anim;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.zc.common.anim.sprite.CNode;

import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

/**
 * 展现层
 * @author zc
 */
public class CLayer extends CNode implements OnTouchListener {

	private List<CNode> mNodes;
	
	protected CLayer(){
		super();
	}
	
	public static CLayer create(){
		return new CLayer();
	}
	
	@Override
	public synchronized void render(Canvas canvas) {
		super.render(canvas);
		if(mNodes == null)
			return;
		try {
			for(CNode node : mNodes){
				if(node != null){
					node.render(canvas);
				}
			}
		} catch (Exception e) {
		}
		
	}

	@Override
	public synchronized void update(float dt) {
		super.update(dt);
		if(mNodes == null)
			return;
		try {
			for(CNode node : mNodes){
				node.update(dt);
			}
		} catch (Exception e) {
		}
	}
	
	/**
	 * 添加渲染节点
	 * @param node
	 * @param zindex z轴索引
	 */
	public synchronized void addNode(CNode node, int zindex){
		if(node == null)
			return;
		
		try {
			if(mNodes == null)
				mNodes = new ArrayList<CNode>();
			
			node.setZindex(zindex);
			mNodes.add(node);
			node.setParent(this);
			Collections.sort(mNodes, new Comparator<CNode>() {
				@Override
				public int compare(CNode lhs, CNode rhs) {
					return lhs.getZindex() - rhs.getZindex();
				}
			});
		} catch (Exception e) {
		}
	}
	
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		if(mNodes == null)
			return false;
		try {
			for(CNode node : mNodes){
				if(node.onTouch(v, event)){
					return true;
				}
			}
		} catch (Exception e) {
		}
		return false;
	}
	
	@Override
	public boolean isActived() {
		try {
			if(mNodes != null && mNodes.size() > 0) {
				for(int i = 0; i < mNodes.size(); i++){
					CNode node = mNodes.get(i);
					if(node.isActived()){
						return true;
					}
				}
			}
		} catch (Exception e) {
		}
		return false;
	}
}
