package com.zc.common.anim.particle;

/**
 * 粒子缓冲集合
 * @author zc
 *
 */
public class CParticleArrayList {

	//粒子集合
    private CParticle[] mParticles;
    private int mSize = 1024;

    public CParticleArrayList(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException();
        }
        this.mSize = capacity;
        mParticles = new CParticle[capacity];
    }

    public CParticleArrayList() {
    	mParticles = new CParticle[mSize];
    }

    /**
     * 添加粒子
     * @param particle
     * @return
     */
    public boolean add(CParticle particle) {
        if(mParticles != null){
        	for(int i=0; i< mParticles.length; i++){
        		if(mParticles[i] == null
        				|| !mParticles[i].isAlive()){
        			mParticles[i] = particle;
        			return true;
        		}
        	}
        }
        return false;
    }

    /**
     * 删除粒子
     */
    public void clear() {
    	if(mParticles != null){
        	for(int i=0; i< mParticles.length; i++){
        		mParticles[i] = null;
        	}
        }
    }
    
    /**
     * 粒子数
     * @return
     */
    public int size(){
    	return mSize;
    }

    /**
     * 获得粒子
     * @param index
     * @return
     */
    public CParticle get(int index){
    	if(index >= mParticles.length
    			|| index < 0)
    		return null;
    	
    	return mParticles[index];
    }
}
