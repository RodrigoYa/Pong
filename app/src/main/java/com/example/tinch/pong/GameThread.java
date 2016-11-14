package com.example.tinch.pong;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;


/**
 * Created by tinch on 7/10/2016.
 */

public class GameThread extends Thread {


    private SurfaceHolder surface;
    private Paint paint;
    private GameState estado;
    private Context context;
    private int segundos;

    public GameThread(SurfaceHolder surfaceHolder, Context context, Display display, boolean modo, String dificultad)
    {
        surface = surfaceHolder;
        paint = new Paint();
        //si modo es igual a 1 vs 1
        if(modo)
        {
            estado = new GameState1vs1(display);
        }
        //si modo es igual a 1 vs PC
        else
        {
            estado = new GameStateVsPC(display, dificultad);
        }
        this.context = context;
        segundos = 2000;
    }

    @Override
    public void run() {
        while(true)
        {
            Canvas canvas = surface.lockCanvas();
            estado.update();
            //si canvas esta incializado dibujamos.
            if(canvas != null) {
                //dibujamos la pantalla
                estado.dibujar(canvas, paint);
                surface.unlockCanvasAndPost(canvas);

                // se espera hasta que se toque la pantalla
                while (estado.getGol()) {

                }
            }
        }
    }

    public boolean OnTouchEvent(MotionEvent event)
    {
        estado.setGol(false);
        return true;
    }

    public GameState getGameState()
    {
        return estado;
    }

    public SurfaceHolder getSurface() {
        return surface;
    }

    public void setSurface(SurfaceHolder surface) {
        this.surface = surface;
    }

    public Paint getPaint() {
        return paint;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }

    public GameState getEstado() {
        return estado;
    }

    public void setEstado(GameState estado) {
        this.estado = estado;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public int getSegundos() {
        return segundos;
    }

    public void setSegundos(int segundos) {
        this.segundos = segundos;
    }
}
