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

public class GameState1vs1 extends GameState {

    Random random;
    //private boolean gol;
    //pantalla
    final int _screenWidth;
    final int _screenHeight;

    //pelota
    final int tamañoPelota;
    int pelotaX; 	int pelotaY;
    int velocidadPelotaX; 	int velocidadPelotaY;

    //bates
    final int bateLargo;	final int bateAncho;
    int bateSuperiorX;
    final int bateSuperiorY;
    int bateInferiorX;
    int bateInferiorY;
    final int velocidadBate;

    //contornos
    int contorno;
    int contornoDcha, contornoInferior;

    //puntaje
    int puntosBateSuperior, puntosBateInferior;

    public GameState1vs1(Display display)
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
        velocidadBate = 3;

        //area de juego
        contorno = 2; //ancho y largo de los contornos
        contornoDcha = 120;
        contornoInferior = 2;

        //puntaje
        puntosBateInferior = 0; puntosBateSuperior = 0;
    }

    public void update() {

        pelotaX += velocidadPelotaX;
        pelotaY += velocidadPelotaY;

        //gol para el bate superior
        if(pelotaY > _screenHeight)
        {
            pelotaX = (_screenWidth-contornoDcha)/2;
            pelotaY = _screenHeight/2;
            velocidadPelotaX = random.nextInt(5) + 7;
            velocidadPelotaY = random.nextInt(5) + 7;
            velocidadPelotaY *= -1;
            puntosBateSuperior +=1;
            gol = true;

        }
        //gol para el bate inferior
        if (pelotaY < 0)
        {
            pelotaX = (_screenWidth-contornoDcha)/2;
            pelotaY = _screenHeight/2;
            velocidadPelotaX = random.nextInt(5) + 7;
            velocidadPelotaY = random.nextInt(5) + 7;
            puntosBateInferior += 1;
            gol = true;
        }
        //colision con borde
        if(pelotaX > _screenWidth - contornoDcha - 30 || pelotaX < 10)
        {
            velocidadPelotaX *= -1;
        }
        //colision con bate superior
        if(pelotaX > bateSuperiorX && pelotaX < bateSuperiorX + bateLargo && pelotaY < bateSuperiorY + 20 && pelotaY > bateSuperiorY)
        {
            velocidadPelotaY *= -1;
        }
        //colision con bate inferior
        if(pelotaX > bateInferiorX && pelotaX < bateInferiorX+bateLargo && pelotaY > bateInferiorY - 23 && pelotaY > bateSuperiorY)
        {
            velocidadPelotaY *= -1;
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {

        for (int i = 0; i < e.getPointerCount(); i++) {
            //si presiona arriba de la mitad de la pantalla
            if (e.getY(i) < _screenHeight / 2)
            {

                if( e.getX(i) >= _screenWidth - 300)
                {
                    //si toca afuera del contorno limitamos el movimiento
                    bateSuperiorX = _screenWidth - 300;
                }
                else
                {
                    //movemos el bate hacia donde se toco la pantalla en el eje X
                    bateSuperiorX = (int) e.getX(i);
                }
            }
            // si toca abajo de la mitad de la pantalla
            else if (e.getX(i) >= _screenWidth - 300)
            {
                //si toca afuera del contorno limitamos el movimiento
                bateInferiorX = _screenWidth - 300;
            }
            else
            {
                //movemos el bate hacia donde se toco la pantalla en el eje X
                bateInferiorX = (int) e.getX(i);
            }
        }

        return true;
    }


    public void dibujar(Canvas canvas, Paint paint) {

        if(canvas != null){
            //limpiar pantalla
            canvas.drawRGB(20, 20, 20);

            //color
            paint.setARGB(255, 255, 255, 255);

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

}