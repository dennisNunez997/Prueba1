package com.example.prueba1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;


// Implementamos el metodo onTouch en la clase principal
public class ejercicio39 extends AppCompatActivity implements View.OnTouchListener {
    //Declaramos variables globales
    private int corx, cory;
    private Lienzo fondo;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicio39);

        //seteamos los parametros iniciales
        corx = 100;
        cory = 100;
        RelativeLayout layout1 =  findViewById(R.id.layout1);
        //inicializamos el lienzo
        fondo = new Lienzo(this);
        //la propia clase manejara el evento onTouch
        fondo.setOnTouchListener(this);
        //se reemplazara los valores del evento
        layout1.addView(fondo);
    }

    //Obtenemos las nuevas coordenadas de pulsacion
    @SuppressLint("ClickableViewAccessibility")
    public boolean onTouch(View v, MotionEvent event){
        corx = (int) event.getX();
        cory = (int) event.getY();
        //Volvemos a pintar el circulo en el lienzo (sobreescribir)
        fondo.invalidate();
        return true;
    }
    //El método onDraw pinta el fondo de amarillo, crea un objeto de la clase Paint y procede a dibujar
    // un círculo en las coordenadas indicadas por los atributos corx y cory
    class Lienzo extends View{

        public Lienzo(Context context) {
            super(context);
        }

        protected void onDraw(Canvas canvas){
            canvas.drawRGB(255,255,0);
            @SuppressLint("DrawAllocation") Paint pincel1 = new Paint();
            pincel1.setARGB(255,255,0,0);
            pincel1.setStrokeWidth(4);
            pincel1.setStyle(Paint.Style.STROKE);
            canvas.drawCircle(corx, cory,20, pincel1);
        }
    }
}
