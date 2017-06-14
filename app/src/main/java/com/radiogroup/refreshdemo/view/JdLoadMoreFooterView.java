package com.radiogroup.refreshdemo.view;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.aspsine.swipetoloadlayout.SwipeLoadMoreFooterLayout;
import com.radiogroup.refreshdemo.R;

/**
 * Created by Administrator on 2017/6/9.
 */

public class JdLoadMoreFooterView extends SwipeLoadMoreFooterLayout {

    private ImageView ivSpeed;

    private ImageView ivRefresh;

    private AnimationDrawable mAnimDrawable;

    private Animation mTwinkleAnim;

    private int mTriggerOffset;

    public JdLoadMoreFooterView(Context context) {
        this(context, null);
    }

    public JdLoadMoreFooterView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public JdLoadMoreFooterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mTriggerOffset = context.getResources().getDimensionPixelOffset(R.dimen.refresh_header_height_jd);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ivRefresh = (ImageView) findViewById(R.id.ivRefresh);
        ivSpeed = (ImageView) findViewById(R.id.ivSpeed);
        mAnimDrawable = (AnimationDrawable) ivRefresh.getBackground();
        mTwinkleAnim = AnimationUtils.loadAnimation(getContext(), R.anim.twinkle);
    }

    @Override
    public void onLoadMore() {
        ivSpeed.setVisibility(VISIBLE);
        ivSpeed.startAnimation(mTwinkleAnim);
        if (!mAnimDrawable.isRunning()){
            mAnimDrawable.start();
        }
    }

    @Override
    public void onPrepare() {
        ivSpeed.clearAnimation();
        ivSpeed.setVisibility(GONE);
    }

    @Override
    public void onMove(int y, boolean isComplete, boolean automatic) {
    }

    @Override
    public void onRelease() {
        if (!mAnimDrawable.isRunning()){
            mAnimDrawable.start();
        }
    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onReset() {
        mAnimDrawable.stop();
        ivSpeed.clearAnimation();
        ivSpeed.setVisibility(GONE);
    }


}
