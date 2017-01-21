package com.simplemobiletools.draw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

public class MyBackground extends View {
    private Paint mPaint;
    private boolean mVerticalLinesEnabled = true;
    private boolean mHorizontalLinesEnabled = true;
    private int mGapSize = 1;

    public MyBackground(Context context, AttributeSet attrs) {
        super(context, attrs);

        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(0.5f);
        mPaint.setAntiAlias(true);
        mPaint.setAlpha(10);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if(mVerticalLinesEnabled || mHorizontalLinesEnabled) {
            canvas.drawLines(createGridPoints(getWidth(), getHeight()), mPaint);
        }
    }

    private float[] createGridPoints(final int width, final int height) {
        float gap = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_MM, mGapSize, getResources().getDisplayMetrics());
        int rows = mVerticalLinesEnabled ? (int) (width / gap) : 0;
        int cols = mHorizontalLinesEnabled ? (int) (height / gap) : 0;

        float[] lines = new float[(rows + cols) * 4];
        int pointIndex = 0;

        for (int vIndex = 1; vIndex < rows; vIndex++) {
            lines[pointIndex++] = width * vIndex / rows;
            lines[pointIndex++] = 0;
            lines[pointIndex++] = width * vIndex / rows;
            lines[pointIndex++] = height;
        }
        for (int hIndex = 1; hIndex < cols; hIndex++) {
            lines[pointIndex++] = 0;
            lines[pointIndex++] = height * hIndex / cols;
            lines[pointIndex++] = width;
            lines[pointIndex++] = height * hIndex / cols;
        }

        return lines;
    }

    private void setGridColor(int color) {
        mPaint.setColor(color);
        mPaint.setAlpha(100);
    }

    public void setGapSize(int gapSize) {
        mGapSize = gapSize;
    }

    public void setGridLines(boolean vLinesEnabled, boolean hLinesEnabled) {
        mVerticalLinesEnabled = vLinesEnabled;
        mHorizontalLinesEnabled = hLinesEnabled;
    }

    public void setBackgroundColor(int color, boolean isDarkBackground) {
        super.setBackgroundColor(color);
        setGridColor(isDarkBackground ? Color.WHITE : Color.BLACK);
    }
}
