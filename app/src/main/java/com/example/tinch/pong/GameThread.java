package com.example.tinch.pong;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.widget.Toast;


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
                    //Toast toast = Toast.makeText(context,"gol de tu mama",Toast.LENGTH_SHORT);
                    //toast.show();
                }
            }
        }
    }

    public boolean OnTouchEvent(MotionEvent event)
    {
        estado.setGol(false);
        return true;
    }

    /*Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message message) {
            // This is where you do your work in the UI thread.
            // Your worker tells you in the message what to do.
        }
    };

    void workerThread() {
        // And this is how you call it from the worker thread:
        Message message = mHandler.obtainMessage();
        message.sendToTarget();
    }*/
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
