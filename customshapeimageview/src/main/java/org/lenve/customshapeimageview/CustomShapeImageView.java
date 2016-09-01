package org.lenve.customshapeimageview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by 王松 on 2016/8/31.
 */
public class CustomShapeImageView extends ImageView {
    private static final int TYPE_CIRCLE = 0;
    private static final int TYPE_RHOMBUS = 1;
    private static final int TYPE_ROUNRECTANGLE = 2;
    private static final int TYPE_OVAL = 3;
    private static final int TYPE_HEXAGON = 4;
    /**
     * ImageView形状
     */
    private int shape;
    private Paint paint;
    /**
     * 圆角矩形圆角的大小
     */
    private int corner;
    /**
     * 旋转角度
     */
    private int rotate;

    public CustomShapeImageView(Context context) {
        this(context, null);
    }

    public CustomShapeImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomShapeImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CustomShapeImageView);
        shape = ta.getInt(R.styleable.CustomShapeImageView_shape, TYPE_CIRCLE);
        corner = ta.getDimensionPixelSize(R.styleable.CustomShapeImageView_corner, 5);
        rotate = ta.getInt(R.styleable.CustomShapeImageView_rotate, 0);
        ta.recycle();
        paint = new Paint();
        paint.setAntiAlias(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        if (shape == TYPE_CIRCLE || shape == TYPE_HEXAGON) {
            int minSize = Math.min(widthSize, heightSize);
            setMeasuredDimension(minSize, minSize);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Drawable drawable = getDrawable();
        if (drawable == null) {
            return;
        }
        paint.reset();
        Bitmap srcBitmap = ((BitmapDrawable) drawable).getBitmap();
        int width = srcBitmap.getWidth();
        int height = srcBitmap.getHeight();
        int measuredHeight = getMeasuredHeight();
        int measuredWidth = getMeasuredWidth();
        float scale = Math.max(measuredWidth * 1f / width, measuredHeight * 1f / height);
        Matrix matrix = new Matrix();
        matrix.postScale(scale, scale);
        Bitmap bitmap = Bitmap.createBitmap(srcBitmap, 0, 0, width, height, matrix, true);
        Bitmap blankBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas mCanvas = new Canvas(blankBitmap);
        int radius = measuredWidth / 2;
        mCanvas.save();
        mCanvas.rotate(rotate, radius, radius);
        switch (shape) {
            case TYPE_CIRCLE: {
                mCanvas.drawCircle(radius, radius, radius, paint);
            }
            break;
            case TYPE_HEXAGON: {
                Path path = new Path();
                path.moveTo(radius, 0);
                path.lineTo(radius + (float) Math.atan(60) * (radius / 2), radius / 2);
                path.lineTo(radius + (float) Math.atan(60) * (radius / 2), radius * 1.5f);
                path.lineTo(radius, radius * 2);
                path.lineTo(radius - (float) Math.atan(60) * (radius / 2), radius * 1.5f);
                path.lineTo(radius - (float) Math.atan(60) * (radius / 2), radius * 0.5f);
                path.close();
                mCanvas.drawPath(path, paint);
            }
            break;
            case TYPE_OVAL: {
                RectF oval = new RectF(0, 0, measuredWidth, measuredHeight);
                mCanvas.drawOval(oval, paint);
            }
            break;
            case TYPE_RHOMBUS: {
                Path path = new Path();
                path.moveTo(radius, 0);
                path.lineTo(radius * 2, radius);
                path.lineTo(radius, radius * 2);
                path.lineTo(0, radius);
                path.close();
                mCanvas.drawPath(path, paint);
            }
            break;
            case TYPE_ROUNRECTANGLE: {
                if (Build.VERSION.SDK_INT > 20) {
                    mCanvas.drawRoundRect(0, 0, measuredWidth, measuredHeight, corner, corner, paint);
                } else {
                    mCanvas.drawRoundRect(new RectF(0, 0, measuredWidth, measuredHeight), corner, corner, paint);
                }
            }
            break;
        }
        mCanvas.restore();
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        mCanvas.drawBitmap(bitmap, 0, 0, paint);
        canvas.drawBitmap(blankBitmap, 0, 0, null);
        if (bitmap != null && !bitmap.isRecycled()) {
            bitmap.recycle();
        }
    }
}
