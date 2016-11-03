package com.liu.coo;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Scroller;

/**
 * PackageName:  com.liu.coo
 * Description: 随手指移动的view
 * date:   2016/11/3 14:49
 */
public class MyView extends View {

    private Scroller mScroller;
    private int mLastX;
    private int mLastY;

    public MyView(Context context) {
        this(context,null);
    }

    public MyView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MyView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        mScroller = new Scroller(context);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mLastX = x;
                mLastY = y;
            break;
            case MotionEvent.ACTION_MOVE:
                //int offsetX = x - mLastX;
                //int offsetY = y - mLastY;
                //此时，计算坐标是相反的
                int offsetX = mLastX - x;
                int offsetY = mLastY - y;
                //让View所在的ViewGroup进行移动
                ((View)getParent()).scrollBy(offsetX,offsetY);
//                moveViewWithFinger(this, offsetX, offsetY);
            break;
            case MotionEvent.ACTION_UP:
                View viewGroup = (View) getParent();
                mScroller.startScroll(viewGroup.getScrollX(),viewGroup.getScrollY(),-viewGroup.getScrollX(),-viewGroup.getScrollY());
                //记住需要invalidate
                invalidate();
            break;
        }
        return true;
    }

    private void moveViewWithFinger(MyView myView, int rawX, int rawY) {
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) myView
                .getLayoutParams();
        params.leftMargin = getLeft()+rawX;
        params.topMargin = getTop()+rawY;
        myView.setLayoutParams(params);
    }


    @Override
    public void computeScroll() {
        super.computeScroll();
        if(mScroller.computeScrollOffset()){
            ((View)getParent()).scrollTo(mScroller.getCurrX(),mScroller.getCurrY());
            //记住，需要不断调用invalidate进行重绘
            invalidate();
        }
    }
}
