package com.example.appgraficoscompipractica01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.objetos.countOperacion

class reporteGraficas : AppCompatActivity() {

    var cantidadBarras:Int=0;
    var cantidadPie:Int=0;
    var textPie:TextView?=null;
    var textBarras:TextView?=null;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reporte_graficas)

        textBarras=findViewById(R.id.textView10) as TextView ;
        textPie=findViewById(R.id.textView11) as TextView;


        val bun = intent.extras;

        cantidadBarras = bun?.get("totalBarras") as Int
        cantidadPie = bun?.get("totalPie") as Int

        textBarras!!.setText(cantidadBarras.toString());
        textPie!!.setText(cantidadPie.toString());

    }
}