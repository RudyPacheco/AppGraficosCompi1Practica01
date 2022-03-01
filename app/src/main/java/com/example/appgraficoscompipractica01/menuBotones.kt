package com.example.appgraficoscompipractica01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.objetos.countOperacion
import com.objetos.grafico

class menuBotones : AppCompatActivity() {

    var botonGraficas:Button?=null;
    var botonReporteOperadores:Button?=null;
    var botonReporteGraficos:Button?=null;
    var graficas:ArrayList<grafico> = arrayListOf();
    var listGraficasEjecutar: ArrayList<String> = arrayListOf()
    var graficasSeleccionadas : ArrayList<grafico> = arrayListOf()
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
        graficas = bun?.get("graficas") as ArrayList<grafico>;
        listGraficasEjecutar = bun?.get("listaEjecucion") as ArrayList<String>


    }

    fun verGraficas(v: View?){
        ordenarGraficas();
        if(listGraficasEjecutar.isEmpty()){
            Toast.makeText(this,"No se a ejecutado ningun grafico", Toast.LENGTH_LONG).show();
        }else {
            var lanzar = Intent(this, generarGraficas::class.java);
            lanzar.putExtra("graficas", graficasSeleccionadas);
            startActivity(lanzar);
        }
    }

    fun reporteOperadores(v: View?){
        if(operaciones!!.isEmpty()){
            Toast.makeText(this,"No se a ingresado ninguna operacion",Toast.LENGTH_LONG).show();
        }else{
            var lanzar = Intent(this,reporteOperadores::class.java);
            lanzar.putExtra("countOperaciones", operaciones);
            startActivity(lanzar);
        }

    }

    fun reporteGraficas(v: View?){
        if (cantidadPie==0 && cantidadPie ==0){
            Toast.makeText(this,"No se a definido ninguna grafica",Toast.LENGTH_LONG).show();
        }else {
            var lanzar = Intent(this, reporteGraficas::class.java);
            lanzar.putExtra("totalBarras", cantidadBarras);
            lanzar.putExtra("totalPie", cantidadPie);
            startActivity(lanzar);
        }
    }

    fun ordenarGraficas(){
        var listTitulos: ArrayList<String> = arrayListOf()
        graficas.forEach {
            if (listGraficasEjecutar.contains(it.titulo) && listTitulos.isEmpty() ){
                graficasSeleccionadas.add(it)
                listTitulos.add((it.titulo.toString()));
            }else if(listGraficasEjecutar.contains(it.titulo) && !listTitulos.contains(it.titulo) ){
                graficasSeleccionadas.add(it);
                listTitulos.add(it.titulo.toString());
            }

        }
    }


}