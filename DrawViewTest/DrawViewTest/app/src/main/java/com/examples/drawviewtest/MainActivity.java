// Siham Hoque
package com.examples.drawviewtest;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                             WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(new MyView(this));
    }

    public class MyView extends View {
        int x, y;
        Bitmap bmp;
        boolean initialized;
        public MyView(Context context) {
            super(context);
            x = 0;
            y = 0;
            initialized = false;

        }

        private void LoadGraphics (Canvas canvas) {
            if (initialized == false) {
                bmp = BitmapFactory.decodeResource (getResources(), R.drawable.wallpaper);
                Bitmap resizedBitmap = Bitmap.createScaledBitmap(bmp,canvas.getWidth(), canvas.getHeight(),false);
                bmp = resizedBitmap;
                initialized = true;
            }

        }
        @Override
        protected void onDraw (Canvas canvas) {
            LoadGraphics(canvas);
            x = (x + 1) % canvas.getWidth();
            y = (y + 1) % canvas.getHeight();
            Paint paint = new Paint();
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(Color.WHITE);
            canvas.drawPaint(paint);

            long currTime = System.currentTimeMillis() / 100 % 10;
                canvas.drawBitmap (bmp, canvas.getWidth()/2-bmp.getWidth()/2, canvas.getHeight()/2-bmp.getHeight()/2, null);

            // Use Color.parseColor to define HTML colors
            paint.setColor(Color.RED);
            if(currTime <= 4)
            canvas.drawRect(getLeft()+(getRight()-getLeft())/3,
                    getTop()+(getBottom()-getTop())/3,
                    getRight()-(getRight()-getLeft())/3,
                    getBottom()-(getBottom()-getTop())/3,paint);
            invalidate();
        }

        public boolean onTouchEvent (MotionEvent event) {
            int action = event.getAction();
            if (action == MotionEvent.ACTION_UP) {
                finish();
            }
            return true;
        }
    }
}
