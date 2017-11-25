package com.wanjian.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.example.wanjian.proportionview.R;

/**
 * Created by wanjian on 2017/11/24.
 */


public class ProportionView extends ViewGroup {
    public ProportionView(Context context) {
        this(context, null);
    }

    public ProportionView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProportionView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.ProportionView);
        proportion = array.getFloat(R.styleable.ProportionView_proportion, 1);
        array.recycle();
    }


    private float proportion = 1;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (getChildCount() > 1) {
            throw new IllegalArgumentException("child count must less than 2");
        }
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int paddingHorizontal = getPaddingLeft() + getPaddingRight();
        int paddingVertical = getPaddingTop() + getPaddingBottom();
        //宽确定，则最终宽确定
        if (widthMode == MeasureSpec.EXACTLY) {
            int width = MeasureSpec.getSize(widthMeasureSpec);
            int height = (int) (width * proportion);
            View child = getChildAt(0);
            if (child != null) {
                LayoutParams params = child.getLayoutParams();
                int marginHorizontal = 0, marginVertical = 0;
                if (params instanceof MarginLayoutParams) {
                    MarginLayoutParams marginParams = ((MarginLayoutParams) params);
                    marginHorizontal = marginParams.leftMargin + marginParams.rightMargin;
                    marginVertical = marginParams.topMargin + marginParams.bottomMargin;
                }
                int childWidthMS = getChildExactlyMeasureSpec(Math.max(0, width - marginHorizontal - paddingHorizontal), params.width);
                int childHeightMS = getChildExactlyMeasureSpec(Math.max(0, height - marginVertical - paddingVertical), params.height);
                child.measure(childWidthMS, childHeightMS);
            }
            setMeasuredDimension(width, height);

        } else if (widthMode == MeasureSpec.AT_MOST) {//宽不确定，先measure child，自身宽度==child的测量宽度，此时自身宽度确定，再重新measure child高度
            int width = MeasureSpec.getSize(widthMeasureSpec);

            View child = getChildAt(0);
            if (child == null) {
                width = Math.max(0, getPaddingLeft() + getPaddingRight());
                int height = (int) (width * proportion);
                setMeasuredDimension(width, height);
                return;
            }
            LayoutParams params = child.getLayoutParams();
            int marginHorizontal = 0, marginVertical = 0;
            if (params instanceof MarginLayoutParams) {
                MarginLayoutParams marginParams = ((MarginLayoutParams) params);
                marginHorizontal = marginParams.leftMargin + marginParams.rightMargin;
                marginVertical = marginParams.topMargin + marginParams.bottomMargin;
            }
            int childWidthMS;
            if (params.width == LayoutParams.MATCH_PARENT || params.width == LayoutParams.WRAP_CONTENT) {
                childWidthMS = MeasureSpec.makeMeasureSpec(Math.max(0, width - marginHorizontal - paddingHorizontal), MeasureSpec.AT_MOST);
            } else {
                childWidthMS = MeasureSpec.makeMeasureSpec(params.width, MeasureSpec.EXACTLY);
            }

            int childHeightMS = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);

            child.measure(childWidthMS, childHeightMS);

            width = Math.min(Math.max(0, child.getMeasuredWidth() + marginHorizontal + paddingHorizontal), width);

            int height = (int) (width * proportion);

            setMeasuredDimension(width, height);

            childWidthMS = getChildExactlyMeasureSpec(Math.max(0, width - marginHorizontal - paddingHorizontal), params.width);
            childHeightMS = getChildExactlyMeasureSpec(Math.max(0, height - marginVertical - paddingVertical), params.height);

            child.measure(childWidthMS, childHeightMS);

        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return super.generateDefaultLayoutParams();
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }


    private int getChildExactlyMeasureSpec(int size, int params) {
        int childMS;
        if (params == LayoutParams.MATCH_PARENT) {
            childMS = MeasureSpec.makeMeasureSpec(size, MeasureSpec.EXACTLY);
        } else if (params == LayoutParams.WRAP_CONTENT) {
            childMS = MeasureSpec.makeMeasureSpec(size, MeasureSpec.AT_MOST);
        } else {
            childMS = MeasureSpec.makeMeasureSpec(params, MeasureSpec.EXACTLY);
        }
        return childMS;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        View child = getChildAt(0);
        if (child == null) {
            return;
        }
        int pl = getPaddingLeft();
        int pt = getPaddingTop();
        LayoutParams params = child.getLayoutParams();
        int ml = 0;
        int mt = 0;
        if (params instanceof MarginLayoutParams) {
            MarginLayoutParams marginLayoutParams = ((MarginLayoutParams) params);
            ml = marginLayoutParams.leftMargin;
            mt = marginLayoutParams.topMargin;
        }
        child.layout(pl + ml, pt + mt, child.getMeasuredWidth() + pl + ml, child.getMeasuredHeight() + pt + mt);
    }

}
