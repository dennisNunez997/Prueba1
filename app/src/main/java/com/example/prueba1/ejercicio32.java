package com.example.prueba1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class ejercicio32 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicio32);
        // Llamamos a nuestro layout
        RelativeLayout layout1 = findViewById(R.id.layout1);
        // Llamamos a la clase Lienzo
        Lienzo fondo = new Lienzo(this);
        // Con la funcion addView, crearemos la vista de los rectangulos
        layout1.addView(fondo);

    }

    // Creacion de la clase Lienxo
    class Lienzo extends View {
        // Creamos un constructor con parametro
        public Lienzo(Context context) {
            super(context);
        }
        // la funcion Ondraw permite dibujar los rectangulos
        // Canvas definir area con cooredenadas relativas
        protected void onDraw(Canvas canvas){
            //definimos fondo blanco
            canvas.drawRGB(255,255,255);
            //obtenemos el ancho del dispositivo
            int ancho = getWidth();
            //creamos un objeto de la clase Paint
            Paint pincel1 = new Paint();
            //Definimos el color (ROJO)
            pincel1.setARGB(255,255,0,0);

            // Todas las posiciones son relativas al ultimo rectangulo
            //Dibujamos rectangulo (x1,y1,x2-10,y2,color)
            canvas.drawRect(10,10,ancho-10,40,pincel1);

            //Dibujamos rectangulo (solo el margen) (x1,y1,x2-10,y2,color)
            pincel1.setStyle(Paint.Style.STROKE);
            canvas.drawRect(10,60,ancho-10,90,pincel1);

            //Dibujamos rectangulo (con mayor grosor) (x1,y1,x2-10,y2,color)
            pincel1.setStrokeWidth(3);
            canvas.drawRect(10,110,ancho-10,140,pincel1);
        }
    }
}