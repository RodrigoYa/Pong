package com.example.tinch.pong;


import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.Display;
import android.view.MotionEvent;
import java.util.Random;

/**
 * Created by tinch on 7/10/2016.
 */

public class GameStateVsPC extends GameState {

    Random random;

    //pantalla
    private final int _screenWidth;
    private final int _screenHeight;

    //pelota
    private final int tamañoPelota;
    private int pelotaX; private int pelotaY;
    private float velocidadPelotaX; 	private float velocidadPelotaY;

    //bates
    private final int bateLargo;	private final int bateAncho;
    private int bateSuperiorX;
    private final int bateSuperiorY;
    private int bateInferiorX;
    private int bateInferiorY;
    private int velocidadBate;

    //contornos
    private int contorno;
    private int contornoDcha, contornoInferior;

    //puntaje
    private int puntosBateSuperior, puntosBateInferior;


    GameStateVsPC(Display display, String dificultad)
    {
        random = new Random();
        gol = false;

        //resolucion pantalla
        _screenWidth = display.getWidth();
        _screenHeight = display.getHeight();

        //la bola
        pelotaX = (_screenWidth-contornoDcha)/2;  pelotaY = _screenHeight/2;
        tamañoPelota = 15;
        velocidadPelotaX = random.nextInt(5) + 7; velocidadPelotaY = random.nextInt(5) + 7;

        //Los palos
        bateLargo = 150;bateAncho = 10;
        bateSuperiorX = (_screenWidth-contornoDcha)/2 - bateLargo;
        bateSuperiorY = 20;
        bateInferiorX = (_screenWidth-contornoDcha)/2 -bateLargo;
        bateInferiorY = _screenHeight - 30;


        //area de juego
        contorno = 2; //ancho y largo de los contornos
        contornoDcha = 120;
        contornoInferior = 2;

        //puntaje
        puntosBateInferior = 0; puntosBateSuperior = 0;

        //dificultad
        switch(dificultad)
        {
            case "Facil":
                velocidadBate = 5;
                break;
            case "Normal":
                velocidadBate = 7;
                break;
            case "Dificil":
                velocidadBate = 9;
                break;
        }
    }

    public void update() {

        pelotaX += velocidadPelotaX;
        pelotaY += velocidadPelotaY;

        if(pelotaY > _screenHeight)// gol
        {
            pelotaX = (_screenWidth-contornoDcha)/2;
            pelotaY = _screenHeight/2;
            velocidadPelotaX = random.nextInt(5) + 7;
            velocidadPelotaY = random.nextInt(5) + 7;
            velocidadPelotaY *= -1;
            puntosBateSuperior +=1;
            if(puntosBateSuperior == 10)
            {
                //poner cartel de victoria
            }
            gol = true;

        }
        if (pelotaY < 0)
        {
            pelotaX = (_screenWidth-contornoDcha)/2;
            pelotaY = _screenHeight/2;
            velocidadPelotaX = random.nextInt(5) + 7;
            velocidadPelotaY = random.nextInt(5) + 7;
            puntosBateInferior += 1;
            if(puntosBateInferior == 10)
            {
                //poner cartel de victoria
            }
            gol = true;
        }

        if(pelotaX > _screenWidth - contornoDcha - 30 || pelotaX < 10)//colision con borde
        {
            velocidadPelotaX *= -1;
        }

        if(pelotaX > bateSuperiorX && pelotaX < bateSuperiorX + bateLargo && pelotaY < bateSuperiorY + 20 && pelotaY > bateSuperiorY)   //Colision con bate superior
        {
            velocidadPelotaY *= -1;
        }

        if(pelotaX > bateInferiorX && pelotaX < bateInferiorX+bateLargo && pelotaY > bateInferiorY - 23 && pelotaY > bateSuperiorY) //colision con bate inferior
        {
            velocidadPelotaY *= -1;
        }
        BateSuperiorIA();
    }

    public void BateSuperiorIA()
    {
        if (pelotaY <= _screenHeight / 2)
        {
            if(pelotaX < bateSuperiorX + bateLargo /2 && bateSuperiorX >= 10)
            {
                bateSuperiorX -= velocidadBate;
            }
            else if(pelotaX > bateSuperiorX + bateLargo/2 && bateSuperiorX <= _screenWidth - 300)
            {
                bateSuperiorX += velocidadBate;
            }
        }
    }
    public boolean onTouchEvent(MotionEvent e) {

        for (int i = 0; i < e.getPointerCount(); i++) {

            if (e.getX(i) >= _screenWidth - 300)
            {
                bateInferiorX = _screenWidth - 300;
            }
            else
            {
                bateInferiorX = (int) e.getX(i); // si toca abajo de la mitad
            }
        }

        return true;
    }


    public void dibujar(Canvas canvas, Paint paint) {

        if(canvas != null){
            //limpiar pantalla
            canvas.drawRGB(20, 20, 20);

            //color
            paint.setARGB(255,255,255,255);

            //dibujar pelota

            canvas.drawCircle(pelotaX,pelotaY,tamañoPelota,paint);
            //dibujar bates
            canvas.drawRect(new Rect(bateSuperiorX, bateSuperiorY, bateSuperiorX + bateLargo,
                    bateSuperiorY + bateAncho), paint); //bate superior
            canvas.drawRect(new Rect(bateInferiorX, bateInferiorY, bateInferiorX + bateLargo,
                    bateInferiorY + bateAncho), paint); //bate inferior

            //dibujar contornos
            canvas.drawRect(new Rect(0,0,_screenWidth - contornoDcha,contorno),paint); // superior
            canvas.drawRect(new Rect(0,0,contorno,_screenHeight - contornoInferior),paint); //izquierda
            canvas.drawRect(new Rect(0,_screenHeight - contornoInferior,_screenWidth - contornoDcha,_screenHeight - contornoInferior + contorno),paint); //inferior
            canvas.drawRect(new Rect(_screenWidth - contornoDcha,0,_screenWidth - contornoDcha + contorno,_screenHeight - contornoInferior),paint); //derecha

            //marcador
            paint.setTextSize(30);
            canvas.drawText(String.valueOf(puntosBateSuperior),_screenWidth - 60,_screenHeight/4,paint);
            canvas.drawText(String.valueOf(puntosBateInferior), _screenWidth - 60, _screenHeight*3/4 , paint);}

    }

    public boolean getGol(){return this.gol;}
    public void setGol(boolean gol){this.gol = gol;}
}