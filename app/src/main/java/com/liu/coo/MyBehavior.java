package com.liu.coo;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;

/**
 * PackageName:  com.liu.coo
 * Description: CoordinatorLayout协调的规则
 * date:   2016/11/3 14:41
 */
public class MyBehavior extends CoordinatorLayout.Behavior<Button> {

    private final int widthPixels;

    public MyBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        widthPixels = displayMetrics.widthPixels;
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, Button child, View dependency) {
        return dependency instanceof MyView;
    }

    //每次dependency位置发生变化，都会执行onDependentViewChanged方法
    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, Button child, View dependency) {
        int top = dependency.getTop();
        int left = dependency.getLeft();

        int x = widthPixels - left - child.getWidth()-100;
        int y = top;

        setPosition(child, x, y);
        return true;
    }

    private void setPosition(View child, int x, int y) {
        CoordinatorLayout.MarginLayoutParams layoutParams = (CoordinatorLayout.MarginLayoutParams) child.getLayoutParams();
        layoutParams.leftMargin = x;
        layoutParams.topMargin = y;
        child.setLayoutParams(layoutParams);
    }
}
