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

public class DogLoadMoreFooterView extends SwipeLoadMoreFooterLayout {


    private ImageView ivRefresh;

    private AnimationDrawable mAnimDrawable;

    private Animation mTwinkleAnim;

    private int mTriggerOffset;

    public DogLoadMoreFooterView(Context context) {
        this(context, null);
    }

    public DogLoadMoreFooterView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DogLoadMoreFooterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mTriggerOffset = context.getResources().getDimensionPixelOffset(R.dimen.load_more_footer_height_classic);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ivRefresh = (ImageView) findViewById(R.id.ivRefresh);
        mAnimDrawable = (AnimationDrawable) ivRefresh.getBackground();
        mTwinkleAnim = AnimationUtils.loadAnimation(getContext(), R.anim.twinkle);
    }

    @Override
    public void onLoadMore() {
        if (!mAnimDrawable.isRunning()){
            mAnimDrawable.start();
        }
    }

    @Override
    public void onPrepare() {
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
    }


}
