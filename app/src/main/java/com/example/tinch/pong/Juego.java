package com.example.tinch.pong;

import android.content.Context;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;

public class Juego extends SurfaceView implements SurfaceHolder.Callback
{
    public GameThread thread;

    public Juego(Context context, boolean modo, String dificultad) {
        super(context);
        Display display = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();

        //para escuchar los eventos
        SurfaceHolder surface = getHolder();
        surface.addCallback(this);
        setFocusable(true);

        //instanciamos thread
        thread = new GameThread(surface, display, modo, dificultad);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e)
    {
        //si se hace un gol se ejecuta el touchEvent del thread
        if (thread.getEstado().getGol())
        {
            return thread.OnTouchEvent(e);
        }
        //sino el del estado
        return thread.getGameState().onTouchEvent(e);

    }
    //se implementa del SurfaceHolder.Callback interface
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {}

    //se implementa del SurfaceHolder.Callback interface
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread.start();
    }

    //se implementa del SurfaceHolder.Callback interface
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        try {
            thread.stop();
        }
        catch (Exception e){e.printStackTrace();}

    }
}