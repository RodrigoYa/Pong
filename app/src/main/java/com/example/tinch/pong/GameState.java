package com.example.tinch.pong;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;


/**
 * Created by tinch on 7/10/2016.
 */

public abstract class GameState {

    public boolean gol;

    public abstract void update();

    public abstract boolean onTouchEvent(MotionEvent e);

    public abstract void dibujar(Canvas canvas, Paint paint);

    public boolean getGol() {
        return gol;
    }

    public void setGol(boolean gol) {
        this.gol = gol;
    }
}