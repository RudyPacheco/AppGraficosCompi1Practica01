package com.example.appgraficoscompipractica01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.objetos.countOperacion

class menuBotones : AppCompatActivity() {

    var botonGraficas:Button?=null;
    var botonReporteOperadores:Button?=null;
    var botonReporteGraficos:Button?=null;
    var operaciones:ArrayList<countOperacion>?=null;
    var cantidadBarras:Int=0;
    var cantidadPie:Int=0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_botones)

        botonGraficas = findViewById(R.id.buttonGraficos);
        botonReporteOperadores = findViewById(R.id.buttonReporteOperadores);
        botonReporteGraficos = findViewById(R.id.buttonReporteGraficos);
        val bun = intent.extras;

        operaciones = bun?.get("countOperaciones") as ArrayList<countOperacion>;
        cantidadBarras = bun?.get("totalBarras") as Int
        cantidadPie = bun?.get("totalPie") as Int

       // botonReporteOperadores.setOnClickListener(this)
    }

    fun verGraficas(v: View?){

    }

    fun reporteOperadores(v: View?){
        var lanzar = Intent(this,reporteOperadores::class.java);
        lanzar.putExtra("countOperaciones", operaciones);
        startActivity(lanzar);
    }

    fun reporteGraficas(v: View?){
        var lanzar = Intent(this,reporteGraficas::class.java);
        lanzar.putExtra("totalBarras",cantidadBarras);
        lanzar.putExtra("totalPie",cantidadPie);
        startActivity(lanzar);

    }


}