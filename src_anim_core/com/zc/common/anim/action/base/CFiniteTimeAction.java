package com.zc.common.anim.action.base;


/**
 * 基于时间动作
 * @author zc
 *
 */
public class CFiniteTimeAction extends CAction {

    protected float mDuration;

    public static CFiniteTimeAction action(float d) {
        return new CFiniteTimeAction(d);
    }

    protected CFiniteTimeAction(float d) {
        mDuration = d;
    }

    /**
     * 获得持续时间
     * @return
     */
    public float getDuration() {
        return mDuration;
    }

    /**
     * 设置持续时间
     * @param duration
     */
    public void setDuration(float duration) {
        this.mDuration = duration;
    }
}
